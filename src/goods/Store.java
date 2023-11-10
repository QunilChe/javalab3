package goods;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Store {
    private List<Product> products;

    public Store() {
        this.products = new ArrayList<>();
    }

    public Store(List<Product> products) {
        this.products = products;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void sellProduct(Product product) {
        if (products.contains(product)) {
            products.remove(product);
        } else {
            throw new StoreException.ProductNotFoundException("Product not found in the store");
        }
    }

    public List<Product> orderProduct(String category, double maxPrice) {
        List<Product> orderedProducts = products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category) && product.getPrice() <= maxPrice)
                .collect(Collectors.toList());

        if (category.equalsIgnoreCase("meat") || category.equalsIgnoreCase("fish")) {
            // Generate a comment for meat and fish products
            String comment = "Keep these products in the refrigerator: " +
                    orderedProducts.stream().map(Product::getName).collect(Collectors.joining(", "));
            System.out.println(comment);
        }

        if (!(category.equalsIgnoreCase("fruits") || category.equalsIgnoreCase("vegetables"))) {
            // Create a receipt.txt file and add non-fruit and non-vegetable products
            try (FileWriter fileWriter = new FileWriter("receipt.txt")) {
                for (Product product : orderedProducts) {
                    fileWriter.write(product.getName() + " - $" + product.getPrice() + "\n");
                }
            } catch (IOException e) {
                System.err.println("Error writing to the receipt file.");
            }
        }

        return orderedProducts;
    }


    public void editProductPrice(String productName, double newPrice) {
        products.stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst()
                .ifPresent(product -> product.setPrice(newPrice));
    }

    public double calculateAveragePrice() {
        if (products.isEmpty()) {
            return 0;
        }
        return products.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0);
    }

    public void editReceipt(Receipt receipt, List<Product> newProducts) {
        receipt.updatePurchasedProducts(newProducts);
    }
}
