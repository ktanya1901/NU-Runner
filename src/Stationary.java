public class Stationary extends State {
    public Stationary(){
        super(false,true);
    }

    @Override
    public void step(Entity e) { }

    @Override
    public String toString(){
        return "Stationary";
    }
}
