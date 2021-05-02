// imports
import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class ParserMain {
    
    public static void main(String[] args) {
        
        /*
         * Comment out either one of the two lines below to switch 
         * between scraping the data from the website (will take a long time)
         * versus loading the data from a .txt file that contains the 
         * same data (faster). 
         */
        DataExtraction.scrape();
//        DataExtraction.load();
        
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
        
        
        /* INSTRUCTIONS
         * ------------
         * Please, follow the instructions displayed below and enter names/locations carefully! 
         * To test for different functions, please run the program again and input a different starting number.
         * (i.e.) To test for our BFS/shortest path implementation, press 1
         * (i.e.) To test for our closure implementation, press 2 or 3
         * (Note) Our classes (see README) for creating the graph are the backbones for these functions
         * (Note) The program may take a while to run the first time, as we are scraping large amounts of data. 
         * We apologise for that.
         * Thank you!   
         */
        
        
        /* Extra note to user
         * ------------
         * Distinct case 2 and 3 for ease of use (please follow this strictly!) 
         * Case 3 allows hometown to be less precise in search (i.e. if someone doesn't know the state abbreviation).
         * Ultimately, this gives the user the benefit of the doubt and makes input easier for them, with the 
         * added flexibility/functionality to search state-wide if interested (i.e. input "CA" rather than "[city], CA")  
         */
        
        // instructions
        System.out.println("Enter '1' to find smallest number of connections between two players.");
        System.out.println("Enter '2' to find the recommended team based on your college.");
        System.out.println("Enter '3' to find the recommended team based on your hometown.");
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
                
                // if same player
                if (p1.equalsIgnoreCase(p2)) {
                    System.out.println("Please enter two different players!");
                    break;
                }
                
                BFSConnections.shortestPath(adjMatrix, nodeList, nodeType, p1, p2);
                break;
                
            case "2": 
                System.out.println("Enter name of college: ");
                String c = scanner.nextLine();
                System.out.println();
                Recommendation.rec(adjMatrix, nodeList, nodeType, c, "c");
                break;
                
            case "3": 
                System.out.println("Enter name of hometown: ");
                String h = scanner.nextLine();
                System.out.println();
                Recommendation.rec(adjMatrix, nodeList, nodeType, h, "h");
                break;
                
            default:
                System.out.println("Please enter a valid input (numbers 1 - 3).");
                break;
        }
        
        scanner.close(); // close scanner
    }
}
