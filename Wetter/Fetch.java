import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Fetch {
    public Fetch() throws IOException{
        // String content = getUrlAsString("https://www.google.com");
        // System.out.println(content);
        URL website = new URL("http://api.openweathermap.org/data/2.5/weather?id=2911293&appid=92c52fc8f6b4286b0a9b8106b4568820&cnt=5&units=metric&lang=de");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(website.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}
