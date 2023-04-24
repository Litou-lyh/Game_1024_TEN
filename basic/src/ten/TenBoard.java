package ten;

public class TenBoard extends Board {
    private int owner = 0;

    // private grid, current_player in father class

    public TenBoard(){
        this.winner_word = "Place conquered by player %d ";
//        this.print_init();
    }

    private void print_init() {
        System.out.println("TenBoard init!");
    }

    public int get_last_step(){
        return this.last_step;
    }

    public int get_owner(){
        return this.owner;
    }

    public void conquer(int player){
        if (win()) {
            this.owner = player;
            String p = this.current_player == 1 ? "A" : "B";
            System.out.println(String.format("Place conquered by player %s !!", p));
        }
    }

//    public boolean game(int player){
//        this.current_player = player;
//        if (this.owner == 0){
//            this.last_step = get_step();
//            System.out.println(this.last_step);
//            step(this.current_player, this.last_step);
//
//            if (conquer(this.current_player)){
//                return true;
//            }
//            return false;
//        }
//        return true;
//    }
}
