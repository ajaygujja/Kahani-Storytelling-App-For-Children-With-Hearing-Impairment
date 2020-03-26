package com.gujja.ajay.fourthver.stories;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gujja.ajay.fourthver.R;

import java.util.HashMap;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

public class HungryFox extends AppCompatActivity implements TextToSpeech.OnInitListener, Animatable, View.OnClickListener {

    int i = 0;
    int j = 0;
    float speed = 0.7f;
    float pitch = 0.8f;
    private TextToSpeech tts;

    @BindView(R.id.textview)
    TextView textSent;
    @BindView(R.id.TextWord)
    TextView textWord;
    @BindView(R.id.Buttonspeak)
    Button buttonspeak;
    @BindView(R.id.buttonstop)
    Button buttonstop;
    @BindView(R.id.Button_Layout)
    LinearLayout ButtonLayout;
    @BindView(R.id.SignImage)
    GifImageView signImage;

    String[] arthur = {
            "Once", "a", "fox", "was", "very", "hungry", " ",
            "It", "looked", "for", "food", "here", "and", "there", " ",
            "But", "it", "could", "not", "get", "any", " ",
            "Atlast", "it", "found", "a", "loaf", "of", "bread", "and", "piece", "of", "meat", "in", "the", "hole", "of", "a", "tree", " ",
            "The", "hungry", "fox", "squeezed", "into", "the", "hole", " ",
            "It", "ate", "all", "the", "food", " ",
            "It", "was", "a", "woodcutter's", "lunch", " ",
            "He", "was", "on", "his", "way", "back", "to", "the", "tree", "to", "have", "lunch", " ",
            "But", "he", "saw", "there", "was", "no", "food", "in", "the", "hole", "instead", "a", "fox", " ",
            "On", "seeing", "the", "woodcutter", "the", "fox", "tried", "to", "get", "out", "of", "the", "hole", " ",
            "But", "it", "couldn't", "Its", "tummy", "was", "swollen", " ",
            "The", "woodcutter", "caught", "the", "fox", "and", "gave", "it", "nice", "beatings"
    };

    String[] joker = {
            "Once, a fox was very hungry.\n",
            "It looked for food here and there.\n",
            "But it could not get any.\n",
            "At last it found a loaf of bread and piece of meat in the hole of a tree.\n",
            "The hungry fox squeezed into the hole.\n",
            "It ate all the food.\n",
            "It was a woodcutter's lunch.\n",
            "He was on his way back to the tree to have lunch.\n",
            "But he saw there was no food in the hole, instead, a fox.\n",
            "On seeing the woodcutter, the fox tried to get out of the hole.\n",
            "But it couldn't.\nIts tummy was swollen.\n",
            "The woodcutter caught the fox and gave it nice beatings.\n"
    };

    String[] stopwords = {
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


    //Initialization of Utterance Listener
    final UtteranceProgressListener mProgressListener = new UtteranceProgressListener() {
        @Override
        public void onStart(String utteranceId) {

            // For Highlighting Spoken Words
            String Replace = "<span style= 'background-color:green'>" + utteranceId + "</span>";
            textWord.setText(Html.fromHtml(Replace));

            //Setting Gif according to words
            if (utteranceId.toLowerCase(Locale.getDefault()).equals("try") || utteranceId.toLowerCase(Locale.getDefault()).equals("catch") || utteranceId.toLowerCase(Locale.ROOT).equals("while")) {
                int ajay = getResources().getIdentifier(utteranceId.toLowerCase(Locale.getDefault()) + "1", "raw", getPackageName());
                signImage.setImageResource(ajay);
            } else {
                int ajay = getResources().getIdentifier(utteranceId.toLowerCase(Locale.getDefault()), "raw", getPackageName());
                signImage.setImageResource(ajay);
            }

            if(isRunning()){
                tts.playSilentUtterance(2000,TextToSpeech.QUEUE_ADD,utteranceId);
            }
        }

        @Override
        public void onRangeStart(String utteranceId, int start, int end, int frame) {
            super.onRangeStart(utteranceId, start, end, frame);
        }

        @Override
        public void onError(String utteranceId) {
        }

        @Override
        public void onDone(String utteranceId) {


            // For Incrementing Sentences
            if (utteranceId.equals(" ")) {
               new Thread(){
                   @Override
                   public void run() {
                       j++;
                       textSent.setText(joker[j]);
                   }
               }.start();
            }

            // For Incrementing Words
            i = i + 1;
            speak(arthur, i);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hungry_fox);
        ButterKnife.bind(this);

        //Setting Default Text
        textSent.setText(joker[0]);
        textWord.setText(arthur[0]);

        // Initialization of Text To Speech
        tts = new TextToSpeech(HungryFox.this, this);

        // Tracking of Words
        tts.setOnUtteranceProgressListener(mProgressListener);

        buttonspeak.setOnClickListener(this);
        buttonstop.setOnClickListener(this);

    }

    public void speak(String[] text, int i) {

        tts.setSpeechRate(speed);  // 0.7f
        tts.setPitch(pitch);
        HashMap<String, String> map = new HashMap<>();

        for (String stopword : stopwords) {
            if (arthur[i].toLowerCase(Locale.getDefault()).equals(stopword)) {
                char[] alphabet_array = stopword.toCharArray();

                for (char c : alphabet_array) {
                    map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, String.valueOf(c).toLowerCase(Locale.getDefault()));
                    tts.setSpeechRate(0.5f);
                    tts.speak(String.valueOf(c), TextToSpeech.QUEUE_ADD, map);
                }
            }
        }

        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, arthur[i]);
        tts.speak(arthur[i].toLowerCase(Locale.getDefault()), TextToSpeech.QUEUE_ADD, map);
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
    public void start() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setLanguage(Locale.ENGLISH);
        }
    }

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
                speed = 0.3f;
                Toast.makeText(this, "Slow is selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.Normal:
                speed = 0.7f;
                Toast.makeText(this, "Normal is selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.Fast:
                speed = 1f;
                Toast.makeText(this, "Fast is selected", Toast.LENGTH_SHORT).show();
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
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.Buttonspeak:
                speak(arthur,i);
                break;

            case  R.id.buttonstop:
                stop();
                break;
        }
    }

    private abstract static class runnable implements Runnable {
    }
}
