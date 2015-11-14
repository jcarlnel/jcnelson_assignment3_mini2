package assignment3.jcnelson.partb;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    VideoView video1, video2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView video1= (VideoView) findViewById(R.id.video1);
        Uri video1URI = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.video1);
        video1.setVideoURI(video1URI);
        video1.requestFocus();
        video1.start();

        VideoView video2= (VideoView) findViewById(R.id.video2);
        Uri video2URI = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.anim_card_flip);
        video2.setVideoURI(video2URI);
        video2.requestFocus();
        video2.start();
    }

}
