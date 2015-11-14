// ViewContact.java
// Activity for viewing a single contact.
package assignment3.jcnelson.parta.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import assignment3.jcnelson.parta.Datbase.DatabaseConnector;
import assignment3.jcnelson.parta.R;

public class ViewStudent extends Activity
{
   private long rowID; // selected contact's name
   private TextView idTextView; // displays contact's name
   private TextView q1TextView; // displays contact's phone
   private TextView q2TextView; // displays contact's phone
   private TextView q3TextView; // displays contact's phone
   private TextView q4TextView; // displays contact's phone
   private TextView q5TextView; // displays contact's phone

   // called when the activity is first created
   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.view_student);

      // get the EditTexts
      idTextView = (TextView) findViewById(R.id.studentIDTextView);
      q1TextView = (TextView) findViewById(R.id.Quiz1TextView);
      q2TextView = (TextView) findViewById(R.id.Quiz2TextView);
      q3TextView = (TextView) findViewById(R.id.Quiz3TextView);
      q4TextView = (TextView) findViewById(R.id.Quiz4TextView);
      q5TextView = (TextView) findViewById(R.id.Quiz5TextView);

      
      // get the selected contact's row ID
      Bundle extras = getIntent().getExtras();
      rowID = extras.getLong(GradeBook.ROW_ID);
   } // end method onCreate

   // called when the activity is first created
   @Override
   protected void onResume()
   {
      super.onResume();
      
      // create new LoadContactTask and execute it 
      new LoadStudentTask().execute(rowID);
   } // end method onResume
   
   // performs database query outside GUI thread
   private class LoadStudentTask extends AsyncTask<Long, Object, Cursor>
   {
      DatabaseConnector databaseConnector =
         new DatabaseConnector(ViewStudent.this);

      // perform the database access
      @Override
      protected Cursor doInBackground(Long... params)
      {
         databaseConnector.open();
         
         // get a cursor containing all data on given entry
         return databaseConnector.getOneStudent(params[0]);
      } // end method doInBackground

      // use the Cursor returned from the doInBackground method
      @Override
      protected void onPostExecute(Cursor result)
      {
         super.onPostExecute(result);
   
         result.moveToFirst(); // move to the first item 
   
         // get the column index for each data item
         int idIndex = result.getColumnIndex("StudentID");
         int q1Index = result.getColumnIndex("Quiz1");
         int q2Index = result.getColumnIndex("Quiz2");
         int q3Index = result.getColumnIndex("Quiz3");
         int q4Index = result.getColumnIndex("Quiz4");
         int q5Index = result.getColumnIndex("Quiz5");

   
         // fill TextViews with the retrieved data
         idTextView.setText(result.getString(idIndex));
         q1TextView.setText(result.getString(q1Index));
         q2TextView.setText(result.getString(q2Index));
         q3TextView.setText(result.getString(q3Index));
         q4TextView.setText(result.getString(q4Index));
         q5TextView.setText(result.getString(q5Index));

   
         result.close(); // close the result cursor
         databaseConnector.close(); // close database connection
      } // end method onPostExecute
   } // end class LoadContactTask

   // create the Activity's menu from a menu resource XML file
   @Override
   public boolean onCreateOptionsMenu(Menu menu)
   {
      super.onCreateOptionsMenu(menu);
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.view_student_menu, menu);
      return true;
   } // end method onCreateOptionsMenu

   // handle choice from options menu
   @Override
   public boolean onOptionsItemSelected(MenuItem item)
   {
      switch (item.getItemId()) // switch based on selected MenuItem's ID
      {
         case R.id.deleteItem:
            deleteStudent(); // delete the displayed contact
            return true;
         default:
            return super.onOptionsItemSelected(item);
      } // end switch
   } // end method onOptionsItemSelected

   // delete a contact
   private void deleteStudent()
   {
      // create a new AlertDialog Builder
      AlertDialog.Builder builder =
              new AlertDialog.Builder(ViewStudent.this);

      builder.setTitle(R.string.confirmTitle); // title bar string
      builder.setMessage(R.string.confirmMessage); // message to display

      // provide an OK button that simply dismisses the dialog
      builder.setPositiveButton(R.string.button_delete,
              new DialogInterface.OnClickListener()
              {
                 @Override
                 public void onClick(DialogInterface dialog, int button)
                 {
                    final DatabaseConnector databaseConnector =
                            new DatabaseConnector(ViewStudent.this);

                    // create an AsyncTask that deletes the contact in another
                    // thread, then calls finish after the deletion
                    AsyncTask<Long, Object, Object> deleteTask =
                            new AsyncTask<Long, Object, Object>()
                            {
                               @Override
                               protected Object doInBackground(Long... params)
                               {
                                  databaseConnector.deleteStudent(params[0]);
                                  return null;
                               } // end method doInBackground

                               @Override
                               protected void onPostExecute(Object result)
                               {
                                  finish(); // return to the AddressBook Activity
                               } // end method onPostExecute
                            }; // end new AsyncTask

                    // execute the AsyncTask to delete contact at rowID
                    deleteTask.execute(new Long[] { rowID });
                 } // end method onClick
              } // end anonymous inner class
      ); // end call to method setPositiveButton

      builder.setNegativeButton(R.string.button_cancel, null);
      builder.show(); // display the Dialog
   } // end method deleteStudent

} // end class ViewContact
