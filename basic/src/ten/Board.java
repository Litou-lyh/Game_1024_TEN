package ten;

import java.util.Scanner;

public class Board {
    private int[] grid = new int[9];
    int current_player = 1;

    int last_step = -1;
    String winner_word = "The winner is player %d ";

    public Board(){
        this.print_init();
    }
    private void print_init() {}
    public void game() {

        this.display();
        while(! this.win()){
            System.out.println(" > ");
            this.step(this.current_player, get_step());
            this.display();
        }
    }

    public void display(){
        for (int i = 0; i < 3; i++) {
            System.out.println(String.format("%4d  %4d  %4d", grid[i * 3], grid[i * 3 + 1], grid[i * 3 + 2]));
        }
    }

    public String line(int i){
        char[] occupier = {'X','-','0'};
        return String.format("%4c  %4c  %4c  ", occupier[grid[i * 3]+1], occupier[grid[i * 3 + 1]+1], occupier[grid[i * 3 + 2]+1]);
    }

    public int get_step(){
        Scanner sc = new Scanner(System.in);
        int idx = -1;
        String player = this.current_player == 1 ? "A" : "B";
        while(true){
            System.out.println(String.format("Player %s step > (Any digit)", player));
            idx = sc.nextInt();
            if (idx < 1 || idx > 9 || this.grid[(2 - (idx - 1) / 3) * 3 + (idx - 1) % 3] != 0) {
                System.out.println("Invalid step!!");
            }
            else break;
        }
        return (2 - (idx - 1) / 3) * 3 + (idx - 1) % 3;
    }

    public void step(int player, int idx){
        if (this.grid[idx] == 0){
            this.grid[idx] = player;
            switch_player();
            this.last_step = idx;
        }
        else {
            System.out.println("Not a valid step!");
        }
    }

    public void switch_player(){
        this.current_player = - this.current_player;
    }

    public boolean win(){
        int[][] win_status = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for (int i = 0; i < 8; i++) {
            int candidate = 0;
            boolean flag = true;
            for (int j = 0; j < 3; j++) {
                if (this.grid[win_status[i][j]] == 0){
                    flag = false;
                    break;
                }
                else if (j == 0) {
                    candidate = this.grid[win_status[i][j]];
                }
                else if (this.grid[win_status[i][j]] != candidate){
                    flag = false;
                    break;
                }
                else {
                    continue;
                }
            }
            if (flag) {
//                System.out.println(String.format(winner_word, candidate));
                return true;
            }
        }
        return false;
    }
    public String getWinnerWord() {
        return this.winner_word;
    }
}
