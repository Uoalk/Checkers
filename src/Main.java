import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Board b=new Board();
        Scanner s = new Scanner(System.in);
        Human h=new Human();

        //the code that runs the game
        System.out.println("Welcome to our checkers AI!!!");
        System.out.println("To play against the AI, type \"play\". To watch two battle each other, type \"demo\"");
        if(s.nextLine().equals("play")){
            //if you type 'play', then the AI will play moves against you
            while(true) {
                //this gets the possible moves for you and awaits your input
                b = h.getMove(b, true).doMove();
                //the red AI plays back against you
                b = b.getBestMove(4, false).doMove();
            }
        }
        else {
            //if you type 'demo', then the two AI's will fight back and forth picking and playing the best move
            while (true) {
                System.out.println("TYPE ANYTHING INTO THE CONSOLE TO SEE NEXT MOVE");
                //black AI's move
                b = b.getBestMove(2, true).doMove();
                //red AI's move
                b = b.getBestMove(2, false).doMove();
                System.out.print(b);
                s.nextLine();
            }
        }

    }
}
