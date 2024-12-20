package org.uob.a1;

import java.util.Scanner;

public class Game {
    
    public static void main(String[] args) {

        //Timer start
        final long startTime = System.nanoTime();

        //Core instances
        Map lordran = new Map(7,7);
        Inventory inventory = new Inventory();
        Score score = new Score(10);
        Scanner scanner = new Scanner(System.in);
        String LINE = "_____________________________"; //Need to implement look <feature>

        //collison areas
        lordran.setCollision();

        //10 unique rooms/areas
        Position castle1 = new Position(6, 6);
        Room castle = new Room("Anor Londo", "A large grand castle which seems to governed by a mysterious duo (Ornstein and Smough). Gwynevere, a princess offers you to join her covenant", 'A', castle1);
        Position woods1 = new Position(0, 6);
        Room woods = new Room("Darkroot Garden", "A dark woodland with an eerie echo and a howl of a wolf in the distance.", 'D', woods1);
        Position poisonSwamp1 = new Position(4, 0);
        Room poisonSwamp = new Room("Blighttown", "A large poison swamp infested with flying blood wasps and scorpions.", 'B', poisonSwamp1);
        Position spawn1 = new Position(3, 3);
        Room spawn = new Room("Firelink Shrine", "A comforting bonfire, surrounded by a large bird perched above a stone wall and a man in chainmail armour.", 'F', spawn1);
        Position ghost1 = new Position(3, 5);
        Room ghost = new Room("New Londo", "An underground cave overflowing with water and filled with ghosts. There is a lever beside you.", 'N', ghost1);
        Position skeleton1 = new Position(1, 3);
        Room skeleton = new Room("Catacombs", "A gravesite filled with undead skeletons and dark caves.", 'C', skeleton1);
        Position easyArea1 = new Position(5, 3);
        Room easyArea = new Room("Undead burg", "A simple connection of stone pathways with undead archers and zombies. There is a chest hidden behind the statue.", 'U', easyArea1);
        Position fortress1 = new Position(5, 5);
        Room fortress = new Room("Sen's Fortress", "A fortress filled with lots of swinging traps and rolling boulders.", 'S', fortress1);
        Position drake1 = new Position(1, 1);
        Room drake = new Room("Valley of Drakes", "A sparce yet frightening valley filled with lightning induced flying drakes.", 'V', drake1);
        Position lava1 = new Position(6, 0);
        Room lava = new Room("Demon Ruins", "A large, lava filled area, with something shining in the middle of a small pool of lava.", 'L', lava1);
        
        //Player
        Position player1 = new Position(3, 3);
        Room player = new Room("player", "The player", 'P', player1);

        //Places all rooms
        lordran.placeRoom(player.getPosition(), player.getSymbol());
        lordran.placeRoom(castle.getPosition(), castle.getSymbol());
        lordran.placeRoom(woods.getPosition(), woods.getSymbol());
        lordran.placeRoom(poisonSwamp.getPosition(), poisonSwamp.getSymbol());
        lordran.placeRoom(spawn.getPosition(), spawn.getSymbol());
        lordran.placeRoom(ghost.getPosition(), ghost.getSymbol());
        lordran.placeRoom(skeleton.getPosition(), skeleton.getSymbol());
        lordran.placeRoom(easyArea.getPosition(), easyArea.getSymbol());
        lordran.placeRoom(fortress.getPosition(), fortress.getSymbol());
        lordran.placeRoom(drake.getPosition(), drake.getSymbol());
        lordran.placeRoom(lava.getPosition(), lava.getSymbol());

        int warpCounter = 3;
        String playerRoom = "";
        boolean chestOpened = false;
        boolean bushSearched = false;
        boolean leverPulled = false;
        boolean discoverF = false;
        boolean discoverA = false;
        boolean discoverD = false;
        boolean discoverB = false;
        boolean discoverN = false;
        boolean discoverC = false;
        boolean discoverU = false;
        boolean discoverS = false;
        boolean discoverV = false;
        boolean discoverL = false;

        //Only performed once
        System.out.println(lordran.betterDisplay());
        //System.out.println(lordran.display());
        System.out.println("Welcome to Lordran, a world which feels... soulless");
        
        //Game loop
        while (true){

            if (score.getPuzzle() == 4){ //Ends game when all puzzles are completed
                //final long duration = System.nanoTime() - startTime;
                //long minutes = duration / 60000000000;
                long duration = System.nanoTime() - startTime;
                long seconds = duration / Long.valueOf(1000000000);
                long minutes = seconds / 60;
                long remainder = seconds % 60;
                System.out.println(LINE);
                System.out.println("You have completed all puzzles in the world of Lordran.");
                System.out.println("Your final score is " + score.getScore() + "/50");
                //System.out.println("Your final time is " + minutes);
                System.out.println("Your final time is " + minutes + " minutes " + remainder + " seconds.");
                System.out.println("The fastest pace set by the developer is 0 minutes 58 seconds");
                System.out.println("Thank you for playing.");
                break;
            }

            if (player1.x == spawn1.x && player1.y == spawn1.y){ //Opening message when area is found
                System.out.println(LINE);
                System.out.println("You are located at " + spawn.getName() + " (" + spawn.getSymbol() + ").");
                System.out.println("   1. move north\n   2. move south\n   3. move east\n   4. move west");
                discoverF = true;
                playerRoom = spawn.getDescription();
                //score.visitRoom();
            }
            else if (player1.x == castle1.x && player1.y == castle1.y){ //Opening message when area is found
                System.out.println(LINE);
                System.out.println("You are located at " + castle.getName() + " (" + castle.getSymbol() + ").");
                System.out.println("   1. move north\n   2. join covenant"); //Can join princess guard covenant to get lava ring.
                playerRoom = castle.getDescription();
                discoverC = true;
                //score.visitRoom();
            }
            else if (player1.x == easyArea1.x && player1.y == easyArea1.y){ //Opening message when area is found
                System.out.println(LINE);
                System.out.println("You are located at " + easyArea.getName() + " (" + easyArea.getSymbol() + ").");
                System.out.println("   1. move south\n   2. move west\n   3. search chest");
                playerRoom = easyArea.getDescription();
                discoverU = true;
                //score.visitRoom();
            }
            else if (player1.x == ghost1.x && player1.y == ghost1.y){ //Opening message when area is found
                System.out.println(LINE);
                System.out.println("You are located at " + ghost.getName() + " (" + ghost.getSymbol() + ").");
                System.out.println("   1. move north\n   2. move south\n   3. pull lever"); //opens new enterance. Need strength ring.
                playerRoom = ghost.getDescription();
                discoverN = true;
                //score.visitRoom();
            }
            else if (player1.x == poisonSwamp1.x && player1.y == poisonSwamp1.y){ //Opening message when area is found
                System.out.println(LINE);
                System.out.println("You are located at " + poisonSwamp.getName() + " (" + poisonSwamp.getSymbol() + ").");
                System.out.println("   1. move south\n   2. move east");
                playerRoom = poisonSwamp.getDescription();
                discoverB = true;
                //score.visitRoom();
            }
            else if (player1.x == drake1.x && player1.y == drake1.y){ //Opening message when area is found
                System.out.println(LINE);
                System.out.println("You are located at " + drake.getName() + " (" + drake.getSymbol() + ").");
                System.out.println("   1. move west\n   2. kill drake"); //drops strength ring needed for lever.
                playerRoom = drake.getDescription();
                discoverV = true;
                //score.visitRoom();
            }
            else if (player1.x == woods1.x && player1.y == woods1.y){ //Opening message when area is found
                if (!bushSearched){
                    System.out.println(LINE);
                    System.out.println("You can see a glint coming from the bush behind the tree.");
                }
                System.out.println(LINE);
                System.out.println("You are located at " + woods.getName() + " (" + woods.getSymbol() + ").");
                System.out.println("   1. move east   2. search bush");
                playerRoom = woods.getDescription();
                discoverD = true;
                //score.visitRoom();
            }
            else if (player1.x == fortress1.x && player1.y == fortress1.y){ //Opening message when area is found
                System.out.println(LINE);
                System.out.println("You are located at " + fortress.getName() + " (" + fortress.getSymbol() + ").");
                System.out.println("   1. move north\n   2. move east");
                playerRoom = fortress.getDescription();
                discoverS = true;
                //score.visitRoom();
            }
            else if (player1.x == skeleton1.x && player1.y == skeleton1.y){ //Opening message when area is found
                System.out.println(LINE);
                System.out.println("You are located at " + skeleton.getName() + " (" + skeleton.getSymbol() + ").");
                System.out.println("   1. move east\n   2. move west");
                playerRoom = skeleton.getDescription();
                discoverC = true;
                //score.visitRoom();
            }
            else if (player1.x == lava1.x && player1.y == lava1.y){ //Opening message when area is found
                System.out.println(LINE);
                System.out.println("You are located at " + lava.getName() + " (" + lava.getSymbol() + ").");
                System.out.println("   1. move west\n   2. move south\n   3. search lava"); //aquire key for door. Need lava ring.
                playerRoom = lava.getDescription();
                discoverL = true;
                //score.visitRoom();
            }
            else{ //Opening message when in an empty space
                System.out.println(LINE);
                lordran.emptyText(player1);
            }

            //initial input
            String input = scanner.nextLine();
            //System.out.println(input.substring(input.indexOf(' ')));
            if (input.equals("quit")){ //Ends the game
                long duration = System.nanoTime() - startTime;
                long seconds = duration / Long.valueOf(1000000000);
                long minutes = seconds / 60;
                long remainder = seconds % 60;
                System.out.println("You have played for " + minutes + " minute " + remainder + " seconds.");
                System.out.println("Thank you for playing.");
                break;
            }
            else if (input.equals("map")){ //displays the map in its current state
                System.out.println(lordran.betterDisplay());
                //System.out.println(lordran.display());
                //System.out.println("");
                //System.out.println(LINE);
            }
            else if (input.equals("look")){ //Displays information of the room the player is in
                if (!player1.playerOnEmpty()){
                    System.out.println(LINE);
                    System.out.println(playerRoom);
                }
                else {
                    System.out.println(LINE);
                    System.out.println("You are not in a room!");
                }
            }
            else if (input.equals("inventory")){ //Displays the inventory
                System.out.println(LINE);
                System.out.println(inventory.displayInventory());
            }
            else if (input.equals("score")){ //Displays the players current score
                System.out.println(LINE);
                System.out.println(score.getScore());
            }
            else if (input.equals("help")){ //Displays a list of all extra commands as well as some tips
                System.out.println(LINE);
                System.out.println("Here is a list of all the possible extra commands:\n   1. quit\n   2. map\n   3. inventory\n   4. score\n   5. look\n   6. look <item>\n   7. look <feature>\n   5. warp <symbol>");
                System.out.println("Some helpful tips:\n   1. Need some more strength? Go to Valley of Drakes (D).\n   2. Need a sharp tool? Have a look at the Undead Burg (U).\n   3. Cant walk on lava? Head to Anor Londo (A).\n   4. You have a maximum of three warps.");
            }
            else if (input.equals("pull lever") && player1.x == ghost1.x && player1.y == ghost1.y){// a puzzle involving using an item to pull a lever, opening up more of the map
                if (inventory.hasItem("strength ring") != -1){
                    System.out.println(LINE);
                    System.out.println("Using the strength ring, you have enough strength to pull the lever, causing the water levels start reducing, revealing an opening. You hear a wolf in the vast distance.");
                    lordran.openDoor(3,6);
                    score.solvePuzzle();
                    inventory.removeItem("strength ring");
                    leverPulled = true;
                }
                else if (!leverPulled){
                    System.out.println(LINE);
                    System.out.println("You take a tug at the lever. It seems stuck. You might need to increase your strength before trying again.");
                }
                else{
                    System.out.println(LINE);
                    System.out.println("Lever has already been pulled!");
                }
            }
            else if (input.equals("join covenant") && player1.x == castle1.x && player1.y == castle1.y){// part of a puzzle to gain an item needed to complete a puzzle
                if (inventory.hasItem("lava ring") == -1){
                    inventory.addItem("lava ring");
                    System.out.println(LINE);
                    System.out.println("You have successfully joined the princess guard covenant. You have been rewarded with a lava ring. I wonder what this could be used for?");
                }
                else{
                    System.out.println(LINE);
                    System.out.println("You have already joined this covenant!");
                }
            }
            else if (input.equals("search lava") && player1.x == lava1.x && player1.y == lava1.y){// part of a puzzle to get an item to finish a puzzle
                if (inventory.hasItem("lava ring") == -1){
                    System.out.println(LINE);
                    System.out.println("Ouch! the lava burns the armour on your toes. You must be missing something!");
                }
                else{
                    System.out.println(LINE);
                    System.out.println("By using the lava ring, you were able to walk on the lava and pick up the item. You found a large rusty key.");
                    inventory.removeItem("lava ring");
                    inventory.addItem("rusty key");
                    score.solvePuzzle();
                }
            }
            else if (input.equals("open door") && player1.x == 6 && player1.y == 2){ //final part of a puzzle
                if (inventory.hasItem("rusty key") == -1){
                    System.out.println(LINE);
                    System.out.println("The door wont budge. Seems like you are missing something...");
                }
                else{
                    System.out.println(LINE);
                    System.out.println("You used your rusty key. The large door slowly opened, revealing a shortcut to the Undead Burg!");
                    inventory.removeItem("rusty key");
                    lordran.openDoor(5, 2);
                    score.solvePuzzle();
                }
            }
            else if (input.equals("search chest") && player1.x == easyArea1.x && player1.y == easyArea1.y){// start of a puzzle, gives an important item
                if (!chestOpened){
                    System.out.println(LINE);
                    System.out.println("You opened the chest. Inside glistened a sharp yet small dagger");
                    inventory.addItem("dagger");
                    chestOpened = true;
                }
                else{
                    System.out.println(LINE);
                    System.out.println("Chest is has already been opened.");
                }
            }
            else if (input.equals("kill drake") && player1.x == drake1.x && player1.y == drake1.y){ // part of a puzzle, drops an item needed
                if (inventory.hasItem("dagger") == -1){
                    if (inventory.hasItem("strength ring") == -1){
                        System.out.println(LINE);
                        System.out.println("You threw a punch but the drake took no damage. It struck back, damaging you. Maybe you need something... sharper?");
                    }
                    else{
                        System.out.println(LINE);
                        System.out.println("Drake is already dead");
                    }
                }
                else{
                    System.out.println(LINE);
                    System.out.println("You took out your dagger and stabbed the drake a few times in quick succession before breaking. It dropped an item.");
                    inventory.removeItem("dagger");
                    inventory.addItem("strength ring");
                    score.solvePuzzle();
                }
            }
            else if (input.equals("search bush") && player1.x == woods1.x && player1.y == woods1.y){ // extra special feature to maximise points gained
                if (!bushSearched){
                    score.addScore(10);
                    System.out.println(LINE);
                    System.out.println("You found a secret hidden bonus 16 points hiding behind some sleeping giant knights. You sneak by and take them for yourself.");
                    System.out.println("+10 point gained!");
                    bushSearched = true;
                }
                else{
                    System.out.println(LINE);
                    System.out.println("There seems to be nothing within the bush.");
                }
            }

            switch (input){
                case "move north": //allows the user to move north when possible
                    if ((player1.x == 5 && player1.y == 3)) {
                        if (lordran.doorLocked(player1)){
                                System.out.println(LINE);
                                System.out.println("A huge door stands before you shut completely. In the center lies a small keyhole. It must lead somewhere...");
                                break;
                        }
                    }   
                    if (player1.y != 0){
                        if (lordran.isCollision(player1, -1, 'y')){
                            if (player1.playerOnEmpty()){
                                lordran.placeRoom(player.getPosition(), '.');     
                            }
                            else{
                                score.visitRoom();
                            }
                            player1.setyPos(player1.y - 1);
                            lordran.placeRoom(player.getPosition(), player.getSymbol());
                            break;
                        }
                    }
                    System.out.println(LINE);
                    System.out.println("You are blocked by a wall! Try again.");
                    break;
                case "move south": //allows the user to move south when possible
                    if (lordran.doorLocked(player1)){
                        if (player1.x == 3 && player1.y == 5){
                            System.out.println(LINE);
                            System.out.println("There is a large body of water preventing you from decending");
                            break;
                        }
                    }
                    if (player1.y != 6){
                        if (lordran.isCollision(player1, 1, 'y')){
                            if (player1.playerOnEmpty()){
                                lordran.placeRoom(player.getPosition(), '.');     
                            }
                            else{
                                score.visitRoom();
                            }
                            player1.setyPos(player1.y + 1);
                            lordran.placeRoom(player.getPosition(), player.getSymbol());
                            break;
                        }
                    }
                    System.out.println(LINE);
                    System.out.println("You are blocked by a wall! Try again.");
                    break;
                case "move east": //allows the user to move east when possible
                    if (player1.x != 6){
                        if (lordran.isCollision(player1, 1, 'x')){
                            if (player1.playerOnEmpty()){
                                lordran.placeRoom(player.getPosition(), '.');     
                            }
                            else{
                                score.visitRoom();
                            }
                            player1.setxPos(player1.x + 1);
                            lordran.placeRoom(player.getPosition(), player.getSymbol());
                            break;
                        }
                    }
                    System.out.println(LINE);
                    System.out.println("You are blocked by a wall! Try again.");
                    break;
                case "move west": //allows the user to move west when possible
                    if ((player1.x == 6 && player1.y == 2)){
                        if (lordran.doorLocked(player1)){
                            System.out.println(LINE);
                            System.out.println("A huge door stands before you shut completely. In the center lies a small keyhole. It must lead somewhere...");
                            break;
                        }
                    }
                    if (player1.x != 0){
                        if (lordran.isCollision(player1, -1, 'x') && player1.x != 0){
                            if (player1.playerOnEmpty()){
                                lordran.placeRoom(player.getPosition(), '.');   
                            }
                            else{
                                score.visitRoom();
                            }
                            player1.setxPos(player1.x - 1);
                            lordran.placeRoom(player.getPosition(), player.getSymbol());
                            break;
                        }
                    }
                    System.out.println(LINE);
                    System.out.println("You are blocked by a wall! Try again.");
                    break;
                case "look dagger": //Part of the look <item> selection
                    if (inventory.hasItem("dagger") != -1){
                        System.out.println(LINE);
                        System.out.println("A smaller knife, made to be used with efficiency without weight holding you back.");
                        break;
                    }
                    break;
                case "look strength ring": //Part of the look <item> selection
                    if (inventory.hasItem("strength ring") != -1){
                        System.out.println(LINE);
                        System.out.println("A ring that boosts the strength of your muscles whilst worn.");
                        break;
                    }
                    break;
                case "look lava ring": //Part of the look <item> selection
                    if (inventory.hasItem("lava ring") != -1){
                        System.out.println(LINE);
                        System.out.println("A ring which almost provides immunity to lava.");
                        break;
                    }
                    break;
                case "look rusty key": //Part of the look <item> selection
                    if (inventory.hasItem("rusty key") != -1){
                        System.out.println(LINE);
                        System.out.println("A large key which seems to have been rusting whilst left in the lava for multiple years. Must have been dropped by an adventurer.");
                        break;
                    }
                    break;
                case "look bonfire": //Part of the look <feature> selection
                    if (player1.x == spawn1.x && player1.y == spawn1.y){
                        System.out.println(LINE);
                        System.out.println("A warm yet bright fire surrounding a sword wedged into the ground. It seems very comforting.");
                        break;
                    }
                    break;
                case "look skeleton": //Part of the look <feature> selection
                    if (player1.x == skeleton1.x && player1.y == skeleton1.y){
                        System.out.println(LINE);
                        System.out.println("Bones scattered on the floor collect together by some mystical force, taking the appearance of a skeleton.");
                        break;
                    }
                    break;
                case "look drake": //Part of the look <feature> selection
                    if (player1.x == drake1.x && player1.y == drake1.y){
                        System.out.println(LINE);
                        System.out.println("A large bird with a sharp beak and black wings lays perched with lightning embued into its skin.");
                        break;
                    }
                    break;
                case "look poison": //Part of the look <feature> selection
                    if (player1.x == poisonSwamp1.x && player1.y == poisonSwamp1.y){
                        System.out.println(LINE);
                        System.out.println("A large swamp causing you to move very slowly whilst draining your health. It seems to be outflowing from the giant pipes.");
                        break;
                    }
                    break;
                case "look lava": //Part of the look <feature> selection
                    if (player1.x == lava1.x && player1.y == lava1.y){
                        System.out.println(LINE);
                        System.out.println("Multiple pools of lava held together by the 'Ceaseless Discharge'. Quite the horrifying spectacle.");
                        break;
                    }
                    break;
                case "look ghost": //Part of the look <feature> selection
                    if (player1.x == ghost1.x && player1.y == ghost1.y){
                        System.out.println(LINE);
                        System.out.println("A translucent figure of a female ghost approaches carrying sharp pincers, moving through the stone walls in its path.");
                        break;
                    }
                    break;
                case "look trees": //Part of the look <feature> selection
                    if (player1.x == woods1.x && player1.y == woods1.y){
                        System.out.println(LINE);
                        System.out.println("Multiple large trees stand tall above you, letting in the moonlight through its leaves.");
                        break;
                    }
                    break;
                case "look zombie": //Part of the look <feature> selection
                    if (player1.x == easyArea1.x && player1.y == easyArea1.y){
                        System.out.println(LINE);
                        System.out.println("A undead hollow, disfigured and covered in blood stands motionless atop the stone wall.");
                        break;
                    }
                    break;
                case "look boulder": //Part of the look <feature> selection
                    if (player1.x == fortress1.x && player1.y == fortress1.y){
                        System.out.println(LINE);
                        System.out.println("A large stone boulder constantly rolling around the fortress. One false step and you will be flattened!");
                        break;
                    }
                    break;
                case "look castle": //Part of the look <feature> selection
                    if (player1.x == castle1.x && player1.y == castle1.y){
                        System.out.println(LINE);
                        System.out.println("A large castle glistening in the illusion of the golden sun. Is this place as remarkable as it seems?");
                        break;
                    }
                    break;
                case "warp F": //Special feature allowing the player to fast travel only to places they have visited.
                    if (discoverF && warpCounter > 0){
                        lordran.warp('F', player1);
                        warpCounter--;
                        System.out.println(LINE);
                        System.out.println("Warp successful!");
                        System.out.println("You have " + warpCounter + " warps left.");
                    }
                    else if (warpCounter > 0){
                        System.out.println(LINE);
                        System.out.println("You have not discovered this location.");
                    }
                    else{
                        System.out.println(LINE);
                        System.out.println("You have run out of warps!");
                    }
                    break;
                case "warp A": //Special feature allowing the player to fast travel only to places they have visited.
                    if (discoverA && warpCounter > 0){
                        lordran.warp('A', player1);
                        warpCounter--;
                        System.out.println(LINE);
                        System.out.println("Warp successful!");
                        System.out.println("You have " + warpCounter + " warps left.");
                    }
                    else if (warpCounter > 0){
                        System.out.println(LINE);
                        System.out.println("You have not discovered this location.");
                    }
                    else{
                        System.out.println(LINE);
                        System.out.println("You have run out of warps!");
                    }
                    break;
                case "warp D": //Special feature allowing the player to fast travel only to places they have visited.
                    if (discoverD && warpCounter > 0){
                        lordran.warp('D', player1);
                        warpCounter--;
                        System.out.println(LINE);
                        System.out.println("Warp successful!");
                        System.out.println("You have " + warpCounter + " warps left.");
                    }
                    else if (warpCounter > 0){
                        System.out.println(LINE);
                        System.out.println("You have not discovered this location.");
                    }
                    else{
                        System.out.println(LINE);
                        System.out.println("You have run out of warps!");
                    }
                    break;
                case "warp B": //Special feature allowing the player to fast travel only to places they have visited.
                    if (discoverB && warpCounter > 0){
                        lordran.warp('B', player1);
                        warpCounter--;
                        System.out.println(LINE);
                        System.out.println("Warp successful!");
                        System.out.println("You have " + warpCounter + " warps left.");
                    }
                    else if (warpCounter > 0){
                        System.out.println(LINE);
                        System.out.println("You have not discovered this location.");
                    }
                    else{
                        System.out.println(LINE);
                        System.out.println("You have run out of warps!");
                    }
                    break;
                case "warp N": //Special feature allowing the player to fast travel only to places they have visited.
                    if (discoverN && warpCounter > 0){
                        lordran.warp('N', player1);
                        warpCounter--;
                        System.out.println(LINE);
                        System.out.println("Warp successful!");
                        System.out.println("You have " + warpCounter + " warps left.");
                    }
                    else if (warpCounter > 0){
                        System.out.println(LINE);
                        System.out.println("You have not discovered this location.");
                    }
                    else{
                        System.out.println(LINE);
                        System.out.println("You have run out of warps!");
                    }
                    break;
                case "warp C": //Special feature allowing the player to fast travel only to places they have visited.
                    if (discoverC && warpCounter > 0){
                        lordran.warp('C', player1);
                        warpCounter--;
                        System.out.println(LINE);
                        System.out.println("Warp successful!");
                        System.out.println("You have " + warpCounter + " warps left.");
                    }
                    else if (warpCounter > 0){
                        System.out.println(LINE);
                        System.out.println("You have not discovered this location.");
                    }
                    else{
                        System.out.println(LINE);
                        System.out.println("You have run out of warps!");
                    }
                    break;
                case "warp U": //Special feature allowing the player to fast travel only to places they have visited.
                    if (discoverU && warpCounter > 0){
                        lordran.warp('U', player1);
                        warpCounter--;
                        System.out.println(LINE);
                        System.out.println("Warp successful!");
                        System.out.println("You have " + warpCounter + " warps left.");
                    }
                    else if (warpCounter > 0){
                        System.out.println(LINE);
                        System.out.println("You have not discovered this location.");
                    }
                    else{
                        System.out.println(LINE);
                        System.out.println("You have run out of warps!");
                    }
                    break;
                case "warp S": //Special feature allowing the player to fast travel only to places they have visited.
                    if (discoverS && warpCounter > 0){
                        lordran.warp('S', player1);
                        warpCounter--;
                        System.out.println(LINE);
                        System.out.println("Warp successful!");
                        System.out.println("You have " + warpCounter + " warps left.");
                    }
                    else if (warpCounter > 0){
                        System.out.println(LINE);
                        System.out.println("You have not discovered this location.");
                    }
                    else{
                        System.out.println(LINE);
                        System.out.println("You have run out of warps!");
                    }
                    break;
                case "warp V": //Special feature allowing the player to fast travel only to places they have visited.
                    if (discoverV && warpCounter > 0){
                        lordran.warp('V', player1);
                        warpCounter--;
                        System.out.println(LINE);
                        System.out.println("Warp successful!");
                        System.out.println("You have " + warpCounter + " warps left.");
                    }
                    else if (warpCounter > 0){
                        System.out.println(LINE);
                        System.out.println("You have not discovered this location.");
                    }
                    else{
                        System.out.println(LINE);
                        System.out.println("You have run out of warps!");
                    }
                    break;
                case "warp L": //Special feature allowing the player to fast travel only to places they have visited.
                    if (discoverL && warpCounter > 0){
                        lordran.warp('L', player1);
                        warpCounter--;
                        System.out.println(LINE);
                        System.out.println("Warp successful!");
                        System.out.println("You have " + warpCounter + " warps left.");
                    }
                    else if (warpCounter > 0){
                        System.out.println(LINE);
                        System.out.println("You have not discovered this location.");
                    }
                    else{
                        System.out.println(LINE);
                        System.out.println("You have run out of warps!");
                    }
                    break;
            }   
        }

    }
}