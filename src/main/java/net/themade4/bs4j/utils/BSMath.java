package net.themade4.bs4j.utils;

public class BSMath {
    public static double round(double d, int precision) {
        return ((double) Math.round(d * Math.pow(10, precision))) / Math.pow(10, precision);
    }
}
