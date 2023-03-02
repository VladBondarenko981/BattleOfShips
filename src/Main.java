import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game example1 = new Game();
        Scanner sc = new Scanner(System.in);
        example1.setTable();
        ArrayList <String> list = new ArrayList<>();
        ArrayList <String> list1 = new ArrayList<>();
        example1.setToLocateShips(list);
        example1.setUncorrectPositions(list1);
        example1.createShips();
        example1.checkPushes(sc.next());
    }
}