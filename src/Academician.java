import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Academician extends Entity {
    public int width;
    public int height;
    private BufferedImage academImage;

    public Academician(String name, Vector2D position, State state,Common common, String path){
        super(name, position, state, common);
        try {
            this.academImage= ImageIO.read(new File(path));

            this.width=this.academImage.getWidth()/4;
            this.height=this.academImage.getHeight()/4;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This method is not used in this work
    //public Assessment createAssessment();

    @Override
    public void draw(Graphics2D g2d) {
        Font origFont=g2d.getFont();
        FontMetrics metrics=g2d.getFontMetrics();
        AffineTransform origin=g2d.getTransform();
        String string;

        g2d.setFont(new Font("Sans Serif", Font.BOLD, 12));
        g2d.translate(position.x, position.y);

        g2d.drawImage(academImage,-width/2, -height/2, width, height, null);

        string=this.name;
        g2d.setPaint(Color.BLACK);
        g2d.drawString(string, (-metrics.stringWidth(string)/2)+1,-height/2-2);

        string=this.state.toString();
        g2d.setPaint(Color.BLACK);
        g2d.drawString(string, (-metrics.stringWidth(string)/2)+1,height/2+10);

        g2d.setTransform(origin);
        g2d.setFont(origFont);
    }
}
