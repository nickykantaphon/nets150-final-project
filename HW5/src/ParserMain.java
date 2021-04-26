import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class ParserMain {
    
    private static int[][] adjMatrix;
    private static List<Player> playerList = new LinkedList<>();
    private static List<String> nodeList = new LinkedList<>();
    private static List<String> nodeType = new LinkedList<>();
    
    public static void main(String[] args) {
        
//        DataExtraction o = new DataExtraction();
//        
//        // Adjacency matrix (1 = edge, 0 = no edge)
//        int[][] adjMatrix = o.getAdjMatrix();
//        
//        // The String of all indices in adjMatrix
//        List<String> nodeList = o.getNodeList();
//        
//        /*
//         * The type of all indices in adjMatrix
//         * "n": player's name
//         * "t": team
//         * "h": home town/ birthplace
//         * "c": college
//         */
//        List<String> nodeType = o.getNodeType();
        
        
        load();
                
        // Examples
        String player = "Stephen Curry";
        
        int indexOfCurry = nodeList.indexOf(player);
        System.out.println(player + " has index of " + indexOfCurry);
        // Find his team
        String CurryTeam = "";
        for (int i = 0; i < adjMatrix[indexOfCurry].length; i++) {
            if (adjMatrix[indexOfCurry][i] == 1 && nodeType.get(i).equals("t")) {
                CurryTeam = nodeList.get(i);
                System.out.println(player + " plays for " + nodeList.get(i));
            }
        }
        // Find all his teammates in the team
        int indexOfCurryTeam = nodeList.indexOf(CurryTeam);
        System.out.println(CurryTeam + " has index of " + indexOfCurryTeam);
        System.out.println("His teammates: ");
        for (int i = 0; i < adjMatrix[indexOfCurryTeam].length; i++) {
            if (adjMatrix[indexOfCurryTeam][i] == 1 && nodeType.get(i).equals("n")) {
                System.out.println(nodeList.get(i));
            }
        }
    }
    
    static void load() {
        File file = Paths.get("HW5/data.txt").toFile();
        BufferedReader br = null; 
        
        try {
            br = new BufferedReader(new FileReader(file));
            
            // get matrix size
            String strSize = br.readLine();
            int size = Integer.parseInt(strSize);
            adjMatrix = new int[size][size];
                 
            // load game board 
            for (int row = 0; row < adjMatrix.length; row++) {
                for (int col = 0; col < adjMatrix[row].length; col++) {
                    int val = br.read() - 48;
                    adjMatrix[row][col] = val;
                } 
                br.read();
            }

            // load player list
            String players = br.readLine();  

            String[] playersSplit = players.split("/");
            
            for (int i = 0; i < playersSplit.length - 3; i += 4) {
                String n = playersSplit[i];
                String t = playersSplit[i + 1];
                String h = playersSplit[i + 2];
                String c = playersSplit[i + 3];
                Player p = new Player(n, t, h, c);               
                playerList.add(p);
            }

            // load node list
            String nodes = br.readLine();
            String[] nodesSplit = nodes.split("/");
            
            for (int i = 0; i < nodesSplit.length; i++) {
                nodeList.add(nodesSplit[i]);
            }
            
            // load node type
            String type = br.readLine();
            String[] typeSplit = type.split("/");
            
            for (int i = 0; i < typeSplit.length; i++) {
                nodeType.add(typeSplit[i]);
            }
            
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            return;
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                return;
            }
        }        
    }
}