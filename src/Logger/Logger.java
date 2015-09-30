package Logger;

import java.io.PrintWriter;

public class Logger {
    private static PrintWriter writer;
    public static void open(String file) {
        try {
            writer = new PrintWriter(file, "UTF-8");
        } catch (Exception e) {}
    }
    public static void close() {
        writer.close();
    }
    public static void log(String message) {
        writer.println(message);
    }
    public static void log() {
        log("");
    }
    public static void log(int[][] arr) {
        int padding = 0;
        int a = arr.length;
        while (0 < a) {
            a /= 10;
            padding += 1;
        }
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                str += pad(padding, "" + arr[i][j]);
            }
            str += "\n";
        }
        log(str);
    }
    private static String spaces = "                  ";
    private static String pad(int padding, String message) {
        int numspaces = padding - message.length();
        if (0 < numspaces) {
            return spaces.substring(0, numspaces) + message;
        }
        return message;
    }
}
