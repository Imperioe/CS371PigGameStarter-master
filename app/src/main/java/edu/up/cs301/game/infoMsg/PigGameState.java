package edu.up.cs301.game.infoMsg;

/**
 * Created by Eric Imperio on 10/12/2016.
 */

public class PigGameState extends GameState {
    private int turnID;
    private int p0Score,p1score;
    private int runTotal;
    private int dieValue;

    public PigGameState(){
        super();
        turnID = 0;
        p0Score = 0;
        p1score = 0;
        runTotal = 0;
        dieValue = 0;
    }

    public PigGameState(PigGameState pgs){
        super();
        this.dieValue = pgs.getDieValue();
        this.runTotal = pgs.getRunTotal();
        this.p1score = pgs.getP1score();
        this.p0Score = pgs.getP0Score();
        this.turnID = pgs.getTurnID();
    }

    public int getP0Score() {
        return p0Score;
    }

    public int getDieValue() {
        return dieValue;
    }

    public int getP1score() {
        return p1score;
    }

    public int getRunTotal() {
        return runTotal;
    }

    public int getTurnID() {
        return turnID;
    }

    public void setDieValue(int dieValue) {
        this.dieValue = dieValue;
    }

    public void setP0Score(int p0Score) {
        this.p0Score = p0Score;
    }

    public void setP1score(int p1score) {
        this.p1score = p1score;
    }

    public void setRunTotal(int runTotal) {
        this.runTotal = runTotal;
    }

    public void setTurnID(int turnID) {
        this.turnID = turnID;
    }
}
