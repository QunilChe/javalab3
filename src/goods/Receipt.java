package goods;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Receipt {
    private List<Product> purchasedProducts;
    private LocalDate purchaseDate;
    private boolean isPaid;
    private Customer customer;

    public Receipt() {
        this.purchasedProducts = new ArrayList<>();
        this.purchaseDate = LocalDate.now();
        this.isPaid = false;
        this.customer = customer;
    }

    public Receipt(List<Product> purchasedProducts) {
        this(); // Calling default constructor to initialize default values
        this.purchasedProducts = purchasedProducts;
    }
    // Додати товар до чеку
    public void addProductToReceipt(Product product) {
        FileService fileService = new FileService();
        List<Product> products = fileService.loadDataFromFile2("products.txt");
        // Перевірка, чи продукт належить до категорії "фрукти" або "овочі"
        if (product.getCategory().equalsIgnoreCase("fruits") || product.getCategory().equalsIgnoreCase("vegetables")) {
            // Додаємо один пакет перед продуктом
            purchasedProducts.add(products.get(0)); // Перша позиція в списку - це пакет
        }
        // Додаємо сам продукт до чеку
        purchasedProducts.add(product);
    }


    // Отримати список куплених товарів
    public List<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    // Отримати дату покупки
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    // Відзначити чек як оплачений
    public void markAsPaid() {
        this.isPaid = true;
    }

    // Перевірка чи чек оплачений
    public boolean isPaid() {
        return isPaid;
    }
    public void updatePurchasedProducts(List<Product> newProducts) {
        if (!isPaid) {
            purchasedProducts = newProducts;
        } else {
            System.out.println("Cannot edit a paid receipt.");
        }
    }
    public Customer getCustomer() {
        return customer;
    }
    public double getTotalPriceForCustomer(Customer customer, List<Receipt> receipts) {
        return receipts.stream()
                .filter(receipt -> receipt.isPaid()) // фільтруйте оплачені чеки
                .filter(receipt -> receipt.getCustomer().equals(customer)) // фільтруйте чеки конкретного користувача
                .flatMap(receipt -> receipt.getPurchasedProducts().stream())
                .collect(Collectors.summingDouble(Product::getPrice));
    }

    public void addCustomer(Customer customer) {
        this.customer = customer;
    }
}
