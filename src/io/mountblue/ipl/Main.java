package io.mountblue.ipl;

import java.io.*;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static final int MATCH_ID = 0;
    public static final int MATCH_SEASON = 1;
    public static final int MATCH_CITY = 2;
    public static final int MATCH_DATE = 3;
    public static final int MATCH_TEAM1 = 4;
    public static final int MATCH_TEAM2 = 5;
    public static final int MATCH_TOSS_WINNER = 6;
    public static final int MATCH_TOSS_DECISION = 7;
    public static final int MATCH_RESULT = 8;
    public static final int MATCH_DL_APPLIED = 9;
    public static final int MATCH_WINNER = 10;
    public static final int MATCH_WIN_BY_RUNS = 11;
    public static final int MATCH_WIN_BY_WICKETS = 12;
    public static final int MATCH_PLAYER_OF_THE_MATCH = 13;
    public static final int MATCH_VENUE = 14;
    public static final int MATCH_UMPIRE1 = 15;
    public static final int MATCH_UMPIRE2 = 16;
    public static final int MATCH_UMPIRE3 = 17;
    public static final int DELIVERY_ID = 0;
    public static final int DELIVERY_INNING = 1;
    public static final int DELIVERY_BATTING_TEAM = 2;
    public static final int DELIVERY_BOWLING_TEAM = 3;
    public static final int DELIVERY_OVER = 4;
    public static final int DELIVERY_BALL = 5;
    public static final int DELIVERY_BATSMEN = 6;
    public static final int DELIVERY_NON_STRIKER = 7;
    public static final int DELIVERY_BOWLER = 8;
    public static final int DELIVERY_SUPER_OVER = 9;
    public static final int DELIVERY_WIDE_RUNS = 10;
    public static final int DELIVERY_BYE_RUNS = 11;
    public static final int DELIVERY_LEG_BYE_RUNS = 12;
    public static final int DELIVERY_NO_BALL_RUNS = 13;
    public static final int DELIVERY_PENALTY_RUNS = 14;
    public static final int DELIVERY_BATSMAN_RUNS = 15;
    public static final int DELIVERY_EXTRA_RUNS = 16;
    public static final int DELIVERY_TOTAL_RUNS = 17;
    public static final int DELIVERY_PLAYER_DISMISSED = 18;
    public static final int DELIVERY_DISMISSED_KIND = 19;
    public static final int DELIVERY_FIELDER = 20;

    public static void main(String[] args) {
        ArrayList<Match> matches = getMatchesData();

        // 1st question
        System.out.println("1. Matches Conducted Per Year ");
        getMatchesPerYear(matches);

        // 2nd question
        System.out.println("2. Matches won by All Teams over All Years ");
        getMatchesWonOfAllTeamsOverAllYears(matches);

        ArrayList<Delivery> deliveries = getDeliveriesData();

        // 3rd question
        System.out.println("3. Extra runs conceded by All Teams in 2016 ");
        int startIdOf2016 = getStartIndexOfYear(matches,"2016");
        int endIdOf2016 = getEndIndexOfYear(matches,"2016");
        getExtrasFor2016ByAllTeams(deliveries,startIdOf2016,endIdOf2016);

        // 4th question
        System.out.println("4. Top Economical Bowlers of 2015 ");
        int startIdOf2015 = getStartIndexOfYear(matches,"2015");
        int endIdOf2015 = getEndIndexOfYear(matches,"2015");
        getTopEconomicalBowler2015(deliveries,startIdOf2015,endIdOf2015);

        // 5th question orange cap;
        System.out.println("5. Orange Cap for All Years ");
        for(int i=2008;i<=2017;i++){
            int startOfYear = getStartIndexOfYear(matches,Integer.toString(i));
            int endOfYear = getEndIndexOfYear(matches,Integer.toString(i));
            getOrangeCapHolder(deliveries,startOfYear,endOfYear,i);
        }
        System.out.println();

        // 6th question purple cap;
        System.out.println("6. Purple cap for All Years ");
        for(int i = 2008; i <= 2017; i++){
            int startOfYear = getStartIndexOfYear(matches,Integer.toString(i));
            int endOfYear = getEndIndexOfYear(matches,Integer.toString(i));
            getPurpleCapHolder(deliveries,startOfYear,endOfYear,i);
        }
        System.out.println();

        // 7th question Leading run scorers of IPL
        System.out.println("7. Leading Run Scorers of IPL ");
        getLeadingRunScorersOfIPL(deliveries);
        System.out.println();

        // 8th question Leading wicket takers of IPL
        System.out.println("8. Leading Wicket Takers of IPL ");
        getLeadingWicketTakersOfIPL(deliveries);
        System.out.println();

        // 9th Most 50's of IPL
        System.out.println("9. Players with Most 50's in IPL");
        getMostFiftiesBattersOfIPL(deliveries);
        System.out.println();

        // 10th city with the highest inning Score for every Season
        System.out.println("10. City with highest inning Score for every Season");
        for(int i = 2008; i <= 2017; i++){
            int startOfYear = getStartIndexOfYear(matches,Integer.toString(i));
            int endOfYear = getEndIndexOfYear(matches,Integer.toString(i));
            getCityWithHighestInningScoreForEachYear(matches,deliveries,startOfYear,endOfYear,i);
        }
        System.out.println();

        // 11th question
        System.out.println("11. Venues to players to sixers ");
        getTopPlayersWithSixersAtEachVenue2016(deliveries,matches,startIdOf2016,endIdOf2016);
    }

    private static void getTopPlayersWithSixersAtEachVenue2016(ArrayList<Delivery> deliveries, ArrayList<Match> matches, int startIdOf2016, int endIdOf2016) {
        HashMap<String,HashMap<String,Integer>> venuesData = new HashMap<>();
        for(Match match:matches){
            if(Integer.parseInt(match.getId())>=startIdOf2016 && Integer.parseInt(match.getId())<=endIdOf2016) {
                for (Delivery delivery : deliveries) {
                    if((delivery.getId()).equals(match.getId())) {
                        if (venuesData.containsKey(match.getVenue())) {
                            HashMap<String, Integer> batsmanSixers = venuesData.get(match.getVenue());
                            if (delivery.getBatsman_runs().equals("6")) {
                                batsmanSixers.put(delivery.getBatsman(), batsmanSixers.getOrDefault(delivery.getBatsman(), 0) + 1);
                            }
                            venuesData.put(match.getVenue(), batsmanSixers);
                        } else {
                            HashMap<String, Integer> batsmanSixers = new HashMap<>();
                            if (delivery.getBatsman_runs().equals("6")) {
                                batsmanSixers.put(delivery.getBatsman(), 1);
                            }
                            venuesData.put(match.getVenue(), batsmanSixers);
                        }
                    }
                }
            }
        }
        for(String venue:venuesData.keySet()){
            HashMap<String,Integer> batsmanSixers = venuesData.get(venue);
            int maxSix = 0;
            String maxPlayer = "";
            String maxVenue = venue;
            for(String batsman:batsmanSixers.keySet()){
                if(batsmanSixers.get(batsman)>maxSix){
                    maxSix = batsmanSixers.get(batsman);
                    maxPlayer = batsman;
                }
            }
            System.out.println(maxVenue+" ---> "+maxPlayer+" ---> "+maxSix);
        }
//        System.out.println(venuesData);
    }

    private static ArrayList<Match> getMatchesData(){
        ArrayList<Match> matches = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("data/matches.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();

            String line;
            String[] tempArr;

            while((line = bufferedReader.readLine()) != null){
                tempArr = line.split(",");
                for(int i = 0; i < tempArr.length; i++){
                    if(tempArr[i].trim().equals("Rising Pune Supergiant")){
                        tempArr[i] = "Rising Pune Supergiants";
                    }
                }

                Match match = new Match();
                match.setId(tempArr[MATCH_ID]);
                match.setSeason(tempArr[MATCH_SEASON]);
                match.setCity(tempArr[MATCH_CITY]);
                match.setDate(tempArr[MATCH_DATE]);
                match.setTeam1(tempArr[MATCH_TEAM1]);
                match.setTeam2(tempArr[MATCH_TEAM2]);
                match.setToss_winner(tempArr[MATCH_TOSS_WINNER]);
                match.setToss_decision(tempArr[MATCH_TOSS_DECISION]);
                match.setResult(tempArr[MATCH_RESULT]);
                match.setDl_applied(tempArr[MATCH_DL_APPLIED]);
                match.setWinner(tempArr[MATCH_WINNER]);
                match.setWin_by_runs(tempArr[MATCH_WIN_BY_RUNS]);
                match.setWin_by_wickets(tempArr[MATCH_WIN_BY_WICKETS]);
                match.setPlayer_of_match(tempArr[MATCH_PLAYER_OF_THE_MATCH]);
                match.setVenue(tempArr[MATCH_VENUE]);
                if(tempArr.length > 15) {
                    match.setUmpire1(tempArr[MATCH_UMPIRE1]);
                }
                if(tempArr.length > 16) {
                    match.setUmpire2(tempArr[MATCH_UMPIRE2]);
                }
                if(tempArr.length > 17) {
                    match.setUmpire3(tempArr[MATCH_UMPIRE3]);
                }
                matches.add(match);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return matches;
    }

    private static ArrayList<Delivery> getDeliveriesData(){
        ArrayList<Delivery> deliveries = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("data/deliveries.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();

            String line;
            String[] tempArr;

            while ((line = bufferedReader.readLine()) != null){
                tempArr = line.split(",");
                for(int i = 0; i < tempArr.length; i++){
                    if(tempArr[i].trim().equals("Rising Pune Supergiant")){
                        tempArr[i] = "Rising Pune Supergiants";
                    }
                }

                Delivery delivery = new Delivery();
                delivery.setId(tempArr[DELIVERY_ID]);
                delivery.setInning(tempArr[DELIVERY_INNING]);
                delivery.setBatting_team(tempArr[DELIVERY_BATTING_TEAM]);
                delivery.setBowling_team(tempArr[DELIVERY_BOWLING_TEAM]);
                delivery.setOver(tempArr[DELIVERY_OVER]);
                delivery.setBall(tempArr[DELIVERY_BALL]);
                delivery.setBatsman(tempArr[DELIVERY_BATSMEN]);
                delivery.setNon_striker(tempArr[DELIVERY_NON_STRIKER]);
                delivery.setBowler(tempArr[DELIVERY_BOWLER]);
                delivery.setIs_super_over(tempArr[DELIVERY_SUPER_OVER]);
                delivery.setWide_runs(tempArr[DELIVERY_WIDE_RUNS]);
                delivery.setBye_runs(tempArr[DELIVERY_BYE_RUNS]);
                delivery.setLegbye_runs(tempArr[DELIVERY_LEG_BYE_RUNS]);
                delivery.setNoball_runs(tempArr[DELIVERY_NO_BALL_RUNS]);
                delivery.setPenalty_runs(tempArr[DELIVERY_PENALTY_RUNS]);
                delivery.setBatsman_runs(tempArr[DELIVERY_BATSMAN_RUNS]);
                delivery.setExtra_runs(tempArr[DELIVERY_EXTRA_RUNS]);
                delivery.setTotal_runs(tempArr[DELIVERY_TOTAL_RUNS]);
                if(tempArr.length > 18){
                    delivery.setPlayer_dismissed(tempArr[DELIVERY_PLAYER_DISMISSED]);
                }
                if(tempArr.length > 19){
                    delivery.setDismissal_kind(tempArr[DELIVERY_DISMISSED_KIND]);
                }
                if(tempArr.length >  20){
                    delivery.setFielder(tempArr[DELIVERY_FIELDER]);
                }
                deliveries.add(delivery);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return deliveries;
    }

    private static void getMatchesPerYear(ArrayList<Match> matches){
        TreeMap<String,Integer> matchPerYear = new TreeMap<>();
        for(Match match : matches){
            matchPerYear.put(match.getSeason(),matchPerYear.getOrDefault(match.getSeason(),0)+1);
        }

        for(String year : matchPerYear.keySet()){
            System.out.println("For year "+year+" Matches played are : "+matchPerYear.get(year));
        }
        System.out.println();
    }

    private static void getMatchesWonOfAllTeamsOverAllYears(ArrayList<Match> matches){
        HashMap<String,Integer> matchesWonByAllTeamsForAllYear = new HashMap<>();
        for(Match match : matches){
            if(!match.getWinner().isEmpty()) {
                matchesWonByAllTeamsForAllYear.put(match.getWinner(),
                        matchesWonByAllTeamsForAllYear.getOrDefault(match.getWinner(), 0) + 1);
            }
        }

        for(String team : matchesWonByAllTeamsForAllYear.keySet()){
            System.out.println(team+" ---> "+matchesWonByAllTeamsForAllYear.get(team));
        }
        System.out.println();
    }

    private static void getExtrasFor2016ByAllTeams(ArrayList<Delivery> deliveries, int start, int end){
        HashMap<String,Integer> extrasPerTeam = new HashMap<>();
        for(Delivery delivery : deliveries){
            if(Integer.parseInt(delivery.getId()) >= start && Integer.parseInt(delivery.getId()) <= end) {
                extrasPerTeam.put(delivery.getBowling_team(),
                        extrasPerTeam.getOrDefault(delivery.getBowling_team(), 0)
                                + Integer.parseInt(delivery.getExtra_runs()) );
            }
        }

        for(String team : extrasPerTeam.keySet()){
            System.out.println(team+" ---> "+extrasPerTeam.get(team));
        }
        System.out.println();
    }

    private static void getTopEconomicalBowler2015(ArrayList<Delivery> deliveries, int start, int end){
        HashMap<String,Double> runsConcededPerBowler = new HashMap<>();
        for(Delivery delivery : deliveries){
            if(Integer.parseInt(delivery.getId()) >= start && Integer.parseInt(delivery.getId()) <= end) {
                runsConcededPerBowler.put(delivery.getBowler(),
                        runsConcededPerBowler.getOrDefault(delivery.getBowler(), 0.0)
                                + (double)(Integer.parseInt(delivery.getTotal_runs())
                                - Integer.parseInt(delivery.getBye_runs())
                                - Integer.parseInt(delivery.getLegbye_runs())
                                - Integer.parseInt(delivery.getPenalty_runs()) ) );
            }
        }

        HashMap<String,Double> deliveriesPerBowler = new HashMap<>();
        for(Delivery delivery : deliveries){
            if(Integer.parseInt(delivery.getId()) >= start && Integer.parseInt(delivery.getId()) <= end) {
                deliveriesPerBowler.put(delivery.getBowler(),
                        deliveriesPerBowler.getOrDefault(delivery.getBowler(), 0.0) + 1);
            }
        }

        //economy = (runs_conceded)/(overs)  over = (balls/6)
        HashMap<String,Double> economyPerBowler = new HashMap<>();
        for(String bowler : runsConcededPerBowler.keySet()){
            double overs = deliveriesPerBowler.get(bowler)/6;
            double economy = runsConcededPerBowler.get(bowler)/overs;
            economyPerBowler.put(bowler,economy);
        }

        ArrayList<Map.Entry<String,Double>> sortedListOfEconomy = new ArrayList<>(economyPerBowler.entrySet());
        Collections.sort(sortedListOfEconomy,(o1,o2)->{
            return o1.getValue().compareTo(o2.getValue());
        });


        for(int i = 0; i < 5; i++){
            System.out.print(sortedListOfEconomy.get(i).getKey() + " ");
            System.out.printf(" with economy %.3f", sortedListOfEconomy.get(i).getValue());
            System.out.println();
        }
        System.out.println();
    }

    private static void getOrangeCapHolder(ArrayList<Delivery> deliveries, int start, int end, int year){
        HashMap<String,Integer> runsPerBatter = new HashMap<>();

        int maxRuns = 0;
        String maxRunsBatter = "";

        for(Delivery delivery:deliveries){
            int match_id = Integer.parseInt(delivery.getId());
            String batter = delivery.getBatsman();
            if(match_id >= start && match_id <= end){
                runsPerBatter.put(batter, runsPerBatter.getOrDefault(batter, 0)
                                + Integer.parseInt(delivery.getBatsman_runs()));

                if (runsPerBatter.get(batter) > maxRuns) {
                    maxRuns = runsPerBatter.get(batter);
                    maxRunsBatter = batter;
                }
            }
        }

        System.out.println("Orange cap for the year " + year + " goes to : " + maxRunsBatter + " runs: " + maxRuns);
    }

    private static void getPurpleCapHolder(ArrayList<Delivery> deliveries, int start, int end, int year){
        HashMap<String,Integer> wicketsPerBowler = new HashMap<>();
        int maxWickets = 0;
        String maxWicketsBowler = "";

        for(Delivery delivery : deliveries){
            String dismissKind = delivery.getDismissal_kind();
            int match_id = Integer.parseInt(delivery.getId());

            if(dismissKind != null && match_id >= start && match_id <= end){

                if(dismissKind.equals("caught") || dismissKind.equals("bowled") || dismissKind.equals("lbw")
                        || dismissKind.equals("caught and bowled") || dismissKind.equals("stumped")) {
                    wicketsPerBowler.put(delivery.getBowler(),
                            wicketsPerBowler.getOrDefault(delivery.getBowler(), 0) + 1);

                    if (wicketsPerBowler.get(delivery.getBowler()) > maxWickets) {
                        maxWickets = wicketsPerBowler.get(delivery.getBowler());
                        maxWicketsBowler = delivery.getBowler();
                    }
                }
            }
        }

        System.out.println("Purple cap for the year " + year + " goes to : " + maxWicketsBowler + " wickets: "
                + maxWickets);
    }

    private static void getLeadingRunScorersOfIPL(ArrayList<Delivery> deliveries){
        HashMap<String,Integer> runsScoredPerBatter = new HashMap<>();
        for(Delivery delivery : deliveries){
            String batter = delivery.getBatsman();
            runsScoredPerBatter.put(batter, runsScoredPerBatter.getOrDefault(batter,0)
                    + Integer.parseInt(delivery.getBatsman_runs()));
        }

        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<>(runsScoredPerBatter.entrySet());
        Collections.sort(list, (o1, o2)->{
            return o2.getValue().compareTo(o1.getValue());
        });

        for(int i = 0; i < 10; i++){
            System.out.println(list.get(i).getKey() + " runs : " + list.get(i).getValue());
        }
    }

    private static void getLeadingWicketTakersOfIPL(ArrayList<Delivery> deliveries){
        HashMap<String,Integer> wicketsPerBowler = new HashMap<>();
        for(Delivery delivery : deliveries){
            if(delivery.getDismissal_kind() != null) {
                String dismissKind = delivery.getDismissal_kind();
                String bowler = delivery.getBowler();

                if (dismissKind.equals("caught") || dismissKind.equals("bowled") || dismissKind.equals("lbw")
                        || dismissKind.equals("caught and bowled") || dismissKind.equals("stumped")) {
                    wicketsPerBowler.put(bowler, wicketsPerBowler.getOrDefault(bowler, 0) + 1);
                }
            }
        }

        ArrayList<Map.Entry<String,Integer>> sortedWicketTakers = new ArrayList<>(wicketsPerBowler.entrySet());
        Collections.sort(sortedWicketTakers, (o1, o2)->{
            return o2.getValue().compareTo(o1.getValue());
        });

        for(int i = 0; i < 10; i++){
            System.out.println(sortedWicketTakers.get(i).getKey() + " wickets : "
                    + sortedWicketTakers.get(i).getValue());
        }
    }

    private static void getMostFiftiesBattersOfIPL(ArrayList<Delivery> deliveries){
        HashMap<String,Integer> totalFiftiesPerPlayer = new HashMap<>();
        for(int i = 1; i <= 636; i++){
            HashMap<String,Integer> runsPerMatch = new HashMap<>();

            for(Delivery delivery:deliveries){
                if(delivery.getId().equals(Integer.toString(i))){
                    runsPerMatch.put(delivery.getBatsman(),
                            runsPerMatch.getOrDefault(delivery.getBatsman(),0)
                                    +Integer.parseInt(delivery.getBatsman_runs()));
                }
                if(Integer.parseInt(delivery.getId()) > ((i))){
                    break;
                }
            }

            for(String batter : runsPerMatch.keySet()){
                if(runsPerMatch.get(batter) >= 50 && runsPerMatch.get(batter) < 100){
                    totalFiftiesPerPlayer.put(batter,totalFiftiesPerPlayer.getOrDefault(batter,0) + 1);
                }
            }
        }

        ArrayList<Map.Entry<String,Integer>> sortedBatterWithMost50 = new ArrayList<>(totalFiftiesPerPlayer.entrySet());
        Collections.sort(sortedBatterWithMost50, (o1, o2)->{
            return o2.getValue().compareTo(o1.getValue());
        });

        for(int i = 0; i < 10; i++){
            System.out.println(sortedBatterWithMost50.get(i).getKey()+" fifties : "
                    +sortedBatterWithMost50.get(i).getValue());
        }
    }

    private static void getCityWithHighestInningScoreForEachYear(ArrayList<Match> matches,
                                                                 ArrayList<Delivery> deliveries,
                                                                 int start, int end, int year){
        HashMap<String,Integer> cityToRunsForInnings1 = new HashMap<>();
        HashMap<String,Integer> cityToRunsForInnings2 = new HashMap<>();

        String maxInningCity = "";
        int maxInningScore = 0;
        String teamWithMaxInningScore = "";

        for(int i = start; i <= end; i++){
            String currentCity = "";
            String team1 = "";
            String team2 = "";

            for(Match match : matches){
                if(Integer.parseInt(match.getId()) == i){
                    currentCity = match.getCity();
                    break;
                }
            }

            for(Delivery delivery : deliveries){
                if(Integer.parseInt(delivery.getId()) == i) {

                    if(delivery.getInning().equals("1")) {
                        cityToRunsForInnings1.put(currentCity,
                                cityToRunsForInnings1.getOrDefault(currentCity, 0)
                                        + Integer.parseInt(delivery.getTotal_runs()));
                        team1 = delivery.getBatting_team();
                    }
                    else if(delivery.getInning().equals("2")) {
                        cityToRunsForInnings2.put(currentCity,
                                cityToRunsForInnings2.getOrDefault(currentCity, 0)
                                        + Integer.parseInt(delivery.getTotal_runs()));
                        team2 = delivery.getBatting_team();
                    }
                }
            }

            if(cityToRunsForInnings1.get(currentCity) > maxInningScore){
                maxInningScore = cityToRunsForInnings1.get(currentCity);
                maxInningCity = currentCity;
                teamWithMaxInningScore = team1;
            }
            if(cityToRunsForInnings2.get(currentCity) > maxInningScore){
                maxInningScore = cityToRunsForInnings2.get(currentCity);
                maxInningCity = currentCity;
                teamWithMaxInningScore = team2;
            }

            cityToRunsForInnings1.put(currentCity,0);
            cityToRunsForInnings2.put(currentCity,0);
        }

        System.out.println("for year " + year + " " + maxInningCity + " contains highest innings score of "
                + maxInningScore + " by "+teamWithMaxInningScore);
    }

    private static int getStartIndexOfYear(ArrayList<Match> matches, String year){
        int start = 0;

        for(Match match : matches){
            if(match.getSeason().equals(year)){
                start = Integer.parseInt(match.getId());
                break;
            }
        }

        return  start;
    }
    private static int getEndIndexOfYear(ArrayList<Match> matches, String year){
        int end = 0;

        for(Match match : matches){
            if(match.getSeason().equals(year)){
                end = Integer.parseInt(match.getId());
            }
        }

        return  end;
    }

}