import org.junit.Test;

public class CustomerJDBCTest {

    @Test
    public void test(){

        try {
            CustmerDao cd = new CustomerJDBCDaoImpl();
            Customer c = cd.findById(1);
            c.setAge(15);
            cd.update(c);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
