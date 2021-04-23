package com.mm.gui.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

public class ApiConnection {
    StringBuffer response = new StringBuffer();;
    String apiResponse;
    String finalResult;
    int responseCode;
    ApiConnectionCity apiCity = new ApiConnectionCity();
    ApiConnectionWeatherData apiWeatherData = new ApiConnectionWeatherData();

    public String executeGet(String GET_URL) throws IOException {
        finalResult = connectWithApi(GET_URL);
        return finalResult;
    }

    private String connectWithApi(String GET_URL) throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        responseCode = con.getResponseCode();
        apiResponse = checkConnectionCode(response, responseCode, con);
        response.delete(0,response.length());
        if (!apiResponse.equals("[]") || apiResponse.equals("Limit") || apiResponse.equals("GET request not worked")) {
            if (apiResponse.contains("Key") == true) {
                String cityKey = apiCity.executeGet(apiResponse);
                return cityKey;
            }
            else {
                String weatherData = apiWeatherData.executeGet(apiResponse);
                apiWeatherData.executeGet(apiResponse);
                return weatherData;
            }
        }
        else {
            return apiResponse;
        }
    }

    private String checkConnectionCode(StringBuffer response, int responseCode, HttpURLConnection con) throws IOException {
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String inputLine;
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        }  else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
            return "[]";
        }
        else if (responseCode == 503) {
            return "Limit";
        }
        else {
            return "GET request not worked";
        }
    }
}
