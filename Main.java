import java.util.Scanner;
class Main 
{
    public static void main(String[] args) {
        String[][] testInventory = new String[2][3];
        testInventory[0][0] = "Drama: Past Lives (2023)";
        testInventory[0][1] = "Horror: Psycho (1960)";
        testInventory[0][2] = "Comedy: The Grand Budapest Hotel (2014)";
        testInventory[1][0] = "Comedy: Jojo Rabbit (2019)";
        testInventory[1][1] = "Drama: Lady Bird (2017)";
        testInventory[1][2] = "Fantasy: Wicked (2024)";
        ClawMachine test = new ClawMachine(testInventory, (testInventory.length * testInventory[0].length), 10);
        Scanner keyboard = new Scanner(System.in);
        boolean done = false;

        do{
            System.out.println("\n~~~Main Menu~~~\n");
            System.out.println("1. Try to get a movie recommendation from the claw machine.");
            System.out.println("3. Check number of prizes and attempts.");
            System.out.println("2. Exit program");
            System.out.print("What would you like to do?\nEnter choice: ");
            int input = keyboard.nextInt();
            System.out.println();

            switch(input)
            {
                case 1:
                    int row = 0;
                    int col = 0;
                    System.out.println("Please enter row number (0-1)");
                    row = keyboard.nextInt();
                    System.out.println("Please enter column number (0-2)");
                    col = keyboard.nextInt();
                    test.activateClaw(row, col);
                    System.out.println("\nReturning to main menu.\n");
                    break;
                case 2:
                    test.updateStatus();
                    System.out.println("\nReturning to main menu.\n");
                    break;
                case 3:
                    System.out.println("Exiting Program...\n");
                    keyboard.close();
                    done = true;
                    break;
                default:
                    System.out.println("Invalid input, please enter a valid choice.");
                    System.out.println("\nReturning to main menu...\n");
                    break;
            }
        }while(!done);

        System.out.println("Thank you for trying out the claw machine!");
        
    }
}