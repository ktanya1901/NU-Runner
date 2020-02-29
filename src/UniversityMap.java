import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UniversityMap extends Entity {

    String name;
    public BufferedImage mapImg;
    public int width;
    public int height;


    public UniversityMap(String name, Vector2D position, State state, Common common, String path){
        super(name, position, state, common);
        try {
            this.name=name;
            this.mapImg= ImageIO.read(new File(path));

            this.width=this.mapImg.getWidth()*3/2;
            this.height=this.mapImg.getHeight()*3/2;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(mapImg,0,0, width,height,null);
    }
}
