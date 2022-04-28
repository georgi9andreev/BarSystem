package base;

import frames.MainFrame;

import javax.swing.*;

public class BasePanel extends JPanel {

    public MainFrame frame;

    public BasePanel(MainFrame frame){
        setLayout(null);
        this.frame = frame;
    }

    public void showError (String message){

        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);

    }

    public int showQuesttionPopup(String message){
        int result = JOptionPane.showConfirmDialog(null, message, "Внимание", JOptionPane.YES_NO_OPTION);
            return result;


    }


}
