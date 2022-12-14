package io;

import constants.CONSTANTS;
import constants.GAME_STATE;
import game.GUIGame;
import screens.HUD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static game.Game.HEIGHT;
import static game.Game.WIDTH;

public class MouseInput extends MouseAdapter {
    public GUIGame game;
    public HUD hud;

    // Constructors for MouseInput
    public MouseInput(GUIGame game, HUD hud) {
        this.game = game;
        this.hud = hud;
    }

    public MouseInput(GUIGame game) {
        this.game = game;
    }


    @Override
    public final void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        switch (game.getGameState()) {
            case MAIN_MENU -> {
                if (mouseOver(mouseX, mouseY, 480, 400, 200, 70)) {
                    game.resetGame();
                    game.setGameState(GAME_STATE.IN_GAME);
                } else if (mouseOver(mouseX, mouseY, 480, 515, 200, 70)) {
                    game.setGameState(GAME_STATE.SETTINGS);
                } else if (mouseOver(mouseX, mouseY, 480, 630, 200, 70)) {
                    if (JOptionPane.showConfirmDialog(null, "Quit the game?", "Quit",
                            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                        game.setGameState(GAME_STATE.END);
                        System.exit(0);
                    }
                }
            }
            case PAUSED -> {
                if (mouseOver(mouseX, mouseY, 450, 475, 275, 70)) {
                    game.setGameState(GAME_STATE.IN_GAME);
                }
            }
            case SETTINGS -> {
                if (mouseOver(mouseX, mouseY, WIDTH - 215, HEIGHT - 175, 170, 80)) {
                    game.setGameState(GAME_STATE.MAIN_MENU);
                } else if (mouseOver(mouseX, mouseY, (int) (WIDTH / 2.70), (HEIGHT / 18) + 250, 300, 80)) {
                    JFrame jFrame = new JFrame();
                    Container container = jFrame.getContentPane();
                    container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
                    jFrame.setLocationRelativeTo(null);
                    jFrame.setResizable(false);
                    jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                    // make the buttons
                    final JButton background = new JButton("Choose Background Colour");
                    final JButton background_dark = new JButton("Choose Darkened Background Colour");
                    final JButton font = new JButton("Choose Font Colour");
                    final JButton accent = new JButton("Choose Accent Colour");
                    final JButton cursor = new JButton("Choose Cursor Colour");
                    final JButton textbox = new JButton("Choose Text Box Colour");
                    final JButton close = new JButton("Quit");

                    // align them properly
                    background.setAlignmentX(Component.CENTER_ALIGNMENT);
                    background_dark.setAlignmentX(Component.CENTER_ALIGNMENT);
                    font.setAlignmentX(Component.CENTER_ALIGNMENT);
                    accent.setAlignmentX(Component.CENTER_ALIGNMENT);
                    cursor.setAlignmentX(Component.CENTER_ALIGNMENT);
                    textbox.setAlignmentX(Component.CENTER_ALIGNMENT);
                    close.setAlignmentX(Component.CENTER_ALIGNMENT);

                    // make the listeners
                    ActionListener backgroundListener = e1 -> {
                        Color selected = JColorChooser.showDialog(null, "Pick a Background Colour",
                                CONSTANTS.BACKGROUND_COLOUR);
                        if (selected != null) {
                            CONSTANTS.BACKGROUND_COLOUR = selected;
                            JOptionPane.showMessageDialog(null, "Done!", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    };

                    ActionListener backgroundDarkListener = e1 -> {
                        Color selected = JColorChooser.showDialog(null, "Pick a Darkened Background Colour",
                                CONSTANTS.BACKGROUND_DARKENED);
                        if (selected != null) {
                            CONSTANTS.BACKGROUND_DARKENED = selected;
                            JOptionPane.showMessageDialog(null, "Done!", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    };

                    ActionListener fontListener = e1 -> {
                        Color selected = JColorChooser.showDialog(null, "Pick a Font Colour",
                                CONSTANTS.FONT_COLOUR);
                        if (selected != null) {
                            CONSTANTS.FONT_COLOUR = selected;
                            JOptionPane.showMessageDialog(null, "Done!", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    };

                    ActionListener accentListener = e1 -> {
                        Color selected = JColorChooser.showDialog(null, "Pick a Font Colour",
                                CONSTANTS.ACCENT_COLOUR);
                        if (selected != null) {
                            CONSTANTS.ACCENT_COLOUR = selected;
                            JOptionPane.showMessageDialog(null, "Done!", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    };

                    ActionListener cursorListener = e1 -> {
                        Color selected = JColorChooser.showDialog(null, "Pick a Cursor Colour",
                                CONSTANTS.CURSOR_COLOUR);
                        if (selected != null) {
                            CONSTANTS.CURSOR_COLOUR = selected;
                            JOptionPane.showMessageDialog(null, "Done!", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    };

                    ActionListener textBoxListener = e1 -> {
                        Color selected = JColorChooser.showDialog(null, "Pick a Text Box Colour",
                                CONSTANTS.TEXTBOX_COLOUR);
                        if (selected != null) {
                            CONSTANTS.TEXTBOX_COLOUR = selected;
                            JOptionPane.showMessageDialog(null, "Done!", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    };

                    ActionListener quitListener = e1 -> {
                        jFrame.setVisible(false);
                    };

                    // add the listeners to the buttons
                    background.addActionListener(backgroundListener);
                    background_dark.addActionListener(backgroundDarkListener);
                    font.addActionListener(fontListener);
                    accent.addActionListener(accentListener);
                    cursor.addActionListener(cursorListener);
                    textbox.addActionListener(textBoxListener);
                    close.addActionListener(quitListener);

                    // add all the buttons to the frame
                    container.add(background, BorderLayout.CENTER);
                    container.add(background_dark);
                    container.add(font);
                    container.add(accent);
                    container.add(cursor);
                    container.add(textbox);
                    container.add(close);

                    // make visible
                    jFrame.pack();
                    jFrame.setVisible(true);

                }
            }
            case IN_GAME -> {
                if (mouseOver(mouseX, mouseY, WIDTH - 75, 25, 50, 50)) {
                    game.setGameState(GAME_STATE.PAUSED);
                }
            }
        }
    }

    @Override
    public final void mouseReleased(MouseEvent e) {

    }

    public final boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
        return (mouseX > x && mouseX < x + width) && (mouseY > y && mouseY < y + height);
    }
}
