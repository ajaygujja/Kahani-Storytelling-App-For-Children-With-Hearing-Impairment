package com.gujja.ajay.fourthver;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import pl.droidsonroids.gif.GifImageView;

public class HungryFox extends AppCompatActivity implements TextToSpeech.OnInitListener, Animatable {

    int i = 0;
    int j = 0;
    TextView textSent, textWord;
    GifImageView signImage;
    Button buttonspeak, buttonstop;

    String[] arthur = {"Once", "a", "wolf", "was", "very", "hungry", " ", "It", "looked", "for", "food", "here", "and", "there", " ", "But", "it", "couldn't", "get", "any", " ", "At", "last", "it", "found", "a", "loaf", "of", "bread", "and", "piece", "of", "meat", "in", "the", "hole", "of", "a", "tree", " ", "The", "hungry", "wolf", "squeezed", "into", "the", "hole", " ", "It", "ate", "all", "the", "food", " ", "It", "was", "a", "woodcutter's", "lunch", " ", "He", "was", "on", "his", "way", "back", "to", "the", "tree", "to", "have", "lunch", " ", "But", "he", "saw", "there", "was", "no", "food", "in", "the", "hole", "instead", "a", "wolf", " ", "On", "seeing", "the", "woodcutter", "the", "wolf", "tried", "to", "get", "out", "of", "the", "hole", " ", "But", "it", "couldn't", "Its", "tummy", "was", "swollen", " ", "The", "woodcutter", "caught", "the", "wolf", "and", "gave", "it", "nice", "beatings"};

    String[] joker = {"Once, a wolf was very hungry.\n", "It looked for food here and there.\n", "But it couldn't get any.\n", "At last it found a loaf of bread and piece of meat in the hole of a tree.\n", "The hungry wolf squeezed into the hole.\n", "It ate all the food.\n", "It was a woodcutter's lunch.\n", "He was on his way back to the tree to have lunch.\n", "But he saw there was no food in the hole, instead, a wolf.\n", "On seeing the woodcutter, the wolf tried to get out of the hole.\n", "But it couldn't.\nIts tummy was swollen.\n", "The woodcutter caught the wolf and gave it nice beatings.\n"};

    private TextToSpeech tts;
    int index = 0;

    private int[] SignLang = {
            R.raw.once,
            R.raw.a,
            R.raw.wolf,
            R.raw.was,
            R.raw.very,
            R.raw.hungry,
            R.raw.coffee

    };

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
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    private void speak(String[] text, int i) {

        tts.setSpeechRate(0.7f);
        tts.setPitch(1f);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Message ID");
        tts.speak(arthur[i], TextToSpeech.QUEUE_ADD, map);

        /*new Thread() {
            public void run() {
                HungryFox.this.runOnUiThread(new runnable() {
                    @Override
                    public void run() {
                       // MediaController mediaController = new MediaController(HungryFo);
                       // mediaController.setAnchorView(hungeyvideoview);
                        hungeyvideoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cloud));
                        hungeyvideoview.requestFocus();
                        hungeyvideoview.setSoundEffectsEnabled(false);
                        hungeyvideoview.start();

                    }
                });
            }

        }.start();
*/

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

            // For Highlighting Spoken Words
            String Replce = "<span style= 'background-color:green'>" + arthur[i] + "</span>";
            textWord.setText(Html.fromHtml(Replce));

            if (i <= SignLang.length){
                signImage.setImageResource(SignLang[i]);
              /*if(isRunning()){
                   new Thread(new runnable() {
                       @Override
                       public void run() {
                            SignLang.wait();
                       }
                   });
               }else {
                   onDone("Message ID");
               }*/

            }

            else {
                signImage.setImageURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.coffee));
            }



            /*new Thread() {
                public void run() {
                    HungryFox.this.runOnUiThread(new runnable() {
                        public void run() {
                            // For Highlighting Spoken Words
                            String Replce = "<span style= 'background-color:green'>" + arthur[i] + "</span>";
                            textWord.setText(Html.fromHtml(Replce));


                           *//* hungeyvideoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.once));
                            hungeyvideoview.requestFocus();
                            hungeyvideoview.start();*//*

                        }
                    });
                }
            }.start();*/
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


}
