package com.gujja.ajay.fourthver;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class HungryFox extends AppCompatActivity implements TextToSpeech.OnInitListener {

    int i = 0;
    int j = 0;
    TextView textSent, textWord;
    Button buttonspeak, buttonstop;

    String[] arthur = {"Once", "a", "wolf", "was", "very", "hungry", " ", "It", "looked", "for", "food", "here", "and", "there", " ", "But", "it", "couldn't", "get", "any", " ", "At", "last", "it", "found", "a", "loaf", "of", "bread", "and", "piece", "of", "meat", "in", "the", "hole", "of", "a", "tree", " ", "The", "hungry", "wolf", "squeezed", "into", "the", "hole", " ", "It", "ate", "all", "the", "food", " ", "It", "was", "a", "woodcutter's", "lunch", " ", "He", "was", "on", "his", "way", "back", "to", "the", "tree", "to", "have", "lunch", " ", "But", "he", "saw", "there", "was", "no", "food", "in", "the", "hole", "instead", "a", "wolf", " ", "On", "seeing", "the", "woodcutter", "the", "wolf", "tried", "to", "get", "out", "of", "the", "hole", " ", "But", "it", "couldn't", "Its", "tummy", "was", "swollen", " ", "The", "woodcutter", "caught", "the", "wolf", "and", "gave", "it", "nice", "beatings"};

    String[] joker = {"Once, a wolf was very hungry.\n", "It looked for food here and there.\n", "But it couldn't get any.\n", "At last it found a loaf of bread and piece of meat in the hole of a tree.\n", "The hungry wolf squeezed into the hole.\n", "It ate all the food.\n", "It was a woodcutter's lunch.\n", "He was on his way back to the tree to have lunch.\n", "But he saw there was no food in the hole, instead, a wolf.\n", "On seeing the woodcutter, the wolf tried to get out of the hole.\n", "But it couldn't.\nIts tummy was swollen.\n", "The woodcutter caught the wolf and gave it nice beatings.\n"};

    private TextToSpeech tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_Hungry_Fox);

        //Initialization of Views
        textSent = findViewById(R.id.textview);
        textWord = findViewById(R.id.TextWord);
        buttonspeak = findViewById(R.id.Buttonspeak);
        buttonstop = findViewById(R.id.buttonstop);

        //Setting Default Text
        textSent.setText(joker[0]);
        textWord.setText(arthur[0]);

        // Initialization of Text To Speech
        tts = new TextToSpeech(HungryFox.this, this);

        // Tracking of Words
        tts.setOnUtteranceProgressListener(mProgressListener);

        buttonspeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        HungryFox.this.runOnUiThread(new runnable() {
                            @Override
                            public void run() {
                                speak(arthur, i);
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

    private void stop() {
        new Thread() {
            public void run() {
                HungryFox.this.runOnUiThread(new runnable() {
                    public void run() {

                        Toast.makeText(getBaseContext(), "TTS Completed", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }.start();
        tts.stop();
        tts.shutdown();
        textWord.setText(arthur[0]);
        textSent.setText(joker[0]);
    }

    private void speak(String[] text, int i) {

        tts.setSpeechRate(0.8f);
        tts.setPitch(1.3f);
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
    UtteranceProgressListener mProgressListener = new UtteranceProgressListener() {

        @Override
        public void onStart(String utteranceId) {
            new Thread() {
                public void run() {
                    HungryFox.this.runOnUiThread(new runnable() {
                        public void run() {
                            // For Highlighting Spoken Words
                            String Replce = "<span style= 'background-color:green'>" + arthur[i] + "</span>";
                            textWord.setText(Html.fromHtml(Replce));
                        }
                    });
                }
            }.start();
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
