import java.util.List;

public interface CustmerDao {
    Customer  findById(long customerId);
    String update(Customer c);
    String create (Customer c);
    String delete(long customerId);
    List<Customer> getallCustomers();

}
