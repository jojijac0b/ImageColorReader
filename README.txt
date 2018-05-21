Goal:
  Write a program that processes a list of urls that point to images, given in a file 'urls.txt', and for each image
  return the three most frequent colors in the  RGB scheme in hexidecimal format and write it to a csv file.
    - each solution for a link should be written to the file in the format <image url>, <1st mf color>, <2nd mf color>, <3rd mf color>

Approach:
  Preprocessing:
    - Need to read the lines of urls.txt file line by line
      * use BufferedReader
    - Need to convert url String to image object
      * use BufferedImage to store image and use ImageIO.read('url') function to convert url to image

- Once we have a BufferedImage object representing the image in the url:
  Algorithm:
    - For each picture iterate through all of its pixels

    Option 1:
      - use a HashMap with (key -> value) == (String of color in hex -> count of color frequency)

      - iterate through all entries in the HashMap to find the key -> value pair with the highest count and remove that entry.
        repeat three times for the top three color counts.

      t.c: O(m) + O(3n) where m = the number of pixels in the image, n = the number of unique colors in image

    Option 2:
      - use a HashMap with (key -> value) == (String of color in hex -> Hex object)
      ** by using a Hex object we have more flexibility in how to keep track of the most frequent colors.
      - Hex object will have fields:
        String name(color in hex format)
        int count(frequency of color)
      - when adding Hex objects to HashMap and updating their counts, put them in a max heap based on count. This way we can get the top 3 colors in O(1) time
      t.c: O(mlogn) where m = the number of pixels in the image, n =  the number of unique colors in image
      **OPTIMIZATION**: By fixing the size of our heap to 3 and changing it to a min heap we can change the time it takes to add to the heap to O(1)
      updated t.c: O(m) where m = the number of pixels in the image

      Option 2 with optimization is best approach.

    edge cases to account for:
      - if there is less than 3 colors in the image
      - if the colors in the image appear the same amount of times(i.e. multiple answers)

    tradeoffs:
      - when iterating through each pixel we can increment by factors of 5; this way it will read MOST of the pixels in the image
      - this is trading speed for accuracy.
      - this solution will choose accuracy over speed and increment by a factor of 1

  Organization:
    - We will read the urls.txt file line by line in ColorFinderMain class
    - We will pass each url to our ColorFinder class to do the work of finding the 3 most prevalent colors and returning them in a String[]
    - We will write the answer to our solutions.txt file in the ColorFinderMain class


How to use program:
  - Compile and run on the command line
  - Run ColorFinderTester.java file to test that ColorFinder.java is accurate and passes some edge cases
  - Run ColorFinderMain.java to run program with given urls.text(there are alot of urls so it will take a while)
