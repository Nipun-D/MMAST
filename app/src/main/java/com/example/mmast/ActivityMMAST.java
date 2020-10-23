package com.example.mmast;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Random;

public class ActivityMMAST extends AppCompatActivity {

    //Variables
    Button mageButton, marksmanButton, assassinButton, supportButton, tankButton;
    TextView scoreboard;
    ImageView playerChoice, computerChoice;
    int humanScore, computerScore = 0;
    private SoundPool soundPool;
    private int soundWin, soundLost, soundDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking variables to buttons, images and text on the app
        mageButton = findViewById(R.id.mageButton);
        marksmanButton = findViewById(R.id.marksmanButton);
        assassinButton = findViewById(R.id.assassinButton);
        supportButton = findViewById(R.id.supportButton);
        tankButton = findViewById(R.id.tankButton);

        playerChoice = findViewById(R.id.playerChoiceImage);
        computerChoice = findViewById(R.id.computerChoiceImage);

        scoreboard = findViewById(R.id.scoreboard);

        //Accessing sound effects
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(2)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        }

        //Linking sound variables with sound files
        soundWin = soundPool.load(this, R.raw.winsound, 1);
        soundLost = soundPool.load(this, R.raw.lostsound, 1);
        soundDraw = soundPool.load(this, R.raw.drawsound, 1);

        //Player presses a button. Updates the playerChoice image, score, activates computers random choice
        //and activates notification
        mageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerChoice.setImageResource(R.drawable.mage);
                String message = playTurn("mage");
                Toast.makeText(ActivityMMAST.this, message, Toast.LENGTH_SHORT).show();
                scoreboard.setText("Score: Player " + humanScore + " / Computer " + computerScore);
            }
        });

        marksmanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerChoice.setImageResource(R.drawable.marksman);
                String message = playTurn("marksman");
                Toast.makeText(ActivityMMAST.this, message, Toast.LENGTH_SHORT).show();
                scoreboard.setText("Score: Player " + humanScore + " / Computer " + computerScore);
            }
        });

        assassinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerChoice.setImageResource(R.drawable.assassin);
                String message = playTurn("assassin");
                Toast.makeText(ActivityMMAST.this, message, Toast.LENGTH_SHORT).show();
                scoreboard.setText("Score: Player " + humanScore + " / Computer " + computerScore);
            }
        });

        supportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerChoice.setImageResource(R.drawable.support);
                String message = playTurn("support");
                Toast.makeText(ActivityMMAST.this, message, Toast.LENGTH_SHORT).show();
                scoreboard.setText("Score: Player " + humanScore + " / Computer " + computerScore);
            }
        });

        tankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerChoice.setImageResource(R.drawable.tank);
                 String message = playTurn("tank");
                Toast.makeText(ActivityMMAST.this, message, Toast.LENGTH_SHORT).show();
                scoreboard.setText("Score: Player " + humanScore + " / Computer " + computerScore);

            }
        });

    }
    //Function for computer turn when player has pressed a button
    public String playTurn(String playerTurn){
        String computerTurn;
        Random random = new Random();

        //random number is chosen in order to select the computerChoice
        int computerTurnNumber = random.nextInt(5) + 1;

        if (computerTurnNumber == 1) {
            computerTurn = "mage";
            computerChoice.setImageResource(R.drawable.mage);
        } else
        if (computerTurnNumber == 2) {
                computerTurn = "marksman";
                computerChoice.setImageResource(R.drawable.marksman);
        } else
        if (computerTurnNumber == 3) {
                    computerTurn = "assassin";
                    computerChoice.setImageResource(R.drawable.assassin);
        } else
        if (computerTurnNumber == 4) {
                        computerTurn = "support";
                        computerChoice.setImageResource(R.drawable.support);
        } else {
                            computerTurn = "tank";
                            computerChoice.setImageResource(R.drawable.tank);
        }

        //Rules of the game + Activating the sound dependent on win/loss/draw
        // + notification message dependent on win/loss/draw + updating the score
        if (computerTurn.equals(playerTurn)) {
            soundPool.play(soundDraw, 1, 1, 0,0,1);
            return "Draw! Battle again!";
        } else
        if (playerTurn.equals("mage") && computerTurn.equals("marksman")){
            humanScore++;
            soundPool.play(soundWin, 1, 1, 0,0,1);
            return "Mage has blasted the Marksman. You win!";
        } else
        if (playerTurn.equals("marksman") && computerTurn.equals("mage")){
            computerScore++;
            soundPool.play(soundLost, 1, 1, 0,0,1);
            return "Marksman got blasted by Mage. You lost!";
        } else
        if (playerTurn.equals("mage") && computerTurn.equals("support")){
            humanScore++;
            soundPool.play(soundWin, 1, 1, 0,0,1);
            return "Mage has blasted the Support. You win!";
        } else
        if (playerTurn.equals("support") && computerTurn.equals("mage")){
            computerScore++;
            soundPool.play(soundLost, 1, 1, 0,0,1);
            return "Support got blasted by Mage. You lost!";
        } else
        if (playerTurn.equals("marksman") && computerTurn.equals("support")){
            humanScore++;
            soundPool.play(soundWin, 1, 1, 0,0,1);
            return "Marksman sniped the Support. You win!";
        } else
        if (playerTurn.equals("support") && computerTurn.equals("marksman")) {
            computerScore++;
            soundPool.play(soundLost, 1, 1, 0,0,1);
            return "Support got sniped by Marksman. You lost!";
        } else
        if (playerTurn.equals("marksman") && computerTurn.equals("tank")) {
            humanScore++;
            soundPool.play(soundWin, 1, 1, 0,0,1);
            return "Marksman sniped the Tank. You win!";
        } else
        if (playerTurn.equals("tank") && computerTurn.equals("marksman")){
            computerScore++;
            soundPool.play(soundLost, 1, 1, 0,0,1);
            return "Tank got sniped by Marksman. You lost!";
        } else
        if (playerTurn.equals("support") && computerTurn.equals("assassin")){
            humanScore++;
            soundPool.play(soundWin, 1, 1, 0,0,1);
            return "Support caught the Assassin. You win!";
        } else
        if (playerTurn.equals("assassin") && computerTurn.equals("support")){
            computerScore++;
            soundPool.play(soundLost, 1, 1, 0,0,1);
            return "Assassin got caught by Support. You lost!";
        } else
        if (playerTurn.equals("support") && computerTurn.equals("tank")){
            humanScore++;
            soundPool.play(soundWin, 1, 1, 0,0,1);
            return "Support caught the Tank. You win!";
        } else
        if (playerTurn.equals("tank") && computerTurn.equals("support")){
            computerScore++;
            soundPool.play(soundLost, 1, 1, 0,0,1);
            return "Tank got caught by Support. You lost!";
        } else
        if (playerTurn.equals("tank") && computerTurn.equals("mage")){
            humanScore++;
            soundPool.play(soundWin, 1, 1, 0,0,1);
            return "Tank crushed the Mage. You win!";
        } else
        if (playerTurn.equals("mage") && computerTurn.equals("tank")){
            computerScore++;
            soundPool.play(soundLost, 1, 1, 0,0,1);
            return "Mage got crushed by Tank. You lost!";
        } else
        if (playerTurn.equals("tank") && computerTurn.equals("assassin")){
            humanScore++;
            soundPool.play(soundWin, 1, 1, 0,0,1);
            return "Tank crushed the Assassin. You win!";
        } else
        if (playerTurn.equals("assassin") && computerTurn.equals("tank")){
            computerScore++;
            soundPool.play(soundLost, 1, 1, 0,0,1);
            return "Assassin got crushed by Tank. You lost!";
        } else
        if (playerTurn.equals("assassin") && computerTurn.equals("mage")){
            humanScore++;
            soundPool.play(soundWin, 1, 1, 0,0,1);
            return "Assassin shredded the Marksman. You win!";
        } else
        if (playerTurn.equals("mage") && computerTurn.equals("assassin")){
            computerScore++;
            soundPool.play(soundLost, 1, 1, 0,0,1);
            return "Mage got shredded by Assassin. You lost!";
        } else
        if (playerTurn.equals("assassin") && computerTurn.equals("marksman")){
            humanScore++;
            soundPool.play(soundWin, 1, 1, 0,0,1);
            return "Assassin shredded the Marksman. You win!";
        } else
        if (playerTurn.equals("marksman") && computerTurn.equals("assassin")){
            computerScore++;
            soundPool.play(soundLost, 1, 1, 0,0,1);
            return "Marksman got shredded by Assassin. You lost!";
        } else return null;
    }
}
