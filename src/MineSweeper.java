import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    //
    int rowNumber;
    int colNumber;
    int size;
    boolean isFinish = false;
    String[][] gameBoard;
    String[][] mineBoard;
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    MineSweeper(int rowNumber, int colNumber){
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.gameBoard = new String[rowNumber][colNumber];
        this.mineBoard = new String[rowNumber][colNumber];
        this.size = rowNumber * colNumber;
    }
    //--------------------------------------------------------------------------
    public void run(){
        prepareBoard();
        printMineBoard(mineBoard);
        System.out.println("=================THE GAME BEGINS=================");
        while (!isFinish){

            printGameBoard(gameBoard);//Every time the user logs in, the playground is printed here again and updated. Point 11
            usersChoose();
            System.out.println("==========================================");
        }
    }
    //-------------------------------------------------------------------------
    public void usersChoose(){
        //at the very beginning I check whether the game is over or not.
        // if the previous round ended with all the points selected without mines, the game ends here. points 14.
        if (isGameFinish(mineBoard)){
            System.out.println("CONGRATULATIONS YOU WON THE GAME");// points 15
            isFinish = true;
            return;
        }
        //here the user marks the row and column they want. point 9
        System.out.println("Enter the line number you want to select");
        int choosenRow = scanner.nextInt();
        System.out.println("Enter the column number you want to select");
        int choosenCol = scanner.nextInt();
        //here it is checked whether the entered number is in the array. point 10
        if (choosenRow >= 0 && choosenRow < gameBoard.length && choosenCol >= 0 && choosenCol < gameBoard[choosenRow].length){
            if (mineBoard[choosenRow][choosenCol] == "*"){ //if the user steps on a mine, the game ends here. points 13
                System.out.println("You choose the mine");// points 15
                System.out.println("GAME OVER !!!"); // points 15
                isFinish = true;
            }
            else{
                //The number of mines around is checked here and if it is 0 or a different value, that value is printed. Point 12
                int numberOfMinesAround = 0;
                for (int i = choosenRow -1;i<=choosenRow +1;i++){
                    for (int j = choosenCol-1;j<= choosenCol+1;j++){
                        if ((i>= 0 && i < rowNumber) && (j >= 0 && j < colNumber)){
                            if (mineBoard[i][j] == "*"){
                                numberOfMinesAround++;
                            }
                        }
                    }
                }
                mineBoard[choosenRow][choosenCol] = Integer.toString(numberOfMinesAround);
                gameBoard[choosenRow][choosenCol] = Integer.toString(numberOfMinesAround);

            }
        }
        else {
            System.out.println("The number you entered exceeds the array indices");
            System.out.println("please try again.");
            printGameBoard(gameBoard);
            usersChoose();
        }
    }
    //------------------------------------------------------------------------------
    //in this function you prepare the game tables and place the mines.
    public void prepareBoard(){
        int randomRow,randomCol,mineNum = 0;
       //mines are placed here at a rate of one fourth of the number of cells. point 8
        while (mineNum != (size/4)){
            randomRow = random.nextInt(rowNumber);  //3
            randomCol = random.nextInt(colNumber);  //1
            if (!Objects.equals(mineBoard[randomRow][randomCol], "*")){//if(mineboard[randomrow][randomcol] != "*"
                mineBoard[randomRow][randomCol] = "*";                      //
                mineNum++;
            }
        }
        for (int i = 0;i< mineBoard.length;i++){
            for (int j = 0 ; j<mineBoard[i].length;j++){
                if (!Objects.equals(mineBoard[i][j], "*")){
                    mineBoard[i][j] = "-";
                }
            }
        }
        for (String[] strings : gameBoard) {
            Arrays.fill(strings, "-");
        }
    }
    //------------------------------------------------------------------------------------
    //I use this function to print the mines to the screen in the first place
    public void printMineBoard(String[][] array){
        for (String[] strings : array) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }
    //-------------------------------------------------------------------------------------
    //I use this function to print the table where the player will play
    public void printGameBoard(String[][] array){
        for (String[] strings : array) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }
    //---------------------------------------------------------------------------------------
    //this function is used to check if the game has completed successfully, if there are still unselected cells in the table the game is over
    public boolean isGameFinish(String[][] array){
        for (String[] strings : array) {
            for (String string : strings) {
                if (Objects.equals(string, "-")) {
                    return false;
                }
            }
        }
        return true;
    }
}
