package com.gujja.ajay.fourthver.stories;

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

import com.gujja.ajay.fourthver.R;

import java.util.HashMap;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

public class GreedyLion extends AppCompatActivity implements TextToSpeech.OnInitListener, View.OnClickListener {

    int i = 0;
    int j = 0;
    float speed = 0.7f;
    float pitch = 0.8f;
    private TextToSpeech tts;

    String[] Greedy = {
            "It was an incredibly hot day, and a lion was feeling very hungry.\n",
            "He came out of his den and searched here and there.",
            "He could find only a small hare",
            "He caught the hare with some hesitation",
            "This hare can't fill my stomach thought the lion.\n ",
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
            "It", "was", "an", "incredibly", "hot", "day", "and", "a", "lion", "was", "feeling", "very", "hungry", " ",
            "He", "came", "out", "of", "his", "den", "and", "searched", "here", "and", "there", " ",
            "He", "could", "find", "only", "a", "small", "hare", " ",
            "He", "caught", "the", "hare", "with", "some", "hesitation", " ",
            "This", "hare", "can't", "fill", "my", "stomach", "thought", "the", "lion", " ",
            "As", "the", "lion", "was", "about", "to", "kill", "the", "hare", " ",
            "a", "deer", "ran", "that", "way", " ",
            "The", "lion", "became", "greedy", "He", "thought", " ",
            "Instead", "of", "eating", "this", "small", "hare", " ",
            "let", "me", "eat", "the", "big", "deer", " ",
            "He", "let", "the", "hare", "go", "and", "went", "behind", "the", "deer", " ",
            "But", "the", "deer", "had", "vanished", "into", "the", "forest", " ",
            "The", "lion", "now", "felt", "sorry", "for", "letting", "the", "hare", "off"
    };


    String[] stopwords = {
            "the", "all", "into", "but", "for", "and", "at", "found", "of", "in", "squeezed", "hole", "to", "have", "caught", "gave",
            "it", "came", "on", "become", "trick", "with", "carry", "cotton", "that", "felt", "every", "stream", "lesson", "let", "upon",
            "tremble", "fear", "left", "anpther", "other", "by", "hunter", "thus", "afterwards", "used", "cross", "tumbled", "also", "fell", "hence",
            "loaded", "would", "be", "still", "become", "dampened", "wet", "anymore", "an", "feeling", "den", "find", "only", "hesitation", "can", "fill",
            "as", "about", "instead", "went", "letting", "off", "incredibly", "hot"
    };


    @BindView(R.id.GreedySent)
    TextView GreedySentence;
    @BindView(R.id.GreedyWord)
    TextView GreedyWord;
    @BindView(R.id.Greedy_Button_speak)
    Button GreedyButtonSpeak;
    @BindView(R.id.Greedy_button_stop)
    Button GreedyButtonStop;
    @BindView(R.id.Greedy_Lion_SignImage)
    GifImageView GreedyLionSignGif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greedy_lion);
        ButterKnife.bind(this);

        GreedySentence.setText(Greedy[0]);
        GreedyWord.setText(lion[0]);

        // Initialization of Text To Speech
        tts = new TextToSpeech(GreedyLion.this, this);

        // Tracking of Words
        tts.setOnUtteranceProgressListener(mProgressListener);

      /*  GreedyButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        GreedyLion.this.runOnUiThread(new runnable() {
                            @Override
                            public void run() {
                                speak(lion, i);

                            }
                        });
                    }
                }.start();
            }
        });


        GreedyButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });*/
      GreedyButtonSpeak.setOnClickListener(this);
      GreedyButtonStop.setOnClickListener(this);

    }

    private void stop() {
        new Thread() {
            public void run() {
                GreedyLion.this.runOnUiThread(() -> Toast.makeText(getBaseContext(), "TTS Completed", Toast.LENGTH_SHORT).show());
            }
        }.start();
        tts.stop();
        tts.shutdown();
        GreedySentence.setText(Greedy[0]);
        GreedyWord.setText(lion[0]);
        GreedyLionSignGif.setVisibility(View.INVISIBLE);
    }

    private void speak(String[] text, int i) {

        tts.setSpeechRate(speed);

        tts.setPitch(pitch);
        HashMap<String, String> map = new HashMap<>();


        for (String stopword : stopwords) {
            if (lion[i].toLowerCase().equals(stopword)) {
                char[] alphabet_array = stopword.toCharArray();

                for (char c : alphabet_array) {

                    map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, String.valueOf(c));
                    tts.setSpeechRate(0.5f);
                    tts.speak(String.valueOf(c), TextToSpeech.QUEUE_ADD, map);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.Greedy_Button_speak:
                speak(lion,i);
                break;

            case  R.id.Greedy_button_stop:
                stop();
                break;
        }
    }

    private abstract class runnable implements Runnable {
    }

    //Initialization of Utterance Listener
    final UtteranceProgressListener mProgressListener = new UtteranceProgressListener() {
        @Override
        public void onStart(final String utteranceId) {
            new Thread() {
                public void run() {
                    GreedyLion.this.runOnUiThread(new runnable() {
                        @Override
                        public void run() {

                            // For Highlighting Spoken Words
                            String Replce = "<span style= 'background-color:green'>" + utteranceId + "</span>";
                            GreedyWord.setText(Html.fromHtml(Replce));


                            if (utteranceId.toLowerCase().equals("try") || utteranceId.toLowerCase().equals("catch")) {
                                int gif_view = getResources().getIdentifier(utteranceId.toLowerCase() + "1", "raw", getPackageName());
                                GreedyLionSignGif.setImageResource(gif_view);
                            } else {
                                int gif_view = getResources().getIdentifier(utteranceId.toLowerCase(), "raw", getPackageName());
                                GreedyLionSignGif.setImageResource(gif_view);
                            }

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


            if (utteranceId.equals(" ")) {
                j++;
                GreedySentence.setText(Greedy[j]);

            }

        }

        @Override
        public void onError(String utteranceId) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.speedmeter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.Slow:
                speed = 0.4f;
                Toast.makeText(this, "Slow is selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.Normal:
                speed = 0.8f;
                Toast.makeText(this, "Normal is selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.Fast:
                speed = 1.2f;
                Toast.makeText(this, "Fast is selected", Toast.LENGTH_SHORT).show();
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
