import javax.swing.*;
import java.awt.event.*;

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
        JLabel label = new JLabel("Ticketautomat");
        JMenuBar menuBar = new JMenuBar();
        JMenu ticketMenu = new JMenu("Tickets");

        ImageIcon icon = new ImageIcon("icon.png");

        JLabel bisherGezahltLabel = new JLabel("Bisher gezahlt: " + bisherGezahlt + "€");
        JLabel ticketLabel = new JLabel("Ticket: " + ticket.ticketName);
        JLabel ticketPreisLabel = new JLabel("Ticketpreis: " + ticket.ticketPreis + "€");
        JTextField ticketInput = new JTextField(30);
        JButton einwerfenBtn = new JButton("Einwerfen");
        JButton zurueckgebenBtn = new JButton("Zurückgeben");
        JButton settingsBtn = new JButton("Einstellungen ⚙️");

        JDialog settingsDialog = new JDialog();
        
        einwerfenBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String inputSummeString = ticketInput.getText();
                int inputSummeInt = Integer.parseInt(inputSummeString);
                int wechselGeld = GeldEinwerfen(inputSummeInt);
                ticketInput.setText("");
                bisherGezahltLabel.setText("Bisher gezahlt: " + bisherGezahlt + "€");
                if (wechselGeld >= 0) {
                    JOptionPane.showMessageDialog(frame, ticket.ticketName + " gedruckt. \n Wechselgeld: " + wechselGeld + "€", "Ticket gedruckt", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        settingsBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                settingsDialog.setTitle("Einstellungen");
                settingsDialog.setSize(300, 200);
                settingsDialog.setLocationRelativeTo(frame);
                settingsDialog.setVisible(true);
            }
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
        }

        menuBar.add(ticketMenu);

        container.add(label);
        container.add(menuBar);
        container.add(bisherGezahltLabel);
        container.add(ticketLabel);
        container.add(ticketPreisLabel);
        container.add(ticketInput);
        container.add(einwerfenBtn);
        container.add(settingsBtn);
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
