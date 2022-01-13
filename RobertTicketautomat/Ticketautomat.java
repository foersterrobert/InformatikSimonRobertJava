import java.awt.*;
import javax.swing.*;

public class Ticketautomat {
    float bisherGezahlt;
    float bisherEingenommen;
    Ticket[] tickets = { new Ticket(2.40f, "Kinder-Ticket"), new Ticket(5.20f, "Erwachsenen-Ticket"),
            new Ticket(4.60f, "Studenten-Ticket"), new Ticket(7.40f, "Gruppen-Ticket") };
    JMenuItem[] menuitems = { new JMenuItem("Kinder-Ticket"), new JMenuItem("Erwachsenen-Ticket"),
            new JMenuItem("Studenten-Ticket"), new JMenuItem("Gruppen-Ticket") };
    Ticket ticket;

    public Ticketautomat() {
        bisherGezahlt = 0.0f;
        bisherEingenommen = 0.0f;
        ticket = tickets[0];

        JFrame frame = new JFrame("Ticketautomat");
        JPanel container = new JPanel();
        JLabel title = new JLabel("Ticketautomat");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        JMenuBar menuBar = new JMenuBar();
        JMenu ticketMenu = new JMenu("Ticketauswahl");
        ImageIcon icon = new ImageIcon("icon.png");

        JLabel bisherGezahltLabel = new JLabel("Bisher gezahlt: " + bisherGezahlt + "€");
        JLabel bisherEingenommenLabel = new JLabel("Durch Tickets eingenommen: " + bisherEingenommen + "€");
        JLabel ticketLabel = new JLabel("Ticket: " + ticket.ticketName);
        JLabel ticketPreisLabel = new JLabel("Preis: " + ticket.ticketPreis + "€");
        JTextField ticketInput = new JTextField(10);
        JButton einwerfenBtn = new JButton("Einwerfen");
        JButton zurueckgebenBtn = new JButton("Zurückgeben");
        JButton settingsBtn = new JButton("Einstellungen ⚙️");

        JDialog settingsDialog = new JDialog();
        JPanel settingsContainer = new JPanel();

        einwerfenBtn.addActionListener(l -> {
            String inputSummeString = ticketInput.getText();
            float inputSummeFloat = Float.parseFloat(inputSummeString);
            float wechselGeld = GeldEinwerfen(inputSummeFloat);
            ticketInput.setText("");
            bisherGezahltLabel.setText("Bisher gezahlt: " + bisherGezahlt + "€");
            bisherEingenommenLabel.setText("Durch Tickets eingenommen: " + bisherEingenommen + "€");
            if (wechselGeld >= 0) {
                JOptionPane.showMessageDialog(frame,
                        ticket.ticketName + " gedruckt. \n Wechselgeld: " + wechselGeld + "€", "Ticket gedruckt",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        settingsBtn.addActionListener(l -> {
            settingsDialog.setTitle("Einstellungen");
            settingsDialog.setSize(300, 200);
            settingsDialog.setLocationRelativeTo(frame);
            settingsDialog.setVisible(true);
        });

        zurueckgebenBtn.addActionListener(l -> {
            float wechselGeld = GeldZurueckgeben();
            bisherGezahltLabel.setText("Bisher gezahlt: " + bisherGezahlt + "€");
            if (wechselGeld >= 0) {
                JOptionPane.showMessageDialog(frame, "Wechselgeld: " + wechselGeld + "€", "Wechselgeld zurückgegeben",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.setVisible(true);
        frame.setTitle("Ticketautomat");
        frame.setIconImage(icon.getImage());
        frame.setSize(380, 450);
        frame.setResizable(false);

        for (int i = 0; i < menuitems.length; i++) {
            ticketMenu.add(menuitems[i]);
            int temp = i;
            menuitems[i].addActionListener(l -> {
                ticket = tickets[temp];
                ticketLabel.setText("Ticket: " + ticket.ticketName);
                ticketPreisLabel.setText("Preis: " + ticket.ticketPreis + "€");
            });
        }

        menuBar.add(ticketMenu);

        container.setLayout(null);

        container.add(title);
        container.add(menuBar);
        container.add(ticketLabel);
        container.add(ticketPreisLabel);
        container.add(bisherGezahltLabel);
        container.add(ticketInput);
        container.add(einwerfenBtn);
        container.add(zurueckgebenBtn);
        container.add(settingsBtn);

        title.setBounds(10, 10, 200, 30);
        menuBar.setBounds(260, 10, 95, 30);
        ticketLabel.setBounds(15, 40, 200, 30);
        ticketPreisLabel.setBounds(15, 60, 200, 30);
        bisherGezahltLabel.setBounds(10, 302, 200, 30);
        ticketInput.setBounds(10, 332, 240, 30);
        einwerfenBtn.setBounds(255, 332, 91, 30);
        zurueckgebenBtn.setBounds(10, 370, 120, 30);
        settingsBtn.setBounds(135, 370, 200, 30);

        settingsDialog.setIconImage(icon.getImage());
        settingsDialog.setTitle("Einstellungen");

        settingsContainer.add(bisherEingenommenLabel);

        for (int i = 0; i < tickets.length; i++) {
            settingsContainer.add(new JLabel(tickets[i].ticketName));
            settingsContainer.add(new JTextField(10));
        }

        settingsDialog.add(settingsContainer);

        frame.add(container);
    }

    public float GeldEinwerfen(float betrag) {
        bisherGezahlt += betrag;
        if (bisherGezahlt >= ticket.ticketPreis) {
            bisherGezahlt -= ticket.ticketPreis;
            bisherEingenommen += ticket.ticketPreis;
            return GeldZurueckgeben();
        }
        return -1;
    }

    public float GeldZurueckgeben() {
        float wechselgeld = bisherGezahlt;
        bisherGezahlt = 0;
        return wechselgeld;
    }
}
