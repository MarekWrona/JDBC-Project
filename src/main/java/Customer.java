import java.math.BigDecimal;

public class Customer {
    private long id;
    private String name;
    private int age;
    private String surname;
    private String address;
    private BigDecimal salary;

    public Customer(long id, String name, String surname, int age, String adress, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.address = adress;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public long getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getSurname() {
        return surname;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(id).append(". ").append(name).append(" ").append(surname.toUpperCase());
        sb.append(", ").append(age).append(", ").append(address);
        sb.append(", ").append(salary).append("\n");
        return sb.toString();
    }
}