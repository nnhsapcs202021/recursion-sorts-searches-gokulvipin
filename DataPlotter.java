import java.awt.*;
import java.io.*;
import java.util.*;
/**
 * This DataPlotter object reads a space delimited text file of elevations
 * and plots the result as a range of greyscale images, and then calculates 
 * and plots the steepest downward path from each location in the image.
 * 
 * @author gokulvipin
 * @version 6/3/2021
 */
public class DataPlotter
{
    private static String fileName = "Colorado";
    private static int[][] grid;
    private static DrawingPanel panel;
    private static Scanner fileReader;
    private static int rows, cols;

    public static void main(String[] args) throws IOException
    {
        readValues();
        plotData();
        try {Thread.sleep(3000); } catch (Exception e){};  // pause display for 3 seconds
        plotAllPaths();
    }

    private static void readValues() throws IOException
    {
        fileReader = new Scanner(new File(fileName + ".dat"));
        rows = fileReader.nextInt();    // first integer in file
        cols = fileReader.nextInt();    // second integer in file

        // instantiate and initialize the instance variable grid 
        // then read all of the data into the array in row major order
        grid = new int[rows][cols];
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
                grid[r][c] = fileReader.nextInt();

            }
        }

    }

    // plot the altitude data read from file
    private static void plotData()
    {
        panel = new DrawingPanel(cols, rows);

        //finding maximum and minimum heights
        int maxHeight = grid[0][0];
        int minHeight = grid[0][0];
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
                if(grid[r][c] > maxHeight){
                    maxHeight = grid[r][c];
                }
                else if(grid[r][c] < minHeight){
                    minHeight = grid[r][c];
                }

            }
        }


        //scaling each int value
        double scale = 255.00 / maxHeight;
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
                int value = (int) ((grid[r][c] - minHeight) * scale);
                Color color = new Color(value, value, value);
                panel.setPixel(c, r, color);
            }
        }

    }


    // for a given x, y value, plot the downhill path from there
    private static void plotDownhillPath(int x, int y)
    {
        int minHeight = grid[y][x];
        int indexY = y;
        int indexX = x;
        for(int left = y-1; left <= y+1; left++){
            for(int right = x-1; right <= x+1; right++){
                try{
                    if(grid[left][right] < minHeight){
                        minHeight = grid[left][right];
                        indexX = right;
                        indexY = left;
                    }
                }
                catch (Exception ArrayIndexOutOfBoundsException){}
            }
        }

        if(minHeight == grid[y][x]){ //the center is the highest height  
            panel.setPixel(x,y,Color.BLUE);
            return;
        }
        else{ //not the center
            panel.setPixel(indexX,indexY,Color.BLUE);
        }
        plotDownhillPath(indexX,indexY); //recurse
    }

    private static void plotAllPaths() {
        for (int x = 0; x < grid[0].length; x++) { //x = col
            for (int y = 0; y < grid.length; y++) { // y = row
                plotDownhillPath(x, y);

            }
        }

    }

}