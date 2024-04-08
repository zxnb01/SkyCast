import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SkyCast {
    private static final String API_KEY = "9be8bcb47c3dbcc810006c2826aa6780";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter city name: ");
            String city = scanner.nextLine();
            String weatherData = getWeatherData(city);
            System.out.println("Weather data for " + city + ":\n" + weatherData);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getWeatherData(String city) throws IOException, InterruptedException {
        String urlString = API_URL + "?q=" + city + "&appid=" + API_KEY;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
