import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board implements Comparable{
    Space[][] board;
    final int width;
    public Board(){
        width=8;
        board =new Space[width][width];
        for(int rowI=0;rowI<width;rowI++){
            board[rowI]=new Space[width];
            for(int colI=0;colI<width;colI++){
                if((colI+(rowI%2))%2==1 && rowI<3) board[rowI][colI]=new Piece(true);
                else if ((colI+(rowI%2))%2==1 && rowI>4) board[rowI][colI]=new Piece(false);
                else board[rowI][colI]=new Space();
            }
        }

    }
    public Board getCopy() {
        Board b=new Board();
        for(int rowI=0;rowI<width;rowI++){
            for(int colI=0;colI<width;colI++){
                b.board[rowI][colI]=(board[rowI][colI].getCopy());
            }
        }
        return b;
    }
    public String toString(){
        String str="";
        for(int rowI=0;rowI<width;rowI++){
            for(int colI=0;colI<width;colI++) {
                str+=board[rowI][colI];
            }
            str+="\n";
        }
        return str;
    }
    public boolean isEmpty(Vector v){
        return v.x>=0 && v.y>=0 && v.x<width && v.y<width && !(board[v.y][v.x] instanceof Piece);
    }
    public Space getSpace(Vector v){
        return board[v.y][v.x];
    }
    public void setSpace(Vector v, Space s){
        board[v.y][v.x]=s;
    }
    public ArrayList<Move> getPositionMoves(Vector v){
        return getSpace(v).getMoves(this,v);
    }
    public float rank(){
        //Kings are worth 2 points
        //Each piece is worth 0.1 * how far it went

        float score=0;
        for(int rowI=0;rowI<width;rowI++){
            for(int colI=0;colI<width;colI++) {

                Space space=getSpace(new Vector(colI,rowI));
                if(space instanceof Piece){
                    Piece piece=(Piece)space;
                    if(piece.isKing){
                        if(piece.isBlack)score-=2;
                        else score+=2;
                    } else{
                        if(piece.isBlack)score-=0.1*rowI;
                        else score+=0.1*(width-1-rowI);
                    }
                }
            }
        }
        if(Math.abs(score)<0.01)score=0;//account for floating point errors
        return score;
    }



    public int compareTo(Object o){
        assert(o instanceof Board);
        return (int)Math.signum(rank()-((Board)o).rank());
    }

    public ArrayList<Move> getMoves(boolean isBlack){
        ArrayList<Move> moves= new ArrayList<Move>();
        for(int rowI=0;rowI<width;rowI++){
            for(int colI=0;colI<width;colI++) {
                Space space=getSpace(new Vector(colI,rowI));
                if(space instanceof Piece && ((Piece)space).isBlack==isBlack){
                    moves.addAll(space.getMoves(this,new Vector(colI,rowI)));
                }
            }
        }
        return moves;
    }
    public Move getBestMove(boolean isBlack){
        ArrayList<Move> moves=getMoves(isBlack);
        int bestMoveIndex=0;
        float bestRank=moves.get(0).doMove().rank();
        for(int i=0;i<moves.size();i++){
            float rank=moves.get(i).doMove().rank();
            if(isBlack){
                if(rank<bestRank){
                    bestMoveIndex=i;
                    bestRank=rank;
                }
            }else{
                if(rank>bestRank){
                    bestMoveIndex=i;
                    bestRank=rank;
                }
            }
        }
        return moves.get(bestMoveIndex);
    }
    public Move getBestMove(int n, boolean isBlack){
        ArrayList<Move> moves=getMoves(isBlack);
        int bestMoveIndex=0;
        float bestRank=moves.get(0).doMove().rank();
        for(int i=0;i<moves.size();i++){
            float rank=moves.get(i).doMove().rank();
            if(isBlack){
                if(rank<bestRank){
                    bestMoveIndex=i;
                    bestRank=rank;
                }
            }else{
                if(rank>bestRank){
                    bestMoveIndex=i;
                    bestRank=rank;
                }
            }
        }
        return moves.get(bestMoveIndex);
    }

}

