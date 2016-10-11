package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ApplicationWindow extends JFrame{

    private void readFile(File file){
        try {
            Files.readAllLines(file.toPath()).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ApplicationWindow(){
        this.setTitle("Test Title");
        this.setSize(400,200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("over lapping menu");
        JMenuItem item = new JMenuItem("over lapping item");
        menu.add(item);
        menuBar.add(menu);

        this.setJMenuBar(menuBar);
        this.validate();

        this.setType(Type.POPUP);

        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics gradient) {
                if (gradient instanceof Graphics2D) {
                    final int Red = 120;
                    final int Green = 50;
                    final int Blue = 150;
                    Paint paint = new GradientPaint(0.0f, 0.0f,
                            new Color(Red, Green, Blue, 0),
                            getWidth(), getHeight(),
                            new Color(Red, Green, Blue, 255));
                    Graphics2D gradient2d = (Graphics2D) gradient;
                    gradient2d.setPaint(paint);
                    gradient2d.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        panel.setBorder(BorderFactory.createLineBorder(Color.red,5,false));
        this.setLayout(new FlowLayout());

        this.setContentPane(panel);
        this.setLayout(new FlowLayout());

        FileDialog fileDialog = new FileDialog(this,"FileDialge");
        fileDialog.setMultipleMode(true);

        JButton button_file = new JButton("FileDiage");
        button_file.addActionListener(e->{
            fileDialog.setVisible(true);
            for(File file : fileDialog.getFiles()){
                readFile(file);
            }
        });

        JButton button_exit = new JButton("exit");
        panel.add(button_exit);
        panel.add(button_file);
        
        //this.add(panel);

        //Button button = new Button("exit");
        //button.addActionListener(e -> System.exit(0));
        //this.add(button);
    }
}
