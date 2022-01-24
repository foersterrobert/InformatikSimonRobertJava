import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class Fetch {
    public Fetch() throws IOException {
        new GUI();
        // String content = getUrlAsString("https://www.google.com");
        // System.out.println(content);
        URL website = new URL("https://api.openweathermap.org/data/2.5/weather?q=eimsbuettel&appid=92c52fc8f6b4286b0a9b8106b4568820&cnt=5&units=metric&lang=de");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(website.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)System.out.println(inputLine);
        in.close();

    }

    public static void read() {
        try {
            File f = new File("gesamtesGeld.txt");
            Scanner fScanner = new Scanner(f);
            while (fScanner.hasNextLine()) {
                String data = fScanner.nextLine();
                
            }
            fScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public static void write() throws IOException {
        FileWriter f = new FileWriter("gesamtesGeld.txt");
        f.write("");
        f.close();
    }
}
