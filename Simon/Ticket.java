import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** 
 * Diese Klassse ist für jegliche Prozesse zuständig, die direkt mit der
 * Verarbeitung des Tickets zu tun haben.
 * 
 * @author Simon Laschinger
 * @version v1.0
 */

public class Ticket extends JPanel {
    public double preis;
    public double preisTag;
    public String ticketname;
    static JButton ticketBtn;

    /**
     * Konstruktor für Objekte der Klasse Ticket
     */
    public Ticket(String _ticketname, int _preis) {
        preis = _preis;
        ticketname = _ticketname;
        preisTag = preis / 100;
        
        ticketBtn = new JButton(ticketname + " " + String.format("%,.2f", preisTag) + " €");

        ticketBtn.setToolTipText("Ein " + ticketname + " kaufen!");

        // Ein Actionlistener, der dann aufgerufen wird, wenn auf ein Ticket gedrückt
        // wird.
        ticketBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Ticketautomat.inGesamt >= preisTag) {
                    Ticketautomat.inGesamt -= preisTag;
                    Ticketautomat.eingeworfen.setText("<html>"+ "<center><font color=#FFFFFF>"+String.format("%,.2f", Ticketautomat.inGesamt) +" €"+"</font></center>");

                    ticketDrucken();
                    Ticketautomat.gesamtesGeld += preis;
                    Ticketautomat.gesamtesGeldTag = Ticketautomat.gesamtesGeld / 100;
                    Ticketautomat.gesGeldLabel.setText("Eingenommenes Geld: "+ String.format("%,.2f", Ticketautomat.gesamtesGeldTag) +"€");
                    try {
                        Ticketautomat.write();
                    } catch (java.io.IOException ioe) {
                        ioe.printStackTrace();
                    }
                } else {
                    Ticketautomat.gekauft.setText("<html>"+ "<center><font color=#FFFFFF>Bitte werfen Sie Geld ein!</font></center>");
                }
            }
        });

        add(ticketBtn);
    }

    // Eine Methode, die dem Käufer das Ticket "ausdruckt"/zeigt.
    public void ticketDrucken() {
        JFrame tdFrame = new JFrame();
        JPanel tdJP = new JPanel();

        DateFormat df = new SimpleDateFormat("EEE, d.M.yyyy");
        Date date = Ticketautomat.cal.getTime();
        String dateForButton = df.format(date);

        DateFormat dfhm = new SimpleDateFormat("HH:mm:ss");
        Date datehm = Ticketautomat.cal.getTime();
        String dateForButtonhm = dfhm.format(datehm);

        tdFrame.setVisible(true);
        tdFrame.setSize(200, 120);
        tdFrame.setTitle(ticketname);
        tdFrame.setResizable(false);

        JButton jbticketname = new JButton(ticketname);
        JButton jbticketpreis = new JButton("" + String.format("%,.2f", preisTag) + " €");
        JButton jbcurrentDate = new JButton(dateForButton);
        JButton jbcurrentDateHM = new JButton(dateForButtonhm);

        tdJP.add(jbticketname);
        tdJP.add(jbticketpreis);
        tdJP.add(jbcurrentDate);
        tdJP.add(jbcurrentDateHM);

        tdFrame.add(tdJP);
    }
}
