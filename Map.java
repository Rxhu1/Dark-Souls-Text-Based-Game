package org.uob.a1;

public class Map {
    private int width;
    private int height;
    private char[][] map;
    private final char EMPTY = '.';
    private String mapLayout;
    //makes my game more readable

    //Constructor
    public Map(int width, int height){
        this.width = width;
        this.height = height;
        this.map = new char[this.height][this.width];
        this.mapLayout = "";

        for (int i = 0; i < this.height; i++){
            for (int j = 0; j < this.width; j++){
                map[j][i] = EMPTY;
            }
        }
    }

    // Used to place a room on the map, on top of the 'EMPTY' spaces
    public void placeRoom(Position pos, char symbol){
        int x = pos.x;
        int y = pos.y;
        if(symbol == 'P' && (map[x][y] != '.')){}
        else{
            map[x][y] = symbol;
        }
    }

    //Used at the start of program to place hashtags with collision to act as walls. Makes map more unique
    public void setCollision(){
        map[0][0] = '#';
        map[1][0] = '#';
        map[2][0] = '#';
        map[3][0] = '#';
        map[2][1] = '#';
        map[2][2] = '#';
        map[1][2] = '#';
        map[4][2] = '#';
        map[5][1] = '#';
        map[0][4] = '#';
        map[1][4] = '#';
        map[2][4] = '#';
        map[0][5] = '#';
        map[1][5] = '#';
        map[2][5] = '#';
        map[4][5] = '#';
        map[4][6] = '#';
        map[5][6] = '#';
        map[4][4] = '#';
        map[6][4] = '#';
        map[6][3] = '#';
        map[3][6] = '-';
        map[5][2] = '-';
    }

    // Checks if there is a hastag in the direction the player wants to move thus checking collision
    public boolean isCollision(Position pos, int val, char xORy){
        if (xORy == 'x'){
            return (map[pos.x + val][pos.y] != '#');
        }
        else {
            return (map[pos.x][pos.y + val] != '#');
        }
    }

    //Used when warping to set the player position to the location being warped to.
    public void warp(char symbol, Position pos){
        switch(symbol){
            case 'F':
                pos.setxPos(3);
                pos.setyPos(3);
                break;
            case 'A':
                pos.setxPos(6);
                pos.setyPos(6);
                break;
            case 'D':
                pos.setxPos(0);
                pos.setyPos(6);
                break;
            case 'B':
                pos.setxPos(4);
                pos.setyPos(0);
                break;
            case 'N':
                pos.setxPos(3);
                pos.setyPos(5);
                break;
            case 'C':
                pos.setxPos(1);
                pos.setyPos(3);
                break;
            case 'U':
                pos.setxPos(5);
                pos.setyPos(3);
                break;
            case 'S':
                pos.setxPos(5);
                pos.setyPos(5);
                break;
            case 'V':
                pos.setxPos(1);
                pos.setyPos(1);
                break;
            case 'L':
                pos.setxPos(6);
                pos.setyPos(0);
                break;
        }
    }

    // Used to replace the '-' symbol for a locked door with an 'EMPTY' space once the door is opened.
    public void openDoor(int x, int y){
        map[x][y] = '.';
    }

    // Used to check if the door next to the player is locked
    public boolean doorLocked(Position pos){
        if (pos.x == 3 && pos.y == 5){
            if (map[3][6] == '-'){
                return true;
            }
        }
        else if ((pos.x == 5 && pos.y == 3) || (pos.x == 6 && pos.y == 2)){
            if (map[5][2] == '-'){
                return true;
            }
        }
        return false;
    }

    //Displays text to each empty square to give more life to my world.
    public void emptyText(Position pos){
        switch (pos.x){
            case 0:
                switch (pos.y){
                    case 1:
                        System.out.println("A loud cry of a drake suffocates the eerie surroundings. On your right is a large valley. Beneath you paths back towards the Catacombs.");
                        System.out.println("   1. move south\n   2. move east");
                        break;
                    case 2:
                        System.out.println("You have ventured further into the remaining outskirts of the Catacombs. A mysterious pathway leads up ahead. Beneath remains the Catacombs.");
                        System.out.println("   1. move north\n   2. move south");
                        break;
                    case 3:
                        System.out.println("You have entered a dark and silent cave right beside the catacombs filled with the undead. Above follows the remaining outskirts of the Catacombs.");
                        System.out.println("   1. move north\n   2. move east");
                        break;
                }
                break;
            case 1:
                switch (pos.y){
                    case 6:
                        System.out.println("After venturing far in this secret greenland, a dark yet lush forest lies to your west. Or you can take a long journey to the entrance.");
                        System.out.println("   1. move east\n   2. move west");
                        break;
                }
                break;
            case 2:
                switch (pos.y){
                    case 3:
                        System.out.println("You find yourself along the a path covered with skulls and bones. I wonder where this will lead to?");
                        System.out.println("   1. move east\n   2. move west");
                        break;
                    case 6:
                        System.out.println("The outflow of water brings you a large lake which creates a path of rocks leading to a dark forest. There is always a chance to go back...");
                        System.out.println("   1. move east\n   2. move west");
                        break;
                }
                break;
            case 3:
                switch (pos.y){
                    case 1:
                        System.out.println("As your move deeper into this narrow wooden path over a large drop, you see a few signs. They state 'TURN BACK NOW'.");
                        System.out.println("   1. move south\n   2. move east");
                        break;
                    case 2:
                        System.out.println("You find yourself on the edge of a mountain followed by a row of narrow wooden planks held between the mountain ledges. Are you sure you want to take this path?");
                        System.out.println("   1. move north\n   2. move south");
                        break;
                    case 4:
                        System.out.println("You are on an lift moving very slowly. Above is the warmth of the FireLink Shrine. You hear cries of a ghost lingering below.");
                        System.out.println("   1. move north\n   2. move south");
                        break;
                    case 6:
                        System.out.println("Now that the water level has reduced this entrance seems very large, and for some reason is shaped like a wolf.");
                        System.out.println("   1. move north\n   2. move west");
                        break;
                }
                break;
            case 4:
                switch (pos.y){
                    case 1:
                        System.out.println("You are at the entrancem of a mineshaft, covered in blood and grit. There is a writing on the wall, seeming to be written by someone crazy. What could be so bad about this place?");
                        System.out.println("   1. move north\n   2. move west");
                        break;
                    case 3:
                        System.out.println("You find your self heading over a set of stone stairs. There are growns of pain heard in the distance. A large bell hovers above in the distance.");
                        System.out.println("   1. move east\n   2. move west");
                        break;
                }
                break;
            case 5:
                switch (pos.y){
                    case 0:
                        System.out.println("With only a small portion of light flowing in from a crack in the rocks, a large pool of lava is found east.");
                        System.out.println("   1. move east\n   2. move west");
                        break;
                    case 2:
                        System.out.println("The door has been unlocked, creating a shortcut between the Demon's Ruins and the Undead burg below.");
                        System.out.println("   1. move south\n   2. move east");
                        break;
                    case 4:
                        System.out.println("A mid sized fortress stands looming in front with a rolling bolder rolling it. Seems quite dangerous. Should you continue?");
                        System.out.println("   1. move north\n   2. move south");
                        break;
                }
                break;
            case 6:
                switch (pos.y){
                    case 1:
                        System.out.println("The heat from the Demon's Ruins lingers in the dried magma lying below. This pathway seems to lead somewhere...");
                        System.out.println("   1. move north\n   2. move south");
                        break;
                    case 2:
                        System.out.println("You find yourself far from the lava now with a large door beside you. It seems to go somewhere you recognise. Take the path?");
                        System.out.println("   1. move north\n   2. move west\n   3. open door");
                        break;
                    case 5:
                        System.out.println("From one fortress to another... three undead birds grasp your arms and fly you back and forth. A larger fortress looms above, with the sun shining a golden hue.");
                        System.out.println("   1. move south\n   2. move west");
                        break;
                }
                break;
        }
    }

    //Used to display my map
    public String display(){
        this.mapLayout = "";
        for (int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                //implement switch case after
                /*if (map[i][j] == '-'){this.mapLayout += map[i][j] + " ";continue;}
                if (map[i][j] == '#'){this.mapLayout += map[i][j] + " ";continue;}
                if (map[i][j] == 'A'){this.mapLayout += map[i][j] + " ";continue;}
                if (map[i][j] == 'D'){this.mapLayout += map[i][j] + " ";continue;}
                if (map[i][j] == 'B'){this.mapLayout += map[i][j] + " ";continue;}
                if (map[i][j] == 'F'){this.mapLayout += map[i][j] + " ";continue;}
                if (map[i][j] == 'N'){this.mapLayout += map[i][j] + " ";continue;}
                if (map[i][j] == 'C'){this.mapLayout += map[i][j] + " ";continue;}
                if (map[i][j] == 'U'){this.mapLayout += map[i][j] + " ";continue;}
                if (map[i][j] == 'S'){this.mapLayout += map[i][j] + " ";continue;}
                if (map[i][j] == 'V'){this.mapLayout += map[i][j] + " ";continue;}
                if (map[i][j] == 'L'){this.mapLayout += map[i][j] + " ";continue;}
                if (map[i][j] == 'P'){this.mapLayout += map[i][j] + " ";continue;}
                */

                /*
                //maybe try ord???
                switch (map[j][i]){
                    case '-': this.mapLayout += map[j][i];continue;
                    case '#': this.mapLayout += map[j][i];continue;
                    case 'A': this.mapLayout += map[j][i];continue;
                    case 'D': this.mapLayout += map[j][i];continue;
                    case 'B': this.mapLayout += map[j][i];continue;
                    case 'F': this.mapLayout += map[j][i];continue;
                    case 'N': this.mapLayout += map[j][i];continue;
                    case 'C': this.mapLayout += map[j][i];continue;
                    case 'U': this.mapLayout += map[j][i];continue;
                    case 'S': this.mapLayout += map[j][i];continue;
                    case 'V': this.mapLayout += map[j][i];continue;
                    case 'L': this.mapLayout += map[j][i];continue;
                    case 'P': this.mapLayout += map[j][i];continue;
                    case 'r': this.mapLayout += map[j][i];continue;
                    case 'c': this.mapLayout += map[j][i];continue;
                }
                map[j][i] = EMPTY;*/
                this.mapLayout += map[j][i];
            }
            if (i == this.height){break;}
            this.mapLayout += "\n";
        }
        return this.mapLayout;
    }

    /*IMPORTANT: THIS IS THE EXACT SAME CODE AS display(), THE ONLY DIFFERENCE BEING THAT I HAVE ADDED SPACES AFTER EACH DOT
    TO MAKE IT LOOK BETTER FOR A MORE FUN EXPERIENCE PLAYING MY GAME. THIS METHOD DOESNT PASS THE TESTS BUT MY MAIN ONE DOES.
    PLEASE KEEP THIS IN MIND*/
    public String betterDisplay(){
        this.mapLayout = "";
        for (int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                /*
                switch (map[j][i]){
                    case '-': this.mapLayout += map[j][i] + " ";continue;
                    case '#': this.mapLayout += map[j][i] + " ";continue;
                    case 'A': this.mapLayout += map[j][i] + " ";continue;
                    case 'D': this.mapLayout += map[j][i] + " ";continue;
                    case 'B': this.mapLayout += map[j][i] + " ";continue;
                    case 'F': this.mapLayout += map[j][i] + " ";continue;
                    case 'N': this.mapLayout += map[j][i] + " ";continue;
                    case 'C': this.mapLayout += map[j][i] + " ";continue;
                    case 'U': this.mapLayout += map[j][i] + " ";continue;
                    case 'S': this.mapLayout += map[j][i] + " ";continue;
                    case 'V': this.mapLayout += map[j][i] + " ";continue;
                    case 'L': this.mapLayout += map[j][i] + " ";continue;
                    case 'P': this.mapLayout += map[j][i] + " ";continue;
                    case 'r': this.mapLayout += map[j][i] + " ";continue;
                    case 'c': this.mapLayout += map[j][i] + " ";continue;
                }
                map[j][i] = EMPTY;
                */
                this.mapLayout += map[j][i] + " ";
            }
            if (i == this.height){break;}
            this.mapLayout += "\n";
        }
        return this.mapLayout;
    }

}