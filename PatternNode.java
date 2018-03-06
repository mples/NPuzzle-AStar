package astar;

import java.util.ArrayList;
import java.util.Arrays;

public class PatternNode {
    byte[] pBoard;
    int size;
    int blankX;
    int blankY;

    int cost = 0;
    //long zobristKey;

    public PatternNode(byte[] board, int size) {
        this.pBoard = board.clone();
        this.size = size;

        for (int i=0; i<size*size; ++i) {
                if (pBoard[i] == 0) {
                    blankX = i%size;
                    blankY = i/size;
                }
        }

        //zobristKey = Zobrist.instance().initZobristKeyForBoard(pBoard);
    }

    private PatternNode(PatternNode node){
        this.pBoard = node.pBoard.clone();
        this.size = node.size;
        this.blankX = node.blankX;
        this.blankY = node.blankY;

        this.cost = node.cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatternNode that = (PatternNode) o;
        return Arrays.equals(pBoard, that.pBoard);
    }

    /*@Override
    public int hashCode() {
        return Long.hashCode(zobristKey);
        //return (int)zobristKey;
    }*/

    @Override
    public int hashCode() {
        return Arrays.hashCode(pBoard);
    }

    ArrayList<PatternNode> generateChildren(){
        ArrayList<PatternNode> childrenList = new ArrayList<>();
        if(blankX < size-1){
            PatternNode rChild = new PatternNode(this);
            rChild.rightMove();
            childrenList.add(rChild);
        }
        if(blankX > 0){
            PatternNode lChild = new PatternNode(this);
            lChild.leftMove();
            childrenList.add(lChild);
        }
        if(blankY > 0){
            PatternNode uChild = new PatternNode(this);
            uChild.upMove();
            childrenList.add(uChild);
        }
        if(blankY < size-1){
            PatternNode dChild = new PatternNode(this);
            dChild.downMove();
            childrenList.add(dChild);
        }
        return childrenList;
    }

    private void rightMove(){
        byte temp = this.pBoard[blankY*size+ blankX + 1];
        this.pBoard[blankY*size+ blankX + 1] = 0;
        this.pBoard[blankY*size+ blankX] = temp;
        TileMove move = new TileMove(temp, blankY*size+ blankX + 1, blankY*size+ blankX);
        blankX = blankX +1;

        if (temp != size*size) {  // irrelevant tile
            ++cost;
        }

        //zobristKey = Zobrist.instance().tileMoveFromState(zobristKey, move);
    }
    private void leftMove(){
        byte temp = this.pBoard[blankY*size+ blankX - 1];
        this.pBoard[blankY*size+ blankX - 1] = 0;
        this.pBoard[blankY*size+ blankX] = temp;
        TileMove move = new TileMove(temp, blankY*size+ blankX - 1, blankY*size+ blankX);
        blankX = blankX -1;

        if (temp != size*size) {  // irrelevant tile
            ++cost;
        }

        //zobristKey = Zobrist.instance().tileMoveFromState(zobristKey, move);
    }
    private void upMove(){
        byte temp = this.pBoard[(blankY-1)*size+ blankX];
        this.pBoard[(blankY-1)*size+ blankX] = 0;
        this.pBoard[blankY*size+ blankX] = temp;
        TileMove move = new TileMove(temp, (blankY-1)*size+ blankX, blankY*size+ blankX);
        blankY = blankY -1;

        if (temp != size*size) {  // irrelevant tile
            ++cost;
        }

        //zobristKey = Zobrist.instance().tileMoveFromState(zobristKey, move);
    }
    private void downMove(){
        byte temp = this.pBoard[(blankY+1)*size+ blankX];
        this.pBoard[(blankY+1)*size+ blankX] = 0;
        this.pBoard[blankY*size+ blankX] = temp;
        TileMove move = new TileMove(temp, (blankY+1)*size+ blankX, blankY*size+ blankX);
        blankY = blankY +1;

        if (temp != size*size) {  // irrelevant tile
            ++cost;
        }

        //zobristKey = Zobrist.instance().tileMoveFromState(zobristKey, move);
    }
}
