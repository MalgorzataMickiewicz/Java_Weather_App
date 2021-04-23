package com.mm.gui.controller;

public class ApiConnectionCity {
    public String executeGet(String apiResponse) {
        String cityKey = "";
        int beginOfKey = apiResponse.indexOf("\"Key\":\"");
        int endOfKey = apiResponse.indexOf("\"Type\"");
        if (apiResponse.contains("Key")) {
            for (int i = beginOfKey + 7; i < endOfKey - 2; i++) {
                cityKey += apiResponse.charAt(i);
            }
            return cityKey;
        } else {
            return "apiResponse doesn't contain key value";
        }
    }
}
