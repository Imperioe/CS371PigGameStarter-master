package edu.up.cs301.pig;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.actionMsg.PigHoldAction;
import edu.up.cs301.game.actionMsg.PigRollAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.PigGameState;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigComputerPlayer extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        PigGameState pgs = (PigGameState) info;
        if(playerNum == pgs.getTurnID()){
            Random rand = new Random();
            int i = rand.nextInt(2);
            if(i > 0){
                PigHoldAction pha = new PigHoldAction(this);
                game.sendAction(pha);
            }else{
                PigRollAction pra = new PigRollAction(this);
                game.sendAction(pra);
            }
        }else{
            return;
        }
    }//receiveInfo

}
