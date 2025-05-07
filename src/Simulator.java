import listeners.ScanListener;
import listeners.StateListener;
import models.SimulatorState;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Simulator {

    String filepath;
    int port;
    boolean continueProcessing;
    ServerSocket serverSocket;
    Thread writerThread;

    String currentMessage;
    List<ScanListener> scanListeners;
    List<StateListener> stateListeners;

    // File stuff
    File inputFile;
    static List<String> fastCounts = new ArrayList<>() {
        {
            add("GS");
            add("NS");
            add("GA");
            add("NA");
            add("SP");
            add("GX");
        }
    };

    public Simulator(int port, String filepath, boolean quickRead) throws IOException {
        this.port = port;
        this.filepath = filepath;

        writerThread = new Thread(
        () -> {
            try {
                System.out.println("Waiting for client connection...");
                setState(SimulatorState.WAITING);

                Socket socket = serverSocket.accept();

                System.out.println("Client connected");
                setState(SimulatorState.CONNECTED);

                List<String> lines = Files.readAllLines(Paths.get(filepath), StandardCharsets.UTF_8);

                for (String line : lines) {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    if(line.length() > 30) {
                        // Get rid of date at end of line
                        currentMessage = line.substring(0, line.length()-13) + "\r\n";
                    } else {
                        currentMessage = line + "\r\n";
                    }

                    String messageType = currentMessage.substring(0, 2);
                    if(quickRead) {
                        Thread.sleep(100);
                    } else {
                        if(fastCounts.contains(messageType)) {
                            Thread.sleep(200);
                        } else {
                            Thread.sleep(1000);
                        }
                    }

                    System.out.print(currentMessage);
                    out.print(currentMessage);
                    out.flush();

                    for(ScanListener listener : scanListeners) {
                        listener.onNewScan(currentMessage);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                setState(SimulatorState.ENDED);
            }
        });
    }

    public void init() throws IOException {
        serverSocket = new ServerSocket(port, 50, InetAddress.getByName("0.0.0.0"));

        System.out.println("Server started");

        // Start server
        continueProcessing = true;

        // Get file from filepath
        inputFile = new File(filepath);
        if(!inputFile.exists()) {
            setState(SimulatorState.ERROR);
        }

        scanListeners = new ArrayList<>();
        stateListeners = new ArrayList<>();
    }

    public void start() {
        this.writerThread.start();
    }

    public boolean isRunning() {
        return !serverSocket.isClosed();
    }

    public void stop() {
        synchronized (this) {
            continueProcessing = false;
            try {
                serverSocket.close();
                System.out.println("Socket closed");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addScanListener(ScanListener listener) {
        scanListeners.add(listener);
    }

    public void addStateListener(StateListener listener) {
        stateListeners.add(listener);
    }

    private void setState(SimulatorState state) {
        for(StateListener listener : stateListeners) {
            listener.onStateChange(state);
        }
    }

}
