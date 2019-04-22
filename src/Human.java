import java.util.ArrayList;
import java.util.Scanner;

public class Human {
    Scanner s;
    public Human(){
        s=new Scanner(System.in);
    }
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
