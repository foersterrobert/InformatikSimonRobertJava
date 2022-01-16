import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Calendar;

public class YearView extends JPanel {

        MonthYearView[] myvList = new MonthYearView[12];

        Calendar cal = Calendar.getInstance();
        
        
        JButton[] switches = new JButton[2];
        JLabel currentYear;

        JPanel panelYearSwitch = new JPanel();
        JPanel panelMonths = new JPanel();
        int chosenYear;

        BoxLayout layoutYear = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        GridLayout layoutMonths = new GridLayout(1, 0, 4, 0);

    public YearView() {

        setLayout(layoutYear);
        panelMonths.setLayout(layoutMonths);
        

        int currentYearCalendar = cal.get(Calendar.YEAR);
        chosenYear = currentYearCalendar;


        switches[0] = new JButton("<");
        currentYear = new JLabel(""+chosenYear);
        switches[1] = new JButton(">");

        panelYearSwitch.add(switches[0]);
        panelYearSwitch.add(currentYear);
        panelYearSwitch.add(switches[1]);
        add(panelYearSwitch);

        initMonths();

        switches[0].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                chosenYear--;
                currentYear.setText(""+chosenYear);
                changeMonths();
            }
            
        });

        switches[1].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                chosenYear++;
                currentYear.setText(""+chosenYear);
                changeMonths();
            }
            
        });

    }

    public void initMonths(){
        for (int i = 0; i < myvList.length; i++){
            myvList[i] = new MonthYearView(chosenYear, i);
            panelMonths.add(myvList[i]);
        }
        add(panelMonths);
    }

    public void changeMonths(){
        for (int i = 0; i < myvList.length; i++){
            panelMonths.remove(myvList[i]);
        }
        initMonths();
    }

}
