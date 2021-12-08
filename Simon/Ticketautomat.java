import javax.swing.*;
import java.awt.event.*;

/**
 * Die Klasse nimmt die hat zwei Methoden. Mit der ersten Methode kann die Anzahl an Cent bestimmt werden die "eingeworfen" wurde. 
 * Mit der zweiten Methode kann ein Ticket gekauft werden, das 100 Cent kostet.
 * 
 * @author Simon Laschinger
 * @version v1.0
 */
public class Ticketautomat extends JFrame
{
    static int gesamtesGeld = 0;
    private Ticket gruppenticket;
    private Ticket einzelticket;
    static double inGesamt;
    public JPanel jp = new JPanel();
    /*GroupLayout layout = new GroupLayout(0, 2);
    jp.setlayout(layout);*/

    JTextField einwurf = new JTextField(30);

    static JLabel gesGeldLabel = new JLabel("Eingenommenes Geld: 0 €");
    JLabel centEingabe = new JLabel("Eingabe in Cent");
    static JLabel eingeworfen = new JLabel("0 €");
    static JLabel gekauft = new JLabel();
    JButton einwurf_sichern = new JButton("<html>"
                 + "<font color=#FF0000>Bestätigen</font>");

    /**
     * Konstruktor für Objekte der Klasse Ticketautomat
     */
    public Ticketautomat()
    {
        setTitle("Ticketautomat");
        setVisible(true);
        setSize(400, 400);

        gruppenticket = new Ticket("Gruppenticket", 500);
        einzelticket = new Ticket("Einzelticket", 100);

        einwurf_sichern.setBounds(10, 10, 400, 25);
        einwurf_sichern.setContentAreaFilled(false);

        einwurf_sichern.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String eingeworfenInput = einwurf.getText();
                int eingeworfenInt = Integer.parseInt(eingeworfenInput);
                inGesamt = inGesamt + eingeworfenInt;
                eingeworfen.setText((inGesamt/100) +" €");
                einwurf.setText("");
            }
        });
        
        einwurf.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String eingeworfenInput = einwurf.getText();
                int eingeworfenInt = Integer.parseInt(eingeworfenInput);
                inGesamt = inGesamt + eingeworfenInt;
                eingeworfen.setText((inGesamt/100) +" €");
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

        add(jp);
    }


    public static void main(String[]arg){
        Ticketautomat ta = new Ticketautomat();
    }
}
