import java.util.ArrayList;
public class Slot {
    Vector pos;
    public void Slot(Vector pos){
        assert(!(null==pos));
        this.pos=pos;
    }
    public String toString() {
        return "-";
    }
    public ArrayList<Vector> getMoves(){
        return new ArrayList<>();
    }
}
