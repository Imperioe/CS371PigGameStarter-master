package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.actionMsg.PigHoldAction;
import edu.up.cs301.game.actionMsg.PigRollAction;
import edu.up.cs301.game.infoMsg.GameState;
import edu.up.cs301.game.infoMsg.PigGameState;

import android.util.Log;

import java.util.Random;

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    private PigGameState pgs;
    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        pgs = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if(pgs.getTurnID() == playerIdx){
            return true;
        }
        return false;
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if(action instanceof PigHoldAction){
            int id = pgs.getTurnID();
            if(id == 0){
                pgs.setP0Score(pgs.getRunTotal()+pgs.getP0Score());
            }else if(id == 1){
                pgs.setP1score(pgs.getRunTotal()+pgs.getP1score());
            }

            pgs.setRunTotal(0);
            if(players.length > 1){
                if(id == 0){
                    pgs.setTurnID(1);
                }else if(id == 1){
                    pgs.setTurnID(0);
                }
            }

            return true;
        }else if(action instanceof PigRollAction){
            Random rand = new Random();
            pgs.setDieValue(rand.nextInt(6)+1);

            return true;
        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //TODO  You will implement this method
        return null;
    }

}// class PigLocalGame
