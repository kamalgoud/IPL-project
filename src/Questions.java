import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Questions {
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
}
