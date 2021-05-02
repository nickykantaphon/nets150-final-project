import java.util.*;

public class Recommendation {
        
    /**
     * Prints out the recommendations for user based on college and/or hometown input (in ParserMain)
     * @param adjMatrix - adjacency matrix of graph
     * @param nodeList list of node names 
     * @param nodeType list of node types
     * @param type - college or hometown (user input for recommendation)
     */
    public static void rec(int[][] adjMatrix, List<String> nodeList, List<String> nodeType, String type, String ver) {    
        if (getIndex(nodeList, type, ver) != -1) {  // if exists in adjacency matrix (not = -1)
            
            // use helper methods to initialize playerList and teamList
            List<String> playerList = getPlayerList(adjMatrix, nodeList, getIndex(nodeList, type, ver));
            List<String> teamList = getMaxTeams(getTeamMap(adjMatrix, nodeList, nodeType, playerList));
            
            // header for output
            System.out.println("For a player from " + type + ", the following " + teamList.size() + " NBA team(s) are recommended: " + (String.join(" & ", teamList)) + "\n");
            
            // for each loop through teamList
            for (String t : teamList) {
                System.out.println(type + " players in the " + t + ": "); // sub header for each team
                for (String p : playerList) { // for loop through relevant players
                    if (adjMatrix[nodeList.indexOf(p)][nodeList.indexOf(t)] == 1) {
                        System.out.println(p); // outputs players from list and in team
                    }
                }
                System.out.println(); // new line divider
            }
        }
    } // end red method
    
    /**
     * Gets the index of a type input in the list of nodes, with case insensitive searching.
     * @param nodeList - list of node names
     * @param type - type input to get index of 
     * @return index of type input or -1 if not found (with message)
     */
    private static int getIndex(List<String> nodeList, String type, String ver) {
        for (int i = 0; i < nodeList.size(); i++) { // case insensitive (easier for user)
            if (ver.equals("c")) { // case if college 
                if (nodeList.get(i).equalsIgnoreCase(type)) {
                    return i;
                } 
            } else if (ver.equals("h")) { // case if hometown (easier for user)
                if (nodeList.get(i).toLowerCase().contains(type.toLowerCase())) { // toLowerCase() makes it case insensitive
                    return i;
                }
            }
        }
        System.out.println("Invalid input. No players found from " + type + ".\n"); // if not found
        return -1;
    }
    
    /**
     * Gets the list of players that match the user's input (college or hometown)
     * @param adjMatrix - adjacency matrix of graph
     * @param nodeList - list of node names
     * @param index - index of type (college or hometown)
     * @return list of players matching user input (i.e. players from "Duke")
     */
    private static List<String> getPlayerList(int[][] adjMatrix, List<String> nodeList, int index) {
        List<String> playerList = new ArrayList<String>(); // empty list for return
        
        for (int i = 0; i < adjMatrix[index].length; i++) { // loop through matrix
            if (adjMatrix[index][i] == 1) { // if an edge exists (1 in adjacency matrix)
                playerList.add(nodeList.get(i)); // add to list
            }
        }
        return playerList; // returns filled player list
    } // end method
    
    /**
     * Creates a map of team names and frequency among players in playerList list. 
     * For each player, finds their team and adds to map. If player already exists, increments value by 1.
     * @param adjMatrix - adjacency matrix of graph
     * @param nodeList - list of node names
     * @param nodeType - list of node types
     * @param playerList - list of players matching user input
     * @return complete map of keys (teams) and values (frequency) amongst playerList players
     */
    private static Map<String, Integer> getTeamMap(int[][] adjMatrix, List<String> nodeList, List<String> nodeType, List<String> playerList) {
        Map<String, Integer> teamMap = new HashMap<>(); // defines hashmap for return
        
        for (String p : playerList) { // for each loop through players
            for (int i = 0; i < adjMatrix.length; i++) { // for loop through adjacency matrix (2D and square so adjMatrix.length works)
                if (adjMatrix[nodeList.indexOf(p)][i] == 1 && nodeType.get(i).equals("t")) { // if edge exists and to a team node
                    String team = nodeList.get(i);
                    
                    // algorithm for adding to and updating map (k, v)
                    if (teamMap.containsKey(team)) {
                        teamMap.put(team, teamMap.get(team) + 1);
                    } else {
                        teamMap.put(team, 1);
                    }
                }
            }
        }
        return teamMap; // returns hash map
    }

    /**
     * Takes map of players and creates list with highest value teams (most common) 
     * @param teamMap - map of teams and their frequency (number of players from that team that match user type input) 
     * @return list of teams with the maximum frequency (values) from the map
     */
    private static List<String> getMaxTeams(Map<String, Integer> teamMap) {
        List<String> teamList = new ArrayList<String>(); // defines list for return
        
        for (@SuppressWarnings("rawtypes") Map.Entry el : teamMap.entrySet()) { // for each loop through map
            if ((int) el.getValue() == Collections.max(teamMap.values())) { // if element's value = max element value
                teamList.add((String) el.getKey()); // add team to list
            }
        }
        return teamList; // returns teamList
    }
}
