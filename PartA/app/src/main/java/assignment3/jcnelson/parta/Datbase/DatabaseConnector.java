// DatabaseConnector.java
// Provides easy connection and creation of UserContacts database.
package assignment3.jcnelson.parta.Datbase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnector 
{
   // database name
   private static final String DATABASE_NAME = "Students";
   private SQLiteDatabase database; // database object
   private DatabaseOpenHelper databaseOpenHelper; // database helper

   // public constructor for DatabaseConnector
   public DatabaseConnector(Context context)
   {
      // create a new DatabaseOpenHelper
      databaseOpenHelper = 
         new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
   } // end DatabaseConnector constructor

   // open the database connection
   public void open() throws SQLException
   {
      // create or open a database for reading/writing
      database = databaseOpenHelper.getWritableDatabase();
   } // end method open

   // close the database connection
   public void close() 
   {
      if (database != null)
         database.close(); // close the database connection
   } // end method close

   // inserts a new contact in the database
public void insertStudent(int StudentID, int q1, int q2, int q3, int q4, int q5)
   {
      ContentValues newStudent = new ContentValues();
      newStudent.put("StudentID", StudentID);
      newStudent.put("Quiz1", q1);
      newStudent.put("Quiz2", q2);
      newStudent.put("Quiz3", q3);
      newStudent.put("Quiz4", q4);
      newStudent.put("Quiz5", q5);


      open(); // open the database
      database.insert("Students", null, newStudent);
      close(); // close the database
   } // end method insertContact



   // return a Cursor with all contact information in the database
   public Cursor getAllStudents()
   {
      return database.query("Students", new String[] {"_id", "StudentID, Quiz1, Quiz2, Quiz3, Quiz4, Quiz5"},
         null, null, null, null, "StudentID");
   } // end method getAllContacts

   public Cursor getOneStudent(long id)
   {
      return database.query(
              "Students", null, "_id=" + id, null, null, null, null);
   } // end method getOnContact

   // delete the contact specified by the given String name
   public void deleteStudent(long id)
   {
      open(); // open the database
      database.delete("Students", "_id=" + id, null);
      close(); // close the database
   } // end method deleteContact
   
   private class DatabaseOpenHelper extends SQLiteOpenHelper
   {
      // public constructor
      public DatabaseOpenHelper(Context context, String name,
         CursorFactory factory, int version)
      {
         super(context, name, factory, version);
      } // end DatabaseOpenHelper constructor

      // creates the contacts table when the database is created
      @Override
      public void onCreate(SQLiteDatabase db)
      {
         // query to create a new table named contacts
         String createQuery = "CREATE TABLE Students" +
            "(_id integer primary key autoincrement," +
            "StudentID INT, Quiz1 INT, Quiz2 INT, " +
            "Quiz3 INT, Quiz4 INT, Quiz5 INT);";
                  
         db.execSQL(createQuery); // execute the query
      } // end method onCreate

      @Override
      public void onUpgrade(SQLiteDatabase db, int oldVersion,
          int newVersion) 
      {
      } // end method onUpgrade
   } // end class DatabaseOpenHelper
} // end class DatabaseConnector
