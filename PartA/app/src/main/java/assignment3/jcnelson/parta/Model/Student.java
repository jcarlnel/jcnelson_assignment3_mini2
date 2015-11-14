package assignment3.jcnelson.parta.Model;

/**
 * Created by jacobnelson on 11/12/15.
 */
public class Student {
    //implements a student with their id and quiz scores
    private int SID;
    private int scores[] = new int[5];

    public Student(int[] info){
        SID = info[0];
        scores[0] = info[1];
        scores[1] = info[2];
        scores[2] = info[3];
        scores[3] = info[4];
        scores[4] = info[5];
    }

    public int getSID() {
        return SID;
    }
    public void setSID(int sID) {
        SID = sID;
    }
    public int[] getScores() {
        return scores;
    }
    public void setScores(int scores[]) {
        this.scores = scores;
    }


}

