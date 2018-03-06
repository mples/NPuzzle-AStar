package astar;

public class TileMove {
    byte tile;
    int fromPos;
    int toPos;
    //Node fromState;
    //Node toState;

    TileMove(byte tile, int fromPos, int toPos) {
        this.tile = tile;
        this.fromPos = fromPos;
        this.toPos = toPos;
    }
}
