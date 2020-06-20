package application.example.mydatabaseapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import application.example.mydatabaseapp.database.DatabaseHelper;
import application.example.mydatabaseapp.model.StudentModel;

public class MainActivity extends AppCompatActivity implements AddStudentDialog.AddStudentListener {

    private static final String TAG = "MainActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addStudent(View view) {
        AddStudentDialog dialog = new AddStudentDialog();
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), TAG);

    }

    public void viewStudents(View view) {
        DatabaseHelper helper = new DatabaseHelper(this);
        if (helper.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Empty!!").setMessage("First you have to add student")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        }
        else {
            Intent intent = new Intent(this, ViewStudentActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void addStudent(StudentModel student) {

            DatabaseHelper db = new DatabaseHelper(this);
            boolean success = db.addStudent(student);
            if (success) {
                Toast.makeText(this, "Student Added Successfully", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Student Already Exits", Toast.LENGTH_SHORT).show();
            }
    }
}
