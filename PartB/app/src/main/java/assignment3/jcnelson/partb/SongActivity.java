package assignment3.jcnelson.partb;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class SongActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private int playbackPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        Button playButton = (Button)findViewById(R.id.playButton);
        Button pauseButton = (Button)findViewById(R.id.pauseButton);
        Button restartButton = (Button)findViewById(R.id.restartButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    playLocalAudio_UsingDescriptor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(mediaPlayer!=null)
                {
                    playbackPosition = mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                }
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(mediaPlayer!=null && !mediaPlayer.isPlaying())
                {
                    mediaPlayer.seekTo(playbackPosition);
                    mediaPlayer.start();
                }
            }
        });
    }

    private void playAudio(String url)throws Exception
    {
        killMediaPlayer();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(url);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        killMediaPlayer();
    }

    private void killMediaPlayer()
    {
        if(mediaPlayer!=null)
        {
            try
            {
                mediaPlayer.release();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    private void playLocalAudio_UsingDescriptor() throws Exception
    {
        Spinner s = (Spinner) findViewById(R.id.songSelect);
        AssetFileDescriptor fileDesc;
        if(s.getSelectedItem().toString().equals("disappear")){
            fileDesc = getResources().openRawResourceFd(R.raw.disappear);
        }
        else if(s.getSelectedItem().toString().equals("music_file")){
            fileDesc = getResources().openRawResourceFd(R.raw.music_file);
        }
        else{
            fileDesc = getResources().openRawResourceFd(R.raw.test_cbr);
        }
        if (fileDesc != null)
        {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(fileDesc.getFileDescriptor(), fileDesc.getStartOffset(), fileDesc.getLength());
            fileDesc.close();
            mediaPlayer.prepare();
            mediaPlayer.start();
        }
    }
}
