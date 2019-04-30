import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Board b=new Board();
        Scanner s = new Scanner(System.in);
        Human h=new Human();

        System.out.println("Welcome to our checkers AI!!!");
        System.out.println("To play against the AI, type \"play\". To watch two battle each other, type \"demo\"");
        if(s.nextLine().equals("play")){
            while(true) {
                b = h.getMove(b, true).doMove();
                b = b.getBestMove(4, false).doMove();
            }
        }
        else {
            while (true) {
                System.out.println("TYPE ANYTHING INTO THE CONSOLE TO SEE NEXT MOVE");
                //b=h.getMove(b,true).doMove();
                //b=b.getBestMove(4, false).doMove();
                //System.out.println(b);
                //System.out.println(b.rank(true,2));
                //s.nextInt();
                b = b.getBestMove(2, true).doMove();
                b = b.getBestMove(2, false).doMove();
                System.out.print(b);
                s.nextLine();
            }
        }

    }
}
