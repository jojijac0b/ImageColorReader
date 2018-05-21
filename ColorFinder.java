import java.awt.Color;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import javax.imageio.ImageIO;
import java.util.*;


public class ColorFinder {
  PriorityQueue<Hex> heap;
  HashMap<String, Hex> map;

  public ColorFinder(){
      heap = new PriorityQueue<>(3, new Comparator<Hex>(){
        public int compare(Hex a, Hex b){
          return a.count - b.count;
        }
      });

      map = new HashMap<>();
  }

  public String[] getMostFrequent(String url){
      Color color;
      int r;
      int g;
      int b;
      String hex;
      BufferedImage img = getImage(url);


      for(int i = 0; i < img.getHeight(); i+=1){
        for(int j = 0; j < img.getWidth(); j+=1){

          color = new Color(img.getRGB(j,i));

          r = color.getRed();
          g = color.getGreen();
          b = color.getBlue();
          hex = String.format("#%02x%02x%02x", r, g, b);

          if(!map.containsKey(hex)){
              map.put(hex, new Hex(hex));
          }

          Hex curr = map.get(hex);

          curr.count++;

          if(heap.contains(curr)){
            heap.remove(curr);
            heap.add(curr);
          }
          else{
            heap.add(curr);
            if(heap.size() > 3)heap.remove();
          }
        }
      }

      return getResult();
  }

  private BufferedImage getImage(String url){
      BufferedImage img = null;

      try{
          img = ImageIO.read(new URL(url));
      }
      catch(IOException e){
          e.printStackTrace();
      }

      return img;
  }

  private String[] getResult(){
      String[] ret = new String[heap.size()];

      for(int i = ret.length-1; i >= 0; i--){
        ret[i] = heap.remove().name;
      }

      return ret;
  }
}
