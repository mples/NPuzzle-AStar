package astar;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class Zobrist {
    private int boardSize;      // 2*n
    private int tilesNum;
    private long[][] tileKeys;  // [tileNumber][tilePosition]

    private final int blankTile = 0;
    private boolean withExtraTile;  // TODO add extra tile in loadFromFile !!!
    private int extraTile;
    private long currStateKey;  // TODO other class? -- ZobristKey
    private boolean initialized = false;  // TODO Zobrist generator initialized?

    private static Zobrist instance;

    public static Zobrist instance() {
        if (instance == null) {
            instance = new Zobrist();
        }
        return instance;
    }

    public long tileMoveFromState(long fromStateKey, TileMove move) {
        fromStateKey ^= tileKeys[move.tile][move.fromPos] ^ tileKeys[move.tile][move.toPos];
        fromStateKey ^= tileKeys[blankTile][move.toPos] ^ tileKeys[blankTile][move.fromPos];

        return fromStateKey;
    }

    private Zobrist() {
        // boardSize == tilesNum == 0
        // tileKeys == null
    }

    public void initZobristGenerator(int boardSize, boolean addExtraTile) {
        this.boardSize = boardSize;
        this.tilesNum = addExtraTile ? boardSize+1 : boardSize; // extra '-1' tile for database patterns
        this.tileKeys = new long[tilesNum][boardSize];

        this.withExtraTile = addExtraTile;
        this.extraTile = boardSize;

        generateZobristKeysTable();
        this.initialized = true;
    }

    private Zobrist(int boardSize, boolean addExtraTile) {
        initZobristGenerator(boardSize, addExtraTile);
    }

    private Zobrist(String filepath) {
        if (!loadZobristKeysTableFromFile(filepath))
            System.err.println("Loading ZobristKeyTable from file: " + filepath + " did not succeed. Zobrist will be reset to default.");
    }

    public long initZobristKeyForState(Node node) {
        assert node.pBoard.length == boardSize : "Error: Node's board (game state) has too small size for this Zobrist generator.";

        currStateKey = 0;
        for (int pos = 0; pos < boardSize; ++pos) {
            currStateKey ^= tileKeys[node.pBoard[pos]][pos];
        }

        return currStateKey;
    }

    public long initZobristKeyForBoard(byte[] board) {
        assert board.length == boardSize : "Error: Board has too small size for this Zobrist generator.";

        currStateKey = 0;
        for (int pos = 0; pos < boardSize; ++pos) {
            currStateKey ^= tileKeys[board[pos]][pos];
        }

        return currStateKey;
    }

    public long transformZobristKeyByTileMove(TileMove move) {
        currStateKey ^= tileKeys[move.tile][move.fromPos] ^ tileKeys[move.tile][move.toPos];
        currStateKey ^= tileKeys[blankTile][move.toPos] ^ tileKeys[blankTile][move.fromPos];

        return currStateKey;
    }

    public void loadFromFileOrGenerate(String filepath) {
        if (!loadZobristKeysTableFromFile(filepath))    // TODO only if good parameters!!!
            System.err.println("Loading ZobristKeyTable of given size from file: " + filepath + " did not succeed. Zobrist will be generated.");
        generateZobristKeysTable();
    }

    public void generateZobristKeysTable() {
        int tableSize = tilesNum*boardSize;
        Random rnd = new Random();

        // use LinkedHashSet to maintain insertion order
        Set<Long> generatedRnds = new LinkedHashSet<>();
        while (generatedRnds.size() < tableSize) {
            Long next = rnd.nextLong() + 1;     // TODO +1?
            generatedRnds.add(next); // automatically does a containment check
        }

        assert generatedRnds.size() == tableSize : "Error: Generated too few random values.";
        assert tileKeys.length*tileKeys[0].length == tableSize : "Error: TileKeys array has too small size.";

        int tile = 0, pos = 0;
        for (long value : generatedRnds) {
            tileKeys[tile][pos++] = value;
            if (pos % boardSize == 0) {
                pos = 0;
                ++tile;
            }
        }
        assert tile == tilesNum : "Error: Didn't generate values for all tiles.";
        assert pos == boardSize : "Error: Didn't generate values for all positions.";
    }

    public void saveZobristKeysTableToFile(String filepath) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filepath)))) {

            // format:
            // tilesNum
            // boardSize
            // tileKey - 1 per line (tilesNum*boardSize lines)

            // tileKeys[0][0 - boardSize-1]
            // ...
            // tileKeys[tilesNum-1][0 - boardSize-1]

            //writer.println(tilesNum + " " + boardSize);
            writer.println(tilesNum);
            writer.println(boardSize);
            for (int tile=0; tile < tilesNum; ++tile) {
                for (int pos=0; pos < boardSize; ++pos)
                    //writer.println( Long.toHexString(tileKeys[tile][pos]) );
                    writer.println( tileKeys[tile][pos] );
            }

        } catch (IOException ex) {
            System.err.println("Error opening file: " + filepath + " for writing when saving ZobristKeysTable.");
        }
    }

    public boolean loadZobristKeysTableFromFile(String filepath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {

            // format:
            // tilesNum
            // boardSize
            // tileKey - 1 per line (tilesNum*boardSize lines)

            // tileKeys[0][0 - boardSize-1]
            // ...
            // tileKeys[tilesNum-1][0 - boardSize-1]

            // TODO test for files with wrong format
            // TODO test for non-existing file
            String line;
            if ((line = reader.readLine()) == null)
                return false;
            tilesNum = Integer.parseUnsignedInt(line);
            if ((line = reader.readLine()) == null)
                return false;
            boardSize =Integer.parseUnsignedInt(line);

            tileKeys = new long[tilesNum][boardSize];

            for (int tile=0; tile < tilesNum; ++tile) {
                for (int pos=0; pos < boardSize; ++pos) {
                    if ((line = reader.readLine()) == null) {
                        System.out.println("Tile " + tile + " Pos " + pos + " Line is null");
                        return false;
                    }
                    //tileKeys[tile][pos] = Long.parseLong(line, 16);
                    tileKeys[tile][pos] = Long.parseLong(line);
                }
            }

        } catch (EOFException ex) {
            System.err.println("EOFException!");
            reset();
            return false;
        } catch (IOException ex) {
            System.err.println("Error opening file: " + filepath + " for reading when loading ZobristKeysTable.");
        }

        return true;
    }

    private void reset() {
        boardSize = tilesNum = 0;
        tileKeys = null;
    }
}