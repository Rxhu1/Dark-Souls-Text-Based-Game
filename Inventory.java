package org.uob.a1;

public class Inventory {
    private final int MAX_ITEMS = 10;
    private int currItems;
    private String[] items;

    //Constructor
    public Inventory(){
        this.currItems = 0;
        this.items = new String[this.MAX_ITEMS];
    }

    //Adds an item to the array of items
    public void addItem(String item){
        if (this.currItems < this.MAX_ITEMS){
            for (int i=0; i<MAX_ITEMS; i++){
                if (this.items[i] == null){
                    this.items[i] = item;
                    this.currItems++;
                    System.out.println("Discovery: '" + item + "' added to inventory");
                    break;
                }
            }
        }
        else{
            System.out.println("Unable to add " + item + ". Your Inventory is full!");
        }
    }

    //Checks that the player has a certain item in their inventory.
    public int hasItem(String item){
        for(int i = 0; i < MAX_ITEMS; i++){
            if (this.items[i] == item){
                return i;
            }
        }
        return -1;
    }

    //Removes an item from the inventory
    public void removeItem(String item){
        int inInventory = hasItem(item);
        if (currItems > 0){
            if (inInventory == -1){
                System.out.println(item + " does not exist in your inventory!");
            }
            else{
                this.items[inInventory] = null;
                System.out.println("'" + item + "' removed from inventory");
                currItems--;
            }
        }
        else{
            System.out.println("Unable to remove " + item + ". Your Inventory is empty!");
        }
    }

    //Displays the inventory as a string to the player
    public String displayInventory(){
        String inventory = "";
        for (int i = 0; i < MAX_ITEMS; i++){
            if (this.items[i] == null){
                continue;
            }
            inventory += this.items[i] + " ";
        }
        return inventory;
    }
   
}