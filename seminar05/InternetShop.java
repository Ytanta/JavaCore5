package seminar05;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Класс, описывающий интернет-магазин. Хранит данные о покупателях, товарах и заказах.
 * Имеет метод для оформления нового заказа.
 */
final class InternetShop {
    // Данные о покупателях, товарах и заказах хранятся в ArrayList'х

    final File productsF = new File("products.txt");
    final File customerF = new File("customers.txt");
    final File ordersF = new File("orders.txt");

    enum ObjectType {
        PRODUCT, CUSTOMER, ORDER;
    }

    // При создании экземпляра интернет-магазина он имеет готовый набор зарегистрированных покупателей и товаров
    public InternetShop() {
        readData(customerF);
        System.out.println(DataStorage.customers);
        readData(productsF);
        readData(ordersF);
    }

    /**
     * Создаёт экземпляр заказа и проверяет входящие данные на корректность.
     *
     * @param customer покупатель товара
     * @param product  покупаемый товар
     * @param amount   количество покупаемого товара
     * @param date     дата оформления заказа из набора перечислений для учёта наличия праздника
     * @return экземпляр заказа с валидными данными
     * @throws CustomerException фатальное исключение, завершающее работу приложения, в случае неучтенного покупателя
     * @throws ProductException  исключение при некорректном указании товара, заказ не оформляется
     * @throws AmountException   исключение при некорректном указании количества товара, заказ оформляется для количества 1.
     */
    Order makeOrder(Customer customer, Product product, int amount, Celebration date)
            throws CustomerException, ProductException, AmountException {
        if (!DataStorage.customers.contains(customer)) {
            throw new CustomerException(customer);
        }
        if (!DataStorage.products.contains(product)) {
            throw new ProductException(product);
        }
        if (amount < 1 || amount > 100) {
            throw new AmountException(amount);
        }
        return new Order(customer, product, amount, date);
    }

    void readData(File file) {
        switch (file.getName()) {
            case "products.txt":
                loadData(file, DataStorage.products, ObjectType.PRODUCT);
                break;
            case "customers.txt":
                loadData(file, DataStorage.customers, ObjectType.CUSTOMER);
                break;
            case "orders.txt":
                loadData(file, DataStorage.orders, ObjectType.ORDER);
                break;
            default:
                System.out.println("Incorrect file name");
                break;
        }
    }

    void loadData(File file, List list, ObjectType type) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String[] prod = new String[3];
            String str;
            while ((str = br.readLine()) != null) {
                prod = str.split(",");
                System.out.println(Arrays.toString(prod));
                switch (type) {
                    case PRODUCT -> {
                        list.add(new Product(Integer.parseInt(prod[0]), prod[1], Integer.parseInt(prod[2])));
                    }

                    case CUSTOMER -> {
                        Customer customer = new Customer(Integer.parseInt(prod[0]), prod[1], Integer.parseInt(prod[2]),
                                prod[3], prod[4].equals("m") ? Gender.MALE : Gender.FEMALE);
                        System.out.println(customer);
                        list.add(customer);
                    }
                    case ORDER -> list.add(new Order(Integer.parseInt(prod[0]),
                            Integer.parseInt(prod[1]), Integer.parseInt(prod[2]), Integer.parseInt(prod[3])));
                    default -> System.out.println("Incorrect type");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
    void writeData(ObjectType type) {
        switch (type) {
            case PRODUCT:
                unloadDate(, DataStorage.products, ObjectType.PRODUCT);
                break;
            case CUSTOMER:
                unloadDate(file, DataStorage.customers, ObjectType.CUSTOMER);
                break;
            case ORDER:
                unloadDate(file, DataStorage.orders, ObjectType.ORDER);
                break;
            default:
                System.out.println("Incorrect type");
                break;
        }
    }
    void unloadDate (File file, List list, ObjectType type){
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                try (final ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("androids.dat"))) {

                    switch (type) {
                        case PRODUCT -> {
                            final Product product = (Product) inputStream.readObject();
                            System.out.println(product);
                        }

                        case CUSTOMER -> {
                            final Customer customer = (Customer) inputStream.readObject();

                            System.out.println(customer);

                        }
                        case ORDER -> {
                            final Order order = (Order) inputStream.readObject();
                        }
                        default -> System.out.println("Incorrect type");
                    }
            } catch (ClassNotFoundException e) {
                 throw new RuntimeException(e);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
            }

        }
    }
