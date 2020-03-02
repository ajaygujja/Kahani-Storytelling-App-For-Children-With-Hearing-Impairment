package com.gujja.ajay.fourthver;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import pl.droidsonroids.gif.GifImageView;

public class HungryFox extends AppCompatActivity implements TextToSpeech.OnInitListener, Animatable {

    int i = 0;
    int j = 0;
    float speed = 0.8f;

    TextView textSent, textWord;
    GifImageView signImage;
    Button buttonspeak, buttonstop;

    String[] arthur = {"Once", "a", "fox", "was", "very", "hungry", " ", "It", "looked", "for", "food", "here", "and", "there", " ", "But", "it", "could","not", "get", "any", " ", "Atlast", "it", "found", "a", "loaf", "of", "bread", "and", "piece", "of", "meat", "in", "the", "hole", "of", "a", "tree", " ", "The", "hungry", "fox", "squeezed", "into", "the", "hole", " ", "It", "ate", "all", "the", "food", " ", "It", "was", "a", "woodcutter's", "lunch", " ", "He", "was", "on", "his", "way", "back", "to", "the", "tree", "to", "have", "lunch", " ", "But", "he", "saw", "there", "was", "no", "food", "in", "the", "hole", "instead", "a", "fox", " ", "On", "seeing", "the", "woodcutter", "the", "fox", "tried", "to", "get", "out", "of", "the", "hole", " ", "But", "it", "couldn't", "Its", "tummy", "was", "swollen", " ", "The", "woodcutter", "caught", "the", "fox", "and", "gave", "it", "nice", "beatings"};

    String[] joker = {"Once, a fox was very hungry.\n", "It looked for food here and there.\n", "But it could not get any.\n", "At last it found a loaf of bread and piece of meat in the hole of a tree.\n", "The hungry fox squeezed into the hole.\n", "It ate all the food.\n", "It was a woodcutter's lunch.\n", "He was on his way back to the tree to have lunch.\n", "But he saw there was no food in the hole, instead, a fox.\n", "On seeing the woodcutter, the fox tried to get out of the hole.\n", "But it couldn't.\nIts tummy was swollen.\n", "The woodcutter caught the fox and gave it nice beatings.\n"};

    private TextToSpeech tts;
    int index = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hungry_fox);

        //Initialization of Views
        textSent = findViewById(R.id.textview);
        textWord = findViewById(R.id.TextWord);
        buttonspeak = findViewById(R.id.Buttonspeak);
        buttonstop = findViewById(R.id.buttonstop);
        signImage = findViewById(R.id.SignImage);

        GifImageView gifImageView = new GifImageView(this);

        //Setting Default Text
        textSent.setText(joker[0]);
        textWord.setText(arthur[0]);
        //signImage.setImageResource(SignLang[1]);


        // Initialization of Text To Speech
        tts = new TextToSpeech(HungryFox.this, this);

        // Tracking of Words
        tts.setOnUtteranceProgressListener(mProgressListener);


        buttonspeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                speak(arthur,i);
            }
        });


        buttonstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

        tts.stop();
        tts.shutdown();
        textWord.setText(arthur[0]);
        textSent.setText(joker[0]);
        signImage.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    private void speak(String[] text, int i) {

        tts.setSpeechRate(speed);  // 0.4f
        tts.setPitch(1.0f);       // 0.8f
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Message ID");
        tts.speak(arthur[i], TextToSpeech.QUEUE_ADD, map);

    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setLanguage(Locale.ENGLISH);
        }
    }

    private abstract class runnable implements Runnable {
    }


    //Initialization of Utterance Listener
    final UtteranceProgressListener mProgressListener = new UtteranceProgressListener() {

        @Override
        public void onStart(String utteranceId) {

            // For Highlighting Spoken Words
            String Replce = "<span style= 'background-color:green'>" + arthur[i] + "</span>";
            textWord.setText(Html.fromHtml(Replce));


            int ajay = getResources().getIdentifier(String.valueOf(arthur[i].toLowerCase()), "raw", getPackageName());

            signImage.setImageResource(ajay);
           /* if (i <= SignLang.length){
                signImage.setImageResource(SignLang[i]);
              *//*if(isRunning()){
                   new Thread(new runnable() {
                       @Override
                       public void run() {
                            SignLang.wait();
                       }
                   });
               }else {
                   onDone("Message ID");
               }*//*

            }

            else {
                signImage.setImageURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.coffee));
            }
            */

        }

        @Override
        public void onError(String utteranceId) {
        }

        @Override
        public void onDone(String utteranceId) {

            // For Incrementing Words
            i = i + 1;
            speak(arthur, i);

            // For Incrementing Sentences
            if (arthur[i].equals(" ")) {
                j++;
                textSent.setText(joker[j]);
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.speedmeter,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){
            case R.id.Slow :
                speed = 0.4f;
                Toast.makeText(this,"Slow is selected",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.Normal :
                speed = 0.8f;
                Toast.makeText(this,"Normal is selected",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.Fast :
                speed = 1.2f;
                Toast.makeText(this,"Fast is selected",Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
