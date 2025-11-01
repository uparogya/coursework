/**
 * A simple dmonstration of how interrupts work.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class KeyEventHandler extends Frame implements KeyListener {

    Label label;

    public KeyEventHandler() {
        label = new Label();
        label.setBounds(20, 50, 200, 20);
        add(label);

        addKeyListener(this);

        setSize(400, 400);
        setLayout(null);
        setVisible(true);


        Thread timerThread = new Thread(() -> {
            int interruptNumber = 0;

            try {
                while (true) {
                    Thread.sleep(500); // Sleep for 1 second (1000 milliseconds)
                    label.setText("Timer Interrupt " + interruptNumber++);
                }
            } catch (InterruptedException e) { }

        });

        // Start the thread
        timerThread.start();

        Thread networkThread = new Thread(() -> {
            int interruptNumber = 0;
            Random rand = new Random();
            try {
                while (true) {
                    Thread.sleep(rand.nextInt(2000)); // Sleep for 1 second (1000 milliseconds)
                    label.setText("NetworkInterrupt " + interruptNumber++);
                }
            } catch (InterruptedException e) { }

        });

        networkThread.start();
    }

    public void keyPressed(KeyEvent e) {
        label.setText("Keyboard Interrupt: " + e.getKeyChar());
    }

    public void keyReleased(KeyEvent e) {
        // Not used in this example
    }

    public void keyTyped(KeyEvent e) {
        // Not used in this example
    }

    public static void main(String[] args) {
        new KeyEventHandler();
    }
}
