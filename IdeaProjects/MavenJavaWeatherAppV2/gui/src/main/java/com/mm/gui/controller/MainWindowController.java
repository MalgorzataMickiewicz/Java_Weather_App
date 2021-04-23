package com.mm.gui.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mm.gui.Config;
import com.mm.gui.view.ViewFactory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class MainWindowController extends AbstractController implements Initializable {

    private String city = "";
    private String queryFirstCity = "";
    private String querySecondCity = "";
    private String firstCityAdd = "";
    private String secondCityAdd = "";
    private String absolutePath = "";
    private String cityKey = "";
    private String weatherData = "";
    private String result = "";

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    private Text temperatureFirst1;

    @FXML
    private Text descriptionFirst1;

    @FXML
    private TextField firstCityField;

    @FXML
    private Text temperatureFirst2;

    @FXML
    private Text descriptionFirst2;

    @FXML
    private Text temperatureFirst3;

    @FXML
    private Text descriptionFirst3;

    @FXML
    private Text temperatureFirst4;

    @FXML
    private Text descriptionFirst4;

    @FXML
    private Text temperatureFirst5;

    @FXML
    private Text descriptionFirst5;

    @FXML
    private Text temperatureFirst12;

    @FXML
    private Text temperatureFirst22;

    @FXML
    private Text temperatureFirst32;

    @FXML
    private Text temperatureFirst42;

    @FXML
    private Text temperatureFirst52;

    @FXML
    private Text descriptionFirst12;

    @FXML
    private Text descriptionFirst52;

    @FXML
    private Text descriptionFirst22;

    @FXML
    private Text descriptionFirst32;

    @FXML
    private Text descriptionFirst42;

    @FXML
    private TextField secondCityField;

    @FXML
    private Label imageFirst1;

    @FXML
    private Label imageFirst12;

    @FXML
    private Label imageFirst2;

    @FXML
    private Label imageFirst22;

    @FXML
    private Label imageFirst3;

    @FXML
    private Label imageFirst32;

    @FXML
    private Label imageFirst4;

    @FXML
    private Label imageFirst42;

    @FXML
    private Label imageFirst5;

    @FXML
    private Label imageFirst52;

    @FXML
    private Text temperatureSecond1;

    @FXML
    private Text temperatureSecond2;

    @FXML
    private Text temperatureSecond3;

    @FXML
    private Text temperatureSecond4;

    @FXML
    private Text temperatureSecond5;

    @FXML
    private Text descriptionSecond1;

    @FXML
    private Text descriptionSecond2;

    @FXML
    private Text descriptionSecond3;

    @FXML
    private Text descriptionSecond4;

    @FXML
    private Text descriptionSecond5;

    @FXML
    private Text temperatureSecond12;

    @FXML
    private Text temperatureSecond22;

    @FXML
    private Text temperatureSecond32;

    @FXML
    private Text temperatureSecond42;

    @FXML
    private Text temperatureSecond52;

    @FXML
    private Text descriptionSecond12;

    @FXML
    private Text descriptionSecond22;

    @FXML
    private Text descriptionSecond32;

    @FXML
    private Text descriptionSecond42;

    @FXML
    private Text descriptionSecond52;

    @FXML
    private Label imageSecond1;

    @FXML
    private Label imageSecond12;

    @FXML
    private Label imageSecond2;

    @FXML
    private Label imageSecond22;

    @FXML
    private Label imageSecond3;

    @FXML
    private Label imageSecond32;

    @FXML
    private Label imageSecond4;

    @FXML
    private Label imageSecond42;

    @FXML
    private Label imageSecond5;

    @FXML
    private Label imageSecond52;

    @FXML
    private Button Button;

    @FXML
    private Label commentFirstCity;

    @FXML
    private Label commentSecondCity;

    @FXML
    private Label flagFirstCity;

    @FXML
    private Label flagSecondCity;

    @FXML
    void firstCitySearch() throws IOException {
        city = "First";
        clearDataFromMainWindow(city);
        commentFirstCity.setText("");
        prepareDateInMainWindowController(city);
    }

    @FXML
    void secondCitySearch() throws IOException {
        city = "Second";
        clearDataFromMainWindow(city);
        commentSecondCity.setText("");
        prepareDateInMainWindowController(city);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            addCitiesToMainWindow();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void prepareDateInMainWindowController(String city) throws IOException {
        queryFirstCity = firstCityField.getText();
        querySecondCity = secondCityField.getText();
        String communicatConnection = "Błąd połączenia";
        String communicatCity = "Nie znaleziono miasta";
        String communicatLimit = "Wyczerpałeś limit wyszukań";

        if (city.equals("First")) {
            if (!queryFirstCity.equals("")) {
                result = loadData(queryFirstCity);

                if (result.equals("GET request not worked")) {
                    commentFirstCity.setText(communicatConnection);
                } else if (result.equals("[]")) {
                    commentFirstCity.setText(communicatCity);
                } else if (result.equals("Limit")) {
                    commentFirstCity.setText(communicatLimit);
                } else {
                    editApiResult(result, city);
                    flagFirstCity.setText(queryFirstCity);
                    FileWriter fileWriter = loadPathToFileToAddCities();
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(flagFirstCity.getText());
                    bufferedWriter.newLine();
                    if (flagSecondCity.getText().equals("")) {
                        bufferedWriter.write("1");
                    } else {
                        bufferedWriter.write(flagSecondCity.getText());
                    }
                    bufferedWriter.close();
                }
            }
        }
        else if (city.equals("Second")) {
            if (!querySecondCity.equals("")) {
                result = loadData(querySecondCity);
                if (result.equals("GET request not worked")) {
                    commentSecondCity.setText(communicatConnection);
                }
                else if (result.equals("[]")) {
                    commentSecondCity.setText(communicatCity);
                }
                else if (result.equals("Limit")) {
                    commentSecondCity.setText(communicatLimit);
                }
                else {
                    editApiResult(result, city);
                    flagSecondCity.setText(querySecondCity);
                    FileWriter fileWriter = loadPathToFileToAddCities();
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    if (flagFirstCity.getText().equals("")){
                        bufferedWriter.write("1");
                    }
                    else {
                        bufferedWriter.write(flagFirstCity.getText());
                    }
                    bufferedWriter.newLine();
                    bufferedWriter.write(flagSecondCity.getText());
                    bufferedWriter.close();
                }
            }
        }
    }

    private FileWriter loadPathToFileToAddCities() throws IOException {
        absolutePath = loadAbsolutePath();
        FileWriter fileWriter = new FileWriter(absolutePath, false);
        return fileWriter;
    }

    private BufferedReader loadPathToFile() throws FileNotFoundException {
        absolutePath = loadAbsolutePath();
        FileReader fileReader = new FileReader(absolutePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return bufferedReader;
    }

    private String loadAbsolutePath() {
        URL res = getClass().getClassLoader().getResource("cities");
        File file = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        absolutePath = file.getAbsolutePath();
        return absolutePath;
    }

    public void addCitiesToMainWindow() throws FileNotFoundException {
        BufferedReader bufferedReader = loadPathToFile();
        try {
            String line;
            int counter = 0;
            while ((line = bufferedReader.readLine()) != null) {
                counter++;
                if (line.contains("1")) {
                    if (counter == 1){
                        firstCityAdd = "";
                    }
                    else {
                        secondCityAdd = "";
                    }
                }
                else {
                    if(counter == 1){
                        firstCityAdd = line;
                    }
                    else {
                        secondCityAdd = line;
                    }
                }
            }

            firstCityField.setText(firstCityAdd);
            secondCityField.setText(secondCityAdd);

            if (!firstCityAdd.equals("1")) {
                firstCitySearch();
            }
            if (!secondCityAdd.equals("1")) {
                secondCitySearch();
            }
        } catch (IOException e) { e.printStackTrace();}
    }

    private String loadData(String query) throws IOException {
        String queryEncoding = URLEncoder.encode(query, StandardCharsets.UTF_8);
        ApiConnectionCity apiCity = new ApiConnectionCity();
        ApiConnectionWeatherData apiWeatherData = new ApiConnectionWeatherData();
        ApiConnection apiConnection =  new ApiConnection();
        Config config = new Config();
        String apiKey = config.getApiKey();
        cityKey = apiConnection.executeGet("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + apiKey + "=" + queryEncoding + "&language=pl-PL&details=Key");
        weatherData = apiConnection.executeGet("http://dataservice.accuweather.com/forecasts/v1/daily/5day/" + cityKey + "?apikey=" + apiKey + "&language=pl-PL");
        return weatherData;
    }

    private void clearDataFromMainWindow(String city) {
        if (city.equals("First")) {
            clearTemperatureFirstCity();
            clearDescriptionFirstCity();
            clearImageIconsFirstCity();
        }
        else if (city.equals("Second")) {
            clearTemperatureSecondCity();
            clearDescriptionSecondCity();
            clearImageIconsSecondCity();
        }
    }

    private void clearImageIconsFirstCity() {
        Images image = new Images();
        String emptyImage = image.getImage("0");
        ImageView imageView = new ImageView(getClass().getResource(emptyImage).toExternalForm());
        imageFirst1.setGraphic(imageView);
        imageFirst2.setGraphic(imageView);
        imageFirst3.setGraphic(imageView);
        imageFirst4.setGraphic(imageView);
        imageFirst5.setGraphic(imageView);
        imageFirst12.setGraphic(imageView);
        imageFirst22.setGraphic(imageView);
        imageFirst32.setGraphic(imageView);
        imageFirst42.setGraphic(imageView);
        imageFirst52.setGraphic(imageView);
    }

    private void clearImageIconsSecondCity() {
        Images image = new Images();
        String emptyImage = image.getImage("0");
        ImageView imageView = new ImageView(getClass().getResource(emptyImage).toExternalForm());
        imageSecond1.setGraphic(imageView);
        imageSecond2.setGraphic(imageView);
        imageSecond3.setGraphic(imageView);
        imageSecond4.setGraphic(imageView);
        imageSecond5.setGraphic(imageView);
        imageSecond12.setGraphic(imageView);
        imageSecond22.setGraphic(imageView);
        imageSecond32.setGraphic(imageView);
        imageSecond42.setGraphic(imageView);
        imageSecond52.setGraphic(imageView);
    }

    private void clearDescriptionFirstCity() {
        descriptionFirst1.setText("");
        descriptionFirst2.setText("");
        descriptionFirst3.setText("");
        descriptionFirst4.setText("");
        descriptionFirst5.setText("");
        descriptionFirst12.setText("");
        descriptionFirst22.setText("");
        descriptionFirst32.setText("");
        descriptionFirst42.setText("");
        descriptionFirst52.setText("");
    }
    
    private void clearDescriptionSecondCity() {
        descriptionSecond1.setText("");
        descriptionSecond2.setText("");
        descriptionSecond3.setText("");
        descriptionSecond4.setText("");
        descriptionSecond5.setText("");
        descriptionSecond12.setText("");
        descriptionSecond22.setText("");
        descriptionSecond32.setText("");
        descriptionSecond42.setText("");
        descriptionSecond52.setText("");
    }

    private void clearTemperatureFirstCity() {
        temperatureFirst1.setText("");
        temperatureFirst2.setText("");
        temperatureFirst3.setText("");
        temperatureFirst4.setText("");
        temperatureFirst5.setText("");
        temperatureFirst12.setText("");
        temperatureFirst22.setText("");
        temperatureFirst32.setText("");
        temperatureFirst42.setText("");
        temperatureFirst52.setText("");
    }

    private void clearTemperatureSecondCity() {
        temperatureSecond1.setText("");
        temperatureSecond2.setText("");
        temperatureSecond3.setText("");
        temperatureSecond4.setText("");
        temperatureSecond5.setText("");
        temperatureSecond12.setText("");
        temperatureSecond22.setText("");
        temperatureSecond32.setText("");
        temperatureSecond42.setText("");
        temperatureSecond52.setText("");
    }

    private void editApiResult(String result, String city) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String[] tableTemperatureMinimum = new String[5];
        String[] tableTemperatureMaximum = new String[5];
        String[] tableDescriptionDay = new String[5];
        String[] tableDescriptionNight = new String[5];
        String[] tableIconNight = new String[5];
        String[] tableIconDay = new String[5];
        String[] tableOfIconResultDay = new String[5];
        String[] tableOfIconResultNight = new String[5];

        JsonNode jsonNode = objectMapper.readValue(result, JsonNode.class);
        JsonNode object = jsonNode.get("DailyForecasts");
        for (int i = 0; i < 5; i++) {
            JsonNode array = object.get(i);
            JsonNode temperature = array.get("Temperature");
            JsonNode temperatureMinimumObject = temperature.get("Minimum");
            JsonNode temperatureMinimumNode = temperatureMinimumObject.get("Value");
            String temperatureMinimumString = temperatureMinimumNode.toString();
            float temperatureMinimumFloat = (float) (((Float.valueOf(temperatureMinimumString))-32)/1.8);
            int temperatureMinimumFloatRound = Math.round(temperatureMinimumFloat);
            tableTemperatureMinimum[i] = String.valueOf(temperatureMinimumFloatRound);
            JsonNode temperatureMaximumObject = temperature.get("Maximum");
            JsonNode temperatureMaximumNode = temperatureMaximumObject.get("Value");
            String temperatureMaximumString = temperatureMaximumNode.toString();
            float temperatureMaximumFloat = (float) (((Float.valueOf(temperatureMaximumString))-32)/1.8);
            int temperatureMaximumFloatRound = Math.round(temperatureMaximumFloat);
            tableTemperatureMaximum[i] = String.valueOf(temperatureMaximumFloatRound);
            JsonNode day = array.get("Day");
            JsonNode dayDescriptionObject = day.get("IconPhrase");
            String dayDescriptionString = (dayDescriptionObject.toString()).replace("\"", "");
            tableDescriptionDay[i] = dayDescriptionString;
            JsonNode dayIconObject = day.get("Icon");
            String dayIconString = dayIconObject.toString();
            tableIconDay[i] = dayIconString;
            JsonNode night = array.get("Night");
            JsonNode nightDescriptionObject = night.get("IconPhrase");
            String nightDescriptionString = (nightDescriptionObject.toString()).replace("\"", "");
            tableDescriptionNight[i] = nightDescriptionString;
            JsonNode nightIconObject = night.get("Icon");
            String nightIconString = nightIconObject.toString();
            tableIconNight[i] = nightIconString;
        }

        for (int j = 0; j < 5; j++) {
            Images image = new Images();
            tableOfIconResultDay[j] = image.getImage(tableIconDay[j]);
            tableOfIconResultNight[j] = image.getImage(tableIconNight[j]);
        }

        if (city.equals("First")) {
            addTemperaturesToFirstCity(tableTemperatureMaximum, tableTemperatureMinimum);
            addDescriptionsToFirstCity(tableDescriptionDay, tableDescriptionNight);
            addIconsToFirstCity(tableOfIconResultDay, tableOfIconResultNight);
        }
        else if (city.equals("Second")){
            addTemperaturesToSecondCity(tableTemperatureMaximum, tableTemperatureMinimum);
            addDescriptionsToSecondCity(tableDescriptionDay, tableDescriptionNight);
            addIconsToSecondCity(tableOfIconResultDay, tableOfIconResultNight);
        }
    }

    private void addTemperaturesToFirstCity(String[] tableTemperatureMaximum, String[] tableTemperatureMinimum) {
        temperatureFirst1.setText(tableTemperatureMaximum[0] + "°C");
        temperatureFirst2.setText(tableTemperatureMaximum[1] + "°C");
        temperatureFirst3.setText(tableTemperatureMaximum[2] + "°C");
        temperatureFirst4.setText(tableTemperatureMaximum[3] + "°C");
        temperatureFirst5.setText(tableTemperatureMaximum[4] + "°C");
        temperatureFirst12.setText(tableTemperatureMinimum[0] + "°C");
        temperatureFirst22.setText(tableTemperatureMinimum[1] + "°C");
        temperatureFirst32.setText(tableTemperatureMinimum[2] + "°C");
        temperatureFirst42.setText(tableTemperatureMinimum[3] + "°C");
        temperatureFirst52.setText(tableTemperatureMinimum[4] + "°C");
    }

    private void addTemperaturesToSecondCity(String[] tableTemperatureMaximum, String[] tableTemperatureMinimum) {
        temperatureSecond1.setText(tableTemperatureMaximum[0] + "°C");
        temperatureSecond2.setText(tableTemperatureMaximum[1] + "°C");
        temperatureSecond3.setText(tableTemperatureMaximum[2] + "°C");
        temperatureSecond4.setText(tableTemperatureMaximum[3] + "°C");
        temperatureSecond5.setText(tableTemperatureMaximum[4] + "°C");
        temperatureSecond12.setText(tableTemperatureMinimum[0] + "°C");
        temperatureSecond22.setText(tableTemperatureMinimum[1] + "°C");
        temperatureSecond32.setText(tableTemperatureMinimum[2] + "°C");
        temperatureSecond42.setText(tableTemperatureMinimum[3] + "°C");
        temperatureSecond52.setText(tableTemperatureMinimum[4] + "°C");
    }

    private void addDescriptionsToFirstCity(String[] tableDescriptionDay, String[] tableDescriptionNight) {
        descriptionFirst1.setText(tableDescriptionDay[0]);
        descriptionFirst2.setText(tableDescriptionDay[1]);
        descriptionFirst3.setText(tableDescriptionDay[2]);
        descriptionFirst4.setText(tableDescriptionDay[3]);
        descriptionFirst5.setText(tableDescriptionDay[4]);
        descriptionFirst12.setText(tableDescriptionNight[0]);
        descriptionFirst22.setText(tableDescriptionNight[1]);
        descriptionFirst32.setText(tableDescriptionNight[2]);
        descriptionFirst42.setText(tableDescriptionNight[3]);
        descriptionFirst52.setText(tableDescriptionNight[4]);
    }

    private void addDescriptionsToSecondCity(String[] tableDescriptionDay, String[] tableDescriptionNight) {
        descriptionSecond1.setText(tableDescriptionDay[0]);
        descriptionSecond2.setText(tableDescriptionDay[1]);
        descriptionSecond3.setText(tableDescriptionDay[2]);
        descriptionSecond4.setText(tableDescriptionDay[3]);
        descriptionSecond5.setText(tableDescriptionDay[4]);
        descriptionSecond12.setText(tableDescriptionNight[0]);
        descriptionSecond22.setText(tableDescriptionNight[1]);
        descriptionSecond32.setText(tableDescriptionNight[2]);
        descriptionSecond42.setText(tableDescriptionNight[3]);
        descriptionSecond52.setText(tableDescriptionNight[4]);
    }

    private void addIconsToFirstCity(String[] tableOfIconResultDay, String[] tableOfIconResultNight) {
        ImageView imageView = new ImageView(getClass().getResource(tableOfIconResultDay[0]).toExternalForm());
        imageFirst1.setGraphic(imageView);
        imageView = new ImageView(getClass().getResource(tableOfIconResultDay[1]).toExternalForm());
        imageFirst2.setGraphic(imageView);
        imageView = new ImageView(getClass().getResource(tableOfIconResultDay[2]).toExternalForm());
        imageFirst3.setGraphic(imageView);
        imageView = new ImageView(getClass().getResource(tableOfIconResultDay[3]).toExternalForm());
        imageFirst4.setGraphic(imageView);
        imageView = new ImageView(getClass().getResource(tableOfIconResultDay[4]).toExternalForm());
        imageFirst5.setGraphic(imageView);
        imageView = new ImageView(getClass().getResource(tableOfIconResultNight[0]).toExternalForm());
        imageFirst12.setGraphic(imageView);
        imageView = new ImageView(getClass().getResource(tableOfIconResultNight[1]).toExternalForm());
        imageFirst22.setGraphic(imageView);
        imageView = new ImageView(getClass().getResource(tableOfIconResultNight[2]).toExternalForm());
        imageFirst32.setGraphic(imageView);
        imageView = new ImageView(getClass().getResource(tableOfIconResultNight[3]).toExternalForm());
        imageFirst42.setGraphic(imageView);
        imageView = new ImageView(getClass().getResource(tableOfIconResultNight[4]).toExternalForm());
        imageFirst52.setGraphic(imageView);
    }

    private void addIconsToSecondCity(String[] tableOfIconResultDay, String[] tableOfIconResultNight) {
        ImageView imageView = new ImageView(getClass().getResource(tableOfIconResultDay[0]).toExternalForm());
        imageSecond1.setGraphic(imageView);
        imageView = new ImageView(getClass().getResource(tableOfIconResultDay[1]).toExternalForm());
        imageSecond2.setGraphic(imageView);
        imageView = new ImageView(getClass().getResource(tableOfIconResultDay[2]).toExternalForm());
        imageSecond3.setGraphic(imageView);
        imageView = new ImageView(getClass().getResource(tableOfIconResultDay[3]).toExternalForm());
        imageSecond4.setGraphic(imageView);
        imageView = new ImageView(getClass().getResource(tableOfIconResultDay[4]).toExternalForm());
        imageSecond5.setGraphic(imageView);
        imageView = new ImageView(getClass().getResource(tableOfIconResultNight[0]).toExternalForm());
        imageSecond12.setGraphic(imageView);
        imageView = new ImageView(getClass().getResource(tableOfIconResultNight[1]).toExternalForm());
        imageSecond22.setGraphic(imageView);
        imageView = new ImageView(getClass().getResource(tableOfIconResultNight[2]).toExternalForm());
        imageSecond32.setGraphic(imageView);
        imageView = new ImageView(getClass().getResource(tableOfIconResultNight[3]).toExternalForm());
        imageSecond42.setGraphic(imageView);
        imageView = new ImageView(getClass().getResource(tableOfIconResultNight[4]).toExternalForm());
        imageSecond52.setGraphic(imageView);
    }
}