import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class DayYearView extends JButton implements ActionListener{

    Calendar cal = Calendar.getInstance();
    Calendar currentCal = Calendar.getInstance();

    int currentYear;
    int currentMonth;
    int currentDayOfMonth;

    int yearIndex;
    int monthIndex;
    int dayOfMonthIndex;

    String buttonLabelCurrentDate;

    public DayYearView(int _yearIndex, int _monthIndex, int _dayOfMonthIndex) {

        yearIndex = _yearIndex;
        monthIndex = _monthIndex;
        dayOfMonthIndex = _dayOfMonthIndex;

        setPreferredSize(new Dimension(1920 / 15, 20));

        currentYear = currentCal.get(Calendar.YEAR);
        currentMonth = currentCal.get(Calendar.MONTH);
        currentDayOfMonth = currentCal.get(Calendar.DAY_OF_MONTH);

        markCurrentDay();

        cal.set(Calendar.YEAR, yearIndex);
        cal.set(Calendar.MONTH, monthIndex);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonthIndex);

        setTextButtonLabelCurrentDate();
        
        this.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println(buttonLabelCurrentDate+" "+yearIndex);
                Ticketautomat.cal.set(Calendar.YEAR, yearIndex);
                Ticketautomat.cal.set(Calendar.MONTH, monthIndex);
                Ticketautomat.cal.set(Calendar.DAY_OF_MONTH, dayOfMonthIndex);

                DateFormat df = new SimpleDateFormat("EEE, d.M.yyyy");
                Date date = Ticketautomat.cal.getTime();
                String dateForButton = df.format(date);
                Ticketautomat.datumAuswaehlen.setText(dateForButton);
                

                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.dispose();
            }
        });
    }

    public void markCurrentDay(){
        if (currentMonth == monthIndex && currentDayOfMonth == dayOfMonthIndex) {
            if (currentYear == yearIndex) {
                this.setBackground(Color.RED);
            } else {
                this.setBackground(Color.GRAY);
            }
        }
    }
    public void setTextButtonLabelCurrentDate(){
        DateFormat df = new SimpleDateFormat("d. EEE");
        Date date = cal.getTime();
        buttonLabelCurrentDate = df.format(date);
        this.setText(buttonLabelCurrentDate);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
