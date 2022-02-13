import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticketautomat {
    Ticket[] tickets = { new Ticket(1.40f, "Kinder-Ticket"), new Ticket(3.60f, "Erwachsenen-Ticket"),
    new Ticket(3.20f, "Studenten-Ticket"), new Ticket(5.40f, "Gruppen-Ticket") };
    JMenuItem[] menuitems = { new JMenuItem("Kinder-Ticket"), new JMenuItem("Erwachsenen-Ticket"),
    new JMenuItem("Studenten-Ticket"), new JMenuItem("Gruppen-Ticket") };
    Ticket ticket;
    float bisherGezahlt;
    float bisherEingenommen;
    public BereichBtnGroup btnGroup;
    LocalDateTime time;
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd.MM. HH:mm");
    String formattedtime;

    public Ticketautomat() {
        time = LocalDateTime.now();
        formattedtime = time.format(timeFormat);

        bisherGezahlt = 0.0f;
        bisherEingenommen = read();
        ticket = tickets[0];

        JFrame frame = new JFrame("Ticketautomat");
        JPanel container = new JPanel();
        JLabel title = new JLabel("Ticketautomat");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        JMenuBar menuBar = new JMenuBar();
        JMenu ticketMenu = new JMenu("Ticketauswahl");
        ticketMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        ImageIcon icon = new ImageIcon("icon.png");
        JLabel timeLabel = new JLabel(formattedtime);

        JLabel bisherGezahltLabel = new JLabel("Bisher gezahlt: " + bisherGezahlt + "€");
        JLabel bisherEingenommenLabel = new JLabel("Durch Tickets eingenommen: " + bisherEingenommen + "€");
        JLabel ticketLabel = new JLabel("Ticket: " + ticket.ticketName);
        JLabel ticketPreisLabel = new JLabel("Preis: " + ticket.ticketPreis + "€");

        btnGroup = new BereichBtnGroup();
        JRadioButton[] radioButtons = { new JRadioButton("Kurzstrecke", true), new JRadioButton("Nahbereich", false),
                new JRadioButton("Hamburg AB", false) };

        JTextField ticketInput = new JTextField(10);
        TextPrompt ticketInputPlaceholder = new TextPrompt("Münzen einwerfen...", ticketInput);

        JButton einwerfenBtn = new JButton("Einwerfen");
        einwerfenBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton zurueckgebenBtn = new JButton("Zurückgeben");
        zurueckgebenBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton settingsBtn = new JButton("Einstellungen ⚙️");
        settingsBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JDialog gekauftDialog = new JDialog();
        gekauftDialog.setTitle("Ticket");
        gekauftDialog.setSize(300, 200);
        gekauftDialog.setResizable(false);
        JPanel gekauftContainer = new JPanel();
        JLabel gekauftLabel = new JLabel();
        JButton gekauftBtn = new JButton("OK");

        JDialog passwordDialog = new JDialog();
        passwordDialog.setTitle("Passwort");
        passwordDialog.setSize(300, 200);
        passwordDialog.setResizable(false);
        JPanel passwordContainer = new JPanel();
        JPasswordField passwordInput = new JPasswordField(10);
        TextPrompt passwordInputPlaceholder = new TextPrompt("Passwort eingeben...", passwordInput);
        JButton passwordBtn = new JButton("OK");

        JDialog settingsDialog = new JDialog();
        settingsDialog.setTitle("Einstellungen");
        settingsDialog.setSize(300, 200);
        settingsDialog.setResizable(false);
        JPanel settingsContainer = new JPanel();

        // Erneuert alle 45 Sekunden die Uhrzeit
        ActionListener updateTime = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                time = LocalDateTime.now();
                formattedtime = time.format(timeFormat);
                timeLabel.setText(formattedtime);
            }
        };
        Timer timer = new Timer(45000, updateTime);
        timer.start();

        // ActionListener für den Einwerfen-Button
        einwerfenBtn.addActionListener(l -> {
            try {
                String inputSummeString = ticketInput.getText();
                float inputSummeFloat = Float.parseFloat(inputSummeString);
                float wechselGeld = GeldEinwerfen(inputSummeFloat);
                ticketInput.setText("");
                bisherGezahltLabel.setText("Bisher gezahlt: " + bisherGezahlt + "€");
                bisherEingenommenLabel.setText("Durch Tickets eingenommen: " + bisherEingenommen + "€");
                if (wechselGeld >= 0) {
                    String gekauftLabelText = String.format("<html>%s gekauft<hr>Datum: %s<br>Bereich: %s<br>Preis: %s€<br>Wechselgeld: %s€</html>",
                            ticket.ticketName, formattedtime, btnGroup.bereichString, ticket.ticketPreis * btnGroup.bereichKoeffizient, wechselGeld);
                    gekauftLabel.setText(gekauftLabelText);
                    gekauftDialog.setLocationRelativeTo(frame);
                    gekauftDialog.setVisible(true);
                }
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Bitte werfen Sie Münzen ein! :)", "Fehler",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // ActionListener für den Passwort-Button
        passwordBtn.addActionListener(l -> {
            char[] inputPasswordValue = passwordInput.getPassword();
            String inputPasswordString = String.valueOf(inputPasswordValue);
            if (inputPasswordString.equals("Passwort")) {
                passwordInput.setText("");
                passwordDialog.dispose();
                settingsDialog.setLocationRelativeTo(frame);
                settingsDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(frame, "Falsches Passwort!", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });

        // ActionListener für den Settings-Button
        settingsBtn.addActionListener(l -> {
            passwordDialog.setLocationRelativeTo(frame);
            passwordDialog.setVisible(true);
        });

        // ActionListener für den Zurückgeben-Button
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
        
        // Fügt die MenüItems der Menüleiste hinzu
        for (int i = 0; i < menuitems.length; i++) {
            ticketMenu.add(menuitems[i]);
            int temp = i;
            menuitems[i].addActionListener(l -> {
                ticket = tickets[temp];
                ticketLabel.setText("Ticket: " + ticket.ticketName);
                ticketPreisLabel.setText("Preis: " + ticket.ticketPreis * btnGroup.bereichKoeffizient + "€");
            });
        }

        menuBar.add(ticketMenu);

        // Fügt die Radiobuttons der ButtonGroup hinzu
        for (int i = 0; i < radioButtons.length; i++) {
            btnGroup.add(radioButtons[i]);
            container.add(radioButtons[i]);
            radioButtons[i].setBounds(10, 100 + i * 30, 200, 30);
            int temp = i;
            radioButtons[i].addActionListener(l -> {
                btnGroup.bereichString = radioButtons[temp].getText();
                btnGroup.bereichKoeffizient = temp + 1;
                ticketPreisLabel.setText("Preis: " + ticket.ticketPreis * btnGroup.bereichKoeffizient + "€");
            });
        }
        
        // container: hinzufügen der Komponenten
        container.setLayout(null);
        container.add(title);
        container.add(menuBar);
        container.add(timeLabel);
        container.add(ticketLabel);
        container.add(ticketPreisLabel);
        container.add(bisherGezahltLabel);
        container.add(ticketInput);
        container.add(einwerfenBtn);
        container.add(zurueckgebenBtn);
        container.add(settingsBtn);

        // container: Positionierung
        title.setBounds(10, 10, 200, 30);
        menuBar.setBounds(255, 10, 115, 30);
        ticketLabel.setBounds(15, 40, 200, 30);
        ticketPreisLabel.setBounds(15, 60, 200, 30);
        bisherGezahltLabel.setBounds(10, 302, 200, 30);
        timeLabel.setBounds(275, 302, 100, 30);
        ticketInput.setBounds(10, 332, 240, 30);
        einwerfenBtn.setBounds(255, 332, 105, 30);
        zurueckgebenBtn.setBounds(10, 370, 128, 30);
        settingsBtn.setBounds(143, 370, 200, 30);

        // ActionListener für den gekauft-Button im gekauft-Dialog
        gekauftBtn.addActionListener(l -> {
            gekauftDialog.dispose();
        });

        // gekauft: hinzufügen der Komponenten
        gekauftContainer.setLayout(null);
        gekauftContainer.add(gekauftLabel);
        gekauftContainer.add(gekauftBtn);
        gekauftDialog.add(gekauftContainer);

        gekauftLabel.setBounds(10, 10, 250, 100);
        gekauftBtn.setBounds(10, 125, 200, 30);

        passwordContainer.setLayout(null);
        passwordContainer.add(passwordInput);
        passwordContainer.add(passwordBtn);
        passwordDialog.add(passwordContainer);

        passwordInput.setBounds(10, 10, 280, 30);
        passwordBtn.setBounds(10, 45, 91, 30);
        
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
                write();
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
    
    // Methode zum Einwerfen von Münzen
    public float GeldEinwerfen(float betrag) {
        bisherGezahlt += betrag;
        if (bisherGezahlt >= ticket.ticketPreis * btnGroup.bereichKoeffizient) {
            bisherGezahlt -= ticket.ticketPreis * btnGroup.bereichKoeffizient;
            bisherEingenommen += ticket.ticketPreis * btnGroup.bereichKoeffizient;
            write();
            return GeldZurueckgeben();
        }
        return -1;
    }

    // Gibt Geld zurück
    public float GeldZurueckgeben() {
        float wechselgeld = bisherGezahlt;
        bisherGezahlt = 0;
        return wechselgeld;
    }

    // Liest die Eingaben aus der Datei
    public float read() {
        try {
            File eingenommenFile = new File("eingenommen.txt");
            Scanner eingenommenScanner = new Scanner(eingenommenFile);
            String eingenommenText = eingenommenScanner.nextLine();
            int temp = 0;
            while (eingenommenScanner.hasNextLine()) {
                tickets[temp].ticketPreis = Float.parseFloat(eingenommenScanner.nextLine());
                temp++;
            }
            float eingenommen = Float.parseFloat(eingenommenText);
            eingenommenScanner.close();
            return eingenommen;
        }
        catch (Exception e) {
            return 0f;
        }
    }
    
    // Speichert die Einnahmnen in der Datei
    public void write() {
        try {
            File eingenommenFile = new File("eingenommen.txt");
            PrintWriter eingenommenWriter = new PrintWriter(eingenommenFile);
            eingenommenWriter.println(bisherEingenommen);
            for (Ticket myTicket : tickets) {
                eingenommenWriter.println(myTicket.ticketPreis);
            }
            eingenommenWriter.close();
        }
        catch (Exception e) {
            System.out.println("Fehler beim Schreiben der Datei");
        }
    }
}
