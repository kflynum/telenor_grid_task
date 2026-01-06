import java.io.*;
import java.util.*;

public class GridProduct {
  public static void main(String[] args) {
    String fileName = "grid3.txt";
    int span = 4;

    int gridWidth;
    int gridLength;
    long[][] grid;
    long maxProduct = Integer.MIN_VALUE;
    long product;

    // reading file into 2d-array, and figuring out size of grid
    List<long[]> rowList = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            long[] row = new long[st.countTokens()];
            for (int i = 0; st.hasMoreTokens(); i++) {
                row[i] = Long.parseLong(st.nextToken());
            }
            rowList.add(row);
        }
    } catch (IOException e) {
        e.printStackTrace();
        return;
    }
    grid = rowList.toArray(new long[0][]);
    gridLength = grid.length;
    gridWidth = grid.length;
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
            
            product = 1;
            for (int i = 0; i < span; i++) {
                product *= grid[y + dir[1] * i][x + dir[0] * i];
            }
            maxProduct = Math.max(maxProduct, product);
        }
      }
    }
    System.out.println(maxProduct);
  }
}
