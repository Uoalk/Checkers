
public class Move {
    Vector from,to, skippedPiece;
    Piece piece;
    Board board;
    boolean isJump;
    public Move(Piece piece, Board board, Vector pos, Vector move){
        //initialized with a piece, board, position, and move
        this.piece=piece.getCopy();
        this.board=board;
        this.from=new Vector(pos);
        this.to=new Vector(pos);
        this.to.add(move);

        if(Math.abs(move.x)==1 && Math.abs(move.y)==1)isJump=false;
        else if(Math.abs(move.x)==2 && Math.abs(move.y)==2){


            isJump=true;
            skippedPiece=new Vector(move);
            skippedPiece.div(2);

            skippedPiece.add(pos);


        }
        //throws error if move is invalid
        else throw(new Error("Invalid Move"));

    }

    private boolean canMove(){//takes in the position, the attempted move direction
        if(board.isEmpty(to))return true;
        return false;
    }
    private boolean canJump(){//takes in the position, the attempted jump direction

        if(!board.isEmpty(to))return false;//can't jump if they are landing in an occupied square

        if(!(board.getSpace(skippedPiece) instanceof Piece))return false;//must jump over a piece
        if(((Piece) board.getSpace(skippedPiece)).isBlack==piece.isBlack)return false;//must jump over piece of opposite color


        return true;
    }
    public boolean isValidMove(){
        if(isJump)return canJump();
        else return canMove();
    }
    //returns the two vectors as a possibility for the moves
    public String toString(){
        return from+" to "+to;
    }
    //returns a board with new move
    public Board doMove(){
        assert(isValidMove());
        Board b=board.getCopy();
        if(this.isJump)b.setSpace(skippedPiece,new Space());

        b.setSpace(to,piece);

        if(piece.isBlack && to.y==board.width-1 || !piece.isBlack && to.y==0)((Piece)b.getSpace(to)).isKing=true;

        b.setSpace(from,new Space());
        return b;
    }

}
