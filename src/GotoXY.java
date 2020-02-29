public class GotoXY extends State {
    public Vector2D destination;
    public Vector2D step;

    public GotoXY(Vector2D destination){
        super(false,true);
        this.destination=destination;
    }

    @Override
    public void step(Entity e) {
        if(e.position.distanceTo(this.destination) <= 3){
            e.position.set(this.destination);
            e.state.isOver=true;
        }
        else{
            this.step=(this.destination.minus(e.position)).normalize();
            this.step.x=this.step.x*2.5;
            this.step.y=this.step.y*2.5;
            e.position.set(e.position.plus(this.step));

            if(Math.abs(e.position.x-destination.x)<=0.5 && Math.abs(e.position.y-destination.y)<=0.5){
                e.position.set(this.destination);
                e.state.isOver=true;
            }
        }
    }

    @Override
    public String toString(){
        return "GotoXY";
    }
}
