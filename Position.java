package org.uob.a1;

public class Position {
    //Public as they will constantly be used, so getters become redundant
    public int x;
    public int y;

    //Constructor
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    //Setters
    public void setxPos(int x){
        this.x = x;
    }

    public void setyPos(int y){
        this.y = y;
    }

    //Checks if the player is on an empty square
    public boolean playerOnEmpty(){
        if (y < 4 && y > 0 && x == 0){
            return true;
        }
        else if (x < 4 && x > 0 && y == 6){
            return true;
        }
        else if (x == 6 && y == 5){
            return true;
        }
        else if (y == 4 && x == 5){
            return true;
        }
        else if (y == 4 && x == 3){
            return true;
        }
        else if (y == 3 && x == 2){
            return true;
        }
        else if (y == 3 && x == 4){
            return true;
        }
        else if (y == 3 && x == 2){
            return true;
        }
        else if (y == 2 && x == 3){
            return true;
        }
        else if (y == 2 && x > 4 && x <= 6){
            return true;
        }
        else if (y == 1 && x > 2 && x < 5){
            return true;
        }
        else if (y == 1 && x == 6){
            return true;
        }
        else if (y == 0 && x == 5){
            return true;
        }
        return false;
    }

}