import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ticketautomat
{
    int bisherGezahlt;
    int summe;
    Ticket[] tickets = {new Ticket(5, "Kinder-Ticket"), new Ticket(10, "Erwachsenen-Ticket"), new Ticket(15, "Studenten-Ticket"), new Ticket(20, "Gruppen-Ticket")};
    JMenuItem[] menuitems = {new JMenuItem("Kinder-Ticket"), new JMenuItem("Erwachsenen-Ticket"), new JMenuItem("Studenten-Ticket"), new JMenuItem("Gruppen-Ticket")};
    Ticket ticket;

    public Ticketautomat()
    {
        bisherGezahlt = 0;
        summe = 0;
        ticket = tickets[0];

        JFrame frame = new JFrame("Ticketautomat");
        JPanel container = new JPanel();
        JLabel title = new JLabel("Ticketautomat");
        JMenuBar menuBar = new JMenuBar();
        JMenu ticketMenu = new JMenu("Tickets");
        ImageIcon icon = new ImageIcon("icon.png");

        GridLayout frameLayout = new GridLayout(3,3, 10, 10);

        JLabel bisherGezahltLabel = new JLabel("Bisher gezahlt: " + bisherGezahlt + "€");
        JLabel ticketLabel = new JLabel("Ticket: " + ticket.ticketName);
        JLabel ticketPreisLabel = new JLabel("Ticketpreis: " + ticket.ticketPreis + "€");
        JTextField ticketInput = new JTextField(30);
        JButton einwerfenBtn = new JButton("Einwerfen");
        JButton zurueckgebenBtn = new JButton("Zurückgeben");
        JButton settingsBtn = new JButton("Einstellungen ⚙️");

        JDialog settingsDialog = new JDialog();
        
        einwerfenBtn.addActionListener(l -> {
                String inputSummeString = ticketInput.getText();
                int inputSummeInt = Integer.parseInt(inputSummeString);
                int wechselGeld = GeldEinwerfen(inputSummeInt);
                ticketInput.setText("");
                bisherGezahltLabel.setText("Bisher gezahlt: " + bisherGezahlt + "€");
                if (wechselGeld >= 0) {
                    JOptionPane.showMessageDialog(frame, ticket.ticketName + " gedruckt. \n Wechselgeld: " + wechselGeld + "€", "Ticket gedruckt", JOptionPane.INFORMATION_MESSAGE);
                }
            });

        settingsBtn.addActionListener(l -> {
                settingsDialog.setTitle("Einstellungen");
                settingsDialog.setSize(300, 200);
                settingsDialog.setLocationRelativeTo(frame);
                settingsDialog.setVisible(true);
            });

        zurueckgebenBtn.addActionListener(l -> {
            int wechselGeld = GeldZurueckgeben();
            bisherGezahltLabel.setText("Bisher gezahlt: " + bisherGezahlt + "€");
            if (wechselGeld >= 0) {
                JOptionPane.showMessageDialog(frame, "Wechselgeld: " + wechselGeld + "€", "Wechselgeld zurückgegeben", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        frame.setVisible(true);
        frame.setTitle("Ticketautomat");
        frame.setIconImage(icon.getImage());
        frame.setSize(500, 600);
        
        for (int i = 0; i < menuitems.length; i++) {
            ticketMenu.add(menuitems[i]);
            int temp = i;
            menuitems[i].addActionListener(l -> {
                ticket = tickets[temp];
                ticketLabel.setText("Ticket: " + ticket.ticketName);
                ticketPreisLabel.setText("Ticketpreis: " + ticket.ticketPreis + "€");
            });
        }
        
        menuBar.add(ticketMenu);
        
        container.setLayout(frameLayout);
        container.add(title);
        container.add(menuBar);
        container.add(settingsBtn);
        container.add(ticketLabel);
        container.add(ticketPreisLabel);
        container.add(bisherGezahltLabel);
        container.add(ticketInput);
        container.add(einwerfenBtn);
        container.add(zurueckgebenBtn);

        settingsDialog.setIconImage(icon.getImage());

        frame.add(container);
    }
    
    public int GeldEinwerfen(int betrag)
    {
        bisherGezahlt += betrag;
        summe += betrag;
        if (bisherGezahlt >= ticket.ticketPreis) {
            bisherGezahlt -= ticket.ticketPreis;
            return GeldZurueckgeben();
        }
        return -1;
    }

    public int GeldZurueckgeben()
    {
        int wechselgeld = bisherGezahlt;
        bisherGezahlt = 0;
        return wechselgeld;
    }
}
