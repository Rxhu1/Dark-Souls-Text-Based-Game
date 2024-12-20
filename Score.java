package org.uob.a1;

public class Score {
    private int startingScore;
    private int currentScore = 0;
    private int roomsVisited = 0;
    private int puzzlesSolved = 0;
    private final int PUZZLE_VALUE = 10;

    //Constructor
    public Score(int startingScore){
        this.startingScore = startingScore;
    }

    //Increments the rooms visited attribute
    public void visitRoom(){
        this.roomsVisited++;
    }

    //Increments the puzzles solved attribute
    public void solvePuzzle(){
        this.puzzlesSolved++;
    }

    //getters
    public double getScore(){ //solves the current score by minusing the rooms visited adding the puzzle score for each puzzle solved
        this.currentScore = this.startingScore - this.roomsVisited + (this.puzzlesSolved*this.PUZZLE_VALUE);
        return this.currentScore;
    }

    public int getPuzzle(){
        return this.puzzlesSolved;
    }

    //Used to add a certain amount of score to the starting score
    public void addScore(int val){
        this.startingScore += val;
    }
        
}