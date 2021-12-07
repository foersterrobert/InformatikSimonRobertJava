import javax.swing.*;

/**
 * Beschreiben Sie hier die Klasse Ticket.
 * 
 * @author Simon Laschinger
 * @version v1.0
 */
public class Ticket extends JPanel
{
    JFrame f = new JFrame();
    JPanel jp2 = new JPanel();
    public int preis;
    public String ticketname;
    public JButton test;
    /**
     * Konstruktor für Objekte der Klasse Ticket
     */
    public Ticket(String _ticketname, int _preis/*, JButton _test*/)
    {
        preis = _preis;
        ticketname = _ticketname;
        /*test = _test;*/
        test = new JButton("Test");
        add(test);
        
    }
    
}
