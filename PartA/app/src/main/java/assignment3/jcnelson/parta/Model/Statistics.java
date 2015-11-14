package assignment3.jcnelson.parta.Model;

/**
 * Created by jacobnelson on 11/12/15.
 */

public class Statistics{
    //calculates the low/high/avg scores and prints them out
    public int [] lowscores = new int [5];
    public int [] highscores = new int [5];
    public float [] avgscores = new float [5];

    public Statistics(){
        for(int i = 0; i < 5; i++){
            lowscores[i] = 100;
            highscores[i] = 0;
        }
    }
    public void findlow(Student [] a){
		/*This method will find the lowest score and store it in
		an array names lowscores. */
        int i = 0;
        while(i < 40 && a[i] != null){
            for(int j = 0; j < 5; j++){
                if(a[i].getScores()[j] < lowscores[j]){
                    lowscores[j] = a[i].getScores()[j];
                }
            }
            i++;
        }
    }

    public void findhigh(Student [] a){
		/* This method will find the highest score and store it in
		an array names highscores. */
        int i = 0;
        while(i < 40 && a[i] != null){
            for(int j = 0; j < 5; j++){
                if(a[i].getScores()[j] > highscores[j]){
                    highscores[j] = a[i].getScores()[j];
                }
            }
            i++;
        }
    }

    public void findavg(Student [] a){
		/* This method will find avg score for each quiz and store
		it in an array names avgscores. */
        int i = 0;
        while(i < 40 && a[i] != null){
            for(int j = 0; j < 5; j++){
                avgscores[j] = avgscores[j] + a[i].getScores()[j];
            }
            i++;
        }
        for(int k = 0; k < 5; k++){
            avgscores[k] = avgscores[k] / i;
        }
    }

}

