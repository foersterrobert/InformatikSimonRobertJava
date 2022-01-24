import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GUI extends JFrame {
    JPanel jp = new JPanel();
    JTextField jtf = new JTextField(10);
    JButton jsubmit = new JButton("Submit");
    JLabel ortLabel = new JLabel("");

    public GUI() throws IOException, ParseException {
        jp.add(jtf);
        jp.add(jsubmit);
        jp.add(ortLabel);
        add(jp);
        setVisible(true);
        setSize(400, 400);
        jsubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = jtf.getText();
                try {
                    fetch(input);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                jtf.setText("");
            }
        });
        jtf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = jtf.getText();
                try {
                    fetch(input);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                jtf.setText("");
            }
        });
        fetch("Hamburg");
    }

    public void fetch(String ort) throws IOException, ParseException {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + ort + "&appid=92c52fc8f6b4286b0a9b8106b4568820&cnt=5&units=metric&lang=de";
        URL website = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(website.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            write(inputLine);
        }
        in.close();

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("wetter.json")) {

            JSONObject wetterJSON = (JSONObject) parser.parse(reader);

            String name = (String) wetterJSON.get("name");
            System.out.println("Ortsname: "+name);
            ortLabel.setText(name);

            long timezone = (long) wetterJSON.get("timezone");
            System.out.println("Zeitzone: "+timezone);

            JSONObject main = (JSONObject) wetterJSON.get("main");
            // System.out.println(main);

            Double temp = (Double) main.get("temp");
            System.out.println("Temperatur: "+temp);

            Long humidity = (Long) main.get("humidity");
            System.out.println("Luftfeuchtigkeit: "+humidity);

            JSONArray weather = (JSONArray) wetterJSON.get("weather");
            // System.out.println(weather);

            
            Iterator<JSONObject> iterator = weather.iterator();
            while (iterator.hasNext()) {
                JSONObject weatherObject = (JSONObject) iterator.next();
                // System.out.println(weatherObject);

                String icon = (String) weatherObject.get("icon");
                System.out.println("Icon: "+icon);

                String description = (String) weatherObject.get("description");
                System.out.println("Beschreibung: "+description);
            }

        }
    }

    public static void read() {
        try {
            File f = new File("wetter.json");
            Scanner fScanner = new Scanner(f);
            while (fScanner.hasNextLine()) {
                String data = fScanner.nextLine();
                System.out.println(data);
            }
            fScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void write(String input) throws IOException {
        FileWriter f = new FileWriter("wetter.json");
        f.write(input);
        f.close();
    }
}