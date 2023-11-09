package goods;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PurchaseHistory {
    private Map<Customer, Receipt> purchaseHistory;

    public Map<Product, Long> getTotalQuantityForCustomer(Customer customer, List<Receipt> receipts) {
        return receipts.stream()
                .filter(receipt -> receipt.getCustomer().equals(customer)) // фільтруємо чеки для вибраного користувача
                .filter(Receipt::isPaid) // фільтруємо оплачені чеки (якщо є поле для позначки)
                .flatMap(receipt -> receipt.getPurchasedProducts().stream()) // розгортаємо список продуктів з чеків
                .collect(Collectors.groupingBy(p -> p, Collectors.counting())); // групуємо за продуктом та підраховуємо кількість кожного
    }
    public PurchaseHistory() {
        this.purchaseHistory = new HashMap<>();
    }

    // Метод для додавання чеку покупки користувача в історію покупок
    public void addPurchaseToHistory(Customer customer, Receipt receipt) {
        purchaseHistory.put(customer, receipt);
    }

    // Метод для отримання чеку покупки для певного користувача
    public Receipt getReceiptForCustomer(Customer customer) {
        return purchaseHistory.get(customer);
    }

    // Метод для генерації коментарів до чеку
    public String generateReceiptComment(Receipt receipt) {
        // Генерація коментаря до чеку
        // Наприклад:
        StringBuilder comment = new StringBuilder("Your purchase receipt on " + receipt.getPurchaseDate() + ":\n");
        for (Product product : receipt.getPurchasedProducts()) {
            comment.append("- ").append(product.getName()).append("\n");
        }
        return comment.toString();
    }
}
