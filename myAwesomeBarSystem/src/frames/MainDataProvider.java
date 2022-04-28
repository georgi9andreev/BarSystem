package frames;

import com.sun.jdi.ArrayReference;
import database.Database;
import models.*;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MainDataProvider {

    public MainFrame currentFrame;

    public ArrayList <User> users;
    public ArrayList<Integer> tabels;
    public ArrayList<Order> orders;
    public ArrayList<Category> categories;

    public MainDataProvider (MainFrame currentFrame){
        this.currentFrame = currentFrame;

    }

    public void fetchUsers (){
        users = new ArrayList<>();
        User user1 = new User ("Ivanka Petkova", "012345667", "0101", UserType.WAITRESS);
        User user2 = new User ("Petko Ivanov", "012345667", "0101", UserType.WAITRESS);
        User user3 = new User ("Georgi Andreev", "012345667", "0101", UserType.MANAGER);
        users.add(user1);
        users.add(user2);
        users.add(user3);


    }
    public void fetchCategories (){
        this.categories = Database.getCategories();
    }
    public void fetchTables (){
        tabels = new ArrayList<>();
        tabels.add(1);
        tabels.add(2);
        tabels.add(3);
        tabels.add(4);
        tabels.add(5);
        tabels.add(6);
        tabels.add(7);
        tabels.add(8);
        tabels.add(9);
        tabels.add(10);



    }

    public boolean isPinCorrect (String pinCode){
        for (User user : users ){
            if (user.getPin().equals(pinCode)){
                return true;
            }
        }
        return false;
    }

    public void refreshOrders(DefaultTableModel model, int tableNumber){
        // Add if check for table number and call this method in constructor of ordersPanel
        model.setRowCount(0);
        for(Order order : this.orders){
            if(order.getTableNumber()== tableNumber) {

                String row[] = new String[3];
                row[0] = order.getUid();
                row[1] = order.getProductsCount();
                row[2] = order.getTotalAmountString();
                model.addRow(row);
            }
        }

    }

    public void loadProductsInTable (DefaultTableModel model, Order order){

        model.setRowCount(0);
        for (Product product : order.getProducts()){
            String row[] = new String[3];
            row[0] = product.getBrand();
            row[1] = product.getCountString();
            row[2] = product.getTotalPriceString();
            model.addRow(row);
        }
    }
}
