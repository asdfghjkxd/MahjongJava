package io;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class NonBlockingIntegerDialog {
    private Integer input = null;
    private Dialog dialog = null;
    public static Integer DEFAULT_IF_FAIL = 0;

    public NonBlockingIntegerDialog(String title, String message, int messageType, Function<Integer, ?> callback) {
        JOptionPane pane = new JOptionPane(message, messageType);
        pane.setWantsInput(true);
        dialog = pane.createDialog(null, title);
        dialog.setModal(false);

        pane.addPropertyChangeListener(
                JOptionPane.VALUE_PROPERTY,
                x -> {
                    try {
                        input = Integer.valueOf(pane.getInputValue().toString());
                    } catch (NumberFormatException ex) {
                        input = DEFAULT_IF_FAIL;
                    }

                    callback.apply(input);
                    dialog.dispose();
                }
        );

        dialog.setVisible(true);
    }

    public int getInput() {
        return input == null ? Integer.MAX_VALUE : input;
    }
}
