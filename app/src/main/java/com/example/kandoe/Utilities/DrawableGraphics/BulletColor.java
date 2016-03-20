package com.example.kandoe.Utilities.DrawableGraphics;

import java.util.ArrayList;
import java.util.Arrays;

public enum BulletColor {
    //RED
    RED("#F44336"),
    UpsdellRed("#ae2029"),
    DarkRed("#a81c07"),

    //ORANGE
    DEEPOrange("#FF5722"),
    Orange("#FF9800"),
    Amber("#FFC107"),
    Tomato("#ff6347"),

    //YELLOW
    Yellow("#FFEB3B"),
    Urobilin("#e1ad21"),
    SandStorm("#ecd540"),

    //GREEN
    Green("#4CAF50"),
    Lime("#CDDC39"),
    DarkGreen("#014421"),
    SeaGreen("#2e8b57"),

    //BLUE
    LIGHTBLUE("#03A9F4"),
    BLUE("#0033aa"),
    BlueGrey("#607D8B"),
    SeaBlue("#006994"),

    //PURPLE/INDIGO
    DEEPPURPLE("#673AB7"),
    INDIGO("#3F51B5"),
    PURPLE("#9C27B0"),
    PINK("#E91E63"),
    DARKPURPLE("#66023c"),
    SKYMAGENTA("#cf71af"),
    SALMON("#ff8c69"),

    //brown, grey , black
    Brown("#795548"),
    Grey("#9E9E9E"),
    Taupe("#8b8589"),
    WarmBlack("#004242"),
    SmokyBlack("#100c08");


    private final String hex;

    private BulletColor(final String hex) {
        this.hex = hex;
    }

    static ArrayList<BulletColor> list = new ArrayList<BulletColor>(Arrays.asList(BulletColor.values()));

    public String getHexCode() {
        return hex;
    }

    public static BulletColor getColor(int cardId) {
        if (cardId >= list.size()) {
            return list.get(cardId%list.size());
        }
        return list.get(cardId);
    }


}
