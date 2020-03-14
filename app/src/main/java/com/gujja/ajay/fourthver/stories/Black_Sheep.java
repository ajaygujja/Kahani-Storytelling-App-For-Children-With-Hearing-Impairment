package com.gujja.ajay.fourthver.stories;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.TextView;

import com.gujja.ajay.fourthver.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

public class Black_Sheep extends AppCompatActivity {

    @BindView(R.id.Black_Sheep_Sentence)
    TextView BlackSheepSentence;
    @BindView(R.id.Black_Sheep_Word)
    TextView BlackSheepWord;
    @BindView(R.id.Black_Sheep_Button_speak)
    Button BlackSheepButtonSpeak;
    @BindView(R.id.Black_Sheep_button_stop)
    Button BlackSheepButtonStop;
    @BindView(R.id.Black_Sheep_Sign_Gif)
    GifImageView BlackSheepSignGif;


    int i = 0;
    int j = 0;
    float speed = 0.7f;
    float pitch = 0.8f;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black__sheep);
        ButterKnife.bind(this);




    }
}
