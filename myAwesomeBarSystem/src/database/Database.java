package database;

import models.Category;
import models.Product;
import models.ProductType;

import java.util.ArrayList;

public class Database {

    public static ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>();

        Product a = new Product("1", ProductType.AlCOHOLIC, "водка", "Флирт", 2.20, 200);
        Product b = new Product("2", ProductType.NOALCOHOLIC, "кока-кола", "Кока-кола", 2.0, 200);
        Product c = new Product("3", ProductType.FOOD, "ядки", "Бадеми", 5.0, 200);
        Product a1 = new Product("4", ProductType.AlCOHOLIC, "уиски", "Бушмилс", 3, 200);
        Product b1 = new Product("5", ProductType.NOALCOHOLIC, "пепси", "Пепси кола", 2.0, 200);
        Product c1 = new Product("6", ProductType.FOOD, "ядки", "кашу", 5.5, 220);
        Product a2 = new Product("7", ProductType.AlCOHOLIC, "джин", "Гордънс", 1.8, 200);
        Product b2 = new Product("8", ProductType.NOALCOHOLIC, "мин. вода", "Горна Баня", 1.5, 200);
        Product c2 = new Product("9", ProductType.FOOD, "ядки", "фъстъци", 3.0, 200);

         products.add(a);
        products.add(b);
        products.add(c);
        products.add(a1);
        products.add(b1);
        products.add(c1);
        products.add(a2);
        products.add(b2);
        products.add(c2);

        return products;

    }

    // добавяме категориите, които искаме да има
    public static ArrayList <Category> getCategories (){
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category(ProductType.AlCOHOLIC, "Алкохол"));
        categories.add(new Category(ProductType.NOALCOHOLIC, "Безалкохолни"));
        categories.add(new Category(ProductType.FOOD, "Храна"));
        return categories;


    }

}
