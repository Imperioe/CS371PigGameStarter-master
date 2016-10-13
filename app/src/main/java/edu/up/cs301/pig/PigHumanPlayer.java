package edu.up.cs301.pig;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.actionMsg.PigHoldAction;
import edu.up.cs301.game.actionMsg.PigRollAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.PigGameState;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * A GUI for a human to play Pig. This default version displays the GUI but is incomplete
 *
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigHumanPlayer extends GameHumanPlayer implements OnClickListener {

	/* instance variables */

    // These variables will reference widgets that will be modified during play
    private TextView    playerScoreTextView = null;
    private TextView    oppScoreTextView    = null;
    private TextView    turnTotalTextView   = null;
    private TextView    messageTextView     = null;
    private ImageButton dieImageButton      = null;
    private Button      holdButton          = null;

    // the android activity that we are running
    private GameMainActivity myActivity;

    /**
     * constructor does nothing extra
     */
    public PigHumanPlayer(String name) {
        super(name);
    }

    /**
     * Returns the GUI's top view object
     *
     * @return
     * 		the top object in the GUI's view heirarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info) {
        if(info instanceof PigGameState) {
            PigGameState pgs = (PigGameState) info;
            playerScoreTextView = (TextView) myActivity.findViewById(R.id.yourScoreValue);
            oppScoreTextView = (TextView) myActivity.findViewById(R.id.oppScoreValue);
            turnTotalTextView = (TextView) myActivity.findViewById(R.id.turnTotalValue);
            messageTextView = (TextView) myActivity.findViewById(R.id.messageTextView);
            dieImageButton = (ImageButton) myActivity.findViewById(R.id.dieButton);
            holdButton = (Button)  myActivity.findViewById(R.id.holdButton);
            if(playerNum == 0) {
                playerScoreTextView.setText(pgs.getP0Score()+"");
                oppScoreTextView.setText(pgs.getP1score()+"");
            }else{
                playerScoreTextView.setText(pgs.getP1score()+"");
                oppScoreTextView.setText(pgs.getP0Score()+"");
            }

            turnTotalTextView.setText(pgs.getRunTotal()+"");
            if(pgs.getDieValue() == 1){
                dieImageButton.setImageResource(R.drawable.face1);
            }else if(pgs.getDieValue() == 2){
                dieImageButton.setImageResource(R.drawable.face2);
            }else if(pgs.getDieValue() == 3){
                dieImageButton.setImageResource((R.drawable.face3));
            }else if(pgs.getDieValue() == 4){
                dieImageButton.setImageResource((R.drawable.face4));
            }else if(pgs.getDieValue() == 5){
                dieImageButton.setImageResource(R.drawable.face5);
            }else if(pgs.getDieValue() == 6) {
                dieImageButton.setImageResource(R.drawable.face6);
            }
        }else{
            flash(Color.RED, 500);
            return;
        }
    }//receiveInfo

    /**
     * this method gets called when the user clicks the die or hold button. It
     * creates a new PigRollAction or PigHoldAction and sends it to the game.
     *
     * @param button
     * 		the button that was clicked
     */
    public void onClick(View button) {
        if(button == holdButton){
            game.sendAction(new PigHoldAction(this));
        }else if( button == dieImageButton){
            game.sendAction(new PigRollAction(this));
        }
    }// onClick

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity
     * 		the activity under which we are running
     */
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.pig_layout);

        //Initialize the widget reference member variables
        this.playerScoreTextView = (TextView)activity.findViewById(R.id.yourScoreValue);
        this.oppScoreTextView    = (TextView)activity.findViewById(R.id.oppScoreValue);
        this.turnTotalTextView   = (TextView)activity.findViewById(R.id.turnTotalValue);
        this.messageTextView     = (TextView)activity.findViewById(R.id.messageTextView);
        this.dieImageButton      = (ImageButton)activity.findViewById(R.id.dieButton);
        this.holdButton          = (Button)activity.findViewById(R.id.holdButton);

        //Listen for button presses
        dieImageButton.setOnClickListener(this);
        holdButton.setOnClickListener(this);

    }//setAsGui

}// class PigHumanPlayer
