import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.select.Elements;

public class DataExtraction {
    
    // List of player objects scraped from ESPN
    private static List<Player> playerList = new LinkedList<Player>();
    
    /**
     * The index of three data structures below is correspondent with one another. For example: 
     * Index 0 of nodesString contains the name of vertex of index 0 in adjMatrix. Index 0 of 
     * nodesType contains a type of a vertex: "n" (name), "t" (team), "h" (home town), "c" (college).
     */
    // List of all vertices in the matrix
    private static List<String> nodesString = new LinkedList<String>();
    // List of the type of vertices in the matrix: 
    private static List<String> nodesType = new LinkedList<String>();
    // Adjacency Matrix
    private static int[][] adjMatrix;

    public static void scrape() {
        // Scrapes ESPN for data
        ESPNParser pageparse = new ESPNParser("https://www.espn.com/nba/teams");
        List<String> teamURLList = pageparse.getRosterList();
        List<String> allPlayerLinks = new LinkedList<String>();
        for (String e : teamURLList) {
            ESPNParser teamparser = new ESPNParser(e);
            teamparser.getLinks();
            Map<String,String> linkMap = teamparser.getMap();
            for (Map.Entry<String,String> entry : linkMap.entrySet() ) {
                if (entry.getValue().contains("/player/") && !entry.getKey().equals("")) {
                    String s = entry.getValue();
                    String[] arr = s.split("/");
                    String[] newArr = new String[arr.length+1];
                    for (int i = 0; i < 5; i++) {
                        newArr[i] = arr[i];
                    }
                    newArr[5] = "bio";
                    for (int i = 5; i < arr.length; i++) {
                        newArr[i+1] = arr[i];
                    }
                    String newStr = "";
                    for (int i = 0; i < newArr.length; i++) {
                        newStr = newStr + newArr[i] + "/";
                    }
                    newStr = newStr.substring(0,newStr.length()-1);
                    allPlayerLinks.add(newStr);
                    ESPNParser playerparse = new ESPNParser(newStr);
                    Elements es = playerparse.getDoc().select("div[class=Wrapper Card__Content] "
                            + "span[class=dib flex-uniform mr3 clr-gray-01], "
                            + "div[class=Wrapper Card__Content] "
                            + "span[class=Bio__Label ttu mr2 dib clr-gray-04]");
             
                    
                    Map<String, String> playerMap = new HashMap <String,String>();
                    for (int i = 0; i < es.size(); i+=2) {
                        playerMap.put(es.get(i).text(), es.get(i+1).text());
                    }
                    
                    String t = playerMap.get("Team");
                    // add to node list
                    if (t != null && !nodesString.contains(t)) {
                        nodesString.add(t);
                        nodesType.add("t");
                    }
                    
                    String h;
                    if (playerMap.containsKey("Birthplace")) {
                        h = playerMap.get("Birthplace");
                    } else {
                        h = null;
                    }
                    // add to node list
                    if (h != null && !nodesString.contains(h)) {
                        nodesString.add(h);
                        nodesType.add("h");
                    }
                    
                    String c;
                    if (playerMap.containsKey("College")) {
                        c = playerMap.get("College");
                    } else {
                        c = null;
                    }
                    // add to node list
                    if (c != null && !nodesString.contains(c)) {
                        nodesString.add(c);
                        nodesType.add("c");
                    }
                    
                    String firstName = playerparse.getDoc().select("span["
                            + "class=truncate min-w-0 fw-light]").text();
                    String lastName = playerparse.getDoc().select("span"
                            + "[class=truncate min-w-0]").text();
                    String n = firstName + " " + lastName;
                    if (!n.equals(null) && !nodesString.contains(n)) {
                        nodesString.add(n);
                        nodesType.add("n");
                    }
                    
                    Player player = new Player(n,t,h,c);
                    playerList.add(player);
//                    System.out.println("Name: " + n);
//                    System.out.println("Team: " + t);
//                    System.out.println("Hometown: " + h);
//                    System.out.println("College: " + c);
//                    System.out.println();
                }               
            }                     
        }
                
        // Create an adjacency matrix
        adjMatrix = new int[nodesString.size()][nodesString.size()];
        for (int i = 0; i < playerList.size(); i++) {
            Player pl = playerList.get(i);
            String n = pl.getName();
            String t = pl.getTeam();
            String h = pl.getHometown();
            String c = pl.getCollege();
            
            if (n != null && t != null) {
                adjMatrix[nodesString.indexOf(n)][nodesString.indexOf(t)] = 1;
                adjMatrix[nodesString.indexOf(t)][nodesString.indexOf(n)] = 1;
//                System.out.println(n + "-" + t);
            }     
            if (n != null && h != null) {
                adjMatrix[nodesString.indexOf(n)][nodesString.indexOf(h)] = 1;
                adjMatrix[nodesString.indexOf(h)][nodesString.indexOf(n)] = 1;
//                System.out.println(n + "-" + h);
            }  
            if (n != null && c != null) {
                adjMatrix[nodesString.indexOf(n)][nodesString.indexOf(c)] = 1;
                adjMatrix[nodesString.indexOf(c)][nodesString.indexOf(n)] = 1;
//                System.out.println(n + "-" + c);
            }                 
        }
        
        System.out.println("Data Extraction Completed.");
        
        save();
    }
    
    public static List<Player> getPlayers() {
        return playerList;
    }
    
    public static List<String> getNodeList() {
        return nodesString;
    }
    
    public static List<String> getNodeType() {
        return nodesType;
    }
    
    public static int[][] getAdjMatrix() {
        return adjMatrix;
    }
    
    public static void load() {
        File file = Paths.get("data.txt").toFile();
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
                nodesString.add(nodesSplit[i]);
            }
            
            // load node type
            String type = br.readLine();
            String[] typeSplit = type.split("/");
            
            for (int i = 0; i < typeSplit.length; i++) {
                nodesType.add(typeSplit[i]);
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
    
    private static boolean save() {
        File file = Paths.get("data.txt").toFile();
        BufferedWriter bw = null;
        
        try {
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            
            bw.write(String.valueOf(adjMatrix.length));
            bw.newLine();
            
            // save matrix
            for (int row = 0; row < adjMatrix.length; row++) {
                for (int col = 0; col < adjMatrix[row].length; col++) {
                    bw.write("" + adjMatrix[row][col]);
                }
                bw.newLine();
            }
            
            // save player list
            for (Player p : playerList) {
                bw.write(p.getName() + "/");
                bw.write(p.getTeam() + "/");
                bw.write(p.getHometown() + "/");
                bw.write(p.getCollege() + "/");
            }
            bw.newLine();
            
            // save node list
            for (String s : nodesString) {
                bw.write(s + "/");
            }
            bw.newLine();
            
            // save node type
            for (String s : nodesType) {
                bw.write(s + "/");
            }               
        } catch (IOException e) {
            return false;
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                return false;
            }
        }
        return true;       
    }  
}