// imports
import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class ParserMain { // class
    
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
        
        
        /* 
         * Please, follow the instructions displayed below and enter names/locations carefully! 
         * To test for different functions, please run the program again and input a different starting number.
         * (i.e.) To test for our BFS/shortest path implementation, press 1
         * (i.e.) To test for our closure implementation, press 2
         * (Note) Our classes (see README) for creating the graph are the backbones for these functions
         * (Note) The program may take a while to run the first time, as we are scraping large amounts of data. 
         * We apologise for that.
         * Thank you!   
         */
        
         
        // instructions
        System.out.println("Enter '1' to find smallest number of connections between two players.");
        System.out.println("Enter '2' to find the recommended team based on your college or hometown.");
        Scanner scanner = new Scanner(System.in); // scanner for userinput
        String input = scanner.nextLine();
        
        // different functions of program for user input (and grading)
        switch (input) {
            case "1":
                System.out.println("Enter name of first player: ");
                String p1 = scanner.nextLine();
                System.out.println("Enter name of second player: ");
                String p2 = scanner.nextLine();
                System.out.println();
                
                // if same player twice
                if (p1.equalsIgnoreCase(p2)) {
                    System.out.println("Please enter two different players!");
                    break;
                }
                
                BFSConnections.shortestPath(adjMatrix, nodeList, nodeType, p1, p2);
                break;
                
            case "2": 
                System.out.println("Enter name of college or hometown: ");
                String t = scanner.nextLine();
                System.out.println();
                Recommendation.rec(adjMatrix, nodeList, nodeType, t);
                break;
                
            default:
                System.out.println("Please enter a valid input (numbers 1 - 2).");
                break;
        }
        
        scanner.close(); // close scanner
    }
}