public class Rest extends State {
    public int duration;

    public Rest(int duration){
        super(false,true);
        this.duration=duration;
    }

    @Override
    public void step(Entity e) {
        int time=this.duration-1;
        this.duration=time;
        if(time <= 0){
            e.state.isOver=true;
        }
    }

    @Override
    public String toString(){
        return "Rest";
    }
}
