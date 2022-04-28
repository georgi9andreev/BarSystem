package frames;

import panels.LoginPanel;
import panels.OrdersPanel;
import panels.TabelsPanel;

public class MainRouter {

    public MainFrame currentFrame;

    public MainRouter (MainFrame currentFrame){
        this.currentFrame = currentFrame;

    }

    public void showLoginPanel(){

        LoginPanel login = new  LoginPanel(this.currentFrame);
        this.currentFrame.setContentPane(login);
        this.currentFrame.validate();
    }

    public void showTablesPanel (){
        TabelsPanel table = new TabelsPanel(this.currentFrame);
        this.currentFrame.setContentPane(table);
        this.currentFrame.validate();
    }
    public void showOrdersPanel (int tableNumber){
        OrdersPanel orders = new OrdersPanel(this.currentFrame, tableNumber);
        orders.tableNumber = tableNumber;
        this.currentFrame.setContentPane(orders);
        this.currentFrame.validate();


    }
}
