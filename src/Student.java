import java.awt.*;
import java.awt.geom.AffineTransform;

public class Student extends Entity {
    public int grade;

    public Student(String name, Vector2D position, State state, Common common, int grade){
        super(name, position, state, common);
        this.grade=grade;
    }

    @Override
    public void draw(Graphics2D g2d) {
        Font origFont=g2d.getFont();
        FontMetrics metrics=g2d.getFontMetrics();
        AffineTransform origin=g2d.getTransform();
        String string;

        g2d.setFont(new Font("Sans Serif", Font.BOLD, 12));
        g2d.translate(position.x, position.y);

        if(grade >= 100){
            g2d.setPaint(Color.MAGENTA);
        }
        else{
            g2d.setPaint(Color.CYAN);
        }

        g2d.fillOval(-15, -15, 30, 30);
        g2d.setPaint(Color.BLACK);
        g2d.drawOval(-15, -15, 30, 30);

        string=this.name;
        g2d.setPaint(Color.BLACK);
        g2d.drawString(string, (-metrics.stringWidth(string)/2)+1, -18);

        string=grade+"";
        g2d.setPaint(Color.BLACK);
        g2d.drawString(string, (-metrics.stringWidth(string)/2)+1,5);

        string=this.state.toString();
        g2d.setPaint(Color.BLACK);
        g2d.drawString(string, (-metrics.stringWidth(string)/2)+1, 25);

        g2d.setTransform(origin);
        g2d.setFont(origFont);
    }
}
