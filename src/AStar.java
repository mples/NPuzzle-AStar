import java.io.IOException;
import java.util.*;

public class AStar {
    Node root;
    Node termNode;
    List<Node> openList;
    List<Direction> movesList;

    private long visitedStates = 0;
    private int searchDepth = 0;

    /** Given time in nanoseconds **/
    private long time;

    AStar(String fname, long time){
        try {
            root = new Node(fname);
        }
        catch( IOException e){
            e.printStackTrace();
        }
        openList = new LinkedList<Node>();
        movesList = new ArrayList<>();
        termNode = new Node(root.size);

        this.time = time;
    }

    public void solve(){
        openList.add(root);
        long start = System.nanoTime();
        //long stop = start + time;

        while( !openList.isEmpty()){
            ++visitedStates;

            Node currNode = null;

            int minF = Integer.MAX_VALUE;
            for( Node n : openList){
                if(n.f <= minF){
                    minF = n.f;
                    currNode = n;
                }
            }

            if(Arrays.equals(currNode.pBoard,termNode.pBoard)){
                System.out.println("Found terminal state :)");

                Node curr = currNode;
                while (curr.parent != null) {
                    movesList.add(curr.direction);
                    curr = curr.parent;
                }
                Collections.reverse(movesList);
                searchDepth = movesList.size();

                System.out.println("Moves list: " + movesList.toString());
                System.out.println("Visited states: " + visitedStates + ", search depth: " + searchDepth);
                break;
                //TODO
            }

            openList.remove(currNode);

            if(System.nanoTime() - start > time) {
                openList.clear();
                start = System.nanoTime();
                System.out.println("Time's out!");
            }


            for(Node n : currNode.genChildren()){
                openList.add(n);
            }


        }



    }

}
