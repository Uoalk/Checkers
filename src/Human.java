import java.util.ArrayList;
import java.util.Scanner;

public class Human {
    Scanner s;
    public Human(){
        //creates a scanner with the human so that you can input moves when you play
        s=new Scanner(System.in);
    }
    //you have to select a correct move from the variety of legal ones and it inputs that to the board
    public Move getMove(Board b, boolean isBlack){
        b.display();
        ArrayList<Move> moves=b.getMoves(isBlack);

        for(int i=0;i<moves.size();i++){
            System.out.print(i+": "+moves.get(i)+", ");
        }
        System.out.println();

        return moves.get(s.nextInt());

    }
}
