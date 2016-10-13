package edu.up.cs301.pig;

import java.util.Random;

import edu.up.cs301.game.actionMsg.PigHoldAction;
import edu.up.cs301.game.actionMsg.PigRollAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.PigGameState;

/**
 * Created by SBEBS on 10/12/2016.
 */

public class PigSmartComputerPlayer extends PigComputerPlayer {
    public PigSmartComputerPlayer(String name){
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
            if (pgs.getRunTotal() < 1) {
                game.sendAction(new PigRollAction(this));
                return;
            } else if (pgs.getRunTotal() < 6) {
                game.sendAction(new PigRollAction(this));
                return;
            }else if(playerNum == 0){
                if(pgs.getP0Score()+pgs.getRunTotal()-pgs.getP1score() < -10){
                    game.sendAction(new PigRollAction(this));
                    return;
                }else if(pgs.getP0Score()+pgs.getRunTotal()-50 > -10 && pgs.getP0Score()+pgs.getRunTotal()-50 < 0){
                    game.sendAction(new PigRollAction(this));
                    return;
                }
            }else if(playerNum == 1){
                if(pgs.getP1score()+pgs.getRunTotal()-pgs.getP0Score() < -10){
                    game.sendAction(new PigRollAction(this));
                    return;
                }else if(pgs.getP1score()+pgs.getRunTotal()-50 > -10 && pgs.getP1score()+pgs.getRunTotal()-50 < 0){
                    game.sendAction(new PigRollAction(this));
                    return;
                }
            }

            game.sendAction(new PigHoldAction(this));

        }else{
            return;
        }
    }//receiveInfo
}
