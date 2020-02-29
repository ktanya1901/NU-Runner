import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NURunner {
    public JFrame window;
    public Display display;
    public Common common;

    private NURunner(String name){
        this.window=new JFrame();
        this.common=new Common();
        this.display=new Display(common);

        this.window.add(this.display);

        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.window.setTitle(name);
        this.window.setResizable(false);
        this.window.pack();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NURunner.this.window.setVisible(true);
            }
        });
    }

    public static void main(String[] args){
        String name="NU Graduation";
        NURunner runner = new NURunner(name);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runner.common.stepAllEntities();
                runner.display.repaint();
            }
        };
        Timer timer=new Timer(30,listener);
        timer.start();
    }

}

