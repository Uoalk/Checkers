import java.util.ArrayList;
public class Piece extends Slot{
    boolean isBlack;
    boolean isKing;
    Vector pos;
    public Piece(Vector pos,boolean isBlack){

        this.pos=pos;
        this.isBlack=isBlack;
        this.isKing=false;
    }
    public void king(){
        this.isKing=true;
    }
    public String toString(){
        if(this.isBlack) return "B";
        else return "R";
    }
    public ArrayList<Vector> getMoves(Board board){
        ArrayList<Vector> moves =new ArrayList<>();
        moves.addAll(getWalks(board));
        moves.addAll(getJumps(board));

        return moves;
    }
    public ArrayList<Vector> getWalks(Board board){
        ArrayList<Vector> moves =new ArrayList<>();
        if(isBlack){
            Vector v=new Vector(pos);
            v.add(new Vector(1,1));
            if(board.isValidMove(v))moves.add(v);

            v=new Vector(pos);
            v.add(new Vector(-1,1));
            if(board.isValidMove(v))moves.add(v);
        }
        return moves;
    }
    public ArrayList<Vector> getJumps(Board board){
        ArrayList<Vector> moves =new ArrayList<>();

        return moves;
    }

}
