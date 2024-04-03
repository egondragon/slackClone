import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame jf = new JFrame();
                jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                loginDialog dialog = new loginDialog(jf);
                dialog.setVisible(true);

                /*
                testSwing t = new testSwing();
                t.setVisible(true);
                t.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                 */
            }
        });
    }
}
