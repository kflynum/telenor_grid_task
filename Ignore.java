import java.io.File;                  // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner;             // Import the Scanner class to read text files

public class Ignore {
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


    // Precompute prefix sums
    int[][] rowSum  = new int[gridLength][gridWidth];
    int[][] colSum  = new int[gridLength][gridWidth];
    int[][] diagDR  = new int[gridLength][gridWidth];
    int[][] diagDL  = new int[gridLength][gridWidth];

    for (int y = 0; y < gridLength; y++) {
        for (int x = 0; x < gridWidth; x++) {

            rowSum[y][x] = grid[y][x] + (x > 0 ? rowSum[y][x - 1] : 0);
            colSum[y][x] = grid[y][x] + (y > 0 ? colSum[y - 1][x] : 0);

            diagDR[y][x] = grid[y][x]
                    + (x > 0 && y > 0 ? diagDR[y - 1][x - 1] : 0);

            diagDL[y][x] = grid[y][x]
                    + (x < gridWidth - 1 && y > 0 ? diagDL[y - 1][x + 1] : 0);
        }
    }

    //find largest sum
    for (int y = 0; y < gridLength; y++) {
        for (int x = 0; x < gridWidth; x++) {

            // Horizontal
            int end = x + span - 1;
            if (end < gridWidth) {
                sum = rowSum[y][end] - (x > 0 ? rowSum[y][x - 1] : 0);
                maxSum = Math.max(maxSum, sum);
            }

            // Vertical
            end = y + span - 1;
            if (end < gridLength) {
                sum = colSum[end][x] - (y > 0 ? colSum[y - 1][x] : 0);
                maxSum = Math.max(maxSum, sum);
            }

            // Diagonal down right
            int endX = x + span - 1;
            int endY = y + span - 1;
            if (endX < gridWidth && endY < gridLength) {
                sum = diagDR[endY][endX]
                        - (x > 0 && y > 0 ? diagDR[y - 1][x - 1] : 0);
                maxSum = Math.max(maxSum, sum);
            }

            // Diagonal down left
            endX = x - (span - 1);
            endY = y + span - 1;
            if (endX >= 0 && endY < gridLength) {
                sum = diagDL[endY][endX]
                        - (x < gridWidth - 1 && y > 0 ? diagDL[y - 1][x + 1] : 0);
                maxSum = Math.max(maxSum, sum);
            }
        }
    }
    System.out.println(maxSum);
  }
}
