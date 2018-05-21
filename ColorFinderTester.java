import java.io.*;
import java.net.URL;

public class ColorFinderTester {
  private int[] testCount;

  public interface Test {
    public boolean execute();
  }

  public static void main(String[] args) {

    int[] testCount = {0, 0};
    System.out.println("Color Finder Test");
    final ColorFinder cf = new ColorFinder();

    assertTest(testCount, "Able to create an instance of ColorFinder", new Test() {
      public boolean execute() {
        return cf instanceof ColorFinder;
      }
    });


    try {
      URL path = ColorFinderMain.class.getResource("testUrls.txt");
      File f = new File(path.getFile());
      final BufferedReader br = new BufferedReader(new FileReader(f));
      assertTest(testCount, "Correctly displays 3 most prevalent color of image", new Test() {
        public boolean execute() {
           String s = "";
           try{
             s = br.readLine();
           } catch(IOException e){
             e.printStackTrace();
           }
           String[] t = cf.getMostFrequent(s);
           return t[0].equals("#ffffff") && t[1].equals("#000390") && t[2].equals("#ff153c");
        }
      });

      assertTest(testCount, "Consistently displays 3 most prevalent color of image if there could be multiple answers", new Test() {
        public boolean execute() {
          String s = "";
          try{
            s = br.readLine();
          } catch(IOException e){
            e.printStackTrace();
          }

          String[] a = cf.getMostFrequent(s);
          String[] b = cf.getMostFrequent(s);

          return a[0].equals(b[0]) && a[1].equals(b[1]) && a[2].equals(b[2]);

        }
      });

      assertTest(testCount, "Correctly displays most prevalent color if there are less than 3 colors in image", new Test() {
        public boolean execute() {
          String s = "";
          try{
            s = br.readLine();
          } catch(IOException e){
            e.printStackTrace();
          }

          String[] t = cf.getMostFrequent(s);

          return t.length == 1 && t[0].equals("#c40026");
        }
      });

    } catch(IOException e){
        e.printStackTrace();
    }

    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");

  }

  private static void assertTest(int[] count, String name, Test test) {
    String pass = "false";
    count[1]++;

    try {
      if (test.execute()) {
        pass = " true";
        count[0]++;
      }
    } catch(Exception e) {}
    String result = "  " + (count[1] + ")   ").substring(0, 5) + pass + " : " + name;
    System.out.println(result);
  }
}
