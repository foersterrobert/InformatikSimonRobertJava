import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
public class Ticketautomat extends JFrame
{
    static int gesamtesGeld;
    static int fetchedGesamtesGeld;
    static double inGesamt;
    public JPanel jp = new JPanel();

    JTextField einwurf = new JTextField(30);

    static JLabel gesGeldLabel;
    JLabel centEingabe = new JLabel("      Eingabe in Cent");
    static JLabel eingeworfen = new JLabel("      0 €");
    static JLabel gekauft = new JLabel();
    static JButton einwurf_sichern = new JButton("<html>"
                 + "<font color=#FF0000>Bestätigen</font>");
    
    GridLayout layout = new GridLayout(0, 2);

    Ticket[] hinzugefuegteTickets = new Ticket[100];
    int nummerNeuesTicket = 2;
                 

    /**
     * Konstruktor für Objekte der Klasse Ticketautomat
     */
    public Ticketautomat()
    {
        setTitle("Ticketautomat");
        setVisible(true);
        setSize(400, 400);
        read();
        
        gesamtesGeld = fetchedGesamtesGeld;
        gesGeldLabel = new JLabel("Eingenommenes Geld: "+gesamtesGeld/100 +"€");

        hinzugefuegteTickets[0] = new Ticket("Gruppenticket", 500);
        hinzugefuegteTickets[1] = new Ticket("Einzelticket", 100);

        einwurf_sichern.setBorderPainted(false);
        einwurf_sichern.setBackground(Color.GRAY);
        einwurf_sichern.setFocusPainted(false);
        einwurf_sichern.setSize(10, 10);

        
        // Ein Actionlistener, der dann aufgerufen wird, wenn der Button:"Bestätigen" gedrückt wird.
        einwurf_sichern.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String eingeworfenInput = einwurf.getText();
                
                if (eingeworfenInput.equals("Schluessel")){
                    ticketHinzufügen();
                } else {
                    int eingeworfenInt = Integer.parseInt(eingeworfenInput);
                    inGesamt = inGesamt + eingeworfenInt;
                    eingeworfen.setText("      "+(inGesamt/100) +" €");
                    einwurf.setText("");
                }
            }
        });
        
        // Ein Actionlistener, der dann aufgerufen wird, wenn im Textfeld die Entertaste gedrückt wird.
        einwurf.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String eingeworfenInput = einwurf.getText();
                
                if (eingeworfenInput.equals("Schluessel")){
                    ticketHinzufügen();
                } else {
                    int eingeworfenInt = Integer.parseInt(eingeworfenInput);
                    inGesamt = inGesamt + eingeworfenInt;
                    eingeworfen.setText("      "+(inGesamt/100) +" €");
                    einwurf.setText("");
                }
            }
        });
        

        // Es werden die ersten wichtigen Teile des GUIs auf das Panel und dann aufs Frame "gesetzt".
        jp.add(centEingabe);
        jp.add(einwurf);
        jp.add(eingeworfen);
        jp.add(einwurf_sichern);
        jp.add(hinzugefuegteTickets[0]);
        jp.add(hinzugefuegteTickets[1]);
        jp.add(gekauft);
        
        jp.setLayout(layout);
        layout.setHgap(10);
        layout.setVgap(10);

    
        add(jp);
    }

    // Diese Methode erstellt ein GUI, um ein Ticket hinzuzufügen oder den Preis eines bereits vorhandenen Tickets zu ändern. Es kann mit dem passenden Schlüssel:"Schluessel" aufgerufen werden.
    public void ticketHinzufügen(){

        JFrame paeFrame = new JFrame();
        JPanel paeJP = new JPanel();

        paeFrame.setVisible(true);
        paeFrame.setSize(400, 400);
        paeFrame.setTitle("Preisändern");

        JLabel descLabel = new JLabel("Ändern Sie den Ticketpreis, indem Sie den Ticketnamen von jenem Ticket eingeben. Ein neues Ticket kann erstellt werden, wenn ein Ticketname eingegeben wird, der nicht vorhanden ist."); // Formatierung fehlt
        JLabel newTicketnameLabel = new JLabel("Ticketname");
        JTextField newTicketname = new JTextField(20);
        JLabel newTicketpreisLabel = new JLabel("Ticketpreis");
        JTextField newTicketpreis = new JTextField(20);
        JButton newTicketBestaetigen = new JButton("Preis ändern/Ticket hinzufügen");

        paeJP.add(descLabel);
        paeJP.add(newTicketnameLabel);
        paeJP.add(newTicketname);
        paeJP.add(newTicketpreisLabel);
        paeJP.add(newTicketpreis);
        paeJP.add(newTicketBestaetigen);
        paeJP.add(gesGeldLabel);
        paeFrame.add(paeJP);

        newTicketBestaetigen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                String ticketnameString = newTicketname.getText();
                String ticketpreisString = newTicketpreis.getText();
                int ticketpreisInt = Integer.parseInt(ticketpreisString);

                for (int i = 0; i < nummerNeuesTicket; i++){
                    System.out.println(hinzugefuegteTickets[i].ticketname);
                    if (hinzugefuegteTickets[i].ticketname.equals(ticketnameString)){
                        jp.remove(hinzugefuegteTickets[i]);
                    }
                }

                jp.remove(gekauft);
                jp.remove(gesGeldLabel);

                hinzugefuegteTickets[nummerNeuesTicket] = new Ticket(ticketnameString, ticketpreisInt);

                jp.add(hinzugefuegteTickets[nummerNeuesTicket]);
                jp.add(gekauft);
                jp.add(gesGeldLabel);

                repaint(); 
                
                newTicketname.setText("");
                newTicketpreis.setText("");

                nummerNeuesTicket++;
            }
        });
    }

   

    // Diese Methode liest aus der Datei "gesamtesGeld.txt", wie viel Geld von dem Ticketautomaten eingenommen wurde.
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

    // Diese Methode speichert wie viel Geld mit dem Ticketautomaten eingenommen wurde. 
    public static void write() throws IOException {
      FileWriter f = new FileWriter("gesamtesGeld.txt");
      f.write(gesamtesGeld+"");
      f.close();
    }

    public static void main(String[]arg) throws UnsupportedLookAndFeelException, IllegalAccessException {

        // Durch diese Funktion wird der "Style" des ganzen Ticketautomaten an das System angepasst.
        try
        {
            try
            {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (InstantiationException ie)
            {
                ie.printStackTrace();
            }
        }
        catch (ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
        new Ticketautomat();
    }
}