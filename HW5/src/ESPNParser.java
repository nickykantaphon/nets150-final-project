import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ESPNParser {
    private String baseURL; 
    private Document doc;
    public List<String> rosterLinkList;
    public Map<String, String> linkMap;
    
    /**
     * Constructor that initializes the base URL and loads 
     * the document produced from that URL
     */
    public ESPNParser(String url) {
        this.baseURL = url;
        try {
            this.doc = Jsoup.connect(this.baseURL).get();
//            System.out.println(this.doc);
        } catch (IOException e) {
//            System.out.println("Could not get the ESPN page");
        }
    }
    
    /**
     * Get all the links from the page and store them in the linkMap
     */
    public void getLinks() {
        this.linkMap = new HashMap<String, String>();
        Elements links = doc.select("a[href]");
        for (Element link : links) {
            String linkURL = link.absUrl("href");
//            System.out.println("\nlink: " + linkURL);
            String linkTitle = link.text();
//            System.out.println("text: " + linkTitle);
            this.linkMap.put(linkTitle, linkURL);
        }
    }
    
    public Map<String, String> getMap() {
        return linkMap;
    }
    
    /**
     * Get link from a text (keyword)
     * @param text
     * @return URL of the link
     */
    public String getLink(String text) {
        getLinks();
        String URL = linkMap.get(text);
        return URL; 
    }
    
    /**
     * Get all the links of teams' rosters from the page and store them in the linkList
     */
    public void getRosterLinks() {
        this.rosterLinkList = new LinkedList<String>();
        Elements links = doc.select("a[href]");
        for (Element link : links) {
            String linkURL = link.absUrl("href");
            String linkTitle = link.text();
            if (linkTitle.equals("Roster")) {
                rosterLinkList.add(linkURL);
            }
        }
    }
    
    public List<String> getRosterList() {
        getRosterLinks();
        return rosterLinkList;
    }
    
    /**
     * Get all lists from the page using CSS selector
     */
    public Elements getLists(String selector) {
        Elements lists = doc.select(selector);
        return lists;
    }
    
    /**
     * Get the whole document of WikiParser
     * @return the document
     */
    public Document getDoc() {
        return doc;
    }
}