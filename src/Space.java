import java.util.ArrayList;
public class Space{

    public Space(){
        //doesnt have any data because it's just a blank spot
    }
    //just returns a dash because it's blank
    public String toString() {
        return "-";
    }
    public ArrayList<Move> getMoves(Board board,Vector v){
        return new ArrayList<>();
    }
    public Space getCopy(){
        return new Space();
    }
}
