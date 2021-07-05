package base;

import org.w3c.dom.ls.LSInput;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
//import java.lang.Math;

public class GameBoard {
    int[] grid;
    int score;
    int[] line;
    int[] free_list = new int[16];


    public void init(){
        grid = new int[]{1, 1, 1, 1,
                         2, 2, 2, 2,
                         4, 4, 4, 4,
                         1, 1, 1, 1};
        score = 0;

//        Random rand = new Random(System.currentTimeMillis());
//        int init_1 = rand.nextInt(16);
//        int init_2 = rand.nextInt(16);
//        while (init_1 == init_2){
//            init_2 = rand.nextInt(16);
//        }
//        grid[init_1] = 1;
//        grid[init_2] = 1;
        System.out.println("==================  Game Start!!!  ===============");
        display();
    }
    public void generate(int range){
        Random ran = new Random(System.currentTimeMillis());
        Random rand = new Random(System.currentTimeMillis());
        int a = ran.nextInt(8192 + score);
        int new_value = 1;
        if (a < score){
            new_value = 2;
        }

        int next = rand.nextInt(range);
        grid[free_list[next]] = new_value;
    }

    public void display(){
        System.out.println(String.format("    Current Score: %d", score));
        for (int i = 0; i < 4; i++) {
            System.out.println(String.format("%4d  %4d  %4d  %4d", grid[i * 4], grid[i * 4 + 1], grid[i * 4 + 2], grid[i * 4 + 3]));
        }
        System.out.println("==================================================");
        System.out.println();
    }

    public void assign_col(int[] board, int col, int[] line){
        for (int i = 0; i < 4; i++) {
            board[col + i * 4] = line[i];
        }
    }

    public void assign_row(int[] board, int row, int[] line){
        for (int i = 0; i < 4; i++) {
            board[row * 4 + i] = line[i];
        }
    }

    public void slide_up(int[] board) {
        for (int i = 0; i < 4; i++) {
            line = new int[]{0,0,0,0};
            int pos = 0;
            int temp = 0;
            for (int j = 0; j < 4; j++) {
                if (grid[i + j * 4] == 0) {
                    continue;
                }
                else if (grid[i + j * 4] == temp) {
                    line[pos] = temp * 2;
                    pos += 1;
                    temp = 0;
                }
                else {
                    if (temp != 0){
                        line[pos] = temp;
                        pos += 1;
                    }
                    temp = grid[i + j * 4];
                }

            }
            line[pos] = temp;
            assign_col(board, i, line);
        }
    }

    public void slide_down(int[] board) {
        for (int i = 0; i < 4; i++) {
            line = new int[]{0,0,0,0};
            int pos = 3;
            int temp = 0;
            for (int j = 3; j >= 0; j--) {
                if (grid[i + j * 4] == 0) {
                    continue;
                } else if (grid[i + j * 4] == temp) {
                    line[pos] = temp * 2;
                    pos -= 1;
                    temp = 0;
                } else {
                    if (temp != 0){
                        line[pos] = temp;
                        pos -= 1;
                    }
                    temp = grid[i + j * 4];
                }
            }
            line[pos] = temp;
            assign_col(board,i, line);
        }
    }

    public void slide_left(int[] board) {
        for (int i = 0; i < 4; i++) {
            line = new int[]{0,0,0,0};
            int pos = 0;
            int temp = 0;
            for (int j = 0; j < 4; j++) {
                if (grid[j + i * 4] == 0) {
                    continue;
                } else if (grid[j + i * 4] == temp) {
                    line[pos] = temp * 2;
                    pos += 1;
                    temp = 0;
                } else {
                    if (temp != 0){
                        line[pos] = temp;
                        pos += 1;
                    }
                    temp = grid[j + i * 4];
                }

            }
            line[pos] = temp;
            assign_row(board,i, line);
        }
    }

    public void slide_right(int[] board) {
        for (int i = 0; i < 4; i++) {
            line = new int[]{0,0,0,0};
            int pos = 3;
            int temp = 0;
            for (int j = 3; j >= 0; j--) {
                if (grid[j + i * 4] == 0) {
                    continue;
                } else if (grid[j + i * 4] == temp) {
                    line[pos] = temp * 2;
                    pos -= 1;
                    temp = 0;
                } else {
                    if (temp != 0){
                        line[pos] = temp;
                        pos -= 1;
                    }
                    temp = grid[j + i * 4];
                }

            }
            line[pos] = temp;
            assign_row(board,i, line);
        }
    }

    public boolean game_over(){
        if (get_free(this.grid) > 0) return false;
        else{
            int[] board_copy = this.grid.clone();
            slide_up(board_copy);
            if (get_free(board_copy) > 0) return false;
            slide_down(board_copy);
            if (get_free(board_copy) > 0) return false;
            slide_left(board_copy);
            if (get_free(board_copy) > 0) return false;
            slide_right(board_copy);
            if (get_free(board_copy) > 0) return false;
        }
        return true;
    }

    public int get_free(int[] board){
        free_list = new int[16];
        int pos = 0;
        for (int i = 0; i < 16; i++) {
            if (board[i] == 0){
                free_list[pos] = i;
                pos += 1;
            }
        }
        return pos;
    }

    public void setScore(){
        score = 0;
        for (int i = 0; i < 16; i++) {
            score += grid[i];
        }
    }

    public void game() {
        this.init();
//        slide_left(this.grid);
//        this.display();
        Scanner sc = new Scanner(System.in);
        while (!game_over()){
            System.out.print("Your Move > ");
            String cmd = sc.next();

            int[] grid_copy = this.grid.clone();

            switch (cmd){
                case "quit": {
                    System.out.println("Goodbye!");
                    return;
                }
                case "w":{
                    slide_up(this.grid);break;
                }
                case "s":{
                    slide_down(this.grid);break;
                }
                case "a":{
                    slide_left(this.grid);break;
                }
                case "d":{
                    slide_right(this.grid);break;
                }
            }
            setScore();
            if (!Arrays.equals(this.grid, grid_copy)){
                generate(get_free(this.grid));
            }
            display();
            for (int i = 0; i < 13; i++) {
                System.out.println();
            }
        }
    }


}

//        int move = direction[dir];
//        int leap_i = 5 - Math.abs(move);
//        int leap_j = - move;
//        boolean column = Math.abs(move) == 4;
//        boolean reverse = move > 0;
//        int [] line = {0, 0, 0, 0};
//        int pos = 0;
//        if (reverse) pos = 3;
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                int base = 8 + move * 2 - 1;
//                if (grid[base + j * leap_j + i * leap_i] == grid[base + j * leap_j + i * leap_i - move]) {
//                    line[pos] = grid[j * leap_j + i * leap_i] * 2;
//                    j += 1;
//                }
//                else {
//                    line[pos] = grid[j * leap_j + i * leap_i];
//                }
//                if (reverse) pos -= 1;
//                else pos += 1;
//            }
//            if (column){
//                assign_col(i, line);
//            }
//            else {
//                assign_row(i,line);
//            }
//
//            pos = 0;
//            if (reverse) pos = 3;
//            line = new int[4];