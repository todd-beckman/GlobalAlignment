package Alignment;
import java.util.Scanner;

import Logger.Logger;

public class Main {

    private static String genString(int length, char[] alphabet) {
        char[] str = new char[length];
        for (int i = 0; i < length; i++){
            str[i] = alphabet[(int)(Math.random() * alphabet.length)];
        }
        return String.valueOf(str);
    }
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What score should a match yield?           ");
        Alignment.MATCH_SCORE = scanner.nextInt();
        System.out.print("What score should a mismatch yield?        ");
        Alignment.MISMATCH_SCORE = scanner.nextInt();
        System.out.print("What score should an indel yield?          ");
        Alignment.INDEL_SCORE = scanner.nextInt();
        System.out.print("How many times should the program run?     ");
        int ITERATIONS = scanner.nextInt();
        System.out.print("What should the lengths of the strings be? ");
        int LENGTH = scanner.nextInt();
        scanner.close();
        System.out.println("Working...");
        String logfile = "logs_" + ITERATIONS + "x" + LENGTH +".txt";
        
        char[] alphabet = {'A', 'C', 'G', 'T'};
        Logger.open(logfile);
        for (int i = 0; i < ITERATIONS; i++) {
            String str1 = genString(LENGTH, alphabet);
            String str2 = genString(LENGTH, alphabet);
            Logger.log("1: " + str1);
            Logger.log("2: " + str2);
            Logger.log("R: " + Alignment.globalAlignment(str1, str2));
            Logger.log("Score: " + Alignment.scores[LENGTH - 1][LENGTH - 1]);
            Logger.log(Alignment.scores);
            Logger.log();
        }
        Logger.close();
        System.out.println("Finished! You can find the results at " + logfile + ".");
    }
}
