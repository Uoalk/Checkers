import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board{
    Space[][] board;
    final int width;
    //creates a board that populates pieces in their right spots and spaces in their not
    public Board(){
        width=8;
        //the width and height is 8x8 for standard checkers
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
    //this returns a deep copy of the board itself
    public Board getCopy() {
        Board b=new Board();
        for(int rowI=0;rowI<width;rowI++){
            for(int colI=0;colI<width;colI++){
                b.board[rowI][colI]=(board[rowI][colI].getCopy());
            }
        }
        return b;
    }
    //this is the tostring that returns the board with all of its pieces
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
    //checks whether a given spot on the board has a piece or not
    public boolean isEmpty(Vector v){
        return v.x>=0 && v.y>=0 && v.x<width && v.y<width && !(board[v.y][v.x] instanceof Piece);
    }
    //returns a space at a given point
    public Space getSpace(Vector v){
        return board[v.y][v.x];
    }
    //sets a space on the board to a point
    public void setSpace(Vector v, Space s){
        board[v.y][v.x]=s;
    }
    //gets all the moves for a given vector
    public ArrayList<Move> getPositionMoves(Vector v){
        return getSpace(v).getMoves(this,v);
    }
    //ranks how good a future state is
    public float rank(boolean isBlack){
        //Kings are worth 2 points
        //Each piece is worth 0.1 * how far it went


        //goes through and calculates the score of a given state
        float score=0;
        for(int rowI=0;rowI<width;rowI++){
            for(int colI=0;colI<width;colI++) {

                Space space=getSpace(new Vector(colI,rowI));
                if(space instanceof Piece){
                    Piece piece=(Piece)space;
                    if(piece.isKing){
                        if(piece.isBlack==isBlack)score+=2;
                        else score-=2;
                    } else{
                        if(piece.isBlack){
                            if(isBlack)score+=0.1*rowI;
                            else score-=0.1*rowI;
                        }else{
                            if(isBlack)score-=0.1*(width-1-rowI);
                            else score+=0.1*(width-1-rowI);
                        }

                    }
                }
            }
        }
        if(Math.abs(score)<0.01)score=0;//account for floating point errors
        return score;
    }
    //ranks board states after a given amount of moves
    public float rank(boolean blackStarts, int n){
        if(n==0)return rank(blackStarts);

        ArrayList<Move> moves=getMoves(blackStarts);
        ArrayList<Board> outcomes=new ArrayList<>();
        for(int i=0;i<moves.size();i++){
            outcomes.add(moves.get(i).doMove());
        }

        //calculates the best rank
        if(moves.size()==0)return -100;
        float bestRank=moves.get(0).doMove().rank(!blackStarts,n-1);
        for(int i=0;i<moves.size();i++) {
            if(-moves.get(0).doMove().rank(!blackStarts,n-1)>bestRank){
                bestRank=moves.get(0).doMove().rank(!blackStarts,n-1);
            }
        }
        return bestRank;

    }

    //gets the moves for any given spot
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
    //calculates the best move from the rankings
    public Move getBestMove(boolean isBlack){
        ArrayList<Move> moves=getMoves(isBlack);
        int bestMoveIndex=0;
        float bestRank=moves.get(0).doMove().rank(isBlack);
        for(int i=0;i<moves.size();i++){
            float rank=moves.get(i).doMove().rank(isBlack);
            if(rank>bestRank){
                bestMoveIndex=i;
                bestRank=rank;
            }
        }
        return moves.get(bestMoveIndex);
    }
    //uses the ranks to find the best move after a given amount of turns
    public Move getBestMove(int n, boolean isBlack){
        ArrayList<Move> moves=getMoves(isBlack);
        int bestMoveIndex=0;

        float bestRank=moves.get(0).doMove().rank(isBlack);
        for(int i=0;i<moves.size();i++){
            float rank=moves.get(i).doMove().rank(isBlack,n);

                if(rank>bestRank){
                    bestMoveIndex=i;
                    bestRank=rank;
                }

        }
        return moves.get(bestMoveIndex);
    }
    //displays the current boardstate
    public void display(){
        System.out.print("  ");
        for(int i=0;i<width;i++) {
            System.out.print(i);
        }
        System.out.println();
        for(int rowI=0;rowI<width;rowI++){
            System.out.print(rowI+" ");
            for(int colI=0;colI<width;colI++) {
                System.out.print(board[rowI][colI]);
            }
            System.out.println();
        }
    }

}

