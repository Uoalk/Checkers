import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Board b=new Board();




        Human h=new Human();
        Scanner s=new Scanner(System.in);
        while(true){
            b=b.getBestMove(true).doMove();
            System.out.println(b);
            b=b.getBestMove(false).doMove();
            System.out.println(b);
            s.nextInt();



        }




    }
}
