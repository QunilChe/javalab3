package goods;

public class Customer {
    private String firstName;
    private String lastName;
    private int age;
    // Інші особисті дані покупця

    public Customer(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        // Ініціалізація інших особистих даних
    }

    // Геттери та сеттери для особистих даних покупця

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
}
