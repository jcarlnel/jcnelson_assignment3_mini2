// AddressBook.java
// Main activity for the Address Book app.
package assignment3.jcnelson.parta.Util;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import assignment3.jcnelson.parta.Datbase.DatabaseConnector;
import assignment3.jcnelson.parta.Model.Student;
import assignment3.jcnelson.parta.R;

public class GradeBook extends ListActivity
{
   public static final String ROW_ID = "row_id"; // Intent extra key
   private ListView StudentListView; // the ListActivity's ListView
   private CursorAdapter adapter; // adapter for ListView
   protected static Student[] students = new Student[40];
   protected static int numStudents = 0;
   
   // called when the activity is first created
   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState); // call super's onCreate
      StudentListView = getListView(); // get the built-in ListView
      StudentListView.setOnItemClickListener(viewStudentListener);

      // map each contact's name to a TextView in the ListView layout
      String[] from = new String[] { "StudentID", "Quiz1", "Quiz2", "Quiz3", "Quiz4", "Quiz5" };
      int[] to = new int[] { R.id.studentTextView };
      adapter = new SimpleCursorAdapter(
         GradeBook.this, R.layout.student_list_item, null, from, to);
      setListAdapter(adapter);
   } // end method onCreate

   @Override
   protected void onResume() 
   {
      super.onResume(); // call super's onResume method
      
       // create new GetContactsTask and execute it 
       new GetStudentsTask().execute((Object[]) null);
    } // end method onResume

   @Override
   protected void onStop() 
   {
      Cursor cursor = adapter.getCursor(); // get current Cursor
      
      if (cursor != null) 
         cursor.deactivate(); // deactivate it
      
      adapter.changeCursor(null); // adapted now has no Cursor
      super.onStop();
   } // end method onStop


   // performs database query outside GUI thread
   private class GetStudentsTask extends AsyncTask<Object, Object, Cursor>
   {
      DatabaseConnector databaseConnector =
         new DatabaseConnector(GradeBook.this);

      // perform the database access
      @Override
      protected Cursor doInBackground(Object... params)
      {
         databaseConnector.open();

         // get a cursor containing call contacts
         return databaseConnector.getAllStudents();
      } // end method doInBackground

      // use the Cursor returned from the doInBackground method
      @Override
      protected void onPostExecute(Cursor result)
      {
         adapter.changeCursor(result); // set the adapter's Cursor
         databaseConnector.close();
      } // end method onPostExecute
   } // end class GetContactsTask
      
   // create the Activity's menu from a menu resource XML file
   @Override
   public boolean onCreateOptionsMenu(Menu menu)
   {
      super.onCreateOptionsMenu(menu);
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.gradebook_menu, menu);
      return true;
   } // end method onCreateOptionsMenu
   
   // handle choice from options menu
   @Override
   public boolean onOptionsItemSelected(MenuItem item)
   {
      Intent addNewStudent =
         new Intent(GradeBook.this, AddEditStudent.class);
      startActivity(addNewStudent); // start the AddEditContact Activity
      return super.onOptionsItemSelected(item); // call super's method
   } // end method onOptionsItemSelected

   // event listener that responds to the user touching a contact's name
   // in the ListView
   OnItemClickListener viewStudentListener = new OnItemClickListener()
   {
      @Override
      public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
         long arg3) 
      {
         // create an Intent to launch the ViewContact Activity
         Intent viewContact =
            new Intent(GradeBook.this, ViewStudent.class);
         
         // pass the selected contact's row ID as an extra with the Intent
         viewContact.putExtra(ROW_ID, arg3);
         startActivity(viewContact); // start the ViewContact Activity
      } // end method onItemClick
   }; // end viewContactListener
} // end class GradeBook
