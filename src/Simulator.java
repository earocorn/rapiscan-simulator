public class Simulator {

    private static Simulator instance;

    public static Simulator getInstance(){
        if (instance == null){
            instance = new Simulator();
        }
        return instance;
    }


}
