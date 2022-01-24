import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GUI extends JFrame {
    JPanel jp = new JPanel();
    JTextField jtf = new JTextField(30);
    JButton jsubmit = new JButton("Submit");
    JLabel wetterLabel = new JLabel("");

    public GUI() throws IOException {
        jp.add(jtf);
        jp.add(jsubmit);
        jp.add(wetterLabel);
        add(jp);
        setVisible(true);
        setSize(400, 400);
        fetch();
    }

    public void fetch() throws IOException {
        URL website = new URL(
            "https://api.openweathermap.org/data/2.5/weather?q=eimsbuettel&appid=92c52fc8f6b4286b0a9b8106b4568820&cnt=5&units=metric&lang=de"
        );
        BufferedReader in = new BufferedReader(new InputStreamReader(website.openStream()));
        JSONTokener tokener = new JSONTokener(in);
        JSONObject json = new JSONObject(tokener);

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        JSONObject json = new JSONObject(sb.toString());

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            wetterLabel.setText(inputLine);
            write(inputLine);
        }
        in.close();
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