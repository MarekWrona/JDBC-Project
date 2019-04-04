package com.example.jdbc.mvp.presenter;

import com.example.jdbc.mvp.model.Customer;
import com.example.jdbc.mvp.model.CustomerDao;
import com.example.jdbc.mvp.view.CreateUserView;

import java.awt.*;
import java.math.BigDecimal;

public class CreateUserPresenterImpl implements CreateUserPresenter {

    private final CreateUserView view;
    private final CustomerDao customerDao;

    public CreateUserPresenterImpl(CreateUserView view, CustomerDao customerDao) {
        this.view = view;
        this.customerDao = customerDao;
    }

    @Override
    public void onClick() {
        view.hideAlert();

        try {
            String name = requireNotEmpty(view.getName());
            String surname = requireNotEmpty(view.getSurname());
            int age = view.getAge();
            String address = requireNotEmpty(view.getAddress());
            BigDecimal salary = view.getSalary();

            Customer customer = new Customer(name, surname, age, address, salary);
            customerDao.create(customer);

        } catch (NumberFormatException e) {
            System.out.println("Unable to create customer. Error: " + e.getMessage());
            view.showAlert("Sprawdź poprawność wprowadzonych danych.", Color.RED);
        } catch (EmptyArgumentException e) {
            System.out.println("Unable to create customer. Error: " + e.getMessage());
            view.showAlert("Sprawdź czy wszystkie pola są uzupełnione.", Color.RED);
        }

    }

    private String requireNotEmpty(String value) {
        if (value.isEmpty()) throw new EmptyArgumentException();
        return value;
    }

    private class EmptyArgumentException extends IllegalArgumentException {
        EmptyArgumentException() {
            super("empty argument passed");
        }
    }
}
