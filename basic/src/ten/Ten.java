package ten;

import java.util.Scanner;

public class Ten {
    private TenBoard[] boards = new TenBoard[9];
    private Board overBoard = new Board();
    private int current_player = 1;
    private int focus = -1;
    public Ten(){
        for (int i = 0; i < 9; i++) {
            boards[i] = new TenBoard();
        }
        display();
        choose_board();
        display();
    }

    public void choose_board(){

        Scanner s = new Scanner(System.in);
        int idx = -1;
        while(idx < 1 || idx > 9 || boards[idx].get_owner() != 0){
            System.out.println("You are free to choose a board now > ");
            idx = s.nextInt();
        }
        this.focus = (2 - (idx - 1) / 3) * 3 + (idx - 1) % 3;
    }

    public void display(){
        String[] boarders = new String[16];
        for (int i = 0; i < 12; i++) {
            boarders[i] = "------------------";
        }
        if (this.focus != -1){
            boarders[this.focus] = "@@@@@@@@@@@@@@@@@@";
            boarders[this.focus + 3] = "@@@@@@@@@@@@@@@@@@";
        }
        String boarder1 = "+" + boarders[0] + "+" + boarders[1] + "+" + boarders[2] + "+";
        String boarder2 = "+" + boarders[3] + "+" + boarders[4] + "+" + boarders[5] + "+";
        String boarder3 = "+" + boarders[6] + "+" + boarders[7] + "+" + boarders[8] + "+";
        String boarder4 = "+" + boarders[9] + "+" + boarders[10] + "+" + boarders[11] + "+";
        System.out.println(boarder1);
        System.out.println("|" + this.boards[0].line(0) + "|" + this.boards[1].line(0) + "|" + this.boards[2].line(0) + "|");
        System.out.println("|" + this.boards[0].line(1) + "|" + this.boards[1].line(1) + "|" + this.boards[2].line(1) + "|");
        System.out.println("|" + this.boards[0].line(2) + "|" + this.boards[1].line(2) + "|" + this.boards[2].line(2) + "|");
        System.out.println(boarder2);
        System.out.println("|" + this.boards[3].line(0) + "|" + this.boards[4].line(0) + "|" + this.boards[5].line(0) + "|" +
                "          " + this.overBoard.line(0));
        System.out.println("|" + this.boards[3].line(1) + "|" + this.boards[4].line(1) + "|" + this.boards[5].line(1) + "|" +
                "          " + this.overBoard.line(1));
        System.out.println("|" + this.boards[3].line(2) + "|" + this.boards[4].line(2) + "|" + this.boards[5].line(2) + "|" +
                "          " + this.overBoard.line(2));
        System.out.println(boarder3);
        System.out.println("|" + this.boards[6].line(0) + "|" + this.boards[7].line(0) + "|" + this.boards[8].line(0) + "|");
        System.out.println("|" + this.boards[6].line(1) + "|" + this.boards[7].line(1) + "|" + this.boards[8].line(1) + "|");
        System.out.println("|" + this.boards[6].line(2) + "|" + this.boards[7].line(2) + "|" + this.boards[8].line(2) + "|");
        System.out.println(boarder4);
    }

    public void play(){
        while (true){
            if (boards[focus].get_owner() != 0){
                this.choose_board();
            }
            else{
                boards[this.focus].step(this.current_player, boards[this.focus].get_step());

                boards[this.focus].conquer(this.current_player);
                this.overBoard.step(boards[this.focus].get_owner(), this.focus);
                this.current_player = - this.current_player;
                this.focus = boards[focus].get_last_step();
                display();
            }
        }
    }

}
