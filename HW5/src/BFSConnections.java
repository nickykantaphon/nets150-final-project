import java.util.*; // imports

public class BFSConnections { // class
    
    /**
     * Prints out the minimum number of links between two players and also
     * how those two players are connected.
     * 
     * @param adjMatrix adjacency matrix of graph
     * @param nodeList list of node names
     * @param nodeType list of node types
     * @param src name of first player
     * @param tgt name of second player
     */
    public static void shortestPath(int[][] adjMatrix, List<String> nodeList, List<String> nodeType, 
            String src, String tgt) {

        if (!containsPlayer(nodeList, src) || !containsPlayer(nodeList, tgt)) {
            throw new NoSuchElementException("Player(s) do not exist.");
        }
        
        int s = getIndexOf(nodeList, src);
        int t = getIndexOf(nodeList, tgt);
        
        if (!DataExtraction.getNodeType().get(s).equals("n") || !DataExtraction.getNodeType().get(t).equals("n")) {
            throw new IllegalArgumentException("Input must be a player name.");
        }
        
        Integer[] parent = bfs(adjMatrix, nodeList, nodeType, s);
        
        LinkedList<Integer> path = getParents(parent, s, t);
        
        if (path.isEmpty()) {
            System.out.println("There is no connection between the two players");
        } else {        
            System.out.println(nodeList.get(path.getFirst()) + " and " + nodeList.get(path.getLast()) + 
                    " are connected by " + (path.size() - 2) + " link(s).");
            System.out.println("\nTheir connection is given by the following players/teams/hometowns/colleges:");
            
            for (Integer i : path) {
                System.out.print(DataExtraction.getNodeList().get(i));
                if (path.getLast() != i) {
                    System.out.print(" - ");
                }
            }
        }
    }
    
    /**
     * Searches the list of nodes for a player with 
     * case insensitive searching.
     * 
     * @param nodeList list of nodes
     * @param player player to search for 
     * @return true if player exists, false otherwise
     */
    private static boolean containsPlayer(List<String> nodeList, String player) {
        for (String p : nodeList) {
            if (p.equalsIgnoreCase(player)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Gets the index of a player in the list of nodes with
     * case insensitive searching.
     * 
     * @param nodeList list of nodes
     * @param player player to get index of 
     * @return index of player or -1 if the player does not exist
     */
    private static int getIndexOf(List<String> nodeList, String player) {
        int idx = 0;
        for (String p : nodeList) {
            if (p.equalsIgnoreCase(player)) {
                return idx;
            } else {
                idx++;
            }
        }
        return -1;
    }
    
    /**
     * BFS algorithm starting from a source vertex s.
     * 
     * @param adjMatrix adjacency matrix of graph to search
     * @param nodeList list of node names
     * @param nodeType list of node types
     * @param s index of source vertex
     * @return an integer array keeping track of each vertex's parent 
     *         in the resulting BFS traversal where the array indices correspond
     *         to the index of each node in the nodeList 
     */
    private static Integer[] bfs(int[][] adjMatrix, List<String> nodeList, 
            List<String> nodeType, int s) {
        int n = adjMatrix.length;
        Integer[] discovered = new Integer[n];
        Integer[] parent = new Integer[n];
        LinkedList<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            discovered[i] = 0;
            parent[i] = null;
        }
        
        queue.add(s);
        discovered[s] = 1;
        
        while (queue.size() != 0) {
            int v = queue.remove();
            Set<Integer> neighbors = getNeighbors(adjMatrix, nodeList, nodeType, v);
            
            for (Integer u : neighbors) {
                if (discovered[u] == 0) {
                    discovered[u] = 1;
                    queue.addLast(u);
                    parent[u] = v;
                }
            }
        }        
        return parent;
    }
    
    /**
     * Helper function for BFS algorithm.
     * Gets the neighbors of a specific vertex.
     * 
     * @param adjMatrix adjacency matrix of graph
     * @param nodeList list of node names
     * @param nodeType list of node types
     * @param v vertex to get neighbors of
     * @return set of integers containing the indices of 
     *         the neighboring nodes
     */
    private static Set<Integer> getNeighbors(int[][] adjMatrix, List<String> nodeList, 
            List<String> nodeType, int v) {
        Set<Integer> neighbors = new HashSet<>();
                
        for (int i = 0; i < adjMatrix[v].length; i++) {
            if (adjMatrix[v][i] == 1) {
                neighbors.add(i);
            }
        }       
        return neighbors;
    }    
    
    /**
     * Recursively gets the shortest path from a source to target
     * vertex by backtracking the parent array returned by the BFS algorithm
     * (recursive function implemented in getParentsHelper).
     * 
     * @param parent parent array
     * @param src source vertex
     * @param tgt target vertex
     * @return list of integers containing the indices of the vertex
     *         in the shortest path (includes source and target vertex);
     *         empty list returned if no path exists
     */
    private static LinkedList<Integer> getParents(Integer[] parent, int src, int tgt) {
        LinkedList<Integer> path = new LinkedList<>();
        path = getParentsHelper(parent, path, src, tgt);     
        return path;
    }
    

    private static LinkedList<Integer> getParentsHelper(Integer[] parent, 
            LinkedList<Integer> path, int src, int u) {
        if (parent[u] == null) {
            if (u == src) {
                path.addFirst(u);
                return path;
            } else {
                return new LinkedList<Integer>();
            }
        } else {   
            path.addFirst(u);    
            getParentsHelper(parent, path, src, parent[u]);
            
            return path;
        }
    }
}
