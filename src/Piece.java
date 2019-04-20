import java.util.ArrayList;
public class Piece extends Space {
    boolean isBlack;
    boolean isKing;
    public Piece(boolean isBlack){
        super();

        this.isBlack=isBlack;
        this.isKing=false;
    }
    public void king(){
        this.isKing=true;
    }
    public String toString(){
        if(isKing){
            if(this.isBlack) return "B";
            else return "R";
        }else{
            if(this.isBlack) return "b";
            else return "r";
        }

    }
    public ArrayList<Move> getMoves(Board board, Vector pos){
        ArrayList<Move> moves =new ArrayList<>();
        moves.addAll(getWalks(board,pos));
        moves.addAll(getJumps(board,pos));

        return moves;
    }

    public ArrayList<Move> getWalks(Board board,Vector pos){
        ArrayList<Move> moves =new ArrayList<>();
        ArrayList<Move> potentialMoves =new ArrayList<>();

        if(isBlack || isKing){
            potentialMoves.add(new Move(this,board,pos,new Vector(1,1)));
            potentialMoves.add(new Move(this,board,pos,new Vector(-1,1)));
        }
        if(!isBlack || isKing){
            potentialMoves.add(new Move(this,board,pos,new Vector(1,-1)));
            potentialMoves.add(new Move(this,board,pos,new Vector(-1,-1)));
        }
        for(int i=0;i<potentialMoves.size();i++){//loop through potential moves and get real moves
            if(potentialMoves.get(i).isValidMove()){
                moves.add(potentialMoves.get(i));//add the valid move to the move  list
            }
        }
        return moves;
    }
    public ArrayList<Move> getJumps(Board board, Vector pos){
        ArrayList<Move> moves =new ArrayList<>();
        ArrayList<Move> potentialMoves =new ArrayList<>();

        if(isBlack || isKing){
            potentialMoves.add(new Move(this,board,pos,new Vector(2,2)));
            potentialMoves.add(new Move(this,board,pos,new Vector(-2,2)));
        }
        if(!isBlack || isKing){
            potentialMoves.add(new Move(this,board,pos,new Vector(2,-2)));
            potentialMoves.add(new Move(this,board,pos,new Vector(-2,-2)));
        }
        for(int i=0;i<potentialMoves.size();i++){//loop through potential moves and get real moves
            if(potentialMoves.get(i).isValidMove()){
                moves.add(potentialMoves.get(i));
            }
        }

        return moves;
    }
    public Piece getCopy(){
        Piece p=new Piece(isBlack);
        p.isKing=this.isKing;
        return p;
    }

}
