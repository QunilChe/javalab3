package goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {



            FileService fileService = new FileService();
            List<Product> products = fileService.loadDataFromFile2("products.txt");

            Store store = new Store(products);







            // Ініціалізація товарів
     //   Store store = new Store();
      //  List<Product> products = store.getAllProducts();

        // Ініціалізація покупців
      Customer customer1 = new Customer("John", "Doe", 30);
      Customer customer2 = new Customer("Alice", "Smith", 25);

        // Отримання товарів
        System.out.println("All available products:");
        products.forEach(product -> System.out.println(product.getName()));

        // Продаж товарів
        Receipt receipt1 = new Receipt();
        receipt1.addProductToReceipt(products.get(3)); // Додаємо перший товар до чеку покупки
      receipt1.addProductToReceipt(products.get(1));
      receipt1.addProductToReceipt(products.get(2));
      receipt1.addProductToReceipt(products.get(4));
      receipt1.addCustomer(customer1);
        receipt1.markAsPaid(); // Позначаємо чек як оплачений
        PurchaseHistory purchaseHistory = new PurchaseHistory();
        purchaseHistory.addPurchaseToHistory(customer1, receipt1);

        // Редагування товарів
        System.out.println("Before editing price:");
        System.out.println(products.get(0).getPrice()); // Виводимо ціну першого товару
        store.editProductPrice(products.get(0).getName(), 15.0); // Змінюємо ціну товару
        System.out.println("After editing price:");
        System.out.println(products.get(0).getPrice()); // Виводимо змінену ціну

        // Замовлення товарів
        List<Product> orderedProducts = store.orderProduct("fruits", 5.0); // Замовляємо фрукти до 5.0 ціни



        fileService.generateOrderReceipt("order_receipt.txt", receipt1);

        // Виведення чеку та коментарів
        System.out.println("Receipt comment:");
        System.out.println(purchaseHistory.generateReceiptComment(receipt1));

        // Використання ShopService методів
        ShopService shopService = new ShopService();
        List<Product> sortedProducts = shopService.filterAndSortProductsByPrice(products);
        double averagePrice = shopService.calculateAveragePrice(products);
        double userExpenses = shopService.calculateUserExpenses(customer1, List.of(receipt1));
        Map<Product, Long> productsCount = shopService.getProductsCountForCustomer(customer1, List.of(receipt1));
        Product mostPopularProduct = shopService.findMostPopularProduct(List.of(receipt1));
        double maxDailyIncome = shopService.findMaxDailyIncome(List.of(receipt1));

        // Виведення результатів
        System.out.println("Sorted products by price:");
        sortedProducts.forEach(product -> System.out.println(product.getName()));

        System.out.println("Average price of all products: " + averagePrice);

        System.out.println("Expenses of " + customer1.getFirstName() + ": " + userExpenses);

        System.out.println("Products count for " + customer1.getFirstName() + ": " + productsCount);

        System.out.println("Most popular product: " + mostPopularProduct.getName());

        System.out.println("Max daily income: " + maxDailyIncome);




      Product product1 = new Product("Apple", 2.5, "Fruit");
      Product product2 = new Product("Chicken", 8.0, "Meat");
      Product product3 = new Product("Carrot", 1.0, "Vegetable");

      // Create a list of products for an unpaid receipt
      List<Product> unpaidProducts = new ArrayList<>();
      unpaidProducts.add(product1);
      unpaidProducts.add(product2);

      // Create a list of products for a paid receipt
      List<Product> paidProducts = new ArrayList<>();
      paidProducts.add(product2);
      paidProducts.add(product3);

      // Create unpaid and paid receipts
      Receipt unpaidReceipt = new Receipt(unpaidProducts);
      Receipt paidReceipt = new Receipt(paidProducts);
      paidReceipt.markAsPaid();

      // Simulate editing unpaid receipt
      System.out.println("Editing unpaid receipt:");
      unpaidReceipt.updatePurchasedProducts(paidProducts);

      // Simulate editing paid receipt
      System.out.println("\nEditing paid receipt:");
      paidReceipt.updatePurchasedProducts(unpaidProducts);




      List<Receipt> receipts = new ArrayList<>();
      receipts.add(receipt1);


      // Створення клієнта
      Customer customer = new Customer("Ім'я Користувача", "ID Користувача", 22);

      // Отримати дані про сумарну кількість кожного купленого продукту заданого користувача
      Customer selectedCustomer = customer1; // вибір першого користувача
      Map<Product, Long> productsQuantity = shopService.getProductsQuantityForCustomer(selectedCustomer, receipts);
      System.out.println("Products Quantity for " + selectedCustomer.getName() + ": " + productsQuantity);
    }


}

