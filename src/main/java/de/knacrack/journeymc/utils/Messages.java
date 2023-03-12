package de.knacrack.journeymc.utils;

public enum Messages {
    PLAYER_NOT_ONLINE(0, "Dieser Spieler ist nicht Online."),
    PLAYER_NOT_EXIST(1, "Dieser Spieler existiert nicht!"),
    ERROR(2, "Ein Fehler ist aufgetreten!"),
    SYNTAX_ERROR(3, "Du hast den Befehl falsch eingegeben"),
    SELF_HEALING(4, "Du hast dich geheilt."),
    OTHER_HEALING(5, "Du Hast %p% geheilt."),
    YOU_CANT_DO_THAT(6, "Du kannst das nicht machen!"),
    NO_PERMISSION(7, "Du hast keine Berechtigung für diese Aktion"),
    PERMISSION_PREFIX(8, "duschpalast"),
    CONSOLE_DO_PLAYER_STUFF(9, "Du musst ein Spieler sein um diesen Befehl ausführen zu können!");



    private final int id;

    private final String message;



    Messages(int id, String message) {
        this.id = id;
        this.message = message;
    }



    public int getMessageId() {
        return this.id;
    }



    public String getMessage() {
        return this.message;
    }
}
