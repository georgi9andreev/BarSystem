package panels;

import base.BasePanel;
import frames.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabelsPanel extends BasePanel implements ActionListener {


    public TabelsPanel(MainFrame frame) {
        super(frame);

        int buttonX = 180;
        int buttonY = frame.getHeight() / 2 - 100;



        for (int i = 0; i < frame.dataProvider.tabels.size(); i ++) {
            Integer tableNumber = frame.dataProvider.tabels.get(i);


            if( i == 5){
                buttonX = 180;
                buttonY = frame.getHeight() /2 - 40;
            }
            buttonX += 60;
            JButton tableButton = new JButton(Integer.toString(tableNumber));
            tableButton.addActionListener(this);
            tableButton.setBounds(buttonX,buttonY,50,50);

            add(tableButton);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

       int tabelNumber = Integer.parseInt(((JButton)e.getSource()).getText());
       //show orders panel
        frame.router.showOrdersPanel(tabelNumber);




    }
}
