import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 
 * 
 * @author Simon Laschinger
 * @version v1.0
 */
public class Ticketautomat extends JFrame {
    static int gesamtesGeld;
    static int fetchedGesamtesGeld;
    static double gesamtesGeldTag;
    static double inGesamt;
    public JPanel jp = new JPanel();

    JTextField einwurf = new JTextField(30);

    static JLabel gesGeldLabel;
    static JLabel jlPlaceholder = new JLabel("");
    static JButton jbgeldAuszahlen = new JButton("Geld auszahlen");
    JLabel centEingabe = new JLabel("<html>"
            + "<center><font color=#FFFFFF>Eingabe in Euro</font></center>");
    static JLabel eingeworfen = new JLabel("<html>"
            + "<font color=#FFFFFF>      0 €</font>");
    static JLabel gekauft = new JLabel();
    static JButton einwurf_sichern = new JButton("<html>"
            + "<font color=#FF0000>Bestätigen</font>");

    GridLayout layout = new GridLayout(0, 2);

    Ticket[] hinzugefuegteTickets = new Ticket[100];
    int nummerNeuesTicket = 2;

    static Calendar cal = Calendar.getInstance();
    static JButton datumAuswaehlen;

    /**
     * Konstruktor für Objekte der Klasse Ticketautomat
     */
    public Ticketautomat() {
        setTitle("Ticketautomat");
        setVisible(true);
        setResizable(false);
        setSize(400, 400);
        read();

        gesamtesGeld = fetchedGesamtesGeld;
        gesamtesGeldTag = gesamtesGeld / 100;
        gesGeldLabel = new JLabel("Eingenommenes Geld: " + String.format("%,.2f", gesamtesGeldTag) + "€");

        hinzugefuegteTickets[0] = new Ticket("Gruppenticket", 500);
        hinzugefuegteTickets[1] = new Ticket("Einzelticket", 100);

        einwurf_sichern.setBorderPainted(false);
        einwurf_sichern.setBackground(Color.GRAY);
        einwurf_sichern.setFocusPainted(false);

        jbgeldAuszahlen.setBackground(Color.GRAY);

        DateFormat df = new SimpleDateFormat("EEE, d.M.yyyy");
        Date date = cal.getTime();
        String dateForButton = df.format(date);
        datumAuswaehlen = new JButton(dateForButton);

        datumAuswaehlen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainFrame();
            }
        });

        // Ein Actionlistener, der dann aufgerufen wird, wenn der Button:"Bestätigen"
        // gedrückt wird.
        einwurf_sichern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String eingeworfenInput = einwurf.getText();

                if (eingeworfenInput.equals("Schluessel")) {
                    ticketHinzufügen();
                    einwurf.setText("");
                } else if (!isNumeric(eingeworfenInput)) {

                    gekauft.setText("<html>"
                            + "<center><font color=#FFFFFF>Bitte werfen Sie Geld ein!</font></center>");
                    einwurf.setText("");
                } else {
                    double eingeworfenInt = Double.parseDouble(eingeworfenInput);
                    inGesamt = inGesamt + eingeworfenInt;
                    eingeworfen.setText("<html>" + "<center><font color=#FFFFFF>" + String.format("%,.2f", inGesamt)
                            + " €" + "</font></center>");
                    einwurf.setText("");
                    gekauft.setText("");
                }
            }
        });

        // Ein Actionlistener, der dann aufgerufen wird, wenn im Textfeld die Entertaste
        // gedrückt wird.
        einwurf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String eingeworfenInput = einwurf.getText();

                if (eingeworfenInput.equals("Schluessel")) {
                    ticketHinzufügen();
                    einwurf.setText("");
                } else if (!isNumeric(eingeworfenInput)) {
                    gekauft.setText("<html>"
                            + "<center><font color=#FFFFFF>Bitte werfen Sie Geld ein!</font></center>");
                    einwurf.setText("");
                } else {
                    double eingeworfenInt = Double.parseDouble(eingeworfenInput);
                    inGesamt = inGesamt + eingeworfenInt;
                    eingeworfen.setText("<html>" + "<center><font color=#FFFFFF>" + String.format("%,.2f", inGesamt)
                            + " €" + "</font></center>");
                    einwurf.setText("");
                    gekauft.setText("");
                }
            }
        });

        jbgeldAuszahlen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                geldAuszahlen();
            }
        });

        // Es werden die ersten wichtigen Teile des GUIs auf das Panel und dann aufs
        // Frame "gesetzt".
        jp.add(centEingabe);
        jp.add(einwurf);
        jp.add(eingeworfen);
        jp.add(einwurf_sichern);
        jp.add(hinzugefuegteTickets[0]);
        jp.add(hinzugefuegteTickets[1]);
        jp.add(gekauft);
        // jp.add(jlPlaceholder);
        jp.add(jbgeldAuszahlen);
        jp.add(datumAuswaehlen);

        jp.setLayout(layout);
        layout.setHgap(10);
        layout.setVgap(10);

        add(jp);
    }

    public void geldAuszahlen() {
        JFrame gaFrame = new JFrame();
        gaFrame.setVisible(true);
        gaFrame.setSize(200, 80);
        gaFrame.setTitle("Ausgezahltes Geld");
        gaFrame.setResizable(false);
        JPanel gaPanel = new JPanel();
        JLabel gaLabel = new JLabel("<html>" + "<font color=#FFFFFF> Ausgezahltes Geld: <br>" + inGesamt + "</font>");

        gaPanel.add(gaLabel);
        gaFrame.add(gaPanel);

        inGesamt = 0;
        eingeworfen.setText("<html>" + "<font color=#FFFFFF> 0 € </font>");

    }

    // Diese Methode erstellt ein GUI, um ein Ticket hinzuzufügen oder den Preis
    // eines bereits vorhandenen Tickets zu ändern. Es kann mit dem passenden
    // Schlüssel:"Schluessel" aufgerufen werden.
    public void ticketHinzufügen() {

        JFrame paeFrame = new JFrame();
        JPanel paeJP = new JPanel();
        JPanel tnJP = new JPanel();
        JPanel tpJP = new JPanel();

        GridLayout paeLayout = new GridLayout(1, 0);

        paeFrame.setVisible(true);
        paeFrame.setSize(400, 400);
        paeFrame.setTitle("Preis ändern");
        paeFrame.setResizable(false);
        paeFrame.setLayout(paeLayout);

        JLabel descLabel = new JLabel(
                "<html> Ändern Sie den Ticketpreis, indem Sie den <br> Ticketnamen von jenem Ticket eingeben. <br> Ein neues Ticket kann erstellt werden, <br> wenn ein Ticketname  eingegeben wird, <br> der nicht vorhanden ist."); // Formatierung
                                                                                                                                                                                                                                      // fehlt
        JLabel newTicketnameLabel = new JLabel("Ticketname");
        JTextField newTicketname = new JTextField(20);
        JLabel newTicketpreisLabel = new JLabel("Ticketpreis");
        JTextField newTicketpreis = new JTextField(20);
        JButton newTicketBestaetigen = new JButton("Preis ändern/Ticket hinzufügen");

        paeJP.add(descLabel);
        tnJP.add(newTicketnameLabel);
        tnJP.add(newTicketname);
        tpJP.add(newTicketpreisLabel);
        tpJP.add(newTicketpreis);
        paeJP.add(tnJP);
        paeJP.add(tpJP);
        paeJP.add(newTicketBestaetigen);
        paeJP.add(gesGeldLabel);
        paeFrame.add(paeJP);

        newTicketBestaetigen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String ticketnameString = newTicketname.getText();
                String ticketpreisString = newTicketpreis.getText();
                Double ticketpreisDouble = Double.parseDouble(ticketpreisString);
                int ticketpreisInt = (int) (ticketpreisDouble * 100);

                for (int i = 0; i < nummerNeuesTicket; i++) {
                    if (hinzugefuegteTickets[i].ticketname.equals(ticketnameString)) {
                        jp.remove(hinzugefuegteTickets[i]);
                    }
                }

                jp.remove(gekauft);
                // jp.remove(jlPlaceholder);
                jp.remove(jbgeldAuszahlen);
                jp.remove(datumAuswaehlen);

                hinzugefuegteTickets[nummerNeuesTicket] = new Ticket(ticketnameString, ticketpreisInt);

                jp.add(hinzugefuegteTickets[nummerNeuesTicket]);
                jp.add(gekauft);
                // jp.add(jlPlaceholder);
                jp.add(jbgeldAuszahlen);
                jp.add(datumAuswaehlen);

                repaint();
                validate();

                newTicketname.setText("");
                newTicketpreis.setText("");

                nummerNeuesTicket++;
            }
        });
    }

    // Diese Methode liest aus der Datei "gesamtesGeld.txt", wie viel Geld von dem
    // Ticketautomaten eingenommen wurde.
    public static void read() {
        try {
            File f = new File("gesamtesGeld.txt");
            Scanner fScanner = new Scanner(f);
            while (fScanner.hasNextLine()) {
                String data = fScanner.nextLine();
                fetchedGesamtesGeld = Integer.parseInt(data);
            }
            fScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Diese Methode speichert wie viel Geld mit dem Ticketautomaten eingenommen
    // wurde.
    public static void write() throws IOException {
        FileWriter f = new FileWriter("gesamtesGeld.txt");
        f.write(gesamtesGeld + "");
        f.close();
    }

    // Diese Methode prüft, ob es sich bei dem eingegebenen String um eine Nummer
    // handelt.
    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] arg) throws UnsupportedLookAndFeelException, IllegalAccessException {

        // Durch diese Funktion wird der "Style" des ganzen Ticketautomaten an das
        // System angepasst.
        try {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (InstantiationException ie) {
                ie.printStackTrace();
            }
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        new Ticketautomat();

    }
}