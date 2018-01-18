import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

enum Direction {UP, DOWN, RIGHT, LEFT, NONE};

public class Node {
    byte[] pBoard;
    public int size;
    int g = 0;
    int h = 0;
    int f = 0;
    int blankX;
    int blankY;
    Node parent;
    Direction direction;

    Node(String input) throws IOException{


        BufferedReader in = new BufferedReader(new FileReader(input));
        int j = 0;
        String line = in.readLine();
        String[] lineArray = line.split(" ");
        size = lineArray.length;
        pBoard = new byte[size*size];
        while(line != null){
            lineArray = line.split(" ");
            for (int i = 0; i < size; i++) {

                pBoard[j*size + i] = Byte.parseByte(lineArray[i]);
                if(pBoard[j*size + i] == 0){
                    blankX = i;
                    blankY = j;
                }
            }
            line = in.readLine();
            j++;
        }
        in.close();

    }
    Node(int s){
        size =s ;
        pBoard = new byte[size*size];
        for(int i =0; i < size*size ; ++i){
            pBoard[i] = Byte.parseByte(Integer.toString(i));
        }
        blankX = 0;
        blankY = 0;

    }
    Node(Node n){
        pBoard = Arrays.copyOf(n.pBoard,n.size*n.size);
        g = n.g +1;
        //TODO H F
        size = n.size;
        blankX = n.blankX;
        blankY = n.blankY;

        parent = n;
    }

    @Override
    public String toString() {
        return "Node{" +
                "pBoard=" + Arrays.toString(pBoard) +
                '}';
    }

    ArrayList<Node> genChildren(){
        ArrayList<Node> childrenList = new ArrayList<Node>();
        if(blankX < size-1){
            Node rChild = new Node(this);
            rChild.rightMove();
            rChild.direction = Direction.RIGHT;
            rChild.updateF();
            childrenList.add(rChild);

        }
        if(blankX >0){
            Node lChild = new Node(this);
            lChild.leftMove();
            lChild.direction = Direction.LEFT;
            lChild.updateF();
            childrenList.add(lChild);
        }
        if(blankY > 0){
            Node uChild = new Node(this);
            uChild.upMove();
            uChild.direction = Direction.UP;
            uChild.updateF();
            childrenList.add(uChild);
        }
        if(blankY < size-1){
            Node dChild = new Node(this);
            dChild.downMove();
            dChild.direction = Direction.DOWN;
            dChild.updateF();
            childrenList.add(dChild);
        }
        return childrenList;
    }

    private void rightMove(){
        byte temp = this.pBoard[blankY*size+ blankX + 1];
        this.pBoard[blankY*size+ blankX + 1] = 0;
        this.pBoard[blankY*size+ blankX] = temp;
        blankX = blankX +1;

    }
    private void leftMove(){
        byte temp = this.pBoard[blankY*size+ blankX - 1];
        this.pBoard[blankY*size+ blankX - 1] = 0;
        this.pBoard[blankY*size+ blankX] = temp;
        blankX = blankX -1;

    }
    private void upMove(){
        byte temp = this.pBoard[(blankY-1)*size+ blankX];
        this.pBoard[(blankY-1)*size+ blankX] = 0;
        this.pBoard[blankY*size+ blankX] = temp;
        blankY = blankY -1;

    }
    private void downMove(){
        byte temp = this.pBoard[(blankY+1)*size+ blankX];
        this.pBoard[(blankY+1)*size+ blankX] = 0;
        this.pBoard[blankY*size+ blankX] = temp;
        blankY = blankY +1;

    }
    void updateF(){
        calculateManhattanHeur();
        f = g + h;
    }
    private void calculateManhattanHeur(){
        int totalDistance = 0;
        for(int j =0 ; j < size; ++j){
            for(int i =0 ; i <size; ++i){
                byte number = pBoard[j*size + i];

                int posY = number/size;
                int posX = number%size;

                int distance = Math.abs(j-posY) + Math.abs(i - posX);
                totalDistance += distance;
            }
        }

        h = totalDistance;
    }
}
