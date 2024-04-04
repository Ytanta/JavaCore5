package seminar05;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Основной класс для запуска приложения, в котором создаётся экземпляр класса интернет-магазина.
 * Через магазин пропускается всевозможный набор данных для оформления заказов с учётом скидок и вызова исключений.
 */

public class Application {
    public static void main(String[] args) throws IOException {
   final InternetShop shop = new InternetShop();
//        final Object[][] data = {
//                // correct data for new order
//                {DataStorage.customers.get(0), DataStorage.products.get(0), 5, Celebration.NONE},
//                // correct data for female on March 8
//                {DataStorage.customers.get(1), DataStorage.products.get(1), 10, Celebration.MARCH_8},
//                // correct data for male on March 8
//                {DataStorage.customers.get(0), DataStorage.products.get(1), 20, Celebration.MARCH_8},
//                // correct data for male on March 8
//                {DataStorage.customers.get(2), DataStorage.products.get(2), 4, Celebration.FEB_23},
//                // correct data on Ney Year
//                {DataStorage.customers.get(3), DataStorage.products.get(3), 10, Celebration.NEW_YEAR},
//                // incorrect amount -7
//                {DataStorage.customers.get(1), DataStorage.products.get(4), -7, Celebration.NONE},
//                // incorrect amount 333
//                {DataStorage.customers.get(2), DataStorage.products.get(1), 333, Celebration.NONE},
//                // incorrect product
//                {DataStorage.customers.get(3), new Product("Car", 100_000), 2, Celebration.NONE},
//                // incorrect customer
//                {new Customer("Alex", 33, "+112", Gender.MALE), DataStorage.products.get(2), 5,
//                        Celebration.NONE}
//        };
//
//        double totalCostOrders = 0;
//        for (Object[] obj : data) {
//            try {
//                Order newOrder = shop.makeOrder((Customer) obj[0], (Product) obj[1], (Integer) obj[2], (Celebration) obj[3]);
//                DataStorage.orders.add(newOrder);
//                totalCostOrders += newOrder.getTotalCost();
//                System.out.printf("Order was successfully saved.\n%s\nTotal cost: %.2f\n",
//                        newOrder, newOrder.getTotalCost());
//            } catch (ProductException e) {
//                System.out.println(e.getMessage());
//                System.out.println("Order cancelled!");
//            } catch (AmountException e) {
//                System.out.println(e.getMessage());
//                Order changedOrder = new Order((Customer) obj[0], (Product) obj[1], 1, (Celebration) obj[3]);
//                DataStorage.orders.add(changedOrder);
//                totalCostOrders += changedOrder.getTotalCost();
//                System.out.printf("Order was created with a product quantity of 1.\n%s\nTotal cost: %.2f\n",
//                        changedOrder, changedOrder.getTotalCost());
//            } catch (CustomerException e) {
//                System.out.println(e.getMessage());
//                System.out.println("Application terminated incorrectly!");
//            } finally {
//                System.out.printf("Number of saved orders: %d. Total cost: %.2f\n", DataStorage.orders.size(), totalCostOrders);
//                System.out.println("-------------".repeat(10));
//            }
//        }
shop.writeData(new Product(6,"car", 22));
shop.writeData(new Customer(5,"Dem", 22,"123123",Gender.MALE));


   }
}


