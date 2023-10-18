import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        ArrayList<ArrayList<String>> matchData = new ArrayList<>();
        //id,season,city,date,team1,team2,toss_winner,toss_decision,result,dl_applied,winner,win_by_runs,win_by_wickets,player_of_match,venue,umpire1,umpire2,umpire3
        //0     1   2       3   4     5         6           7         8        9        10       11           12                13              14      15        16
        Data data = new Data();
        data.matchesData(matchData);
        Questions q = new Questions();


        //1st question
        q.matchesPerYear(matchData);


        //2nd question
        q.matchesWonOfAllTeamsOverAllYears(matchData);

        //match_id,inning,batting_team,bowling_team,over,ball,batsman,non_striker,bowler,is_super_over,wide_runs,bye_runs,legbye_runs,noball_runs,penalty_runs,batsman_runs,extra_runs,total_runs,player_dismissed,dismissal_kind,fielder
        //   0       1       2              3        4     5     6           7       8        9            10       11           12         13           14       15            16          17          18              19          20
        ArrayList<ArrayList<String>> deliveryData = new ArrayList<>();
        data.deliveriesDataMethod(deliveryData);


        //3rd question
        int startIdOf2016 = startIndexeOfYear(matchData,"2016");
        int endIdOf2016 = endIndexeOfYear(matchData,"2016");
        q.extrasFor2016forAll(deliveryData,startIdOf2016,endIdOf2016);


        //4th question
        int startIdOf2015 = startIndexeOfYear(matchData,"2015");
        int endIdOf2015 = endIndexeOfYear(matchData,"2015");
        q.topEconomicalBowler2015(deliveryData,startIdOf2015,endIdOf2015);


    }

    static int startIndexeOfYear(ArrayList<ArrayList<String>> matchData,String year){
        int start = 0;
        for(ArrayList<String> l:matchData){
            if(l.get(1).equals(year)){
                start = Integer.parseInt(l.get(0));
                break;
            }
        }
        return  start;
    }
    static int endIndexeOfYear(ArrayList<ArrayList<String>> matchData,String year){
        int end = 0;
        for(ArrayList<String> l:matchData){
            if(l.get(1).equals(year)){
                end = Integer.parseInt(l.get(0));
            }
        }
        return  end;
    }

}