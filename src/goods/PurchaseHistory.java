package goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PurchaseHistory {
    private Map<Customer, Receipt> purchaseHistory;

    public Map<Product, Long> getTotalQuantityForCustomer(Customer customer, List<Receipt> receipts) {
        return receipts.stream()
                .filter(receipt -> receipt.getCustomer().equals(customer))
                .filter(Receipt::isPaid)
                .flatMap(receipt -> receipt.getPurchasedProducts().stream())
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
    }

    public PurchaseHistory() {
        this.purchaseHistory = new HashMap<>();
    }


    public void addPurchaseToHistory(Customer customer, Receipt receipt) {
        purchaseHistory.put(customer, receipt);
    }


    public Receipt getReceiptForCustomer(Customer customer) {
        return purchaseHistory.get(customer);
    }


    public String generateReceiptComment(Receipt receipt) {
        StringBuilder comment = new StringBuilder("Your purchase receipt on " + receipt.getPurchaseDate() + ":\n");
        for (Product product : receipt.getPurchasedProducts()) {
            comment.append("- ").append(product.getName()).append("\n");
        }
        return comment.toString();
    }
}
