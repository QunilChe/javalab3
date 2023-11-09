package goods;

public class StoreException extends RuntimeException {
    public StoreException(String message) {
        super(message);
    }

    public static class ProductNotFoundException extends StoreException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidCategoryException extends StoreException {
        public InvalidCategoryException(String message) {
            super(message);
        }
    }
    // Add other exception classes here
}
