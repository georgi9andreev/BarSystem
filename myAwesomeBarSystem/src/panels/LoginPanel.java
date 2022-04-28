package panels;

import base.BasePanel;
import frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPanel extends BasePanel {

    public JTextField pinField;

    public LoginPanel(MainFrame frame){

        super (frame);


        JLabel loginLabel = new JLabel("Welcome to Advance Bar");
        loginLabel.setBounds(frame.getWidth() / 2 - 110 ,20,220,40 );
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setFont(new Font("Serif", Font.BOLD, 20));
        add(loginLabel);

        pinField = new JTextField("Enter PIN");
        pinField.setBounds(frame.getWidth() / 2 - 50,60,100,40 );
        pinField.setHorizontalAlignment(SwingConstants.CENTER);
        pinField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pinField.setText("");

            }
        });
        add(pinField);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(frame.getWidth() / 2 - 50,105,100,40 );
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(frame.dataProvider.isPinCorrect(pinField.getText())) {
                    //show next screen
                 frame.router.showTablesPanel();
                }else {
                   showError("Pin is not correct");
                }
            }
        });

        add(loginButton);
    }

}
