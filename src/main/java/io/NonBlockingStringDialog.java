package io;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

public class NonBlockingStringDialog {
    private String input = null;
    private Dialog dialog = null;

    public NonBlockingStringDialog(String title, String message, int messageType, Function<String, ?> callback) {
        JOptionPane pane = new JOptionPane(message, messageType);
        pane.setWantsInput(true);
        dialog = pane.createDialog(null, title);
        dialog.setModal(false);

        pane.addPropertyChangeListener(
                JOptionPane.VALUE_PROPERTY,
                x -> {
                    input = pane.getInputValue().toString();
                    callback.apply(input);
                    dialog.dispose();
                }
        );

        dialog.setVisible(true);
    }
}
