public class Vector {
    int x,y;
    public Vector(int x,int y){
        this.x=x;
        this.y=y;
    }
    public Vector(Vector v){
        this.x=v.x;
        this.y=v.y;
    }
    public void add(Vector v){
        this.x+=v.x;
        this.y+=v.y;
    }
    public void div(int x){
        this.x/=x;
        this.y/=x;
    }
    public String toString(){
        return "{"+x+","+y+"}";
    }
}
