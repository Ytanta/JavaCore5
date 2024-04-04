package seminar05;

import java.util.List;

final class Order {
    // Константы для указания текущих размеров скидок на праздники
    private static final double GENDER_DISCOUNT = 0.15;
    private static final double NEW_YEAR_DISCOUNT = 0.2;
    // Добавлен статический счётчик для присвоения уникальных идентификаторов заказам
    private static int countID = 0;
    private final int ID;
    private Customer customer;
    private Product product;
    private int amount;
    // Добавлено поле для хранения даты оформления заказа, учитывающей наличие праздника в этот день
    private Celebration date;

    public Order(Customer customer, Product product, int amount, Celebration date) {
        this.ID = ++countID;
        this.customer = customer;
        this.product = product;
        this.amount = amount;
        this.date = date;
    }

    public Order(int ID, int customerID, int productID, int amount) {
        this.ID = ID;
        this.customer = (Customer) findByID(customerID, DataStorage.customers);
        this.product = (Product) findByID(productID, DataStorage.products);
        this.amount = amount;
    }

    private Object findByID(int id, List list) {
        for (Object o : list) {
            if (o instanceof Customer && id == ((Customer) o).getID()) {
                return o;
            }
            if (o instanceof Product && id == ((Product) o).getID()) {
                return o;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Order {ID=" + ID + " customer=" + customer.getName() + ", " + product +
                ", amount=" + amount + ", date=" + date.getHoliday() + '}';
    }

    /**
     * Производит расчёт общей стоимости заказа с учётом скидок при оформлении в праздники
     * @return итоговая стоимость заказа с учётом скидки
     */
    public double getTotalCost() {
        if (date == Celebration.NEW_YEAR) {
            return amount * (1 - NEW_YEAR_DISCOUNT) * product.getPrice();
        }
        if (date == Celebration.MARCH_8 && customer.getGender() == Gender.FEMALE ||
                date == Celebration.FEB_23 && customer.getGender() == Gender.MALE) {
            return amount * (1 - GENDER_DISCOUNT) * product.getPrice();
        }
        return amount * product.getPrice();
    }
}