import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Data {
    void matchesData(ArrayList<ArrayList<String>> matchData){
        File f = new File("src/matches.csv");

        try {
            Scanner sc = new Scanner(f);
            sc.nextLine();
            while(sc.hasNextLine()){
                String[] dataArr = sc.nextLine().split(",");
                ArrayList<String> listData = new ArrayList<>();
                for(String s:dataArr){
                    if(s.trim().equals("Rising Pune Supergiant")){
                        listData.add("Rising Pune Supergiants");
                    }
                    else {
                        listData.add(s);
                    }
                }
                matchData.add(listData);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void deliveriesDataMethod(ArrayList<ArrayList<String>> deliveryData){
        File f2 = new File("src/deliveries.csv");
        try {
            Scanner sc = new Scanner(f2);
            sc.nextLine();
            while (sc.hasNextLine()){
                String[] sArr = sc.nextLine().split(",");
                ArrayList<String> l = new ArrayList<>();
                for(String s:sArr){
                    if(s.trim().equals("Rising Pune Supergiant")){
                        l.add("Rising Pune Supergiants");
                    }
                    else {
                        l.add(s);
                    }
                }
                deliveryData.add(l);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
