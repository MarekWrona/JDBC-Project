package com.example.jdbc.mvp.view;

import com.example.jdbc.mvp.presenter.CreateUserPresenter;

import java.awt.*;
import java.math.BigDecimal;

public interface CreateUserView {

    void setPresenter(CreateUserPresenter presenter);

    String getName();

    String getSurname();

    int getAge();

    String getAddress();

    BigDecimal getSalary();

    void showAlert(String message, Color color);

    void hideAlert();

}
