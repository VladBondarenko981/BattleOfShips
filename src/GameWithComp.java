import java.io.IOException;
import java.util.ArrayList;

public class GameWithComp extends Game{
    private String[][] tableForUser = new String[11][11];
    public String[][] getTableForUser() {
        return tableForUser;
    }
    public void setTableForUser(String[][] table) {
        this.tableForUser = table;
    }
    @Override
    public void setTable() {
        super.setTable();
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                tableForUser[i][j] = "-";
            }
        }
        tableForUser[0] = new String[]{" ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (int i = 0; i < 10; i++) {
            tableForUser[i + 1][0] = Character.toString('A' + i);
        }
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(tableForUser[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void showTable(){
        super.showTable();
        System.out.println("Table of user:");
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                System.out.print(tableForUser[i][j]+" ");
            }
            System.out.println();
        }
    }

    //Create ship
    private ArrayList<String> toLocateShipsForUser;
    private ArrayList<String> uncorrectPositionsForUser;
    public ArrayList<String> getToLocateShipsForUser() {
        return toLocateShipsForUser;
    }
    public void setToLocateShipsForUser() {
        ArrayList<String> list = new ArrayList<>();
        this.toLocateShipsForUser = list;
    }
    public ArrayList<String> getUncorrectPositionsForUser() {
        return uncorrectPositionsForUser;
    }
    public void setUncorrectPositionsForUser() {
        ArrayList<String> list = new ArrayList<>();
        this.uncorrectPositionsForUser = list;
    }
    @Override
    public ArrayList createShips(){
        super.createShips();
        char[] array = {'A','B','C','D','E','F','G','H','I','G'};
        while (toLocateShipsForUser.size() != 9 ) {
            int numOne = (int)(Math.random()*2);
            if (numOne == 1) {
                int numOneLength = (int) (Math.random() * 7);
                int numWidth = (int) (Math.random() * 10);
                char numOneWidth = array[numWidth];
                String[] ship = new String[3];
                for (int i = 0; i < 3; i++) {
                    ship[i] = Integer.toString(numOneLength + i) + Character.toString(numOneWidth);
                }
                boolean count = false;
                for (int j = 0; j < 3; j++) {
                    count = uncorrectPositionsForUser.contains(ship[j]);
                    if (count == true)
                        break;
                }
                if (count == false) {
                    tableForUser[numWidth+1][numOneLength+1] = "X";
                    tableForUser[numWidth+1][numOneLength+2] = "X";
                    tableForUser[numWidth+1][numOneLength+3] = "X";
                    for (String number : ship)
                        toLocateShipsForUser.add(number);
                    for (int j = -1; j < 5; j++) {
                        uncorrectPositionsForUser.add(Integer.toString(numOneLength + j) + Character.toString(numOneWidth - 1));
                        uncorrectPositionsForUser.add(Integer.toString(numOneLength + j) + Character.toString(numOneWidth));
                        uncorrectPositionsForUser.add(Integer.toString(numOneLength + j) + Character.toString(numOneWidth + 1));
                    }
                }
            } else {
                int numOneLength = (int) (Math.random() * 10);
                int numWidth = (int) (Math.random() * 7);
                int numOneWidth = array[numWidth];
                String[] ship = new String[3];
                for (int i = 0; i < 3; i++) {
                    ship[i] = Integer.toString(numOneLength) + Character.toString(numOneWidth+i);
                }
                boolean count = false;
                for (int j = 0; j < 3; j++) {
                    count = this.uncorrectPositionsForUser.contains(ship[j]);
                    if (count == true)
                        break;
                }
                if (count == false) {
                    tableForUser[numWidth+1][numOneLength+1] = "X";
                    tableForUser[numWidth+2][numOneLength+1] = "X";
                    tableForUser[numWidth+3][numOneLength+1] = "X";
                    for (String number : ship)
                        toLocateShipsForUser.add(number);

                    for (int j = -1; j < 5; j++) {
                        uncorrectPositionsForUser.add(Integer.toString(numOneLength - 1) + Character.toString(numOneWidth + j));
                        uncorrectPositionsForUser.add(Integer.toString(numOneLength) + Character.toString(numOneWidth + j));
                        uncorrectPositionsForUser.add(Integer.toString(numOneLength + 1) + Character.toString(numOneWidth + j));
                    }
                }
            }
        }
        return toLocateShipsForUser;
    }

    //Blows
    private ArrayList<String> listOfCompBlows;
    public ArrayList<String> getListOfCompBlows() {
        return listOfCompBlows;
    }

    public void setListOfCompBlows() {
        ArrayList<String> list = new ArrayList<>();
        this.listOfCompBlows = list;
    }
    @Override
    public int checkPushes(String pushInt){
        super.checkPushes(pushInt);
        char[] array = {'A','B','C','D','E','F','G','H','I','G'};
        pushInt = Integer.toString((int) (Math.random() * 10)) + Character.toString(array[(int) (Math.random() * 10)]);
        while(listOfCompBlows.contains(pushInt))
            pushInt = Integer.toString((int) (Math.random() * 10)) + Character.toString(array[(int) (Math.random() * 10)]);
        System.out.printf("The comp choice is %s\n", pushInt);
        if(toLocateShipsForUser.indexOf(pushInt) < 0){
            System.out.println("Hit");
            tableForUser[pushInt.charAt(1)-'A'+1][pushInt.charAt(0)-'0'+1] = "1";
            return 0;
        }
        else {
            toLocateShipsForUser.remove(pushInt);
            int newCount = 0;
            for(int i = 0; i < toLocateShipsForUser.size(); i++)
                if(((int)(Math.abs(pushInt.charAt(0) - (toLocateShipsForUser.get(i)).charAt(0))) < 2) && ((int)(Math.abs(pushInt.charAt(1) - (toLocateShipsForUser.get(i)).charAt(1))) < 2)) {
                    newCount += 1;
                    break;
                }
            if(toLocateShipsForUser.size() != 9 & newCount == 0) {
                System.out.println("Sank! Find others");
                tableForUser[pushInt.charAt(1)-'A'+1][pushInt.charAt(0)-'0'+1] = "*";
                return 1;
            }
            else{
                System.out.println("Got it");
                tableForUser[pushInt.charAt(1)-'A'+1][pushInt.charAt(0)-'0'+1] = "*";
                return 1;
            }
        }
    }
}


