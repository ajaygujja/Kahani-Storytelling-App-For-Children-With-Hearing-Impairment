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


public class FourthStory extends AppCompatActivity implements TextToSpeech.OnInitListener, View.OnClickListener {

    int i = 0;
    int j = 0;
    float speed = 0.7f;
    float pitch = 0.8f;
    private TextToSpeech tts;

    @BindView(R.id.friend_textview)
    TextView friendSentence;
    @BindView(R.id.friend_TextWord)
    TextView friendWord;
    @BindView(R.id.friend_Button_speak)
    Button friendButtonSpeak;
    @BindView(R.id.friend_button_stop)
    Button friendButtonStop;
    @BindView(R.id.friend_SignImage)
    GifImageView friendSignGifs;

    String[] friend_word = {
            "Once", "upon", "a", "time", "there", "lived", "a", "lion", "in", "a", "forest", " ",
            "One", "day", "after", "a", "heavy", "meal", " ",
            "It", "was", "sleeping", "under", "a", "tree", " ",
            "After", "a", "while", "there", "came", "a", "mouse", "and", "it", "started", "to", "play", "on", "the", "lion", " ",
            "Suddenly", "the", "lion", "got", "up", "with", "anger", "and", "looked", "for", "those", "who", "disturbed", "its", "nice", "sleep", " ",
            "Then", "it", "saw", "a", "small", "mouse", "standing", "trembling", "with", "fear", " ",
            "The", "lion", "jumped", "on", "it", "and", "started", "to", "kill", "it", " ",
            "The", "mouse", "requested", "the", "lion", "to", "forgive", "it", " ",
            "The", "lion", "felt", "pity", "and", "left", "it", " ",
            "The", "mouse", "ran", "away", " ",
            "On", "another", "day", "the", "lion", "was", "caught", "in", "a", "net", "by", "a", "hunter", " ",
            "The", "mouse", "came", "there", "and", "cut", "the", "net", " ",
            "Thus", "it", "escaped", " ",
            "There", "after", "the", "mouse", "and", "the", "lion", "became", "friends", " ",
            "They", "lived", "happily", "in", "the", "forest", "afterwards"
    };
    String[] friend_sentence = {
            "Once upon a time there lived a lion in a forest.",
            "One day after a heavy meal.",
            "It was sleeping under a tree.",
            "After a while, there came a mouse and it started to play on the lion.",
            "Suddenly the lion got up with anger and looked for those who disturbed its nice sleep.",
            "Then it saw a small mouse standing trembling with fear.",
            "The lion jumped on it and started to kill it.",
            "The mouse requested the lion to forgive it.",
            "The lion felt pity and left it.",
            "The mouse ran away.",
            "On another day, the lion was caught in a net by a hunter.",
            "The mouse came there and cut the net.",
            "Thus it escaped.",
            "There after, the mouse and the lion became friends.",
            "They lived happily in the forest afterwards."};
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
    UtteranceProgressListener mProgressListener = new UtteranceProgressListener() {
        @Override
        public void onStart(final String utteranceId) {

            // For Highlighting Spoken Words
            String Replce = "<span style= 'background-color:green'>" + utteranceId + "</span>";
            friendWord.setText(Html.fromHtml(Replce));

            if (utteranceId.toLowerCase(Locale.getDefault()).equals("try") || utteranceId.toLowerCase(Locale.getDefault()).equals("catch") || utteranceId.toLowerCase(Locale.ROOT).equals("while")) {
                int gif_view = getResources().getIdentifier(utteranceId.toLowerCase(Locale.getDefault()) + "1", "raw", getPackageName());
                friendSignGifs.setImageResource(gif_view);
            } else {
                int gif_view = getResources().getIdentifier(utteranceId.toLowerCase(Locale.getDefault()), "raw", getPackageName());
                friendSignGifs.setImageResource(gif_view);
            }

        }

        @Override
        public void onDone(String utteranceId) {


            if (utteranceId.equals(" ")) {
                new Thread(){
                    @Override
                    public void run() {
                        j++;
                        friendSentence.setText(friend_sentence[j]);
                    }
                };

            }
            // For Incrementing Words
            i = i + 1;
            speak(friend_word, i);

        }

        @Override
        public void onError(String utteranceId) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_story);
        ButterKnife.bind(this);

        //Setting Default Text
        friendSentence.setText(friend_sentence[0]);
        friendWord.setText(friend_word[0]);

        // Initialization of Text To Speech
        tts = new TextToSpeech(FourthStory.this, this);

        // Tracking of Words
        tts.setOnUtteranceProgressListener(mProgressListener);

        friendButtonSpeak.setOnClickListener(this);
        friendButtonStop.setOnClickListener(this);
    }

    private void stop() {

        tts.stop();
        tts.shutdown();
        friendSentence.setText(friend_sentence[0]);
        friendWord.setText(friend_word[0]);
        friendSignGifs.setVisibility(View.INVISIBLE);
    }

    private void speak(String[] text, int i) {

        tts.setSpeechRate(speed);
        tts.setPitch(pitch);
        HashMap<String, String> map = new HashMap<>();


        for (String stopword : stopwords) {
            if (friend_word[i].toLowerCase(Locale.getDefault()).equals(stopword)) {
                char[] alphabet_array = stopword.toCharArray();

                for (char c : alphabet_array) {
                    map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, String.valueOf(c));
                    tts.setSpeechRate(0.5f);
                    tts.speak(String.valueOf(c), TextToSpeech.QUEUE_ADD, map);

                }

            }
        }


        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, friend_word[i]);
        tts.speak(friend_word[i], TextToSpeech.QUEUE_ADD, map);

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
            case  R.id.friend_Button_speak:
                speak(friend_word,i);
                break;

            case  R.id.friend_button_stop:
                stop();
                break;
        }
    }

    private abstract class runnable implements Runnable {
    }
}
