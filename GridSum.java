import java.io.File;                  // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner;             // Import the Scanner class to read text files

public class GridSum {
  public static void main(String[] args) {
    File gridFile = new File("grid3.txt");
    int gridWidth = 20;
    int gridLength = 20;

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


    for(int y=0; y<gridLength; y++){
      for(int x=0; x<gridWidth; x++){
        
        //next 4 numbers horizontaly
        if(x<=gridWidth-4){
          sum = grid[y][x] + grid[y][x+1] + grid[y][x+2] + grid[y][x+3];
          if(sum >maxSum) maxSum = sum;
        }
        //next 4 numbers verticaly
        if(y<=gridLength-4){
          sum = grid[y][x] + grid[y+1][x] + grid[y+2][x] + grid[y+3][x];
          if(sum >maxSum) maxSum = sum;
        }
        //next 4 numbers right down diagonal
        if(x<=gridWidth-4 && y<=gridLength-4){
          sum = grid[y][x] + grid[y+1][x+1] + grid[y+2][x+2] + grid[y+3][x+3];
          if(sum >maxSum) maxSum = sum;
        }
        //next 4 numbers horizontaly left down diagonal
        if(x>=3 && y<=gridLength-4){
          sum = grid[y][x] + grid[y+1][x-1] + grid[y+2][x-2] + grid[y+3][x-3];
          if(sum >maxSum) maxSum = sum;
        }
      }
    }
    System.out.println(maxSum);
  }
}
