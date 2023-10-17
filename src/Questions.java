import java.util.*;

public class Questions {
    //id,season,city,date,team1,team2,toss_winner,toss_decision,result,dl_applied,winner,win_by_runs,win_by_wickets,player_of_match,venue,umpire1,umpire2,umpire3
    //0     1   2       3   4     5         6           7         8        9        10       11           12                13              14      15        16
    void matchesPerYear(ArrayList<ArrayList<String>> matchData){
        TreeMap<String,Integer> mapOfMatchPerYear = new TreeMap<>();
        for(ArrayList<String> l : matchData){
            mapOfMatchPerYear.put(l.get(1),mapOfMatchPerYear.getOrDefault(l.get(1),0)+1);
        }
//        System.out.println(mapOfMatchPerYear);
        for(String s:mapOfMatchPerYear.keySet()){
            System.out.println("For year "+s+" Matches played are : "+mapOfMatchPerYear.get(s));
        }
    }
    void matchesWonOfAllTeamsOverAllYears(ArrayList<ArrayList<String>> matchData){
        HashMap<String,Integer> matchOfAllPerAllYear = new HashMap<>();
        for(ArrayList<String> l : matchData){
            if(l.get(10)!="") {
                matchOfAllPerAllYear.put(l.get(10), matchOfAllPerAllYear.getOrDefault(l.get(10), 0) + 1);
            }
        }
//        System.out.println(matchOfAllPerAllYear);
        ArrayList<Map.Entry<String,Integer>> sortedList = new ArrayList<>(matchOfAllPerAllYear.entrySet());
        Collections.sort(sortedList,(o1,o2)->{
            return o1.getValue().compareTo(o2.getValue());
        });
//        for(String s:matchOfAllPerAllYear.keySet()){
//            System.out.println("Matches won by "+s+" over All Years is : "+matchOfAllPerAllYear.get(s));
//        }
//        System.out.println(sortedList);
        sortedList.forEach(stringIntegerEntry -> {
            System.out.println("Matches won by "+stringIntegerEntry.getKey()+" over All Years is : "+stringIntegerEntry.getValue());
        });
    }
}
