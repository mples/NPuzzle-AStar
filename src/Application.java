import java.io.IOException;

public class Application {
    public static void main(String args[]){
        Node n;
        try{
            n = new Node("/home/mateusz/Downloads/N-Puzzle-Solver/src/puzzle.txt");
            System.out.println(n.toString());


        }
        catch (IOException e){
            e.printStackTrace();
        }
        Node n2 = new Node(3);
        System.out.println(n2.toString());

        AStar as = new AStar("/home/mateusz/Downloads/N-Puzzle-Solver/src/puzzle.txt");
        as.solve();
    }

}
