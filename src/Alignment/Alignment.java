package Alignment;

public class Alignment {
    //  Score of a match
    public static int MATCH_SCORE = 1;
    //  Score of a mismatch
    public static int MISMATCH_SCORE = -1;
    //  Score of an indel
    public static int INDEL_SCORE = -1;
    //  Score of the border
    public static final int BORDER_SCORE = 0;
    /**
     * Find the global alignment of two strings
     * @param str1 The first string to align
     * @param str2 The second string to align
     * @throws Exception 
     */
    public static String globalAlignment (String str1, String str2) {
        //  Construct the table
        int w = str1.length();
        int h = str2.length();
        //  Used for backwards iteration
        int i1 = w - 1;
        int i2 = h - 1;
        //  Initialize to maximally bad scores
        //  allows us to know which cells were already resolved
        scores = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                scores[i][j] = Integer.MIN_VALUE;
            }
        }        
        //  Initialization of path does not matter
        path = new int[w][h];
        //  Call the recursive, dynamic programming method
        //  starting from the bottom right corner
        score(str1, str2, i1, i2);
        //  Construct the string
        String result = "";
        while (-1 < i1 && -1 < i2) {
            //  Query the 
            switch (path[i1][i2]){
            case U:
                //  Append from the first string
                result = str1.charAt(i1) + result;
                //  Decrement once in the first string
                i1--;
                break;
            case L:
                //  Append from the second string
                result = str2.charAt(i2) + result;
                //  Decrement once in the second string
                i2--;
                break;
            case UL:
                //  Match; append from the first arbitrarily
                result = str1.charAt(i1) + result;
                //  Decrement once in both strings
                i1--;
                i2--;
                break;
            }
        }
        //  String is constructed; return it
        return result;
    }    
    //  Recursive dynamic programming method to populate table
    //  Max recursion depth is SUM(str1.length(), str2.length())
    //  O(n^2); O(3^n) without the table check
    private static int score (String str1, String str2, int i1, int i2) {
        //  Use previous scores when available (making polynomial time)
        if (scores[i1][i2] != Integer.MIN_VALUE) {
            return scores[i1][i2];
        }
        //  Used to check the top, left, and diagonal cells in the table
        int[] previous = new int[3];
        //  Base case: Top edge of the table
        if (i1 == 0) {
            previous[U] = BORDER_SCORE;
        }
        else {
            //  Get the score from above
            previous[U] = score (str1, str2, i1 - 1, i2)
                    //  but penalize an indel
                    + INDEL_SCORE;
        }
        //  Base case: Left edge of the table
        if (i2 == 0) {
            previous[L] = BORDER_SCORE;
        }
        else {
            //  Get the score from the left
            previous[L] = score (str1, str2, i1, i2 - 1)
                    //  but penalize an indel
                    + INDEL_SCORE;
        }
        //  Base case: Either edge of the table
        if (i1 == 0 || i2 == 0) {
            previous[UL] = BORDER_SCORE;
        }
        else {
            //  Get the score from the upper left
            previous[UL] = score (str1, str2, i1 - 1, i2 - 1);
            //  Reward a match
            if (str1.charAt(i1) == str2.charAt(i2)) {
                previous[UL] += MATCH_SCORE;
            }
            //  But penalize a mismatch
            else {
                previous[UL] += MISMATCH_SCORE;
            }
        }
        //  Find the top-scoring cell
        int maxi = max_index(previous);
        //  Update the score table
        scores[i1][i2] = previous[maxi];
        //  Update the path table
        path[i1][i2] = maxi;
        //  Return the highest score from this run
        return previous[maxi];
    }
    //  Used internally to identify direction
    private static final int UL = 0;    //  favor this in case of tie
    private static final int U = 1;
    private static final int L = 2;
    //  Used internally to construct the Dynamic Programming scores
    static int[][] scores;
    //  Used internally to construct the Dynamic Programming path
    static int[][] path;
    //  Used internally to find the index of the max of a list
    private static int max_index (int[] arr) {
        int max = 0;    //  favors UL in case of tie
        for (int i = 1; i < arr.length; i++) {
            //  Found a better max
            if (arr[max] < arr[i]) {
                max = i;
            }
        }
        return max;
    }
}
