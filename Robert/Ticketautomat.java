import javax.swing.*;
import java.awt.event.*;

public class Ticketautomat
{
    static private Ticket[] tickets;
    static private int bisherGezahlt;
    static private int summe;

    public Ticketautomat()
    {
        tickets = new Ticket[3];
        for (int i = 0; i<tickets.length; i++) {
            tickets[i] = new Ticket(100*(i+1), "Line " + i);
        }
        bisherGezahlt = 0;
        summe = 0;

        JFrame frame = new JFrame("Ticketautomat");
        JPanel container = new JPanel();
        JLabel label = new JLabel("Ticketautomat");
        JLabel bisherGezahltLabel = new JLabel("Bisher gezahlt: " + bisherGezahlt);
        JLabel ticketPreisLabel = new JLabel("Ticketpreis: " + tickets[0].ticketPreis);
        JTextField input = new JTextField(30);
        JButton button = new JButton("Einwerfen");

        JDialog ticketDialog = new JDialog();
        JLabel ticketLabel = new JLabel("Ticket: " + tickets[0].ticketName);
        JLabel wechseLabel = new JLabel("Wechselgeld: " + summe);
        
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String inputSummeString = input.getText();
                int inputSummeInt = Integer.parseInt(inputSummeString);
                int wechselGeld = GeldEinwerfen(inputSummeInt);
                input.setText("");
                bisherGezahltLabel.setText("Bisher gezahlt: " + bisherGezahlt);
                if (wechselGeld >= 0) {
                    JOptionPane.showMessageDialog(frame, "Ticket " + tickets[0].ticketName + " gedruckt. \n Wechselgeld: " + wechselGeld, "Ticket gedruckt", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        frame.setVisible(true);
        frame.setSize(500, 600);

        container.add(label);
        container.add(bisherGezahltLabel);
        container.add(ticketPreisLabel);
        container.add(input);
        container.add(button);

        ticketDialog.add(ticketLabel);
        ticketDialog.add(wechseLabel);

        frame.add(container);
    }

    public void printPreis()
    {
        System.out.println(tickets[0].ticketPreis + " cent");
    }
    
    public void printBisherGezahlt()
    {
        // put your code here
        System.out.println(bisherGezahlt + " cent");
    }
    
    static public int GeldEinwerfen(int betrag)
    {
        // put your code here
        bisherGezahlt += betrag;
        summe += betrag;
        if (bisherGezahlt >= tickets[0].ticketPreis) {
            bisherGezahlt -= tickets[0].ticketPreis;
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
