package goods;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileService {
    // Метод для генерації чеку замовлення у .txt форматі
    public void generateOrderReceipt(String fileName, Receipt receipt) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Order Receipt\n");
            writer.write("Date: " + receipt.getPurchaseDate() + "\n");
            writer.write("Items:\n");

            List<Product> meatAndFishProducts = new ArrayList<>();
            List<Product> FruitsAndVegetables = new ArrayList<>();
            for (Product product : receipt.getPurchasedProducts()) {
                writer.write("- " + product.getName() + " | Price: " + product.getPrice() + "\n");

                if (product.getCategory().equalsIgnoreCase("meat") || product.getCategory().equalsIgnoreCase("fish")) {
                    meatAndFishProducts.add(product);
                }
            }
            for (Product product : receipt.getPurchasedProducts()) {
                writer.write("- " + product.getName() + " | Price: " + product.getPrice() + "\n");

                if (product.getCategory().equalsIgnoreCase("fruits") || product.getCategory().equalsIgnoreCase("vegetables")) {
                    FruitsAndVegetables.add(product);
                }
            }


            if (!meatAndFishProducts.isEmpty()) {
                writer.write("Don't forget to store items ");
                for (int i = 0; i < meatAndFishProducts.size(); i++) {
                    writer.write(meatAndFishProducts.get(i).getName());
                    if (i < meatAndFishProducts.size() - 1) {
                        writer.write(", ");
                    } else {
                        writer.write(" in the refrigerator\n");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Метод для завантаження даних про кількість товарів та ціни з файлу на початку роботи програми
    public List<String> loadDataFromFile(String fileName) {
        List<String> lines = null;
        try {
            lines = Files.lines(Paths.get(fileName)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }


    // Метод для зчитування даних про товари з файлу
    public List<Product> loadDataFromFile1(String fileName) {
        try {
            return Files.lines(Paths.get(fileName))
                    .map(line -> {
                        String[] parts = line.split(","); // Розділення рядка
                        if (parts.length == 3) {
                            String name = parts[0].trim();
                            String category = parts[1].trim();
                            double price = Double.parseDouble(parts[2].trim());
                            return new Product(name, price, category );
                        }
                        return null;
                    })
                    .filter(product -> product != null)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



    public List<Product> loadDataFromFile2(String fileName) {
        List<Product> products = new ArrayList<>();

        try {
            products = Files.lines(Paths.get(fileName))
                    .map(line -> {
                        String[] parts = line.split(",");
                        if (parts.length == 3) {
                            String name = parts[0].trim();
                            String category = parts[1].trim();
                            double price = Double.parseDouble(parts[2].trim());
                            return new Product(name, price, category);
                        }
                        return null;
                    })
                    .filter(product -> product != null)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

}
