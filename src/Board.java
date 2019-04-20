public class Board {
    Slot[][] board;
    int width;
    public Board(){
        width=8;
        board =new Slot[width][width];
        for(int rowI=0;rowI<width;rowI++){
            board[rowI]=new Slot[width];
            for(int colI=0;colI<width;colI++){
                if((colI+(rowI%2))%2==1 && rowI<3) board[rowI][colI]=new Piece(new Vector(colI,rowI),true);
                else if ((colI+(rowI%2))%2==1 && rowI>4) board[rowI][colI]=new Piece(new Vector(colI,rowI),false);
                else board[rowI][colI]=new Slot();
            }
        }

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
    public boolean isValidMove(Vector v){
        return v.x>0 && v.y>0 && v.x<width && v.y<width && !(board[v.y][v.x] instanceof Piece);
    }
}

