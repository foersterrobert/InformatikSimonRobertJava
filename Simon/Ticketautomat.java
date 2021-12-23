import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

/**
 * Die Klasse nimmt die hat zwei Methoden. Mit der ersten Methode kann die Anzahl an Cent bestimmt werden die "eingeworfen" wurde. 
 * Mit der zweiten Methode kann ein Ticket gekauft werden, das 100 Cent kostet.
 * 
 * @author Simon Laschinger
 * @version v1.0
 */
public class Ticketautomat extends JFrame
{
    static int gesamtesGeld;
    static int fetchedGesamtesGeld;
    private Ticket gruppenticket;
    private Ticket einzelticket;
    static double inGesamt;
    public JPanel jp = new JPanel();

    JTextField einwurf = new JTextField(30);

    static JLabel gesGeldLabel;
    JLabel centEingabe = new JLabel("      Eingabe in Cent");
    static JLabel eingeworfen = new JLabel("      0 €");
    static JLabel gekauft = new JLabel();
    JButton einwurf_sichern = new JButton("<html>"
                 + "<font color=#FF0000>Bestätigen</font>");
    
    GridLayout layout = new GridLayout(0, 2);
                 

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

        gruppenticket = new Ticket("Gruppenticket", 500);
        einzelticket = new Ticket("Einzelticket", 100);

        einwurf_sichern.setBorderPainted(false);
        einwurf_sichern.setBackground(Color.black);
        einwurf_sichern.setFocusPainted(false);
        einwurf_sichern.setSize(10, 10);

        einwurf_sichern.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String eingeworfenInput = einwurf.getText();
                int eingeworfenInt = Integer.parseInt(eingeworfenInput);
                inGesamt = inGesamt + eingeworfenInt;
                eingeworfen.setText("      "+(inGesamt/100) +" €");
                einwurf.setText("");
            }
        });
        
        einwurf.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String eingeworfenInput = einwurf.getText();
                int eingeworfenInt = Integer.parseInt(eingeworfenInput);
                inGesamt = inGesamt + eingeworfenInt;
                eingeworfen.setText("      "+(inGesamt/100) +" €");
                einwurf.setText("");
            }
        });
        

        jp.add(centEingabe);
        jp.add(einwurf);
        jp.add(eingeworfen);
        jp.add(einwurf_sichern);
        jp.add(einzelticket);
        jp.add(gruppenticket);
        jp.add(gekauft);
        jp.add(gesGeldLabel);
        
        jp.setLayout(layout);
        layout.setHgap(10);
        layout.setVgap(10);

    
        add(jp);
    }

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
    public static void write() throws IOException {
      FileWriter f = new FileWriter("gesamtesGeld.txt");
      f.write(gesamtesGeld+"");
      f.close();
    }

    public static void main(String[]arg) throws UnsupportedLookAndFeelException, IllegalAccessException {
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