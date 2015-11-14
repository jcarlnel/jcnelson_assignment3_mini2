package assignment3.jcnelson.parta.Util;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import assignment3.jcnelson.parta.Model.Statistics;
import assignment3.jcnelson.parta.R;

public class ShowStatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Statistics s = new Statistics();
        s.findlow(GradeBook.students);
        s.findavg(GradeBook.students);
        s.findhigh(GradeBook.students);

        TextView q1hi = (TextView) findViewById(R.id.q1hi);
        q1hi.setText(Integer.toString(s.highscores[0]));

        TextView q1lo = (TextView) findViewById(R.id.q1lo);
        q1lo.setText(Integer.toString(s.lowscores[0]));

        TextView q1av = (TextView) findViewById(R.id.q1av);
        q1av.setText(Float.toString(s.avgscores[0]));

        TextView q2hi = (TextView) findViewById(R.id.q2hi);
        q2hi.setText(Integer.toString(s.highscores[1]));

        TextView q2lo = (TextView) findViewById(R.id.q2lo);
        q2lo.setText(Integer.toString(s.lowscores[1]));

        TextView q2av = (TextView) findViewById(R.id.q2av);
        q2av.setText(Float.toString(s.avgscores[1]));

        TextView q3hi = (TextView) findViewById(R.id.q3hi);
        q3hi.setText(Integer.toString(s.highscores[2]));

        TextView q3lo = (TextView) findViewById(R.id.q3lo);
        q3lo.setText(Integer.toString(s.lowscores[2]));

        TextView q3av = (TextView) findViewById(R.id.q3av);
        q3av.setText(Float.toString(s.avgscores[2]));

        TextView q4hi = (TextView) findViewById(R.id.q4hi);
        q4hi.setText(Integer.toString(s.highscores[3]));

        TextView q4lo = (TextView) findViewById(R.id.q4lo);
        q4lo.setText(Integer.toString(s.lowscores[3]));

        TextView q4av = (TextView) findViewById(R.id.q4av);
        q4av.setText(Float.toString(s.avgscores[3]));

        TextView q5hi = (TextView) findViewById(R.id.q5hi);
        q5hi.setText(Integer.toString(s.highscores[4]));

        TextView q5lo = (TextView) findViewById(R.id.q5lo);
        q5lo.setText(Integer.toString(s.lowscores[4]));

        TextView q5av = (TextView) findViewById(R.id.q5av);
        q5av.setText(Float.toString(s.avgscores[4]));

    }

    public void onHomeButtonClick(View view){
        Intent intent = new Intent(this, GradeBook.class);
        startActivity(intent);
    }

}
