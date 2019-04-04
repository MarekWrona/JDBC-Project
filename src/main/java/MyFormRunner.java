import com.example.jdbc.mvp.MainForm;

import java.awt.*;

public class MyFormRunner {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyForm();
            }
        });

    }
}