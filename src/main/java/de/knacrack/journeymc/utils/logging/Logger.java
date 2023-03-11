package de.knacrack.journeymc.utils.logging;

public class Logger {

    public static void info(String message) {
        printStatement(Type.INFORMATION, message);
    }

    public static void error(String message) {
        printStatement(Type.ERROR, message);
    }

    public static void log(Type type, String message) {
        printStatement(type, message);
    }

    private static void printStatement(Type type, String message) {
        System.out.println(type.fontColor.code + "<" + type.label + "> - " + message);
    }

    private enum Type {
        INFORMATION("I", FontColor.ANSI_YELLOW),
        ERROR("E", FontColor.ANSI_RED);

        private final String label;
        private final FontColor fontColor;

        Type(String type, FontColor fontColor) {
            this.label = type;
            this.fontColor = fontColor;
        }
    }

    private enum FontColor {
        ANSI_RESET("\u001B[0m"),
        ANSI_BLACK("\u001B[30m"),
        ANSI_RED("\u001B[31m"),
        ANSI_GREEN("\u001B[32m"),
        ANSI_YELLOW("\u001B[33m"),
        ANSI_BLUE("\u001B[34m"),
        ANSI_PURPLE("\u001B[35m"),
        ANSI_CYAN("\u001B[36m"),
        ANSI_WHITE("\u001B[37m");

        private final String code;

        FontColor(String colorCode) {
            code = colorCode;

        }
    }

    private enum BackgroundColor {
        ANSI_BLACK_BACKGROUND("\u001B[40m"),
        ANSI_RED_BACKGROUND("\u001B[41m"),
        ANSI_GREEN_BACKGROUND("\u001B[42m"),
        ANSI_YELLOW_BACKGROUND("\u001B[43m"),
        ANSI_BLUE_BACKGROUND("\u001B[44m"),
        ANSI_PURPLE_BACKGROUND("\u001B[45m"),
        ANSI_CYAN_BACKGROUND("\u001B[46m"),
        ANSI_WHITE_BACKGROUND("\u001B[47m");

        private final String code;

        BackgroundColor(String backgroundCode) {
            code = backgroundCode;

        }
    }

}
