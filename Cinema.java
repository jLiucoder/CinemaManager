import java.util.*;
import java.text.DecimalFormat;

public class Cinema {

    private static final DecimalFormat df2 = new DecimalFormat("#0.00");

    public static void main(String[] args) {
        boolean repeat = true;

        Scanner scan = new Scanner(System.in);
        int income = 0;
        int totalIncome = 0;
        int ticketGot = 0;
        System.out.println("Enter the number of rows:");
        int numRows = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numSeats = scan.nextInt();
        totalIncome = calcIncome(numRows, numSeats);
        String[][] grid = new String[numRows + 1][numSeats + 1];

        for (int i = 1; i < numRows + 1; i++) {
            for (int j = 0; j < numSeats + 1; j++) {

                if (j == 0) {
                    grid[i][j] = Integer.toString(i);

                } else {
                    grid[i][j] = "S";
                }
            }
        }
        while (repeat) {


            System.out.println("\n1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");

            String option = scan.next();

            if (option.contains("1")) {
                printTable(numRows, numSeats, grid);

            } else if (option.contains("2")) {
                boolean errorLoop = true;

                while (errorLoop) {
                    System.out.println("\nEnter a row number:");
                    int inputRowNumber = scan.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    int inputSeatNumber = scan.nextInt();
                    int ticketPrice = 0;


                    if (inputRowNumber > numRows || inputSeatNumber > numSeats ) {
                        System.out.println("\nWrong input!");
                        errorLoop = true;
                    } else {
                        errorLoop = false;
                        if (numRows * numSeats < 61) {
                            ticketPrice = 10;
                            income += 10;
                            ticketGot++;
                        } else {
                            if (numRows / 2 < inputRowNumber) {
                                ticketPrice = 8;
                                income += 8;
                                ticketGot++;
                            } else {
                                ticketPrice = 10;
                                income += 10;
                                ticketGot++;
                            }

                        }

                        //System.out.println("Ticket price: $" + ticketPrice);
                        if (grid[inputRowNumber][inputSeatNumber].equals("B")) {
                            System.out.println("\nThat ticket has already been purchased!");
                            errorLoop = true;
                        } else {
                            System.out.println("\nTicket price: $" + ticketPrice);
                            grid[inputRowNumber][inputSeatNumber] = "B";
                            errorLoop = false;
                        }
                    }
                }
            } else if (option.contains("3")) {
                double percent = ((ticketGot / ((double) numRows * numSeats)) * 100.0);
                System.out.println("\nNumber of purchased tickets: " + ticketGot + "\n" +
                        "Percentage: " + df2.format(percent) + "%\n" +
                        "Current income: $" + income + "\n" +
                        "Total income: $" + totalIncome);
            } else if (option.contains("0")) {
                repeat = false;
            }
        }
    }


    private static void printTable(int numRows, int numSeats, String[][] grid) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i < numSeats + 1; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 1; i < numRows + 1; i++) {
            for (int j = 0; j < numSeats + 1; j++) {

                if (j == 0) {
                    System.out.print(grid[i][j]);

                } else {
                    System.out.print(" " + grid[i][j]);
                }
            }
            System.out.println();
        }
    }


    private static int calcIncome(int rows, int seats) {
        int income = 0;
        if (rows * seats < 61) {
            income = 10 * rows * seats;
            return income;
        }
        int front = rows / 2;
        int back = rows - front;
        income = front * 10 * seats + back * 8 * seats;
        return income;
    }

}

