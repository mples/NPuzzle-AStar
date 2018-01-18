import java.io.IOException;
import java.util.*;

public class AStar {
    Node root;
    Node termNode;
    List<Node> openList;
    List<Direction> movesList;

    AStar(String fname){
        try {
            root = new Node(fname);
        }
        catch( IOException e){
            e.printStackTrace();
        }
        openList = new LinkedList<Node>();
        movesList = new ArrayList<>();
        termNode = new Node(root.size);
    }

    public void solve(){
        openList.add(root);
        long start = System.nanoTime();

        while( !openList.isEmpty()){
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

                System.out.println("Moves list: " + movesList.toString());
                break;
                //TODO
            }
            openList.remove(currNode);

            for(Node n : currNode.genChildren()){
                openList.add(n);
            }


        }



    }

}
