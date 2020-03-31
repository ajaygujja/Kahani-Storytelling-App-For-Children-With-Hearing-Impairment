package com.gujja.ajay.fourthver.stories;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gujja.ajay.fourthver.R;

import java.util.HashMap;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

public class Black_Sheep extends AppCompatActivity implements TextToSpeech.OnInitListener, View.OnClickListener {

    @BindView(R.id.Black_Sheep_Sentence)
    TextView BlackSheepSentence;
    @BindView(R.id.Black_Sheep_Word)
    TextView BlackSheepWord;
    @BindView(R.id.Black_Sheep_Button_speak)
    ImageButton BlackSheepButtonSpeak;
    @BindView(R.id.Black_Sheep_button_stop)
    ImageButton BlackSheepButtonStop;
    @BindView(R.id.Black_Sheep_Sign_Gif)
    GifImageView BlackSheepSignGif;


    int i = 0;
    int j = 0;
    float speed = 0.7f;
    float pitch = 0.8f;

    String[] sheep_sentence = {
            "There lived a black sheep in a nearby village.",
            "Every spring, he shaved his black wool and sold it to the villagers.",
            "The villagers made sweaters and socks from his black wool.",
            "One day, the black sheep noticed that he had some more wool left.",
            "He thought, ‘It would be such a waste if nobody wants to buy the wool.’",
            "That afternoon, an old man came over to his wooden shed to see him.",
            "He wanted one bag full of the black sheep’s wool.",
            "Then an old woman came over.",
            "She also wanted a bag full of wool.",
            "A short while later, a little boy arrived.",
            "He also wanted one bag full of wool.",
            "Therefore, the black sheep prepared three bags full of wool for them.",
            "He was happy that all of his wool was sold off."
    };

    String[] sheep_words = {
            "There", "lived", "a", "black", "sheep", "in", "a", "nearby", "village"," ",
            "Every", "spring", "he", "shaved", "his", "black", "wool", "and", "sold", "it", "to", "the", "villagers"," ",
            "The", "villagers", "made", "sweaters", "and", "socks", "from", "his", "black", "wool"," ",
            "One", "day", "the", "black", "sheep", "noticed", "that", "he", "had", "some", "more", "wool", "left"," ",
            "He", "thought", "It", "would", "be", "such", "a", "waste", "if", "nobody", "wants", "to", "buy", "the", "wool", " ",
            "That", "afternoon", "an", "old", "man", "came", "over", "to", "his", "wooden", "shed", "to", "see", "him"," ",
            "He", "wanted", "one", "bag", "full", "of", "the", "black", "sheep's", "wool"," ",
            "Then", "an", "old", "woman", "came", "over"," ",
            "She", "also", "wanted", "a", "bag", "full", "of", "wool"," ",
            "A", "short", "while", "later", "a", "little", "boy", "arrived"," ",
            "He", "also", "wanted", "one", "bag", "full", "of", "wool"," ",
            "Therefore", "the", "black", "sheep", "prepared", "three", "bags", "full", "of", "wool", "for", "them"," ",
            "He", "was", "happy", "that", "all", "of", "his", "wool", "was", "sold", "off"};

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
    private TextToSpeech tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black__sheep);
        ButterKnife.bind(this);

        BlackSheepWord.setText(sheep_words[0]);
        BlackSheepSentence.setText(sheep_sentence[0]);

        // Initialization of Text To Speech
        tts = new TextToSpeech(this, this);

        // Tracking of Words
        tts.setOnUtteranceProgressListener(mProgressListener);

        /* @OnClick Listener */
        BlackSheepButtonSpeak.setOnClickListener(this);
        BlackSheepButtonStop.setOnClickListener(this);

    }

    private void stop() {
        tts.stop();
        tts.shutdown();
        BlackSheepWord.setText(sheep_words[0]);
        BlackSheepSentence.setText(sheep_sentence[0]);
        BlackSheepSignGif.setVisibility(View.INVISIBLE);
    }

    /*public Black_Sheep() {
        mProgressListener = new UtteranceProgressListener() {

    }*/

    private void speak(String[] text, int i) {

        tts.setSpeechRate(speed);  // 0.7f
        tts.setPitch(pitch);
        HashMap<String, String> map = new HashMap<>();


        for (String s : stop_words) {
            if (sheep_words[i].toLowerCase(Locale.getDefault()).equals(s)) {
                char[] alphabet_array = s.toCharArray();

                for (char c : alphabet_array) {
                    map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, String.valueOf(c).toLowerCase(Locale.getDefault()));
                    tts.setSpeechRate(0.5f);
                    tts.speak(String.valueOf(c), TextToSpeech.QUEUE_ADD, map);
                }
            }
        }

        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, sheep_words[i]);
        tts.speak(sheep_words[i].toLowerCase(Locale.ROOT), TextToSpeech.QUEUE_ADD, map);

    }


    UtteranceProgressListener mProgressListener = new UtteranceProgressListener() {

        @Override
        public void onStart(String utteranceId) {
            String Replace = "<span style= 'background-color:green'>" + utteranceId + "</span>";
            BlackSheepWord.setText(Html.fromHtml(Replace));

            if(utteranceId.toLowerCase(Locale.ROOT).equals("try") || utteranceId.toLowerCase(Locale.ROOT).equals("catch") || utteranceId.toLowerCase(Locale.ROOT).equals("while")){
                int gif_view = getResources().getIdentifier(utteranceId.toLowerCase(Locale.ROOT) +"1", "raw", getPackageName());
                BlackSheepSignGif.setImageResource(gif_view);
            } else {
                int gif_view = getResources().getIdentifier(utteranceId.toLowerCase(Locale.ROOT), "raw", getPackageName());
                BlackSheepSignGif.setImageResource(gif_view);
            }
        }


        @Override
        public void onDone(String utteranceId) {


            if(utteranceId.equals(" ")){
                new Thread(){
                    @Override
                    public void run() {
                        j++;
                        BlackSheepSentence.setText(sheep_sentence[j]);
                    }
                }.start();

            }

            i = i + 1;
            speak(sheep_words, i);
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
            case  R.id.Black_Sheep_Button_speak:
                BlackSheepButtonSpeak.setEnabled(false);
                speak(sheep_words,i);
                break;

            case  R.id.Black_Sheep_button_stop:
                BlackSheepButtonStop.setEnabled(true);
                stop();
                break;
        }
    }



}
