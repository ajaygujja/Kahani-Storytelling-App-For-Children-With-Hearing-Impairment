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

public class Scared_Lil_Mouse extends AppCompatActivity implements TextToSpeech.OnInitListener, View.OnClickListener {

    @BindView(R.id.Lil_Mouse_textSent)
    TextView LilMouseTextSent;
    @BindView(R.id.Lil_Mouse_TextWord)
    TextView LilMouseTextWord;
    @BindView(R.id.Lil_Mouse_Button_speak)
    Button LilMouseButtonSpeak;
    @BindView(R.id.Lil_Mouse_button_stop)
    Button LilMouseButtonStop;
    @BindView(R.id.Lil_Mouse_Sign_Gif)
    GifImageView LilMouseSignGif;

    int i = 0;
    int j = 0;
    float speed = 0.7f;
    float pitch = 0.8f;
    private TextToSpeech tts;

    String[] mouseWord = {"One", "day", "there", "was", "a", "mouse", "that", "was", "very", "afraid"," ",
            "A", "big", "cat", "was", "chasing", "him"," ",
            "The", "mouse", "was", "running", "as", "fast", "as", "he", "could", "to", "save", "his", "life"," ",
            "The", "mouse", "saw", "a", "big", "grandfather", "clock"," ",
            "It", "climbed", "up", "the", "clock"," ",
            "It", "reached", "the", "top", "and", "sat", "down", "to", "rest"," ",
            "Not", "long", "after", "that", "the", "clock", "struck", "one", "Dong!", " ",
            "The", "mouse", "had", "such", "a", "shock", "that", "he", "ran", "down", "the", "clock"};

    String[] mouseSentence = {"One day, there was a mouse that was very afraid.",
            "A big cat was chasing him.",
            "The mouse was running as fast as he could to save his life.",
            "The mouse saw a big grandfather clock.",
            "It climbed up the clock.",
            "It reached the top and sat down to rest." ,
            "Not long after that, the clock struck one, ‘Dong!’ ",
            "The mouse had such a shock that he ran down the clock."};

    String[] stopwords = {
            "the", "all", "into", "loaf", "but", "for", "and", "at", "found", "of", "in", "squeezed", "hole", "to", "have", "caught", "gave",
            "came", "on", "become", "trick", "with", "carry", "cotton", "that", "felt", "every", "stream", "lesson", "let", "upon",
            "tremble", "fear", "left", "anpther", "other", "by", "hunter", "thus", "afterwards", "used", "cross", "tumbled", "also", "fell", "hence",
            "loaded", "would", "be", "still", "become", "dampened", "wet", "anymore", "an", "feeling", "den", "find", "only", "hesitation", "can", "fill",
            "as", "about", "instead", "went", "letting", "off"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scared__lil__mouse);
        ButterKnife.bind(this);

        LilMouseTextSent.setText(mouseSentence[0]);
        LilMouseTextWord.setText(mouseWord[0]);

        // Initialization of Text To Speech
        tts = new TextToSpeech(this, this);

        // Tracking of Words
        tts.setOnUtteranceProgressListener(mProgressListener);

        /* @OnClick Listener */
        LilMouseButtonSpeak.setOnClickListener(this);
        LilMouseButtonStop.setOnClickListener(this);

    }

    private void stop() {
        tts.stop();
        tts.shutdown();
        LilMouseTextWord.setText(mouseWord[0]);
        LilMouseTextSent.setText(mouseSentence[0]);
        LilMouseSignGif.setVisibility(View.INVISIBLE);
    }


    private void speak(String[] text, int i) {
        tts.setSpeechRate(speed);  // 0.7f
        tts.setPitch(pitch);
        HashMap<String, String> map = new HashMap<>();


        for (String stopword : stopwords) {
            if (mouseWord[i].toLowerCase().equals(stopword)) {
                char[] alphabet_array = stopword.toCharArray();

                for (char c : alphabet_array) {
                    map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, String.valueOf(c).toLowerCase());
                    tts.setSpeechRate(0.3f);
                    tts.speak(String.valueOf(c), TextToSpeech.QUEUE_ADD, map);
                }
            }
        }

        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, mouseWord[i]);
        tts.speak(mouseWord[i].toLowerCase(), TextToSpeech.QUEUE_ADD, map);


    }

    UtteranceProgressListener mProgressListener = new UtteranceProgressListener() {
        @Override
        public void onStart(String utteranceId) {
            // For Highlighting Spoken Words
            String Replace = "<span style= 'background-color:green'>" + utteranceId + "</span>";
            LilMouseTextWord.setText(Html.fromHtml(Replace));


            if(utteranceId.toLowerCase().equals("try") || utteranceId.toLowerCase().equals("catch")){
                int gif_view = getResources().getIdentifier(utteranceId.toLowerCase() +"1", "raw", getPackageName());
                LilMouseSignGif.setImageResource(gif_view);
            } else {
                int gif_view = getResources().getIdentifier(utteranceId.toLowerCase(), "raw", getPackageName());
                LilMouseSignGif.setImageResource(gif_view);
            }
        }

        @Override
        public void onDone(String utteranceId) {
            i = i + 1;
            speak(mouseWord, i);


            if(utteranceId.equals(" ")){
                j++;
                LilMouseTextSent.setText(mouseSentence[j]);

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
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setLanguage(Locale.ENGLISH);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.Lil_Mouse_Button_speak:
                speak(mouseWord,i);
                break;

            case  R.id.Lil_Mouse_button_stop:
                stop();
                break;
        }
    }
}
