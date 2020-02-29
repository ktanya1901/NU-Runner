import java.awt.*;
import java.awt.geom.AffineTransform;

public class Quiz extends Assessment {
    public Quiz(String name, Vector2D position, State state,Common common,int points){
        super(name, position, state, common, points);
    }

    @Override
    public void draw(Graphics2D g2d) {
        Font origFont=g2d.getFont();
        FontMetrics metrics=g2d.getFontMetrics();
        AffineTransform origin=g2d.getTransform();
        String string;

        g2d.setFont(new Font("Sans Serif", Font.BOLD, 12));
        g2d.translate(position.x, position.y);

        g2d.setPaint(new Color(51, 153, 255));
        int x1[]={-10, 0, 10};
        int y1[]={10, -10, 10};
        g2d.fillPolygon(x1, y1, 3);

        string=this.points+"";
        g2d.setPaint(Color.BLACK);
        g2d.drawString(string, (-metrics.stringWidth(string)/2)+1,5);

        g2d.setTransform(origin);
        g2d.setFont(origFont);
    }
}
