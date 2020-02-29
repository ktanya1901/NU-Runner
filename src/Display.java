import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {
    public Common common;

    public Display(Common common){
        this.common=common;
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(common.map.width,common.map.height);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        common.drawAllEntities(g2d);

        if(this.common.workIsOver){
            String ceremony = "Graduation Ceremony";

            g2d.setFont(new Font("Sans Serif", Font.BOLD, 12));
            g2d.setPaint(Color.BLACK);
            g2d.drawString(ceremony,780, 550);
        }
    }
}
