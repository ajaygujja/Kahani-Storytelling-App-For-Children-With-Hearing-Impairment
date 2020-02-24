package com.gujja.ajay.fourthver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.HashMap;
import java.util.Locale;

public class GreedyLion extends AppCompatActivity implements TextToSpeech.OnInitListener {

    int i = 0;
    int j = 0;
    TextView textSent, textWord;
    Button buttonspeak, buttonstop;
    VideoView greedylionvideoview;
    private TextToSpeech tts;

    String[] Greedy = {"It was an incredibly hot day, and a lion was feeling very hungry.\n",
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
            "The lion now felt sorry for letting the hare off."};

    String[] lion = {"It", "was", "an", "incredibly", "hot", "day", "and", "a", "lion", "was", "feeling", "very", "hungry"," ","He", "came", "out", "of", "his", "den", "and", "searched", "here", "and", "there"," ","He", "could", "find", "only", "a", "small", "hare"," ", "He", "caught", "the", "hare", "with", "some", "hesitation", " ", "This", "hare", "can't", "fill", "my", "stomach", "thought", "the", "lion"," ","As", "the", "lion", "was", "about", "to", "kill", "the", "hare", " ","a", "deer", "ran", "that", "way", " ","The", "lion", "became", "greedy", "He", "thought", " ", "Instead", "of", "eating", "this", "small", "hare"," ", "let", "me", "eat", "the", "big", "deer", " ", "He", "let", "the", "hare", "go", "and", "went", "behind", "the", "deer", " ","But", "the", "deer", "had", "vanished", "into", "the", "forest", " ","The", "lion", "now", "felt", "sorry", "for", "letting", "the", "hare", "off"};


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
                                //int rawId = getResources().getIdentifier("once",  "raw",getPackageName());
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

        tts.setSpeechRate(1f);
        tts.setPitch(1f);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Message ID");
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
        public void onStart(String utteranceId) {
            new Thread(){
                public void run(){
                    GreedyLion.this.runOnUiThread(new runnable() {
                        @Override
                        public void run() {
                            // For Highlighting Spoken Words
                            String Replce = "<span style= 'background-color:green'>" + lion[i] + "</span>";
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

            // For Incrementing Sentences
            if (lion[i].equals(" ")) {
                j++;
                textSent.setText(Greedy[j]);
            }

        }

        @Override
        public void onError(String utteranceId) {

        }
    };
}
