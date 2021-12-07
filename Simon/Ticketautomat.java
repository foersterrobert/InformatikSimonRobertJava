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
    private int gesamtesGeld = 0;
    private Ticket gruppenticket;
    private Ticket einzelticket;
    private double inGesamt;
    public JPanel jp = new JPanel();

    JTextField einwurf = new JTextField(30);

    JLabel gesGeldLabel = new JLabel("Eingenommenes Geld: 0 €");
    JLabel centEingabe = new JLabel("Eingabe in Cent");
    JLabel eingeworfen = new JLabel("0 €");
    JLabel gekauft = new JLabel();
    JButton einwurf_sichern = new JButton("<html>"
                 + "<font color=#FF0000>Bestätigen</font>");
    JButton einzelticketKaufen = new JButton("<html>"
                 + "<font color=#000000>Einzelticket 1€</font>");
    JButton gruppenticketKaufen = new JButton("<html>"
                 + "<font color=#000000>Gruppenticket 5€</font>");

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
        gruppenticketKaufen.setToolTipText("Ein Gruppenticket kaufen!");
        einzelticketKaufen.setToolTipText("Ein Einzelticket kaufen!");

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
        
        einzelticketKaufen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (inGesamt >= einzelticket.preis){
                    inGesamt -= einzelticket.preis;
                    eingeworfen.setText((inGesamt/100) +" €");
                    gekauft.setText("Einzelticket wird gedruckt…");
                    gesamtesGeld += einzelticket.preis;
                    gesGeldLabel.setText("Eingenommenes Geld: "+gesamtesGeld/100+" €");
                } else {
                    gekauft.setText("Bitte werfen Sie genügend Geld ein.");
                }
            }
        });
        
        gruppenticketKaufen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (inGesamt >= gruppenticket.preis){
                    inGesamt -= gruppenticket.preis;
                    eingeworfen.setText((inGesamt/100) +" €");
                    gekauft.setText("Gruppenticket wird gedruckt…");
                    gesamtesGeld += gruppenticket.preis;
                    gesGeldLabel.setText("Eingenommenes Geld: "+gesamtesGeld/100+" €");
                } else {
                    gekauft.setText("Bitte werfen Sie genügend Geld ein.");
                }
            }
        });

        jp.add(centEingabe);
        jp.add(einwurf);
        jp.add(eingeworfen);
        jp.add(einwurf_sichern);
        jp.add(einzelticketKaufen);
        jp.add(gruppenticketKaufen);
        jp.add(gekauft);
        jp.add(gesGeldLabel);


        
        add(jp);
    }


    public static void main(String[]arg){
        /*Ticketautomat ta = new Ticketautomat();*/
    }
}
