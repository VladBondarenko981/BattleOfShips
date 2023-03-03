import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameWithComp example1 = new GameWithComp();
        /*int counter = 0;
        example1.setToLocateShips();
        example1.setUncorrectPositions();
        Scanner sc = new Scanner(System.in);
        example1.createShips();
        example1.setTable();
        while(counter != 9){
            System.out.println("Choose field. Please enter in format [0 - 9][A - J]");
            counter += example1.checkPushes(sc.next());
            example1.showTable();
            }
        System.out.println("Game over! You needed " + counter + " attempts!");*/
        int counter = 0;
        Scanner sc = new Scanner(System.in);
        example1.setTable();
        example1.setToLocateShips();
        example1.setUncorrectPositions();
        example1.setToLocateShipsForUser();
        example1.setUncorrectPositionsForUser();
        example1.setListOfCompBlows();
        example1.createShips();
        example1.showTable();
        while(counter != 9){
            System.out.println("Choose field. Please enter in format [0 - 9][A - J]");
            counter += example1.checkPushes(sc.next());
            example1.showTable();
        }
        sc.close();
    }
}