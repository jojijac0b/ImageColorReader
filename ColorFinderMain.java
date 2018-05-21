import java.io.*;
import java.net.URL;

public class ColorFinderMain {

    public static void main(String[] args){

        ColorFinder cf = new ColorFinder();
        FileWriter fileWriter = null;

        try {
          fileWriter = new FileWriter("solutions.txt");

          URL path = ColorFinderMain.class.getResource("urls.txt");
          File f = new File(path.getFile());
          BufferedReader br = new BufferedReader(new FileReader(f));

          String line;
          while ((line = br.readLine()) != null) {
             String[] mf = cf.getMostFrequent(line);

             String toAdd = line;
             for(String s : mf){
                toAdd += ", " + s;
             }
             toAdd += "\n";

             fileWriter.append(toAdd);
          }

        } catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
