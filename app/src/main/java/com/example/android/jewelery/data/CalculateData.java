package com.example.android.jewelery.data;

/**
 * Created by milju on 7/25/2017.
 */

public class CalculateData {

    public static float getHighWeight(float avaWeight, float avaProba, float extraProba) {
        return avaWeight*avaProba/extraProba;
    }

    public static float getPromotedWeight(float avaWeight, float avaProba,
                                          float desiredProba, float extraProba) {
        return avaWeight * (desiredProba - avaProba) / (extraProba - desiredProba);
    }


    public static float getAvaCoppperPercent(String color) throws Exception {
        switch (color) {
            case "Красный":
                return 0.8f;
            case "Жёлтый":
                return 0.35f;
            case "Зелёный":
                return 0.2f;
            default:
                throw new Exception("Your color is not programmed yet");
        }
    }

    public static float getAvaSilverPercent(String color) throws Exception {
        switch (color) {
            case "Красный":
                return 0.2f;
            case "Жёлтый":
                return 0.65f;
            case "Зелёный":
                return 0.8f;
            default:
                throw new Exception("Your color is not programmed yet");
        }
    }






}
