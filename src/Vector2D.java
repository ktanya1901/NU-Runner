public class Vector2D {
    public double x;
    public double y;

    public Vector2D(double x, double y){
        this.x=x;
        this.y=y;
    }

    public void set(Vector2D v){
        this.x=v.x;
        this.y=v.y;
    }

    public double distanceTo(Vector2D other){
       double dist;
       dist=Math.sqrt(Math.pow(other.x-this.x,2.0)+
               Math.pow(other.y-this.y, 2.0));
       return dist;
    }

    public Vector2D normalize(){
        double root = Math.sqrt(Math.pow(this.x,2.0) + Math.pow(this.y, 2.0));
        double x1=this.x/root;
        double y1=this.y/root;
        return new Vector2D(x1,y1);
    }

    public Vector2D plus(Vector2D other){
        double x = this.x + other.x;
        double y = this.y + other.y;
        return new Vector2D(x,y);
    }

    public Vector2D minus(Vector2D other){
        double x = this.x - other.x;
        double y = this.y - other.y;
        return new Vector2D(x,y);
    }
}
