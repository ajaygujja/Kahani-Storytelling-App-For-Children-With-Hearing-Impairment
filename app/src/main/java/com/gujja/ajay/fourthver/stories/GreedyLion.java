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

public class GreedyLion extends AppCompatActivity implements TextToSpeech.OnInitListener, View.OnClickListener {

    @BindView(R.id.GreedySent)
    TextView GreedySentence;
    @BindView(R.id.GreedyWord)
    TextView GreedyWord;
    @BindView(R.id.Greedy_Button_speak)
    ImageButton GreedyButtonSpeak;
    @BindView(R.id.Greedy_button_stop)
    ImageButton GreedyButtonStop;
    @BindView(R.id.Greedy_Lion_SignImage)
    GifImageView GreedyLionSignGif;

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
        setContentView(R.layout.activity_greedy_lion);
        ButterKnife.bind(this);
        GreedyLion.this.setTitle("Greedy Lion");

        GreedySentence.setText(Greedy[0]);
        GreedyWord.setText(lion[0]);

        // Initialization of Text To Speech
        tts = new TextToSpeech(GreedyLion.this, this);

        // Tracking of Words
        tts.setOnUtteranceProgressListener(mProgressListener);

      GreedyButtonSpeak.setOnClickListener(this);
      GreedyButtonStop.setOnClickListener(this);

    }

    private void stop() {
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


        for (String stopword : stop_words) {
            if (lion[i].toLowerCase(Locale.getDefault()).equals(stopword)) {
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
                GreedyButtonSpeak.setEnabled(false);
                speak(lion,i);
                break;

            case  R.id.Greedy_button_stop:
                GreedyButtonSpeak.setEnabled(true);
                stop();
                break;
        }
    }

    private abstract static class runnable implements Runnable {
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


                            if (utteranceId.toLowerCase(Locale.getDefault()).equals("try") || utteranceId.toLowerCase(Locale.getDefault()).equals("catch") || utteranceId.toLowerCase(Locale.ROOT).equals("while")) {
                                int gif_view = getResources().getIdentifier(utteranceId.toLowerCase(Locale.getDefault()) + "1", "raw", getPackageName());
                                GreedyLionSignGif.setImageResource(gif_view);
                            } else {
                                int gif_view = getResources().getIdentifier(utteranceId.toLowerCase(Locale.getDefault()), "raw", getPackageName());
                                GreedyLionSignGif.setImageResource(gif_view);
                            }

                        }
                    });
                }
            }.start();
        }

        @Override
        public void onDone(String utteranceId) {



            if (utteranceId.equals(" ")) {
                new Thread(){
                    @Override
                    public void run() {
                        j++;
                        GreedySentence.setText(Greedy[j]);
                    }
                }.start();
            }

            // For Incrementing Words
            i = i + 1;
            speak(lion, i);
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
        stop();
        super.onBackPressed();
        this.finish();
    }


}
