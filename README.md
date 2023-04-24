# 1024
#### Java practice, implement games: 1024, TEN...



## 1. Game 1024

- ### Rules: Input 'a', 'w', 's' or 'd' to slide all numbers to corresponding direction, any two same neighboring numbers will add up. New numbers will generate randomly in the free space. You lose if there is no valid slide operation.

- ### Goal: To get higher scores, which is the sum of all existing numbers.



## Example of 1024:

![image-20230423194257413](assets\image-20230423194257413.png?raw=true)

### I choose to slide leftwards, two 4s add up to 8 in the second row, two 1s add up to 2 in the last row. Other numbers just slide leftwards as if there is a gravity source.

![image-20230423194156043](assets\image-20230423194156043.png)





## 2. Game TEN

### Found Tic-Tac-Toe too simple, try a upgraded version: TEN!

- ### Rules: TEN has a overall large board as Tic-Tac-Toe. Each grid of this board is a small Tic-Tac-Toe board. Once you win a small Tic-Tac-Toe game, you conquer a grid in the large board. You win the whole game if you conquer three small grid in a straight line. The tricky part is that every steps in a small board indicates the next board you play. See the following example: here we have the 3x3 overall large board (1-9). When we are playing in the small board #3, if player A chooses e, player B must take the next step inthe small board #5, the same position in the large board!

- ### Goal: conquer 3 small boards in a straight line!



### 1      2      3  					board #3  a      b      c

### 4      5      6 					                  d      e      f

### 7      5      9 					                  g      h      i



### Example:

### Note: Number 1-9 in the number keyboard is corresponding to the grids.

![image-20230423200516237](assets\image-20230423200516237.png)



![image-20230423200539044](assets\image-20230423200539044.png)



![image-20230423200632729](assets\image-20230423200632729.png)

### Note: If player B conquer a board, the overview in the right will display the updated result. Player A is then free to choose a new board to continue.

![image-20230423200708634](assets\image-20230423200708634.png)

