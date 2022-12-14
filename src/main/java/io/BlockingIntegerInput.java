package io;

import tests.Test;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class BlockingIntegerInput {
    private static volatile int input;
    public static volatile BEHAVIOUR inputBehaviour = BEHAVIOUR.SILENCE;
    public enum BEHAVIOUR {
        RAISE,
        SILENCE,
        PASS
    }

    public static void getInput(String title, String message, int messageType) {
        try {
            EventQueue.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    String in = JOptionPane.showInputDialog(null, message, title, messageType);
                    try {
                        input = Integer.parseInt(in);
                    } catch (NumberFormatException ex) {
                        switch (inputBehaviour) {
                            case PASS, SILENCE -> {
                                run();
                            }
                            case RAISE -> throw ex;
                        }
                    }
                }
            });
        } catch (InvocationTargetException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static int retrieveInput() {
        return input;
    }
}
