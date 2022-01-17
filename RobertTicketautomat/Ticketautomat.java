import java.awt.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
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
        bisherEingenommen = read();
        ticket = tickets[0];

        JFrame frame = new JFrame("Ticketautomat");
        JPanel container = new JPanel();
        container.setBackground(Color.white);
        JLabel title = new JLabel("Ticketautomat");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        JMenuBar menuBar = new JMenuBar();
        JMenu ticketMenu = new JMenu("Ticketauswahl");
        ticketMenu.setFont(new Font("Arial", Font.PLAIN, 12));
        ticketMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        ImageIcon icon = new ImageIcon("icon.png");

        JLabel bisherGezahltLabel = new JLabel("Bisher gezahlt: " + bisherGezahlt + "€");
        JLabel bisherEingenommenLabel = new JLabel("Durch Tickets eingenommen: " + bisherEingenommen + "€");
        JLabel ticketLabel = new JLabel("Ticket: " + ticket.ticketName);
        JLabel ticketPreisLabel = new JLabel("Preis: " + ticket.ticketPreis + "€");
        JTextField ticketInput = new JTextField(10);
        TextPrompt ticketInputPlaceholder = new TextPrompt("Münzen einwerfen...", ticketInput);
        JButton einwerfenBtn = new JButton("Einwerfen");
        einwerfenBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton zurueckgebenBtn = new JButton("Zurückgeben");
        zurueckgebenBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton settingsBtn = new JButton("Einstellungen ⚙️");
        settingsBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JDialog passwordDialog = new JDialog();
        passwordDialog.setTitle("Passwort");
        passwordDialog.setSize(300, 200);
        passwordDialog.setResizable(false);
        JPanel passwordContainer = new JPanel();
        JTextField passwordInput = new JTextField(10);
        TextPrompt passwordInputPlaceholder = new TextPrompt("Passwort eingeben...", passwordInput);
        JButton passwordBtn = new JButton("OK");

        JDialog settingsDialog = new JDialog();
        settingsDialog.setTitle("Einstellungen");
        settingsDialog.setSize(300, 200);
        settingsDialog.setResizable(false);
        JPanel settingsContainer = new JPanel();

        einwerfenBtn.addActionListener(l -> {
            try {
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
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Bitte werfen Sie nur Münzen ein! :)", "Fehler",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        passwordBtn.addActionListener(l -> {
            String inputPasswordString = passwordInput.getText();
            if (inputPasswordString.equals("Passwort")) {
                passwordDialog.dispose();
                settingsDialog.setLocationRelativeTo(frame);
                settingsDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(frame, "Falsches Passwort!", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });

        settingsBtn.addActionListener(l -> {
            passwordDialog.setLocationRelativeTo(frame);
            passwordDialog.setVisible(true);
        });

        zurueckgebenBtn.addActionListener(l -> {
            float wechselGeld = GeldZurueckgeben();
            bisherGezahltLabel.setText("Bisher gezahlt: " + bisherGezahlt + "€");
            if (wechselGeld >= 0) {
                JOptionPane.showMessageDialog(frame, "Wechselgeld: " + wechselGeld + "€", "Wechselgeld zurückgegeben",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

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

        passwordContainer.add(passwordInput);
        passwordContainer.add(passwordBtn);
        passwordDialog.add(passwordContainer);
        
        settingsContainer.add(bisherEingenommenLabel);
        JLabel[] settingsTicketLabels = new JLabel[tickets.length];
        JTextField[] settingsTicketInputs = new JTextField[tickets.length];
        for (int i = 0; i < tickets.length; i++) {
            JLabel settingsTicketLabel = new JLabel(tickets[i].ticketName);
            JTextField settingsTicketInput = new JTextField(10);
            settingsTicketInput.setText(String.valueOf(tickets[i].ticketPreis));
            settingsTicketLabels[i] = settingsTicketLabel;
            settingsTicketInputs[i] = settingsTicketInput;
            settingsContainer.add(settingsTicketLabels[i]);
            settingsContainer.add(settingsTicketInputs[i]);
        }
        JButton settingsSaveBtn = new JButton("Speichern");
        settingsSaveBtn.addActionListener(l -> {
            try {
                for (int i = 0; i < tickets.length; i++) {
                    tickets[i].ticketPreis = Float.parseFloat(settingsTicketInputs[i].getText());
                }
                ticketPreisLabel.setText("Preis: " + ticket.ticketPreis + "€");
                settingsDialog.dispose();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Bitte geben Sie nur Zahlen ein!", "Fehler",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        settingsContainer.add(settingsSaveBtn);
        
        settingsDialog.setTitle("Einstellungen");
        settingsDialog.setIconImage(icon.getImage());
        settingsDialog.setResizable(false);
        settingsDialog.add(settingsContainer);
        
        frame.add(container);
        frame.setVisible(true);
    }
    
    public float GeldEinwerfen(float betrag) {
        bisherGezahlt += betrag;
        if (bisherGezahlt >= ticket.ticketPreis) {
            bisherGezahlt -= ticket.ticketPreis;
            bisherEingenommen += ticket.ticketPreis;
            write(bisherEingenommen);
            return GeldZurueckgeben();
        }
        return -1;
    }

    public float GeldZurueckgeben() {
        float wechselgeld = bisherGezahlt;
        bisherGezahlt = 0;
        return wechselgeld;
    }

    public float read() {
        try {
            File eingenommenFile = new File("eingenommen.txt");
            Scanner eingenommenScanner = new Scanner(eingenommenFile);
            String eingenommenText = eingenommenScanner.nextLine();
            float eingenommen = Float.parseFloat(eingenommenText);
            eingenommenScanner.close();
            return eingenommen;
        }
        catch (Exception e) {
            return 0f;
        }
    }

    public void write(float eingenommen) {
        try {
            File eingenommenFile = new File("eingenommen.txt");
            PrintWriter eingenommenWriter = new PrintWriter(eingenommenFile);
            eingenommenWriter.println(eingenommen);
            eingenommenWriter.close();
        }
        catch (Exception e) {
            System.out.println("Fehler beim Schreiben der Datei");
        }
    }
}
