import java.io.File;                  // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner;             // Import the Scanner class to read text files

public class GridSum {
  public static void main(String[] args) {
    File gridFile = new File("grid3.txt");
    int gridWidth = 20;
    int gridLength = 20;
    int span = 4;

    int[][] grid = new int[gridLength][gridWidth];
    int maxSum = Integer.MIN_VALUE;
    int sum;

    // reading file into 2d-array
    try (Scanner myReader = new Scanner(gridFile)) {
      for(int y=0; y<gridLength; y++){
        for(int x=0; x<gridWidth; x++){ 
            grid[y][x] =myReader.nextInt();
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    int[][] directions = {{1, 0},{0, 1},{1, 1},{-1, 1}}; //possible direction [x,y]

    for(int y=0; y<gridLength; y++){
      for(int x=0; x<gridWidth; x++){
        for (int[] dir : directions){           
            //early exit, skip invalid directions
            int endX = x + dir[0] * (span - 1);
            int endY = y + dir[1] * (span - 1);
            if (endX < 0 || endX >= gridWidth || endY < 0 || endY >= gridLength) {
                continue;
            }
            
            sum = 1;
            for (int i = 0; i < span; i++) {
                sum *= grid[y + dir[1] * i][x + dir[0] * i];
            }
            maxSum = Math.max(maxSum, sum);
        }
      }
    }
    System.out.println(maxSum);
  }
}
