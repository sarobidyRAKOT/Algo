package utils;

import java.util.ArrayList;
import java.util.HashMap;

public class DNS {
    
    HashMap <String, ArrayList<String>> allsites;

    public DNS () {
        this.allsites = new HashMap<>();
    }

    public void ajouter_site (String site, String adrs_IP) {

        ArrayList <String> list = new ArrayList<>();
        if (allsites.keySet().contains(site)) {
            // allsites.keySet().add(site);
            list = allsites.get(site);
        } 
        list.add(adrs_IP);
        allsites.put(site, list);
    }

    public String[] get_allSITES () {
        String[] all = new String[allsites.keySet().size()];

        int i = 0;
        for (String site : allsites.keySet()) {
            all[i] = site;
            ++ i;
        }
        return all;
    }

    public HashMap<String, ArrayList <String>> getAllsites() { return allsites; }
}
