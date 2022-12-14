package io;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class BlockingStringInput {
    private static volatile String input;

    public static void getInput(String title, String message, int messageType) {
        try {
            EventQueue.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    input = JOptionPane.showInputDialog(null, message, title, messageType);
                }
            });
        } catch (InvocationTargetException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String retrieveInput() {
        return input;
    }
}
