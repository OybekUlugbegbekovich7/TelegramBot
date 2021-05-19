import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    //284beb7c52c19588f2cb5d7433031e4f
    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=87a7472de2558cd32188e4ba44235414");
        Scanner scanner = new Scanner((InputStream) url.getContent());
        String results = "";
        while(scanner.hasNext()){
            results += scanner.nextLine();
        }
        JSONObject object = new JSONObject(results);
        //JSONObject object1 = new JSONObject("country");
        model.setName(object.getString("name"));
        //model.setCountry(object1.getString("country"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String)obj.get("icon"));
            model.setMain((String)obj.get("main"));

        }
        return  "City: " + model.getName() + "\n" +
                "Temperatura: " + model.getTemp() + "\n" +
                "Humidity: " + model.getHumidity() + " %" + "\n" +
                "Country: " + model.getCountry() + "\n" +
                "https://openweathermap.org/img/w/" + model.getIcon()  + ".png";
    }
}
