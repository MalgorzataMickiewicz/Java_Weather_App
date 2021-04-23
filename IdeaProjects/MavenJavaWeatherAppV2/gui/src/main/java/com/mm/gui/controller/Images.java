package com.mm.gui.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Images {
    private static Map<String, File> images = new HashMap<>();

    static {
        images.put("0", new File("/weathericons/0.png"));
        images.put("1", new File("/weathericons/1.png"));
        images.put("2", new File("/weathericons/2.png"));
        images.put("3", new File("/weathericons/3.png"));
        images.put("4", new File("/weathericons/4.png"));
        images.put("5", new File("/weathericons/5.png"));
        images.put("6", new File("/weathericons/6.png"));
        images.put("7", new File("/weathericons/7.png"));
        images.put("8", new File("/weathericons/8.png"));
        images.put("11", new File("/weathericons/11.png"));
        images.put("12", new File("/weathericons/12.png"));
        images.put("13", new File("/weathericons/13.png"));
        images.put("14", new File("/weathericons/14.png"));
        images.put("15", new File("/weathericons/15.png"));
        images.put("16", new File("/weathericons/16.png"));
        images.put("17", new File("/weathericons/17.png"));
        images.put("18", new File("/weathericons/18.png"));
        images.put("19", new File("/weathericons/19.png"));
        images.put("20", new File("/weathericons/20.png"));
        images.put("21", new File("/weathericons/21.png"));
        images.put("22", new File("/weathericons/22.png"));
        images.put("23", new File("/weathericons/23.png"));
        images.put("24", new File("/weathericons/24.png"));
        images.put("25", new File("/weathericons/25.png"));
        images.put("26", new File("/weathericons/26.png"));
        images.put("29", new File("/weathericons/29.png"));
        images.put("30", new File("/weathericons/30.png"));
        images.put("31", new File("/weathericons/31.png"));
        images.put("32", new File("/weathericons/32.png"));
        images.put("33", new File("/weathericons/33.png"));
        images.put("34", new File("/weathericons/34.png"));
        images.put("35", new File("/weathericons/35.png"));
        images.put("36", new File("/weathericons/36.png"));
        images.put("37", new File("/weathericons/37.png"));
        images.put("38", new File("/weathericons/38.png"));
        images.put("39", new File("/weathericons/39.png"));
        images.put("40", new File("/weathericons/40.png"));
        images.put("41", new File("/weathericons/41.png"));
        images.put("42", new File("/weathericons/42.png"));
        images.put("43", new File("/weathericons/43.png"));
        images.put("44", new File("/weathericons/44.png"));
    }

    static String getImage(String imageKey) {
        String.valueOf(images.get(imageKey));
        return String.valueOf(images.get(imageKey)).replace("\\", "/");
    }
}
