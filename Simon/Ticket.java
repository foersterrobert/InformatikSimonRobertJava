import javax.swing.*;
import java.awt.event.*;

/**
 * Beschreiben Sie hier die Klasse Ticket.
 * 
 * @author Simon Laschinger
 * @version v1.0
 */
public class Ticket extends JPanel
{
    JPanel jp2 = new JPanel();
    public int preis;
    public String ticketname;
    static JButton ticketBtn;

    
    
    /**
     * Konstruktor für Objekte der Klasse Ticket
     */
    public Ticket(String _ticketname, int _preis)
    {
        preis = _preis;
        ticketname = _ticketname;
        ticketBtn = new JButton(ticketname+" "+preis/100+" €");
        
        ticketBtn.setToolTipText("Ein "+ticketname+" kaufen!");
        
        // Ein Actionlistener, der dann aufgerufen wird, wenn auf ein Ticket gedrückt wird.
        ticketBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (Ticketautomat.inGesamt >= preis){
                    Ticketautomat.inGesamt -= preis;
                    Ticketautomat.eingeworfen.setText("      "+(Ticketautomat.inGesamt/100) +" €");
                    ticketDrucken();
                    Ticketautomat.gesamtesGeld += preis;
                    Ticketautomat.gesGeldLabel.setText("Eingenommenes Geld: "+Ticketautomat.gesamtesGeld/100+" €");
                    try
                    {
                        Ticketautomat.write();
                    }
                    catch (java.io.IOException ioe)
                    {
                        ioe.printStackTrace();
                    }
                } else {
                    Ticketautomat.gekauft.setText("Bitte werfen Sie genügend Geld ein.");
                }
            }
        });
        
        
        
        add(ticketBtn);
    }

    // Eine Methode, die dem Käufer das Ticket "ausdruckt"/zeigt.
    public void ticketDrucken(){
        JFrame tdFrame = new JFrame();
        JPanel tdJP = new JPanel();

        tdFrame.setVisible(true);
        tdFrame.setSize(200, 200);
        tdFrame.setTitle(ticketname);

        JButton jbticketname = new JButton(ticketname);
        JButton jbticketpreis = new JButton(""+preis/100+" €");

        tdJP.add(jbticketname);
        tdJP.add(jbticketpreis);
        
        tdFrame.add(tdJP);
    }
}
