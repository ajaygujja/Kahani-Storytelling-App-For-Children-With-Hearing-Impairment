package com.gujja.ajay.fourthver;

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
import android.widget.VideoView;

import java.util.HashMap;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class GreedyLion extends AppCompatActivity implements TextToSpeech.OnInitListener {

    int i = 0;
    int j = 0;
    TextView textSent, textWord;
    Button buttonspeak, buttonstop;
    VideoView greedylionvideoview;
    private TextToSpeech tts;

    int index = 0;
    float speed = 0.8f;

    String[] Greedy = {
            "It was an incredibly hot day, and a lion was feeling very hungry.\n",
            "He came out of his den and searched here and there.",
            "He could find only a small hare",
            "He caught the hare with some hesitation",
            "This hare can't fill my stomach thought the lion.\n " ,
            "As the lion was about to kill the hare,",
            "a deer ran that way.",
            "The lion became greedy. He thought;\n",
            "Instead of eating this small hare,",
            "let me eat the big deer.\n",
            "He let the hare go and went behind the deer.",
            "But the deer had vanished into the forest.",
            "The lion now felt sorry for letting the hare off."
    };


    String[] lion = {
            "It", "was", "an", "incredibly", "hot", "day", "and", "a", "lion", "was", "feeling", "very", "hungry"," ",
            "He", "came", "out", "of", "his", "den", "and", "searched", "here", "and", "there"," ",
            "He", "could", "find", "only", "a", "small", "hare"," ",
            "He", "caught", "the", "hare", "with", "some", "hesitation", " ",
            "This", "hare", "can't", "fill", "my", "stomach", "thought", "the", "lion"," ",
            "As", "the", "lion", "was", "about", "to", "kill", "the", "hare", " ",
            "a", "deer", "ran", "that", "way", " ",
            "The", "lion", "became", "greedy", "He", "thought", " ",
            "Instead", "of", "eating", "this", "small", "hare"," ",
            "let", "me", "eat", "the", "big", "deer", " ",
            "He", "let", "the", "hare", "go", "and", "went", "behind", "the", "deer", " ",
            "But", "the", "deer", "had", "vanished", "into", "the", "forest", " ",
            "The", "lion", "now", "felt", "sorry", "for", "letting", "the", "hare", "off"
    };


    String[] stopwords = {
            "the","all","into","but", "for", "and", "at", "found", "of", "in", "squeezed", "hole", "to", "have", "caught","gave",
            "it","came","on","become","trick","with","carry","cotton","that","felt","every","stream","lesson","let","upon",
            "tremble","fear","left","anpther","other","by","hunter","thus","afterwards","used","cross","tumbled","also","fell","hence",
            "loaded","would","be","still","become","dampened","wet","anymore" , "an","feeling","den","find","only","hesitation","can","fill",
            "as","about","instead","went","letting","off"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greedy_lion);

        //Initialization of Views
        textSent = findViewById(R.id.GreedySent);
        textWord = findViewById(R.id.GreedyWord);
        buttonspeak = findViewById(R.id.GreedyButtonspeak);
        buttonstop = findViewById(R.id.Greedybuttonstop);
        greedylionvideoview = findViewById(R.id.GreedyLionVideoView);

        textSent.setText(Greedy[0]);
        textWord.setText(lion[0]);

        // Initialization of Text To Speech
        tts = new TextToSpeech(GreedyLion.this, this);

        // Tracking of Words
        tts.setOnUtteranceProgressListener(mProgressListener);

        buttonspeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run(){
                        GreedyLion.this.runOnUiThread(new runnable(){
                            @Override
                            public void run() {
                                speak(lion,i);

                            }
                        });
                    }
                }.start();
            }
        });




        buttonstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
    }

    private void stop(){
        new Thread  (){
            public void run() {
                GreedyLion.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "TTS Completed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();
        tts.stop();
        tts.shutdown();
        textSent.setText(Greedy[0]);
        textWord.setText(lion[0]);
    }

    private void speak(String[] text, int i) {

        tts.setSpeechRate(speed);
        tts.setPitch(1f);
        HashMap<String, String> map = new HashMap<>();



        for (int k = 0; k < stopwords.length;k++) {
            if (lion[i] == stopwords[k]) {
                char[] alphabet_array = stopwords[k].toCharArray();

                for (int z = 0; z< alphabet_array.length; z++){

                    map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, String.valueOf(alphabet_array[z]));

                    tts.speak(String.valueOf(alphabet_array[z]), TextToSpeech.QUEUE_ADD, map);
                }

            }
        }


        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, lion[i]);
        tts.speak(lion[i], TextToSpeech.QUEUE_ADD, map);

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
    UtteranceProgressListener mProgressListener = new UtteranceProgressListener() {
        @Override
        public void onStart(final String utteranceId) {
            new Thread(){
                public void run(){
                    GreedyLion.this.runOnUiThread(new runnable() {
                        @Override
                        public void run() {

                            // For Highlighting Spoken Words
                            String Replce = "<span style= 'background-color:green'>" + utteranceId + "</span>";
                            textWord.setText(Html.fromHtml(Replce));
                        }
                    });
                }
            }.start();
        }

        @Override
        public void onDone(String utteranceId) {
            // For Incrementing Words
            i = i + 1;
            speak(lion, i);


            if(utteranceId.equals(" ")){
                j++;
                textSent.setText(Greedy[j]);

            }



        }

        @Override
        public void onError(String utteranceId) {

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }


}
