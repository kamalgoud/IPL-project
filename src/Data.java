import java.io.*;
import java.util.ArrayList;

public class Data {
    void matchesData(ArrayList<ArrayList<String>> matchData){
        File f = new File("src/matches.csv");

        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            br.readLine();
            while((line = br.readLine()) != null){
                tempArr = line.split(",");
                ArrayList<String> listData = new ArrayList<>();
                for(String s:tempArr){
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void deliveriesDataMethod(ArrayList<ArrayList<String>> deliveryData){
        File f2 = new File("src/deliveries.csv");
        try {
            FileReader fr = new FileReader(f2);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            br.readLine();
            while ((line = br.readLine()) != null){
                tempArr = line.split(",");
                ArrayList<String> l = new ArrayList<>();
                for(String s:tempArr){
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
