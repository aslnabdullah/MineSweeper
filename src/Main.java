import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        System.out.print("please enter the row number ");
        int row = scanner.nextInt();
        System.out.print("Please enter the column number ");
        int col = scanner.nextInt();
        //In point 7, the array size is set by the user here
        MineSweeper mineSweeper = new MineSweeper(row,col);
        mineSweeper.run();

    }
}