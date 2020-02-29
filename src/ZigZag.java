public class ZigZag extends State {
    public int duration;
    Vector2D direction;

    public ZigZag(Vector2D direction, int duration){
        super(false,true);
        this.duration=duration;
        this.direction=direction.normalize();

        this.direction.x=this.direction.x*2.5;
        this.direction.y=this.direction.y*2.5;
    }

    @Override
    public void step(Entity e) {
        e.position.set(e.position.plus(this.direction));

        if(e.position.x >= e.common.windowWidth || e.position.x <= 0){
            this.direction.x = -this.direction.x;
        }

        if(e.position.y >= e.common.windowHeight-10 || e.position.y <= 0){
            this.direction.y = -this.direction.y;
        }

        int time=this.duration-1;
        this.duration=time;
        if(time <= 0){
            e.state.isOver = true;
        }
    }

    @Override
    public String toString(){
        return "ZigZag";
    }
}
