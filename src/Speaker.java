import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Speaker extends Entity {
    public int width;
    public int height;
    private BufferedImage speakerImage;

    public Speaker(String name,Vector2D position, State state,Common common, String path){
        super(name, position, state, common);
        try {
            this.speakerImage= ImageIO.read(new File(path));
            this.width=this.speakerImage.getWidth()/4;
            this.height=this.speakerImage.getHeight()/4;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        Font origFont=g2d.getFont();
        FontMetrics metrics=g2d.getFontMetrics();
        AffineTransform origin=g2d.getTransform();
        String string;

        g2d.setFont(new Font("Sans Serif", Font.BOLD, 12));
        g2d.translate(position.x, position.y);

        g2d.drawImage(speakerImage,-width/2, -height/2, width, height, null);

        string=this.name;
        g2d.setPaint(Color.BLACK);
        g2d.drawString(string, (-metrics.stringWidth(string)/2)+1,-height/2-2);

        g2d.setTransform(origin);
        g2d.setFont(origFont);
    }
}
