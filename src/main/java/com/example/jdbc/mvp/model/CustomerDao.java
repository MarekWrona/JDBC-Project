package com.example.jdbc.mvp.model;

import java.util.List;

public interface CustomerDao {
    Customer findById(long customerId);

    String update(Customer c);

    String create(Customer c);

    String delete(long customerId);

    List<Customer> getallCustomers();

}
