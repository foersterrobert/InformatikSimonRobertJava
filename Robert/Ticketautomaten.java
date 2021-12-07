import javax.swing.*;

public class Ticketautomaten extends JFrame
{
    // instance variables - replace the example below with your own
    private Ticket[] tickets;
    private float bisherGezahlt;
    private float summe;

    private JPanel jp = new JPanel();
    //private JLabel 
    private JTextField einwurf = new JTextField();
    private JButton einzahlen_sichern = new JButton("Bestätigen");

    public Ticketautomaten()
    {
        tickets = new Ticket[3];
        for (int i = 0; i<tickets.length; i++) {
            tickets[i] = new Ticket(100*(i+1), "Line " + i);
        }
        bisherGezahlt = 0;
        summe = 0;

        jp.add(einwurf);
        jp.add(einzahlen_sichern);
        add(jp);
    }

    public void printPreis()
    {
        // put your code here
        System.out.println(tickets[0].ticketPreis + " cent");
    }
    
    public void printBisherGezahlt()
    {
        // put your code here
        System.out.println(bisherGezahlt + " cent");
    }
    
    public void GeldEinwerfen(float betrag)
    {
        // put your code here
        bisherGezahlt += betrag;
        summe += betrag;
        if (bisherGezahlt >= tickets[0].ticketPreis) {
            TicketDrucken();
        }
    }
    
    private void TicketDrucken()
    {
        // put your code here
        bisherGezahlt -= tickets[0].ticketPreis;
        System.out.println("####################");
        System.out.println("# " + tickets[0].ticketName);
        System.out.println("# Ticket");
        System.out.println("# " + tickets[0].ticketPreis + " cent");
        System.out.println("####################");
        System.out.println("");
        System.out.println("# " + bisherGezahlt + " cent wechselgeld");
        bisherGezahlt = 0;
    }

    public static void main(String[]arg){
        Ticketautomaten ta = new Ticketautomaten();
        System.out.println("Ticketpreis: ");
    }
}
