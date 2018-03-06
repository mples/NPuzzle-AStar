package astar;

import java.io.*;
import java.util.*;

class Patterns {
    public static ArrayList<byte[]> patterns3x3;
    public static byte irlvtTile3x3 = 9;
    public static ArrayList<byte[]> patterns4x4;
    public static byte irlvtTile4x4 = 16;
    public static ArrayList<byte[]> patterns5x5;
    public static byte irlvtTile5x5 = 25;
    public static ArrayList<byte[]> patterns6x6;
    public static byte irlvtTile6x6 = 36;

    public static int[] patternsPerSize = {0, 0, 0, 2, 3, 4, 5};    // index = boardSize

    static {
        patterns3x3 = new ArrayList<>(2);
        Collections.addAll(patterns3x3, new byte[][]{
                {0, 1, irlvtTile3x3,
                 3, 4, irlvtTile3x3,
                 6, irlvtTile3x3, irlvtTile3x3},

                {0, irlvtTile3x3, 2,
                 irlvtTile3x3, irlvtTile3x3, 5,
                 irlvtTile3x3, 7, 8}
        });

        patterns4x4 = new ArrayList<>(3);
        Collections.addAll(patterns4x4, new byte[][]{
                {0, 1, 2, irlvtTile4x4,
                 4, 5, irlvtTile4x4, irlvtTile4x4,
                 8, 9, irlvtTile4x4, irlvtTile4x4,
                 irlvtTile4x4, irlvtTile4x4, irlvtTile4x4, irlvtTile4x4},

                {0, irlvtTile4x4, irlvtTile4x4, 3,
                 irlvtTile4x4, irlvtTile4x4, 6, 7,
                 irlvtTile4x4, irlvtTile4x4, 10, 11,
                 irlvtTile4x4, irlvtTile4x4, irlvtTile4x4, 15},

                {0, irlvtTile4x4, irlvtTile4x4, irlvtTile4x4,
                 irlvtTile4x4, irlvtTile4x4, irlvtTile4x4, irlvtTile4x4,
                 irlvtTile4x4, irlvtTile4x4, irlvtTile4x4, irlvtTile4x4,
                 12, 13, 14, irlvtTile4x4}
        });

        patterns5x5 = new ArrayList<>(4);
        Collections.addAll(patterns5x5, new byte[][]{
                {0, 1, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5,
                 5, 6, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5,
                 10, 11, 12, irlvtTile5x5, irlvtTile5x5,
                 irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5,
                 irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5},

                {0, irlvtTile5x5, 2, 3, 4,
                 irlvtTile5x5, irlvtTile5x5, 7, 8, 9,
                 irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5,
                 irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5,
                 irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5},

                {0, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5,
                 irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5,
                 irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, 13, 14,
                 irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, 18, 19,
                 irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, 23, 24},

                {0, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5,
                 irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5,
                 irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5, irlvtTile5x5,
                 15, 16, 17, irlvtTile5x5, irlvtTile5x5,
                 20, 21, 22, irlvtTile5x5, irlvtTile5x5}
        });

        patterns6x6 = new ArrayList<>(5);
        Collections.addAll(patterns6x6, new byte[][]{
                {0, 1, 2, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6,
                 6, 7, 8, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6,
                 12, 13, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6,
                 irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6,
                 irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6,
                 irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6},

                {0, irlvtTile6x6, irlvtTile6x6, 3, 4, 5,
                 irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, 9, 10, 11,
                 irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, 17,
                 irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6,
                 irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6,
                 irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, },

                {0, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6,
                 irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6,
                 irlvtTile6x6, irlvtTile6x6, 14, 15, 16, irlvtTile6x6,
                 irlvtTile6x6, irlvtTile6x6, 20, 21, 22, irlvtTile6x6,
                 irlvtTile6x6, irlvtTile6x6, 26, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6,
                 irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6},

                {0, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6,
                 irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6,
                 irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6,
                 18, 19, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6,
                 24, 25, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6,
                 30, 31, 32, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6},

                {0, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6,
                 irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6,
                 irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6,
                 irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, 23,
                 irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, 27, 28, 29,
                 irlvtTile6x6, irlvtTile6x6, irlvtTile6x6, 33, 34, 35}
        });
    }
}

public class PatternDatabase {
    private int boardSize;
    private int patternsNum;
    int maxCost;
    private List<HashMap<Integer, Integer>> patternDatabases;
    private ArrayList<byte[]> patternList;

    public PatternDatabase(int boardSize) {     // N x N
        this.boardSize = boardSize;
        this.patternsNum = Patterns.patternsPerSize[boardSize];
        patternDatabases = new ArrayList<>(patternsNum);
        switch (boardSize){
            case 3:
                patternList = Patterns.patterns3x3;
                maxCost = Integer.MAX_VALUE;
                break;
            case 4:
                patternList = Patterns.patterns4x4;
                maxCost = 12;
                break;
            case 5:
                patternList = Patterns.patterns5x5;
                maxCost = 7;
                break;
            case 6:
                patternList = Patterns.patterns6x6;
                maxCost = 5;
                break;
        }
        loadOrGenerateDatabases("/patterns/"+boardSize+"x"+boardSize);

    }

    public int calculateHeu (byte[] board){
        ArrayList<byte[]> boardsToCheck = new ArrayList<byte[]>(patternsNum);
        for(int i = 0 ; i < patternsNum ; ++i){
            byte[] temp = board.clone();
            boardsToCheck.add(temp);
        }
        for( int i = 0 ; i < patternsNum ; ++i){
            for(int j = 0 ; j < boardSize* boardSize ; ++j){
                if( !patternContains(patternList.get(i), boardsToCheck.get(i)[j])) {
                    boardsToCheck.get(i)[j] = (byte) (boardSize * boardSize);
                }
            }

        }

        int heurValue = 0;
        Integer tempValue = new Integer(0);
        for(int i = 0 ; i < patternsNum ; ++i){
            if((tempValue = patternDatabases.get(i).get(Arrays.hashCode(boardsToCheck.get(i)))) != null){
//                if(tempValue > heurValue){
//                    heurValue = tempValue;
//                }
            	heurValue += tempValue;
            }
        }

        return heurValue;
    }


    private boolean patternContains(byte[] pattern, byte x){
        for(int i = 0 ; i < pattern.length ; ++i){
            if(pattern[i] == x){
                return true;
            }
        }
        return false;
    }

    public void loadOrGenerateDatabases(String filepathBase/*, int patternSize == 3*/) { // filepathBase = NxNpattern
    	long startTime = System.nanoTime();
        for (int i=0; i<patternsNum; ++i) {
            if (!loadDatabaseFromFile(filepathBase + "pattern" + i + ".txt", i)) {
                System.out.println("Database file not found or of wrong format. Generating new database.");
                generateDatabase(patternList.get(i), boardSize, filepathBase, i);
            }
        }
        System.out.println("Database generation/load time: " + (System.nanoTime() - startTime));
    }

    public void generateDatabase(byte[] pattern, int patternSize, String patternName, int filenameSuffix) {
    	//long startTime = System.nanoTime();
        System.out.println("Generate pattern " + filenameSuffix);
        HashMap<Integer, Integer> patternDatabase = bfs(pattern, patternSize,maxCost);
        patternDatabases.add(patternDatabase);
        saveDatabaseToFile(patternName + "pattern" + filenameSuffix + ".txt", patternDatabase);
        //System.out.println("Database generation time: " + (System.nanoTime() - startTime));
    }

    private HashMap<Integer, Integer> bfs(byte[] pattern, int size, int maxCost) {
        HashMap<Integer, Integer> costDatabase = new HashMap<>();
        LinkedList<PatternNode> openList = new LinkedList<>();

        openList.add(new PatternNode(pattern, size));
        PatternNode current;
        int counter = 0;
        while ((current = openList.pollFirst()) != null) {
            System.out.println("Curr cost: " + current.cost + " counter " + counter++ + /*" key " + current.zobristKey +*/ " hash " + current.hashCode() /*+ " id " + System.identityHashCode(current)*/);
            costDatabase.put(current.hashCode(), current.cost);

            for(PatternNode node : current.generateChildren()){
                if(costDatabase.containsKey(node.hashCode()) || node.cost > maxCost){
                    continue;
                    // TODO replace cost with the lower one
                } else {
                    openList.add(node);
                }
            }
        }

        return costDatabase;
    }

    public void saveDatabaseToFile(String filepath, HashMap<Integer, Integer> database) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filepath)))) {

            // format:
            // PatternNode.hashCode cost

            database.forEach((k,v) -> writer.println(k /*+ " " + Arrays.toString(k.pBoard)*/ + " " + v));

        } catch (IOException ex) {
            System.err.println("Error opening file: " + filepath + " for writing when saving PatternDatabase.");
        }
    }

    // TODO check file format
    public boolean loadDatabaseFromFile(String filepath, int patternDatabaseIndex) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {

            // format:
            // PatternNode.hashCode cost

            // TODO test for files with wrong format
            // TODO test for non-existing file
            patternDatabases.add(new HashMap<>());
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                if (data.length != 2)
                    return false;
                int nodeHash = Integer.parseInt(data[0]);
                int nodeCost = Integer.parseInt(data[1]);
                patternDatabases.get(patternDatabaseIndex).put(nodeHash, nodeCost);
            }

        } catch (EOFException ex) {
            System.err.println("EOFException!");
            return false;
        } catch (IOException ex) {
            System.err.println("Error opening file: " + filepath + " for reading when loading PatternDatabase.");
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        final int SIZE = 3;
        //Zobrist.instance().initZobristGenerator(SIZE*SIZE, true);

        PatternDatabase database = new PatternDatabase(SIZE);
        //ArrayList<byte[]> small4x4pattern = new ArrayList<>(1);
        //small4x4pattern.add(Patterns.patterns4x4.get(2));

        /*database.generateDatabase(Patterns.patterns3x3, SIZE, "F:/STUDIA/PSZT/Projekt/NPuzzle-AStar/NPuzzleProject/src/astar/3x3");

        database.loadDatabaseFromFile("F:/STUDIA/PSZT/Projekt/NPuzzle-AStar/NPuzzleProject/src/astar/3x3pattern0.txt", 0);
        database.loadDatabaseFromFile("F:/STUDIA/PSZT/Projekt/NPuzzle-AStar/NPuzzleProject/src/astar/3x3pattern1.txt", 1);

        int filenameSuffix = 0;
        for (HashMap<Integer, Integer> db : database.patternDatabases) {
            System.out.println("heh");
            database.saveDatabaseToFile("F:/STUDIA/PSZT/Projekt/NPuzzle-AStar/NPuzzleProject/src/astar/test3x3-" + filenameSuffix + ".txt", db);
            ++filenameSuffix;
        }*/

//        database.loadOrGenerateDatabases3x3("F:/STUDIA/PSZT/Projekt/NPuzzle-AStar/NPuzzleProject/src/astar/3x3");
//        int filenameSuffix = 0;
//        for (HashMap<Integer, Integer> db : database.patternDatabases) {
//            database.saveDatabaseToFile("F:/STUDIA/PSZT/Projekt/NPuzzle-AStar/NPuzzleProject/src/astar/test3x3-" + filenameSuffix + ".txt", db);
//            ++filenameSuffix;
//        }
    }
}
