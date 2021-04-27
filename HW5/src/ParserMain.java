import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class ParserMain {
    
//    static int[][] adjMatrix;
//    static List<Player> playerList = new LinkedList<>();
//    static List<String> nodeList = new LinkedList<>();
//    static List<String> nodeType = new LinkedList<>();
    
    public static void main(String[] args) {
        
        
//        DataExtraction.scrape();
        DataExtraction.load();
        
        // Adjacency matrix (1 = edge, 0 = no edge)
        int[][] adjMatrix = DataExtraction.getAdjMatrix();
        
        // The String of all indices in adjMatrix
        List<String> nodeList = DataExtraction.getNodeList();
        
        /*
         * The type of all indices in adjMatrix
         * "n": player's name
         * "t": team
         * "h": home town/ birthplace
         * "c": college
         */
        List<String> nodeType = DataExtraction.getNodeType();
        
        
        Scanner scanner = new Scanner(System.in);
        // add other functions
        System.out.println("Enter '1' to find smallest number of connections between two players");
        String input = scanner.nextLine();
        
        switch (input) {
            case "1":
                System.out.println("Enter name of first player.");
                String p1 = scanner.nextLine();
                System.out.println("Enter name of second player.");
                String p2 = scanner.nextLine();
                BFSConnections.shortestPath(adjMatrix, nodeList, nodeType, p1, p2);
        }
        
                
        // Examples
//        String player = "Stephen Curry";
//        
//        int indexOfCurry = nodeList.indexOf(player);
//        System.out.println(player + " has index of " + indexOfCurry);
//        // Find his team
//        String CurryTeam = "";
//        for (int i = 0; i < adjMatrix[indexOfCurry].length; i++) {
//            if (adjMatrix[indexOfCurry][i] == 1 && nodeType.get(i).equals("t")) {
//                CurryTeam = nodeList.get(i);
//                System.out.println(player + " plays for " + nodeList.get(i));
//            }
//        }
//        // Find all his teammates in the team
//        int indexOfCurryTeam = nodeList.indexOf(CurryTeam);
//        System.out.println(CurryTeam + " has index of " + indexOfCurryTeam);
//        System.out.println("His teammates: ");
//        for (int i = 0; i < adjMatrix[indexOfCurryTeam].length; i++) {
//            if (adjMatrix[indexOfCurryTeam][i] == 1 && nodeType.get(i).equals("n")) {
//                System.out.println(nodeList.get(i));
//            }
//        }
    }
}