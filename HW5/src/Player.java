public class Player {
    private String name;
    private String team;
    private String hometown;
    private String college;
    
    public Player(String n, String t, String h, String c) {
        name = n;
        team = t;
        hometown = h;
        college = c;
    }
    
    public String getName() {
        return name;
    }
    
    public String getTeam() {
        return team;
    }
    
    public String getHometown() {
        return hometown;
    }
    
    public String getCollege() {
        return college;
    }
}
