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
//        q.matchesPerYear(matchData);
        q.matchesWonOfAllTeamsOverAllYears(matchData);

        //match_id,inning,batting_team,bowling_team,over,ball,batsman,non_striker,bowler,is_super_over,wide_runs,bye_runs,legbye_runs,noball_runs,penalty_runs,batsman_runs,extra_runs,total_runs,player_dismissed,dismissal_kind,fielder
        //   0       1       2              3        4     5     6           7       8        9            10       11           12         13           14       15            16          17          18              19          20

    }


}