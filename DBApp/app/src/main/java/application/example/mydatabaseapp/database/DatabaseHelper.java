package application.example.mydatabaseapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import application.example.mydatabaseapp.model.StudentModel;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "student";
    public static final String STUDENT_TABLE = "STUDENT";
    public static final String ROLLNO = "ROLLNO";
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + STUDENT_TABLE + "(" + ROLLNO + " TEXT PRIMARY KEY, " + NAME + " TEXT NOT NULL, " + EMAIL + " TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        onCreate(db);
    }

    // inserting a record in the db
    public boolean addStudent(StudentModel model) {
        String roll = model.getRollno();
        String name = model.getName();
        String email = model.getEmail();

        ContentValues values = new ContentValues();
        values.put(ROLLNO, roll);
        values.put(NAME, name);
        values.put(EMAIL, email);

        SQLiteDatabase db = this.getWritableDatabase();
        long success = db.insert(STUDENT_TABLE, null, values);
        if(success == -1) {
            return false;
        }
        return true;

    }


    // Retrieving all the records from the db
    public List<StudentModel> getData() {
        ArrayList<StudentModel> list = new ArrayList<>();
        String query = "SELECT * FROM " + STUDENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String roll = cursor.getString(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            StudentModel model = new StudentModel(roll, name, email);
            list.add(model);
        }

        return list;
    }

    // Update the record of the database
    public boolean updateInfo(StudentModel student) {
        String roll = student.getRollno();
        String name = student.getName();
        String email = student.getEmail();

        ContentValues values = new ContentValues();
        values.put(ROLLNO, roll);
        values.put(NAME, name);
        values.put(EMAIL, email);
        
        SQLiteDatabase db = this.getWritableDatabase();

        int update = db.update(STUDENT_TABLE, values, ROLLNO + " = ?", new String[]{roll});
        if (update == 1) {
            return true;
        }
        return false;
    }

    // Deleting the record of the database
    public boolean deleteStudent(StudentModel student) {
        String roll = student.getRollno();

        SQLiteDatabase db = this.getWritableDatabase();
        int delete = db.delete(STUDENT_TABLE, ROLLNO + " = ?", new String[] {roll});

        if (delete == 1) {
            return true;
        }

        return false;

    }

    // Checking the database if it is empty or not
    public boolean isEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + STUDENT_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 0) {
            return true;
        } else {
            return false;
        }
    }

}
