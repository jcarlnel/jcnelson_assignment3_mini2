// AddEditContact.java
// Activity for adding a new entry to or  
// editing an existing entry in the address book.
package assignment3.jcnelson.parta.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import assignment3.jcnelson.parta.Datbase.DatabaseConnector;
import assignment3.jcnelson.parta.Exception.TooManyStudentsException;
import assignment3.jcnelson.parta.Model.Student;
import assignment3.jcnelson.parta.R;

public class AddEditStudent extends Activity implements ShowStatsFragment.OnFragmentInteractionListener
{
   private long rowID; // id of contact being edited, if any
   
   // EditTexts for contact information
   private EditText idEditText;
   private EditText q1EditText;
   private EditText q2EditText;
   private EditText q3EditText;
   private EditText q4EditText;
   private EditText q5EditText;
   
   // called when the Activity is first started
   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState); // call super's onCreate
      setContentView(R.layout.add_student); // inflate the UI

      idEditText = (EditText) findViewById(R.id.studentIDEditText);
      q1EditText = (EditText) findViewById(R.id.quiz1EditText);
      q2EditText = (EditText) findViewById(R.id.quiz2EditText);
      q3EditText = (EditText) findViewById(R.id.quiz3EditText);
      q4EditText = (EditText) findViewById(R.id.quiz4EditText);
      q5EditText = (EditText) findViewById(R.id.quiz5EditText);

      Bundle extras = getIntent().getExtras(); // get Bundle of extras

      // if there are extras, use them to populate the EditTexts
      if (extras != null)
      {
         rowID = extras.getLong("row_id");
         idEditText.setText(extras.getInt("StudentID"));
         q1EditText.setText(extras.getInt("Quiz1"));
         q2EditText.setText(extras.getInt("Quiz2"));
         q3EditText.setText(extras.getInt("Quiz3"));
         q4EditText.setText(extras.getInt("Quiz4"));
         q5EditText.setText(extras.getInt("Quiz5"));
      } // end if
      
      // set event listener for the Save Contact Button
      Button saveStudentButton =
         (Button) findViewById(R.id.saveStudentButton);
      saveStudentButton.setOnClickListener(saveStudentButtonClicked);
   } // end method onCreate

   // responds to event generated when user clicks the Done Button
   OnClickListener saveStudentButtonClicked = new OnClickListener()
   {
      @Override
      public void onClick(View v)
      {
         if (idEditText.getText().length() != 0)
         {
            AsyncTask<Object, Object, Object> saveContactTask =
               new AsyncTask<Object, Object, Object>()
               {
                  @Override
                  protected Object doInBackground(Object... params)
                  {
                     saveStudent(); // save contact to the database
                     return null;
                  } // end method doInBackground
      
                  @Override
                  protected void onPostExecute(Object result)
                  {
                     finish(); // return to the previous Activity
                  } // end method onPostExecute
               }; // end AsyncTask
               
            // save the contact to the database using a separate thread
            saveContactTask.execute((Object[]) null);
         } // end if
         else
         {
            // create a new AlertDialog Builder
            AlertDialog.Builder builder =
               new AlertDialog.Builder(AddEditStudent.this);
      
            // set dialog title & message, and provide Button to dismiss
            builder.setTitle(R.string.errorTitle);
            builder.setMessage(R.string.errorMessage);
            builder.setPositiveButton(R.string.errorButton, null);
            builder.show(); // display the Dialog
         } // end else
      } // end method onClick
   }; // end OnClickListener saveContactButtonClicked

   // saves contact information to the database
   private void saveStudent()
   {
      // get DatabaseConnector to interact with the SQLite database
      DatabaseConnector databaseConnector = new DatabaseConnector(this);

      if (getIntent().getExtras() == null)
      {

         int[] info = new int[]{0,0,0,0,0,0};
         info[0] = Integer.valueOf(idEditText.getText().toString());
         info[1] = Integer.valueOf(q1EditText.getText().toString());
         info[2] = Integer.valueOf(q2EditText.getText().toString());
         info[3] = Integer.valueOf(q3EditText.getText().toString());
         info[4] = Integer.valueOf(q4EditText.getText().toString());
         info[5] = Integer.valueOf(q5EditText.getText().toString());
         System.out.println(info);
         GradeBook.students[GradeBook.numStudents] = new Student(info);

         // insert the contact information into the database
         databaseConnector.insertStudent(
            Integer.valueOf(idEditText.getText().toString()),
            Integer.valueOf(q1EditText.getText().toString()),
            Integer.valueOf(q2EditText.getText().toString()),
            Integer.valueOf(q3EditText.getText().toString()),
            Integer.valueOf(q4EditText.getText().toString()),
            Integer.valueOf(q5EditText.getText().toString()));
      } // end if

   } // end class saveContact

   @Override
   public void onFragmentInteraction(Uri uri) {
      Intent intent = new Intent(this, ShowStatsActivity.class);
      startActivity(intent);
   }
} // end class AddEditContact
