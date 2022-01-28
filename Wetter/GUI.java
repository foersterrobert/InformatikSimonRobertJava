import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GUI extends JFrame {
    Font comfortaa12;
    Font comfortaa15;
    Font comfortaa20;
    Font comfortaa25;
    Font comfortaa30;
    Font comfortaa40;

    JPanel jp = new JPanel();
    JTextField jtf = new JTextField(10);
    JButton jsubmit = new JButton("Submit");
    JLabel ortLabel = new JLabel("");

    String description;
    String icon;

    // Variablen fuer den Wetterbericht
    JLabel tempLabel = new JLabel();
    JLabel descriptionLabel = new JLabel();
    JLabel humidityLabel = new JLabel();
    JLabel speedLabel = new JLabel();
    JLabel feels_likeLabel = new JLabel();
    JLabel iconLabel = new JLabel();

    // JSON parsing
    String name;
    JSONObject wind;
    Double speed;
    JSONObject main;
    Double temp;
    Double feels_like;
    Long humidity;

    public GUI() {

        try {
            comfortaa12 = Font.createFont(Font.TRUETYPE_FONT, new File("src/Comfortaa-Regular.ttf")).deriveFont(12f);
            comfortaa15 = Font.createFont(Font.TRUETYPE_FONT, new File("src/Comfortaa-Regular.ttf")).deriveFont(15f);
            comfortaa20 = Font.createFont(Font.TRUETYPE_FONT, new File("src/Comfortaa-Regular.ttf")).deriveFont(20f);
            comfortaa25 = Font.createFont(Font.TRUETYPE_FONT, new File("src/Comfortaa-Regular.ttf")).deriveFont(25f);
            comfortaa30 = Font.createFont(Font.TRUETYPE_FONT, new File("src/Comfortaa-Regular.ttf")).deriveFont(30f);
            comfortaa40 = Font.createFont(Font.TRUETYPE_FONT, new File("src/Comfortaa-Regular.ttf")).deriveFont(40f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/Comfortaa-Regular.ttf")));
        } catch (IOException | FontFormatException e) {
        }

        add(jp);

        setVisible(true);
        setResizable(false);
        setSize(400, 285);
        setTitle("Wetterbericht – Openweather");
        setBackground(Color.WHITE);
        jp.setLayout(null);


        jsubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = jtf.getText();
                jtf.setText("");
                try {
                    fetch(input);

                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
        });
        jtf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = jtf.getText();
                jtf.setText("");
                try {
                    fetch(input);

                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
        });
        try {
            fetch("Hamburg");
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        jp.add(ortLabel);
        jp.add(iconLabel);
        jp.add(tempLabel);
        jp.add(descriptionLabel);
        jp.add(humidityLabel);
        jp.add(speedLabel);
        jp.add(feels_likeLabel);

        jp.add(jtf);
        jp.add(jsubmit);

        ortLabel.setBounds(10, 0, 400, 60);
        iconLabel.setBounds(290, 50, 120, 120);
        tempLabel.setBounds(200, 60, 200, 70);
        descriptionLabel.setBounds(10, 135, 400, 70);
        humidityLabel.setBounds(10, 50, 150, 70);
        speedLabel.setBounds(10, 66, 150, 70);
        feels_likeLabel.setBounds(10, 102, 400, 70);
        jtf.setBounds(10, 200, 155, 22);
        jsubmit.setBounds(175, 200, 90, 22);
    }

    public void fetch(String ort) throws IOException, ParseException {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + ort +
        "&appid=92c52fc8f6b4286b0a9b8106b4568820&cnt=5&units=metric&lang=de";
        URL website = new URL(url);
        BufferedReader in = new BufferedReader(new
        InputStreamReader(website.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
        write(inputLine);
        }
        in.close();

        ortLabel.setFont(comfortaa40);
        tempLabel.setFont(comfortaa30);
        descriptionLabel.setFont(comfortaa20);
        humidityLabel.setFont(comfortaa12);
        speedLabel.setFont(comfortaa12);
        feels_likeLabel.setFont(comfortaa25);
        jsubmit.setFont(comfortaa12);
        jtf.setFont(comfortaa12);

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("wetter.json")) {

    
            JSONObject wetterJSON = (JSONObject) parser.parse(reader);

            try {
                name = (String) wetterJSON.get("name");
            } catch (Exception e) {
                
            }
            
            try {
                wind = (JSONObject) wetterJSON.get("wind");
            } catch (Exception e) {
                
            }
            try {
                speed = (Double) wind.get("speed");
            } catch (Exception e) {
                
            }

            try {
                main = (JSONObject) wetterJSON.get("main");
            } catch (Exception e) {
                
            }

            try {
                temp = (Double) main.get("temp");
            } catch (Exception e) {
                
            }

            try {
                feels_like = (Double) main.get("feels_like");
            } catch (Exception e) {
                
            }

            try {
                humidity = (Long) main.get("humidity");
            } catch (Exception e) {
                
            }

            try {
                JSONArray weather = (JSONArray) wetterJSON.get("weather");
                Iterator<JSONObject> iterator = weather.iterator();
                while (iterator.hasNext()) {
                    JSONObject weatherObject = (JSONObject) iterator.next();
                    icon = (String) weatherObject.get("icon");
                    description = (String) weatherObject.get("description");
                }
            } catch (Exception e) {
                
            }



            ortLabel.setText(name);
            tempLabel.setText("" + temp + " °");
            descriptionLabel.setText("" + description);
            humidityLabel.setText("Luftfeuchtigkeit: " + humidity + "%");
            speedLabel.setText("Wind: " + speed + " km/h");
            feels_likeLabel.setText("Gefühlte: " + feels_like + " °");

            // Icon
            String path = "src/" + icon + ".png";
            BufferedImage iconBF = ImageIO.read(new File(path));
            iconLabel.setIcon(new ImageIcon(resize(iconBF, 100, 100)));
            validate();

        } catch (ParseException e1) {
            // e1.printStackTrace();
        } catch (IOException e1) {
            // e1.printStackTrace();
        }
    }

    public static void read() {
        try {
            File f = new File("wetter.json");
            Scanner fScanner = new Scanner(f);
            while (fScanner.hasNextLine()) {
                String data = fScanner.nextLine();
            }
            fScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            // e.printStackTrace();
        }
    }

    public static void write(String input) throws IOException {
        FileWriter f = new FileWriter("wetter.json");
        f.write(input);
        f.close();
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}