import java.util.List;

public class ParserMain {
    public static void main(String[] args) {
        DataExtraction o = new DataExtraction();
        
        // Adjacency matrix (1 = edge, 0 = no edge)
        int[][] adjMatrix = o.getAdjMatrix();
        
        // The String of all indices in adjMatrix
        List<String> nodeList = o.getNodeList();
        
        /*
         * The type of all indices in adjMatrix
         * "n": player's name
         * "t": team
         * "h": home town/ birthplace
         * "c": college
         */
        List<String> nodeType = o.getNodeType();
        
        // Examples
        int indexOfCurry = nodeList.indexOf("Stephen Curry");
        System.out.println("Stephen Curry has index of " + indexOfCurry);
        // Find his team
        String CurryTeam = "";
        for (int i = 0; i < adjMatrix[indexOfCurry].length; i++) {
            if (adjMatrix[indexOfCurry][i] == 1 && nodeType.get(i).equals("t")) {
                CurryTeam = nodeList.get(i);
                System.out.println("Stephen Curry plays for " + nodeList.get(i));
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
}
