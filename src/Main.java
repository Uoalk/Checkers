import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Board b=new Board();
        System.out.println(b);



         Scanner s=new Scanner(System.in);
         for(int i=0;i<170;i++){
            b=b.getBestMove(true).doMove();
            System.out.println(b);
            s.nextLine();
            b=b.getBestMove(false).doMove();
            System.out.println(b);
            s.nextLine();
        }


    }
}
