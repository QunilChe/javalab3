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

    public void addProductToReceipt(Product product) {
        FileService fileService = new FileService();
        List<Product> products = fileService.loadDataFromFile2("products.txt");
        if (product.getCategory().equalsIgnoreCase("fruits") || product.getCategory().equalsIgnoreCase("vegetables")) {
            purchasedProducts.add(products.get(0));
        }
        purchasedProducts.add(product);
    }


    public List<Product> getPurchasedProducts() {
        return purchasedProducts;
    }


    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void markAsPaid() {
        this.isPaid = true;
    }

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
                .filter(receipt -> receipt.isPaid())
                .filter(receipt -> receipt.getCustomer().equals(customer))
                .flatMap(receipt -> receipt.getPurchasedProducts().stream())
                .collect(Collectors.summingDouble(Product::getPrice));
    }

    public void addCustomer(Customer customer) {
        this.customer = customer;
    }
}
