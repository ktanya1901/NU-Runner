public abstract class State{
    public boolean isOver;
    public boolean isVisible;

    public State(boolean isOver,boolean isVisible){
        this.isOver=false;
        this.isVisible=true;
    }

    public abstract void step(Entity e);

}
