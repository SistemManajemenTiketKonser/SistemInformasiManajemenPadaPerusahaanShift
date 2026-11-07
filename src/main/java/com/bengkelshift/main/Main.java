package com.bengkelshift.main;

import com.bengkel.shift.view.login;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new login().setVisible(true);
        });

    }
}