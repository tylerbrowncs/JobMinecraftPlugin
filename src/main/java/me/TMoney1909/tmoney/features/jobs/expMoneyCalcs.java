package me.TMoney1909.tmoney.features.jobs;

public class expMoneyCalcs {

    public static float levelToTotalExp(int level) {
        return (float) (int) Math.pow((level-1) / 0.4, 2);
    }

    public static float levelToNextLevelExp(int nextlevel) {
        return levelToTotalExp(nextlevel) - levelToTotalExp(nextlevel-1);
    }

    public static int expToLevel(float exp) {
        return (int) Math.floor((0.4*Math.sqrt(exp)) + 1);
    }
}
