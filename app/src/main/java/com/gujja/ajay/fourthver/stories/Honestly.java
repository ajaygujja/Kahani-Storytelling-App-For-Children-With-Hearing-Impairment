/*      Copyright (c) 2020 ajay gujja

        Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
        associated documentation files (the "Software"), to deal in the Software without restriction,
        including without limitation the rights to use, copy, modify, merge, publish, distribute,
        sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
        subject to the following conditions:

        The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
        EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
        DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
        ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

*/

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

public class Honestly extends AppCompatActivity implements TextToSpeech.OnInitListener, View.OnClickListener  {

    @BindView(R.id.Honestly_textSent)
    TextView HonestlyTextSent;
    @BindView(R.id.Honestly_TextWord)
    TextView HonestlyTextWord;
    @BindView(R.id.Honestly_Button_speak)
    ImageButton HonestlyButtonSpeak;
    @BindView(R.id.Honestly_button_stop)
    ImageButton HonestlyButtonStop;
    @BindView(R.id.Honestly_Sign_Gif)
    GifImageView HonestlySignGif;

    int i = 0;
    int j = 0;
    float speed = 0.7f;
    float pitch = 0.8f;
    private TextToSpeech tts;

    String[] hon_words = {
            "A", "milkman", "became", "very", "wealthy", "through", "dishonest", "means"," ",
            "He", "had", "to", "cross", "a", "river", "daily", "to", "reach", "the", "city", "where", "his", "customers", "lived"," ",
            "He", "mixed", "the", "water", "of", "the", "river", "generously", "with", "the", "milk", "that", "he", "sold", "for", "a", "good", "profit"," ",
            "One", "day", "he", "went", "around", "collecting", "the", "dues", "in", "order", "to", "celebrate", "the", "wedding", "of", "his", "son"," ",
            "With", "the", "large", "amount", "thus", "collected", "he", "purchased", "plenty", "of", "rich", "clothes", "and", "glittering", "gold", "ornaments"," ",
            "But", "while", "crossing", "the", "river", "the", "boat", "capsized", "and", "all", "his", "costly", "purchases", "were", "swallowed", "by", "the", "river"," ",
            "The", "milk", "vendor", "was", "speechless", "with", "grief"," ",
            "At", "that", "time", "he", "heard", "a", "voice", "that", "came", "from", "the", "river", "Do", "not", "weep"," ",
            "What", "you", "have", "lost", "is", "only", "the", "illicit", "gains", "you", "earned", "through", "cheating", "your", "customers"
    };

    String[] hon_sentence={
            "A milkman became very wealthy through dishonest means.",
            "He had to cross a river daily to reach the city where his customers lived.",
            "He mixed the water of the river generously with the milk that he sold for a good profit.",
            "One day he went around collecting the dues in order to celebrate the wedding of his son.",
            "With the large amount thus collected he purchased plenty of rich clothes and glittering gold ornaments.",
            "But while crossing the river the boat capsized and all his costly purchases were swallowed by the river.",
            "The milk vendor was speechless with grief.",
            "At that time he heard a voice that came from the river, “Do not weep.",
            "What you have lost is only the illicit gains you earned through cheating your customers." +
                    ""};

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
        setContentView(R.layout.activity_honestly);
        ButterKnife.bind(this);

        HonestlyTextWord.setText(hon_words[0]);
        HonestlyTextSent.setText(hon_sentence[0]);

        // Initialization of Text To Speech
        tts = new TextToSpeech(this, this);

        // Tracking of Words
        tts.setOnUtteranceProgressListener(mProgressListener);

        /* @OnClick Listener */
        HonestlyButtonSpeak.setOnClickListener(this);
        HonestlyButtonStop.setOnClickListener(this);

    }

    private void stop() {
        tts.stop();
        tts.shutdown();
        HonestlyTextWord.setText(hon_words[0]);
        HonestlyTextSent.setText(hon_sentence[0]);
        HonestlySignGif.setVisibility(View.INVISIBLE);
    }

    private void speak(String[] text, int i) {
        tts.setSpeechRate(speed);  // 0.7f
        tts.setPitch(pitch);
        HashMap<String, String> map = new HashMap<>();


        for (String s : stop_words) {
            if (hon_words[i].toLowerCase(Locale.getDefault()).equals(s)) {
                char[] alphabet_array = s.toCharArray();

                for (char c : alphabet_array) {
                    map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, String.valueOf(c).toLowerCase(Locale.getDefault()));
                    tts.setSpeechRate(0.5f);
                    tts.speak(String.valueOf(c), TextToSpeech.QUEUE_ADD, map);
                }
            }
        }

        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, hon_words[i]);
        tts.speak(hon_words[i].toLowerCase(Locale.getDefault()), TextToSpeech.QUEUE_ADD, map);

    }

    UtteranceProgressListener mProgressListener = new UtteranceProgressListener() {
        @Override
        public void onStart(String utteranceId) {

            String Replace = "<span style= 'background-color:green'>" + utteranceId + "</span>";
            HonestlyTextWord.setText(Html.fromHtml(Replace));

            if(utteranceId.toLowerCase(Locale.getDefault()).equals("try") || utteranceId.toLowerCase(Locale.getDefault()).equals("catch") || utteranceId.toLowerCase(Locale.ROOT).equals("while")){
                int gif_view = getResources().getIdentifier(utteranceId.toLowerCase(Locale.getDefault()) +"1", "raw", getPackageName());
                HonestlySignGif.setImageResource(gif_view);
            } else {
                int gif_view = getResources().getIdentifier(utteranceId.toLowerCase(Locale.getDefault()), "raw", getPackageName());
                HonestlySignGif.setImageResource(gif_view);
            }
        }

        @Override
        public void onDone(String utteranceId) {

            if(utteranceId.equals(" ")){
              new Thread(){
                  @Override
                  public void run() {
                      j++;
                      HonestlyTextSent.setText(hon_sentence[j]);
                  }
              }.start();
            }

            i = i + 1;
            speak(hon_words, i);

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
            case  R.id.Honestly_Button_speak:
                HonestlyButtonSpeak.setEnabled(false);
                speak(hon_words,i);
                break;

            case  R.id.Honestly_button_stop:
                HonestlyButtonSpeak.setEnabled(true);
                stop();
                break;
        }
    }
}
