package com.example.kandoe.DrawableGraphics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Thomas on 2016-02-26.
 */
public enum BulletColor {


    RED("#F44336"),
    PINK("#E91E63"),
    PURPLE("#9C27B0"),
    DEEPPURPLE("#673AB7"),
    INDIGO("#3F51B5"),
    LIGHTBLUE("#03A9F4"),
    Green("#4CAF50"),
    Lime("#CDDC39"),
    Yellow("#FFEB3B"),
    Amber("#FFC107"),
    Orange("#FF9800"),
    DEEPOrange("#FF5722"),
    Brown("#795548"),
    Grey("#9E9E9E"),
    BlueGrey("#607D8B");    //... etc, this is a shorted list

    private final String hex;

    private BulletColor(final String hex) {
        this.hex = hex;
    }
    static ArrayList<BulletColor> list = new ArrayList<BulletColor>(Arrays.asList(BulletColor.values()));
    public String getHexCode() {
        return hex;
    }

    public static BulletColor getColor(int cardId) {


       if (cardId > list.size()) {

            cardId = new Random().nextInt(list.size());
        }
        return list.get(cardId);
    }


}
