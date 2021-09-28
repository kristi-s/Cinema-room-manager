package cinema;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Cinema {

    public static class Hall {
        int rows;
        int seats;
        int sold = 0;
        int currentIncome = 0;
        int totalIncome;
        char[][] places;

        public Hall(int rows, int seats) {
            this.rows = rows;
            this.seats = seats;
            this.places = fillArray(rows, seats);
            this.totalIncome = calcTotalIncome();
        }

        private char[][] fillArray(int row, int seats) {
            char[][] arr = new char[row][seats];
            for(int i = 0; i < row; i++){
                for (int j = 0; j < seats; j++){
                    arr[i][j] = 'S';
                }
            }
            return arr;
        }

        private int calcTotalIncome() {
            int price_front = 10;
            int price = 8;
            int total = 0;
            if (rows * seats < 60) {
                total = price_front * rows * seats;
            } else {
                total = rows / 2 * seats * price_front + (rows - rows / 2) * seats * price;
            }
            return (total);
        }
        
        public void printSeats () {
            System.out.println("Cinema:");
            IntStream.rangeClosed(0, seats).forEach(i -> {
                if (i == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(i + " ");
                }
            });
            System.out.println();
            for(int i = 0; i < rows; i++) {
                System.out.print(i + 1);
                for (int j = 0; j < seats; j++) {
                    System.out.print(" " + places[i][j]);
                }
                System.out.println();
            }
            System.out.println();
        }

        public void buyTicket(Scanner scanner) {
            int row = 0;
            int seat = 0;

            while(true) {
                System.out.println("\nEnter a row number:");
                row = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                seat = scanner.nextInt();
                if (row < 0 || row > rows || seat < 0 || seat > seats) {
                    System.out.println("\nWrong input!");
                } else if (places[row - 1][seat - 1] == 'B') {
                    System.out.println("\nThat ticket has already been purchased!");
                } else {
                    break;
                }
            }
            int price = 10;
            if (rows * seats > 60 && row - 1 >= rows / 2) {
                price = 8;
            }
            System.out.println("\nTicket price: $" + price);
            places[row - 1][seat - 1] = 'B';
            sold++;
            currentIncome += price;
        }

        public void showStat() {
            System.out.println("\nNumber of purchased tickets: " + sold);
            System.out.printf("Percentage: %1$.2f%%\n", ((float)sold / (float)(rows * seats) * 100.0));
            System.out.println("Current income: $" + currentIncome);
            System.out.println("Total income: $" + totalIncome);
        }
    }

    public static void printUserMenu() {
        System.out.println("\n1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");

    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        if (rows <= 0 || seats <= 0) {
            System.out.println("Wrong input!");
            return;
        }

        Hall hall = new Hall(rows, seats);
        int nextCommand = 1;

        while (nextCommand != 0) {
            printUserMenu();
            nextCommand = scanner.nextInt();
            switch (nextCommand) {
                case 1:
                    hall.printSeats();
                    break;
                case 2:
                    hall.buyTicket(scanner);
                    break;
                case 3:
                    hall.showStat();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Incorrect number");
            }
        }
    }
}