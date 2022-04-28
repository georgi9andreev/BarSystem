package panels;

import base.BasePanel;
import customUI.BarButton;
import database.Database;
import frames.MainFrame;
import models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class OrdersPanel extends BasePanel {
    public int tableNumber;
    //orders table
    public JTable ordersTable;
    public DefaultTableModel ordersTableModel;


    // products table
    public JTable productsTable;
    public DefaultTableModel productsTableModel;

    public ArrayList<Product> products;
    public Order selectedOrder;
    public Product selectedProduct;
    public int currentlySelectedProductRow;
    public ArrayList<JButton> categoriesButtons;
    public ArrayList<JButton> productsButtons;



    public OrdersPanel(MainFrame frame, int tableNumber) {
        super(frame);
        this.tableNumber = tableNumber;
        products = Database.getProducts();
        createOrdersButtons();
        createOrdersTable();
        createHeader();
        createProductsTable();
        createCategoriesButtons();
        createSettingsButton();
        refreshOrders();

    }


    public void refreshOrders() {
        frame.dataProvider.refreshOrders(ordersTableModel, this.tableNumber);

    }

    public void loadProducts() {

        frame.dataProvider.loadProductsInTable(productsTableModel, this.selectedOrder);
    }


    public void createCategoriesButtons() {

        categoriesButtons = new ArrayList<>();

        int buttonX = 260;
        int buttonY = 0;

        for (int i = 0; i < frame.dataProvider.categories.size(); i++) {
            Category category = frame.dataProvider.categories.get(i);

            buttonY += 55;

            BarButton productButton = new BarButton(category.title);
            productButton.setBounds(buttonX, buttonY, 280, 50);
            productButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeAllButtons();
                    createProductButtons(category.type);
                    repaint();
                }
            });

            categoriesButtons.add(productButton);
            add(productButton);
        }


    }


    public void createProductButtons(ProductType type) {
        productsButtons = new ArrayList<>();
        int buttonX = 260;
        int buttonY = 0;

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getType() != type) {
                continue;
            }

            buttonY += 55;

            BarButton productButton = new BarButton(product.getBrand());
            productButton.product = product;
            productButton.setBounds(buttonX, buttonY, 280, 50);
            productButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (selectedOrder != null) {
                        boolean isProductFound = false;
                        for (Product product : selectedOrder.getProducts()) {

                            if (product.getUid().equals(((BarButton) e.getSource()).product.getUid())) {
                                product.increaceCount();
                                isProductFound = true;
                                break;
                            }
                        }
                        if (!isProductFound) {
                            selectedOrder.getProducts().add(((BarButton) e.getSource()).product);

                        }
                        refreshOrders();
                        loadProducts();
                    } else {
                        showError("Нямате поръчка за тази маса!");
                    }
                }
            });

            productsButtons.add(productButton);
            add(productButton);
        }
        JButton backButton = new JButton("Назад");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    removeAllButtons();
                    createCategoriesButtons();
                    repaint();
            }
        });
        backButton.setBounds(buttonX, buttonY+55, 280, 50);
        productsButtons.add(backButton);
        add(backButton);
    }


    public void createHeader() {
        JLabel tableNumberLabel = new JLabel("Маса:  " + this.tableNumber);
        tableNumberLabel.setBounds(frame.getWidth() / 2 - 50, 10, 100, 40);
        add(tableNumberLabel);

    }

    public void createProductsTable() {
        String colums[] = {"Продукт", "Количество", "Цена"};
        productsTableModel = new DefaultTableModel();
        productsTableModel.setColumnIdentifiers(colums);

        productsTable = new JTable(productsTableModel);

        productsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                currentlySelectedProductRow = productsTable.getSelectedRow();

                selectedProduct = selectedOrder.getProducts().get(productsTable.getSelectedRow());
            }
        });

        JScrollPane pane = new JScrollPane(productsTable);
        pane.setBounds(frame.getWidth() - 250, 50, 250, 450);
        add(pane);

    }

    public void createSettingsButton() {
        JButton increaceButton = new JButton("+");
        increaceButton.setBounds(frame.getWidth() - 250, 504, 44, 44);
        increaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyProduct(true);
            }
        });
        add(increaceButton);

        JButton decreaceButton = new JButton("-");
        decreaceButton.setBounds(frame.getWidth() - 200, 504, 44, 44);
        decreaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyProduct(false);
            }
        });
        add(decreaceButton);
    }


    public void modifyProduct(boolean isIncreasing) {
        if (selectedProduct == null) {
            showError("You must select product");
            return;
        }
        if (isIncreasing) {
            selectedProduct.increaceCount();
        } else {

            if (selectedProduct.getCount() == 1) {
                selectedOrder.getProducts().remove(selectedProduct);
            } else {
                selectedProduct.decreaceCount();
            }
        }

        refreshOrders();
        loadProducts();
        if (currentlySelectedProductRow < selectedOrder.getProducts().size()) {
            productsTable.setColumnSelectionInterval(currentlySelectedProductRow, currentlySelectedProductRow);
        }
    }

    public void createOrdersTable() {
        String colums[] = {"Номер", "Продукти", "Цена"};
        ordersTableModel = new DefaultTableModel();
        ordersTableModel.setColumnIdentifiers(colums);

        ordersTable = new JTable(ordersTableModel);
        ordersTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //текущо селектираната поръчка ще е равна на тази, която сме селектирали
                selectedOrder = frame.dataProvider.orders.get(ordersTable.getSelectedRow());
                loadProducts();


            }
        });
        JScrollPane pane = new JScrollPane(ordersTable);
        pane.setBounds(0, 50, 250, 550);
        add(pane);
    }

    public void createOrdersButtons() {

        JButton createOrderButton = new JButton("Създай");
        createOrderButton.setBounds(0, 0, 120, 44);
        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createOrderAction();
            }
        });
        add(createOrderButton);

        JButton finishOrderButton = new JButton("Приключи");
        finishOrderButton.setBounds(130, 0, 120, 44);
        finishOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedOrder == null) {
                    showError("Няма селектирана поръчка");
                    return;
                }
                int confurm = showQuesttionPopup("Завършване на поръчката?");
                if (confurm == JOptionPane.YES_OPTION) {
                    //Request -> Server -> Remove order -> OK 200
                    frame.dataProvider.orders.remove(selectedOrder);
                    frame.router.showLoginPanel();
                }
            }
        });
        add(finishOrderButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(0, 506, 250, 44);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.router.showLoginPanel();
            }
        });
        add(cancelButton);
    }


    public void createOrderAction() {
        int result = showQuesttionPopup("Сигурни ли сте,че искате да създадете поръчката?");
        if (result == JOptionPane.YES_OPTION) {
            String uid = Integer.toString(frame.dataProvider.orders.size() + 1);
            Order order = new Order(uid, this.tableNumber, new ArrayList<>());
            frame.dataProvider.orders.add(order);
            // refreshOrders
            refreshOrders();
        }
    }

    public void removeAllButtons() {
        // Remove categories buttons from panel
        if (categoriesButtons != null) {
            for (JButton button : categoriesButtons) {
                remove(button);
            }
        }
        // Remove product buttons from panel

        if (productsButtons != null) {
            for (JButton button : productsButtons) {
                remove(button);
            }
        }
    }
}
