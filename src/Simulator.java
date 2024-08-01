import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Simulator {

    boolean continueProcessing;
    ServerSocket serverSocket;
    Socket socket;
    OutputStream outputStream;
    PrintWriter printWriter;

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

    public Simulator(int port, String filepath) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started...");

        socket = new Socket("localhost", port);
        System.out.println("Client connected...");

        // Start server
        continueProcessing = true;

        // Get file from filepath
        inputFile = new File(filepath);
        if(!inputFile.exists()) {
            throw new IOException("File does not exist!");
        }

        Thread writerThread = new Thread(
        () -> {
            try {
                outputStream = socket.getOutputStream();
                printWriter = new PrintWriter(outputStream);

                BufferedReader bufferedReader;
                bufferedReader = new BufferedReader(new FileReader(inputFile));

                String msgLine = bufferedReader.readLine();
                while(msgLine != null) {
                    String timeRemoved = msgLine.substring(0, msgLine.length()-13);

                    String messageType = timeRemoved.substring(0, 2);
                    if(fastCounts.contains(messageType)) {
                        Thread.sleep(200);
                    } else {
                        Thread.sleep(2500);
                    }
                    printWriter.println(timeRemoved);

                    msgLine = bufferedReader.readLine();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        writerThread.start();
    }
    
    public void stop() {
        synchronized (this) {
            continueProcessing = false;
        }
    }

}
