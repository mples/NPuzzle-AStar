package astar;

import java.io.IOException;
import java.util.*;

public class AStar {
    Node root;
    Node termNode;
    List<Node> openList;
    List<Node> closedList;
    LinkedList<Direction> movesList;

    private long visitedStates = 0;
    private int searchDepth = 0;

    /** Given time in nanoseconds **/
    private long time;

    AStar(String fname, long time) throws IOException{
        root = new Node(fname);
       
        openList = new LinkedList<Node>();
        closedList = new LinkedList<Node>();
        movesList = new LinkedList<>();			
        termNode = new Node(root.size);

        this.time = time;
    }
    
    
    private int getInvCount(byte arr[])
    {
        int inv_count = 0;
        for (int i = 0; i < root.size * root.size - 1; i++)
        {
            for (int j = i + 1; j < root.size * root.size; j++)
            {
                if (arr[j] != 0 && arr[i] != 0 && arr[i] > arr[j])	
                    inv_count++;
            }
        }
        return inv_count;
    }
    
    private int findXPosition(byte[] board)
    {
        for (int i = 0; i <= root.size - 1; i++) {
            for (int j = 0; j <= root.size - 1; j++) {
                if (board[i * root.size + j] == 0) {
                    return i + 1;	
                }
            }
        }
        return 0;	
    }
    
    public boolean isSolvable() {
    	byte[] board = root.pBoard.clone();
        /* Count inversions in given puzzle */
        int invCount = getInvCount(board);
     
        /* If grid is odd, return true if inversion count is even. */
        if ((root.size & 1) != 0) {
            return ((invCount & 1) != 0) ? false : true;
        }
        /* Grid is even */
        else
        {
            int pos = findXPosition(board);
            if ((pos & 1) != 0)
                return ((invCount & 1) != 0) ? false : true;	//odd
            else
                return ((invCount & 1) != 0) ? true : false;	//even
        }
    }

    public void solve(){
        openList.add(root);
        long start = System.nanoTime();
        
        boolean flag = false;

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

            if(System.nanoTime() - start > time) {
              System.out.println("Time's out!");
              flag = true;
            }
            
            if(Arrays.equals(currNode.pBoard,termNode.pBoard) || flag == true){
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
            closedList.add(currNode);

//            if(System.nanoTime() - start > time) {
//                openList.clear();
//                start = System.nanoTime();
//                System.out.println("Time's out!");
//            }


            for(Node n : currNode.genChildren()){
				if(closedList.contains(n)){
					continue;	
				}
				if(!openList.contains(n)) {
					openList.add(n);
				}
            }


        }

    }

	public long getVisitedStates() {
		return visitedStates;
	}

	public void setVisitedStates(long visitedStates) {
		this.visitedStates = visitedStates;
	}

	public int getSearchDepth() {
		return searchDepth;
	}

	public void setSearchDepth(int searchDepth) {
		this.searchDepth = searchDepth;
	}

}
