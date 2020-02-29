import java.util.Iterator;

public class Closest extends State {
    int duration;
    int grade;

    public Closest(int duration){
        super(false,true);
        this.duration=duration;
    }

    @Override
    public void step(Entity e) {

        Assessment closest = null;
        double maxDist=Double.MAX_VALUE;
        Iterator assessIter=e.common.assessments.listIterator();

        while (assessIter.hasNext()){
            Assessment assessment=(Assessment) assessIter.next();
            double dist;
            dist=e.position.distanceTo(assessment.position);
            if(dist<maxDist){
                closest=assessment;
                maxDist=dist;
            }
        }

        if(closest != null){
            if(maxDist<5){
                e.position.set(closest.position);
            }
            else{
                Vector2D step=(closest.position.minus(e.position)).normalize();
                step.x*=2;
                step.y*=2;
                e.position.set(e.position.plus(step));
            }
        }

        this.duration = this.duration-1;
        if(this.duration <= 0){
            this.isOver=true;
        }
    }

    @Override
    public String toString(){
        return "Closest";
    }
}
