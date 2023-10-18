import java.util.*;

public class Questions {
    //id,season,city,date,team1,team2,toss_winner,toss_decision,result,dl_applied,winner,win_by_runs,win_by_wickets,player_of_match,venue,umpire1,umpire2,umpire3
    //0     1   2       3   4     5         6           7         8        9        10       11           12                13              14      15        16

    void matchesPerYear(ArrayList<ArrayList<String>> matchData){
        TreeMap<String,Integer> mapOfMatchPerYear = new TreeMap<>();
        for(ArrayList<String> l : matchData){
            mapOfMatchPerYear.put(l.get(1),mapOfMatchPerYear.getOrDefault(l.get(1),0)+1);
        }
        for(String s:mapOfMatchPerYear.keySet()){
            System.out.println("For year "+s+" Matches played are : "+mapOfMatchPerYear.get(s));
        }
        System.out.println();
    }


    void matchesWonOfAllTeamsOverAllYears(ArrayList<ArrayList<String>> matchData){
        HashMap<String,Integer> matchOfAllPerAllYear = new HashMap<>();
        for(ArrayList<String> l : matchData){
            if(!l.get(10).isEmpty()) {
                matchOfAllPerAllYear.put(l.get(10), matchOfAllPerAllYear.getOrDefault(l.get(10), 0) + 1);
            }
        }
        ArrayList<Map.Entry<String,Integer>> sortedList = new ArrayList<>(matchOfAllPerAllYear.entrySet());
        Collections.sort(sortedList,(o1,o2)->{
            return o1.getValue().compareTo(o2.getValue());
        });
        sortedList.forEach(stringIntegerEntry -> {
            System.out.println("Matches won by "+stringIntegerEntry.getKey()+" over All Years is : "+stringIntegerEntry.getValue());
        });
        System.out.println();
    }


    //match_id,inning,batting_team,bowling_team,over,ball,batsman,non_striker,bowler,is_super_over,wide_runs,bye_runs,legbye_runs,noball_runs,penalty_runs,batsman_runs,extra_runs,total_runs,player_dismissed,dismissal_kind,fielder
    //   0       1       2              3        4     5     6           7       8        9            10       11           12         13           14       15            16          17          18              19          20
    void extrasFor2016forAll(ArrayList<ArrayList<String>> deliveryData,int start,int end){
        HashMap<String,Integer> hashMap = new HashMap<>();
        for(ArrayList<String> list:deliveryData){
            if(Integer.parseInt(list.get(0))>=start && Integer.parseInt(list.get(0))<=end) {
                hashMap.put(list.get(3), hashMap.getOrDefault(list.get(3), 0) + Integer.parseInt(list.get(16)));
            }
        }
        for(String s:hashMap.keySet()){
            System.out.println(s+" conceded Extra runs for 2016 :"+hashMap.get(s));
        }
        System.out.println();
    }

    void  topEconomicalBowler2015(ArrayList<ArrayList<String>> deliveryData,int start,int end){
        HashMap<String,Double> hashMapRuns = new HashMap<>();
        for(ArrayList<String> list:deliveryData){
            if(Integer.parseInt(list.get(0))>=start && Integer.parseInt(list.get(0))<=end) {
                hashMapRuns.put(list.get(8), hashMapRuns.getOrDefault(list.get(8), 0.0) + Integer.parseInt(list.get(17)));
            }
        }
        HashMap<String,Double> hashMapNoOfDeliveries = new HashMap<>();
        for(ArrayList<String> list:deliveryData){
            if(Integer.parseInt(list.get(0))>=start && Integer.parseInt(list.get(0))<=end) {
                hashMapNoOfDeliveries.put(list.get(8), hashMapNoOfDeliveries.getOrDefault(list.get(8), 0.0) + 1);
            }
        }
        //economy = (runs_conceded)/(overs)  over = (balls/6)
        HashMap<String,Double> hashMapEconomy = new HashMap<>();
        for(String s:hashMapRuns.keySet()){
            double d1 = hashMapNoOfDeliveries.get(s)/6;
            double d2 = hashMapRuns.get(s)/d1;
            hashMapEconomy.put(s,d2);
        }
        ArrayList<Map.Entry<String,Double>> sortedList = new ArrayList<>(hashMapEconomy.entrySet());
        Collections.sort(sortedList,(o1,o2)->{
            return o1.getValue().compareTo(o2.getValue());
        });
        for(int i=0;i<10;i++){
            System.out.print(sortedList.get(i).getKey()+" ");
            System.out.printf(" with economy %.3f",sortedList.get(i).getValue());
            System.out.println();
        }
        System.out.println();
    }

    void orangeCap(ArrayList<ArrayList<String>> deliveryData,int start,int end,int year){
        HashMap<String,Integer> runsMap = new HashMap<>();
        int runs = 0;
        String s = "";
        for(ArrayList<String> l:deliveryData){
            if(Integer.parseInt(l.get(0))>=start && Integer.parseInt(l.get(0))<=end){
                    runsMap.put(l.get(6), runsMap.getOrDefault(l.get(6), 0) + Integer.parseInt(l.get(15)));
                    if (runsMap.get(l.get(6)) > runs) {
                        runs = runsMap.get(l.get(6));
                        s = l.get(6);
                    }
            }
        }
        System.out.println("Orange cap for the year "+year+" goes to : "+s+" runs: "+runs);
    }


    void purpleCap(ArrayList<ArrayList<String>> deliveryData,int start,int end,int year){
        HashMap<String,Integer> wicketsMap = new HashMap<>();
        int wickets = 0;
        String s = "";
        for(ArrayList<String> l:deliveryData){
            if(l.size()>19 && Integer.parseInt(l.get(0))>=start && Integer.parseInt(l.get(0))<=end){
                if(l.get(19).equals("caught") || l.get(19).equals("bowled") || l.get(19).equals("lbw") || l.get(19).equals("caught and bowled") || l.get(19).equals("stumped")) {
                    wicketsMap.put(l.get(8), wicketsMap.getOrDefault(l.get(8), 0) + 1);
                    if (wicketsMap.get(l.get(8)) > wickets) {
                        wickets = wicketsMap.get(l.get(8));
                        s = l.get(8);
                    }
                }
            }
        }
        System.out.println("Purple cap for the year "+year+" goes to : "+s+" wickets: "+wickets);
    }


    void leadingRunScorer(ArrayList<ArrayList<String>> deliveryData){
        HashMap<String,Integer> runsScored = new HashMap<>();
        for(ArrayList<String> l:deliveryData){
            runsScored.put(l.get(6),runsScored.getOrDefault(l.get(6),0)+Integer.parseInt(l.get(15)));
        }
        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<>(runsScored.entrySet());
        Collections.sort(list,(o1,o2)->{
            return o2.getValue().compareTo(o1.getValue());
        });
        for(int i=0;i<10;i++){
            System.out.println(list.get(i).getKey()+" runs : "+list.get(i).getValue());
        }
    }

    void leadingWicketTaker(ArrayList<ArrayList<String>> deliveryData){
        HashMap<String,Integer> wickets = new HashMap<>();
        for(ArrayList<String> l:deliveryData){
            if(l.size()>19) {
                if (l.get(19).equals("caught") || l.get(19).equals("bowled") || l.get(19).equals("lbw") || l.get(19).equals("caught and bowled") || l.get(19).equals("stumped")) {
                    wickets.put(l.get(8), wickets.getOrDefault(l.get(8), 0) + 1);
                }
            }
        }
        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<>(wickets.entrySet());
        Collections.sort(list,(o1,o2)->{
            return o2.getValue().compareTo(o1.getValue());
        });
        for(int i=0;i<10;i++){
            System.out.println(list.get(i).getKey()+" wickets : "+list.get(i).getValue());
        }
    }


    void fifties(ArrayList<ArrayList<String>> deliveryData){
        HashMap<String,Integer> total = new HashMap<>();
        for(int i=1;i<=636;i++){
            HashMap<String,Integer> runsPerMatch = new HashMap<>();
            for(ArrayList<String> l:deliveryData){
                if(l.get(0).equals(Integer.toString(i))){
                    runsPerMatch.put(l.get(6),runsPerMatch.getOrDefault(l.get(6),0)+Integer.parseInt(l.get(15)));
                }
                if(Integer.parseInt(l.get(0))>((i))){
                    break;
                }
            }
            for(String x:runsPerMatch.keySet()){
                if(runsPerMatch.get(x)>=50){
                    total.put(x,total.getOrDefault(x,0)+1);
                }
            }
        }
        ArrayList<Map.Entry<String,Integer>> l = new ArrayList<>(total.entrySet());
        Collections.sort(l,(o1,o2)->{
            return o2.getValue().compareTo(o1.getValue());
        });
        for(int i=0;i<10;i++){
            System.out.println(l.get(i).getKey()+" fifties : "+l.get(i).getValue());
        }
    }
}
