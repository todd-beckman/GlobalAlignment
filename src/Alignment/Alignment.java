package Alignment;

import Logger.Logger;

public class Alignment {
    //  Score of a match
    public static final int MATCH_SCORE = 1;
    //  Score of a mismatch
    public static final int MISMATCH_SCORE = 0;
    //  Score of an indel
    public static final int INDEL_SCORE = 0;
    //  Score of the border
    public static final int BORDER_SCORE = 0;
    /**
     * Find the global alignment of two strings
     * @param str1 The first string to align
     * @param str2 The second string to align
     */
    public static void globalAlignment (String str1, String str2) 
        throws Exception {
        //  Construct the table
        int w = str1.length();
        int h = str2.length();
//      Is this true?
//      if (w != h) {
//          throw new RuntimeException("Cannot align "
//                  + "strings of different lengths!");
//      }
        int[][] table = new int[w][h];
        path = new int[w][h];
        //  Call the recursive, dynamic programming method
        //  Use the bottom right corner
        score(table, str1, str2, w - 1, h - 1);
        //  Construct the string
        //  TODO
    }
    //  Recursive dynamic programming method to populate table
    private static int score (int[][] table, String str1, String str2, int i1, int i2) {
        int[] previous = new int[3];
        
        //  Base case: Top edge of the table
        if (i1 == 0) {
            previous[U] = BORDER_SCORE;
        }
        else {
            //  Get the score from above
            previous[U] = score (table, str1, str2, i1 - 1, i2);
        }
        
        //  Base case: Left edge of the table
        if (i1 == 0) {
            previous[L] = BORDER_SCORE;
        }
        else {
            //  Get the score from the left
            previous[L] = score (table, str1, str2, i1, i2 - 1);
        }
        
        //  Base case: Either edge of the table
        if (i1 == 0 || i2 == 0) {
            previous[UL] = BORDER_SCORE;
        }
        else {
            //  Get the score from the upper left
            previous[UL] = score (table, str1, str2, i1 - 1, i2 - 1);
        }
        
        //  Find the top-scoring cell
        int max = max_index(previous);
        
        //  Update the path
        path[i1][i2] = max;
        
        //  Return the highest score from this run
        return previous[max];
    }
    
    //  Used internally to identify direction
    private static final int UL = 0;    //  favor this in case of tie
    private static final int U = 1;
    private static final int L = 2;
    
    //  Used internally to construct the Dynamic Programming path
    private static int[][] path;
    
    //  Used internally to find the index of the max of a list
    private static int max_index (int[] arr) {
        int max = 0;    //  favors UL in case of tie
        for (int i = 1; i < arr.length; i++) {
            if (arr[max] < arr[i]) {
                max = i;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        
    }
}
