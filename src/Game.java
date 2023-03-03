import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    //Filling the table
    private String[][] table = new String[11][11];
    public String[][] getTable() {
        return table;
    }
    public void setTable(String[][] table) {
        this.table = table;
    }
    public void setTable() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                table[i][j] = "-";
            }
        }
        table[0] = new String[]{" ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (int i = 0; i < 10; i++) {
            table[i + 1][0] = Character.toString('A' + i);
        }
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Locate Ships by Computer
    private ArrayList<String> toLocateShips;
    private ArrayList<String> uncorrectPositions;
    public ArrayList<String> getToLocateShips() {
        return toLocateShips;
    }
    public void setToLocateShips() {
        ArrayList<String> list = new ArrayList<>();
        this.toLocateShips = list;
    }
    public ArrayList<String> getUncorrectPositions() {
        return uncorrectPositions;
    }
    public void setUncorrectPositions() {
        ArrayList<String> list = new ArrayList<>();
        this.uncorrectPositions = list;
    }
    public ArrayList createShips(){
        setToLocateShips();
        setUncorrectPositions();
        char[] array = {'A','B','C','D','E','F','G','H','I','G'};
        while (toLocateShips.size() != 9 ) {
            int numOne = (int)(Math.random()*2);
            if (numOne == 1) {
                int numOneLength = (int) (Math.random() * 7);
                char numOneWidth = array[(int) (Math.random() * 10)];
                String[] ship = new String[3];
                for (int i = 0; i < 3; i++) {
                    ship[i] = Integer.toString(numOneLength + i) + Character.toString(numOneWidth);
                }
                boolean count = false;
                for (int j = 0; j < 3; j++) {
                    count = uncorrectPositions.contains(ship[j]);
                    if (count == true)
                        break;
                }
                if (count == false) {
                    for (String number : ship)
                        toLocateShips.add(number);
                    for (int j = -1; j < 5; j++) {
                        uncorrectPositions.add(Integer.toString(numOneLength + j) + Character.toString(numOneWidth - 1));
                        uncorrectPositions.add(Integer.toString(numOneLength + j) + Character.toString(numOneWidth));
                        uncorrectPositions.add(Integer.toString(numOneLength + j) + Character.toString(numOneWidth + 1));
                    }
                }
            } else {
                int numOneLength = (int) (Math.random() * 10);
                int numOneWidth = array[(int) (Math.random() * 7)];
                String[] ship = new String[3];
                for (int i = 0; i < 3; i++) {
                    ship[i] = Integer.toString(numOneLength) + Character.toString(numOneWidth+i);
                }
                boolean count = false;
                for (int j = 0; j < 3; j++) {
                    count = uncorrectPositions.contains(ship[j]);
                    if (count == true)
                        break;
                }
                if (count == false) {
                    for (String number : ship)
                        toLocateShips.add(number);

                    for (int j = -1; j < 5; j++) {
                        uncorrectPositions.add(Integer.toString(numOneLength - 1) + Character.toString(numOneWidth + j));
                        uncorrectPositions.add(Integer.toString(numOneLength) + Character.toString(numOneWidth + j));
                        uncorrectPositions.add(Integer.toString(numOneLength + 1) + Character.toString(numOneWidth + j));
                    }
                }
            }
        }
        return toLocateShips;
    }

    //Blows
    private ArrayList<Integer> locatedShips;
    public int checkPushes(String pushInt){
        if(pushInt.length() > 2 | pushInt.charAt(0) < 48 | pushInt.charAt(0) > 57 | pushInt.charAt(1) > 90 | pushInt.charAt(1) < 65) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Choose correct position.");
                return 0;
            }
        }
        if(toLocateShips.indexOf(pushInt) < 0){
            System.out.println("Hit");
            table[pushInt.charAt(1)-'A'+1][pushInt.charAt(0)-'0'+1] = "1";
            return 0;
        }
        else {
            toLocateShips.remove(pushInt);
            int newCount = 0;
            for(int i = 0; i < toLocateShips.size(); i++)
                if(((int)(Math.abs(pushInt.charAt(0) - (toLocateShips.get(i)).charAt(0))) < 2) && ((int)(Math.abs(pushInt.charAt(1) - (toLocateShips.get(i)).charAt(1))) < 2)) {
                    newCount += 1;
                    break;
                }
            if(toLocateShips.size() != 9 & newCount == 0) {
                System.out.println("Sank! Find others");
                table[pushInt.charAt(1)-'A'+1][pushInt.charAt(0)-'0'+1] = "*";
                return 1;
            }
            else{
                System.out.println("Got it");
                table[pushInt.charAt(1)-'A'+1][pushInt.charAt(0)-'0'+1] = "*";
                return 1;
            }
        }
    }

    public void showTable(){
        System.out.println("Table of comp:");
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                System.out.print(table[i][j]+" ");
            }
            System.out.println();
        }
    }
}
