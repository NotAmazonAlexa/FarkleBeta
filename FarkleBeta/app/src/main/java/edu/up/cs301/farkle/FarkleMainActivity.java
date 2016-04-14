package edu.up.cs301.farkle;

import android.graphics.Color;
import android.media.Image;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;

import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.R;
import edu.up.cs301.game.config.GameConfig;
import edu.up.cs301.game.config.GamePlayerType;

/**
 * This contains the activity for the Farkle game.
 *
 * @author Alexa Baldwin
 * @author Levi Banks
 * @author Sara Perkins
 * @author Briahna Santillana
 * @version 15 March 2016
 */
public class FarkleMainActivity extends GameMainActivity {
    // for networked play
    private static final int PORT_NUMBER = 2234;
    protected static ImageView playerOneImage, playerTwoImage;
    //or I can do references of some kind in GameState
    
    /**
     * Create the default configuration for this game:
     * - one human player vs. one computer player
     * - minimum of 1 player, maximum of 2
     * - one kind of computer player and one kind of human player available
     *
     * @return the new configuration object, representing the default configuration
     */
    @Override
    public GameConfig createDefaultConfig() {
        
        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();
        
        // Farkle has two player types:  human and computer
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new FarkleHumanPlayer(name);
            }});
        playerTypes.add(new GamePlayerType("Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new FarkleDumbComputerPlayer(name);
            }});
        playerTypes.add(new GamePlayerType("Hard Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new FarkleSmartComputerPlayer(name);
            }});
        
        
        // Create a game configuration class for Farkle:
        GameConfig defaultConfig = new GameConfig(playerTypes, 1, 2, "Farkle", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer Smart", 2); // player 2: a computer player
        defaultConfig.setRemoteData("Remote Human Player", "", 0);
        
        
        return defaultConfig;
    }
    
    /**
     * create a local game
     *
     * @return the local farkle game
     */
    @Override
    public LocalGame createLocalGame() {
        return new FarkleLocalGame();
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        //image views
        playerOneImage = FarkleHumanPlayer.playerOneImage;
        playerTwoImage = FarkleHumanPlayer.playerTwoImage;
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        if (id == R.id.score_guide) {
            Toast score_guide = Toast.makeText(getApplicationContext(), "5’s = 50 points\n" +
                                               "1’s = 100 points\n" +
                                               "1,1,1 = 300 points\n" +
                                               "2,2,2 = 200 points\n" +
                                               "3,3,3 = 300 points\n" +
                                               "4,4,4 = 400 points\n" +
                                               "5,5,5 = 500 points\n" +
                                               "6,6,6 = 600 points\n" +
                                               "Four of a Kind = 1,000 points\n" +
                                               "Five of a Kind = 2,000 points\n" +
                                               "Six of a Kind = 3,000 points\n" +
                                               "A Straight of 1-6 = 1,500 points\n" +
                                               "Three Pairs = 1,500 points\n" +
                                               "Four of a Kind + a Pair = 1,500\n" +
                                               "Two sets of Three of a Kind = 2,500", Toast.LENGTH_LONG);
            score_guide.setGravity(Gravity.TOP|Gravity.LEFT, 800, 400);
            
            score_guide.show();
            
            return true;
        }
        
        //noinspection SimplifiableIfStatement
        else if (id == R.id.girl) {
            playerOneImage.setImageResource(R.drawable.avatar_girl);
            return true;
        }
        else if (id == R.id.boyBlackHair)
        {
            playerOneImage.setImageResource(R.drawable.avatar_boy1);
            return true;
        }
        else if (id == R.id.boyRedHair)
        {
            playerOneImage.setImageResource(R.drawable.avatar_boy2);
            return true;
        }
        else if (id == R.id.dog)
        {
            playerOneImage.setImageResource(R.drawable.avatar_puppy);
            return true;
        }
        else if (id == R.id.girl1) {
            playerTwoImage.setImageResource(R.drawable.avatar_girl);
            return true;
        }
        else if (id == R.id.boyBlackHair1)
        {
            playerTwoImage.setImageResource(R.drawable.avatar_boy1);
            return true;
        }
        else if (id == R.id.boyRedHair1)
        {
            playerTwoImage.setImageResource(R.drawable.avatar_boy2);
            return true;
        }
        else if (id == R.id.dog1)
        {
            playerTwoImage.setImageResource(R.drawable.avatar_puppy);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
