package GUI;

import javax.swing.*;
import java.awt.*;

public class Demo1 {
    public static void main(String[] args) {

        GraphicsEnvironment envmt =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = envmt.getDefaultScreenDevice();
        if (!device.isWindowTranslucencySupported
                (GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSLUCENT)) {
            System.out.println("Translucent windows are not supported on your system.");
                    System.exit(0);
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(()->{
            ApplicationWindow window = new ApplicationWindow();
            window.setOpacity(0.65f);
            window.setVisible(true);
        });
    }
}
