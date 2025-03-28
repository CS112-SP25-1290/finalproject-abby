package edu.miracosta.cs112.finalproject.finalproject;

public class ClawMachine
{
   private String[][] inventory;
   private int numOfPrizes;
   private int attempts;


   public ClawMachine()
   {
       this.inventory = null;
       this.numOfPrizes = 0;
       this.attempts = 0;
   }


   public ClawMachine(String[][] inventory, int numOfPrizes, int attempts)
   {
       this.inventory = inventory;
       this.numOfPrizes = numOfPrizes;
       this.attempts = attempts;
   }


   public ClawMachine(ClawMachine original)
   {
       if(original != null)
       {
           this.setAll(original.inventory, original.numOfPrizes, original.attempts);
       }
   }


   public boolean setInventory(String[][] inventory)
   {
       if(inventory != null)
       {
           this.inventory = inventory;
           return true;
       }


       return false;
   }


   public boolean setNumOfPrizes(int numOfPrizes)
   {
       if(numOfPrizes >= 0)
       {
           this.numOfPrizes = numOfPrizes;
           return true;
       }
       return false;
   }


   public boolean setAttempts(int attempts)
   {
       if(attempts >= 0)
       {
           this.attempts = attempts;
           return true;
       }
       return false;
   }


   public boolean setAll(String[][] inventory, int numOfPrizes, int attempts)
   {
       if(setInventory(inventory) && setNumOfPrizes(numOfPrizes) && setAttempts(attempts))
       {
           this.setInventory(inventory);
           this.setNumOfPrizes(numOfPrizes);
           this.setAttempts(attempts);
           return true;
       }


       return false;
   }


   public String[][] getInventory()
   {
       return this.inventory;
   }


   public int getNumOfPrizes()
   {
       return this.numOfPrizes;
   }


   public int getAttempts()
   {
       return this.attempts;
   }


   public String toString()
   {
       return "" + this.inventory + ", " + this.numOfPrizes + ",  " + this.attempts;
   }


   public boolean equals(Object other)
   {
       ClawMachine otherMachine;
       if(other == null)
       {
           return false;
       }
       else if(!(other instanceof ClawMachine))
       {
           return false;
       }
       else
       {
           otherMachine = (ClawMachine) other;
           return this.inventory.equals(otherMachine.inventory) && this.numOfPrizes == otherMachine.numOfPrizes && this.attempts == otherMachine.attempts;
       }
   }


   public boolean activateClaw(int row, int col)
   {
       this.attempts--;
       if(row < 0 || row >= inventory.length || col < 0 || col >= inventory[row].length)
       {
           System.out.println("Invalid location.");
           return false;
       }
       else if(inventory[row][col] != null)
       {
           System.out.println("Prize grabbed: " + inventory[row][col]);
           inventory[row][col] = null;
           numOfPrizes--;
           return true;
       }
       else{
           System.out.println("No prize in this location.");
           return false;
       }
   }


   public void updateStatus()
   {
       System.out.println("Number of remaining prizes: " + this.numOfPrizes);
       System.out.println("Number of attempts: " + this.attempts);
   }

}

