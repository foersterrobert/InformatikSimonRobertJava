import javax.swing.*;
import java.awt.event.*;

public class Ticketautomat
{
    static private int bisherGezahlt;
    static private int summe;
    Ticket kinderTicket = new Ticket(160, "Ticket-Kinder");
    Ticket normalTicket = new Ticket(240, "Ticket-Erwachsene");
    Ticket gruppenTicket = new Ticket(320, "Ticket-Gruppen");

    public Ticketautomat()
    {
        bisherGezahlt = 0;
        summe = 0;

        JFrame frame = new JFrame("Ticketautomat");
        JPanel container = new JPanel();
        JLabel label = new JLabel("Ticketautomat");
        JMenu ticketMenu = new JMenu("Tickets");

        JLabel bisherGezahltLabel = new JLabel("Bisher gezahlt: " + bisherGezahlt);
        JLabel ticketPreisLabel = new JLabel("Ticketpreis: " + kinderTicket.ticketPreis);
        JTextField input = new JTextField(30);
        JButton button = new JButton("Einwerfen");

        JDialog ticketDialog = new JDialog();
        JLabel ticketLabel = new JLabel("Ticket: " + kinderTicket.ticketName);
        JLabel wechseLabel = new JLabel("Wechselgeld: " + summe);
        
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String inputSummeString = input.getText();
                int inputSummeInt = Integer.parseInt(inputSummeString);
                int wechselGeld = GeldEinwerfen(inputSummeInt);
                input.setText("");
                bisherGezahltLabel.setText("Bisher gezahlt: " + bisherGezahlt);
                if (wechselGeld >= 0) {
                    JOptionPane.showMessageDialog(frame, kinderTicket.ticketName + " gedruckt. \n Wechselgeld: " + wechselGeld, "Ticket gedruckt", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        frame.setVisible(true);
        frame.setSize(500, 600);

        container.add(label);
        container.add(ticketMenu);
        container.add(bisherGezahltLabel);
        container.add(ticketPreisLabel);
        container.add(input);
        container.add(button);

        ticketDialog.add(ticketLabel);
        ticketDialog.add(wechseLabel);

        frame.add(container);
    }
    
    public int GeldEinwerfen(int betrag)
    {
        bisherGezahlt += betrag;
        summe += betrag;
        if (bisherGezahlt >= kinderTicket.ticketPreis) {
            bisherGezahlt -= kinderTicket.ticketPreis;
            int wechselgeld = bisherGezahlt;
            bisherGezahlt = 0;
            return wechselgeld;
        }
        return -1;
    }

    public static void main(String[]arg){
        Ticketautomat automat = new Ticketautomat();
    }
}
