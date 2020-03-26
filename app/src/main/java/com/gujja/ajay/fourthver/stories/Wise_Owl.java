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

public class Wise_Owl extends AppCompatActivity implements TextToSpeech.OnInitListener, View.OnClickListener {

    @BindView(R.id.owl_textSent)
    TextView owl_text_Sent;
    @BindView(R.id.owl_TextWord)
    TextView owl_text_Word;
    @BindView(R.id.owl_Button_speak)
    Button owl_Speak;
    @BindView(R.id.owl_button_stop)
    Button owl_Stop;
    @BindView(R.id.owl_Sign_Gif)
    GifImageView owlSignGif;

    int i = 0;
    int j = 0;
    float speed = 0.7f;
    float pitch = 0.8f;
    private TextToSpeech tts;

    String[] words = {"There", "was", "an", "old", "owl", "that", "lived", "in", "an", "oak"," ",
            "Everyday", "he", "saw", "incidents", "happening", "around", "him"," ",
            "Yesterday", "he", "saw", "a", "boy", "helping", "an", "old", "man", "to", "carry", "a", "heavy", "basket"," ",
            "Today", "he", "saw", "a", "girl", "shouting", "at", "her", "mother"," ",
            "The", "more", "he", "saw", "the", "less", "he", "spoke"," ",
            "As", "he", "spoke", "less", "he", "heard", "more"," ",
            "He", "heard", "people", "talking", "and", "telling", "stories"," ",
            "He", "heard", "a", "woman", "saying", "that", "an", "elephant", "jumped", "over", "a", "fence"," ",
            "He", "also", "heard", "a", "man", "saying", "that", "he", "had", "never", "made", "a", "mistake"," ",
            "The", "old", "owl", "had", "seen", "and", "heard", "about", "what", "happened", "to", "people"," ",
            "Some", "became", "better", "and", "some", "became", "worse", "But", "the", "old", "owl", "had", "become", "wiser", "each", "and", "every", "day"};

    String[] sentence = {"There was an old owl that lived in an oak.",
            "Everyday he saw incidents happening around him.",
            "Yesterday he saw a boy helping an old man to carry a heavy basket.",
            "Today he saw a girl shouting at her mother.",
            "The more he saw the less he spoke.",
            "As he spoke less, he heard more.",
            "He heard people talking and telling stories.",
            "He heard a woman saying that an elephant jumped over a fence.",
            "He also heard a man saying that he had never made a mistake.",
            "The old owl had seen and heard about what happened to people.",
            "Some became better and some became worse. But the old owl had become wiser each and every day." ,
            };


    String[] stop_words = {
            "the", "all", "into", "loaf", "but", "for", "and", "at", "found", "of", "in", "squeezed", "hole", "to",
            "have", "caught", "gave", "came", "on", "become", "trick", "with", "carry", "cotton", "that", "felt",
            "every", "stream", "lesson", "let", "upon", "tremble", "fear", "left", "another", "other", "by", "hunter",
            "thus", "afterwards", "used", "cross", "tumbled", "also", "fell", "hence", "loaded", "would", "be", "still",
            "become", "dampened", "wet", "anymore", "an", "feeling", "den", "find", "only", "hesitation", "can", "fill",
            "as", "about", "instead", "went", "letting", "off","it","was","but","didn't","could","were","over","just",
            "even","that","became","him","chasing","struck","dong","such","fairy","tale","if","therefore","story","will",
            "every","spring","villagers","noticed","nobody","over","shed","later","them","moral","oak","fence",
            "worse","observant","this","bush","through","where","customer","generously","dues","order","glittering",
            "capsized","speechless","grief","what","cheating","dealings","supreme","anymore","seller","won't","favor","about","instead"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wise__owl);
        ButterKnife.bind(this);

        owl_text_Word.setText(words[0]);
        owl_text_Sent.setText(sentence[0]);

        // Initialization of Text To Speech
        tts = new TextToSpeech(this, this);

        // Tracking of Words
        tts.setOnUtteranceProgressListener(mProgressListener);

        /* @OnClick Listener */
        owl_Speak.setOnClickListener(this);
        owl_Stop.setOnClickListener(this);

    }

    private void stop() {
        tts.stop();
        tts.shutdown();
        owl_text_Word.setText(words[0]);
        owl_text_Sent.setText(sentence[0]);
        owlSignGif.setVisibility(View.INVISIBLE);
    }

    private void speak(String[] text, int i) {
        tts.setSpeechRate(speed);  // 0.7f
        tts.setPitch(pitch);
        HashMap<String, String> map = new HashMap<>();


        for (String s : stop_words) {
            if (words[i].toLowerCase(Locale.getDefault()).equals(s)) {
                char[] alphabet_array = s.toCharArray();

                for (char c : alphabet_array) {
                    map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, String.valueOf(c).toLowerCase(Locale.getDefault()));
                    tts.setSpeechRate(0.5f);
                    tts.speak(String.valueOf(c), TextToSpeech.QUEUE_ADD, map);
                }
            }
        }

        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, words[i]);
        tts.speak(words[i].toLowerCase(Locale.getDefault()), TextToSpeech.QUEUE_ADD, map);

    }

    UtteranceProgressListener mProgressListener = new UtteranceProgressListener() {
        @Override
        public void onStart(String utteranceId) {

            String Replace = "<span style= 'background-color:green'>" + utteranceId + "</span>";
            owl_text_Word.setText(Html.fromHtml(Replace));

            if(utteranceId.toLowerCase(Locale.getDefault()).equals("try") || utteranceId.toLowerCase(Locale.getDefault()).equals("catch") || utteranceId.toLowerCase(Locale.ROOT).equals("while")){
                int gif_view = getResources().getIdentifier(utteranceId.toLowerCase(Locale.getDefault()) +"1", "raw", getPackageName());
                owlSignGif.setImageResource(gif_view);
            } else {
                int gif_view = getResources().getIdentifier(utteranceId.toLowerCase(Locale.getDefault()), "raw", getPackageName());
                owlSignGif.setImageResource(gif_view);
            }
        }

        @Override
        public void onDone(String utteranceId) {


            if(utteranceId.equals(" ")){
                new Thread(){
                 @Override
                    public void run() {
                         j++;
                         owl_text_Sent.setText(sentence[j]);
                    }
                };

            }

            i = i + 1;
            speak(words, i);
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
        stop();
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
            case  R.id.owl_Button_speak:
                speak(words,i);
                break;

            case  R.id.owl_button_stop:
                stop();
                break;
        }
    }
}
