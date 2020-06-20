package application.example.mydatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import application.example.mydatabaseapp.database.DatabaseHelper;
import application.example.mydatabaseapp.model.StudentModel;

public class StudentInfoActivity extends AppCompatActivity {

    public static final String TAG = "student";
    private EditText editTextRoll, editTextName, editTextEmail;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        editTextRoll = findViewById(R.id.edit_roll);
        editTextName = findViewById(R.id.edit_name);
        editTextEmail = findViewById(R.id.edit_email);

        Intent intent = getIntent();
        StudentModel model = intent.getParcelableExtra(TAG);

        editTextRoll.setText(model.getRollno());
        editTextRoll.setEnabled(false);
        editTextName.setText(model.getName());
        editTextEmail.setText(model.getEmail());
        
        helper = new DatabaseHelper(this);

    }

    public void modifyStudent(View view) {
        String roll = editTextRoll.getText().toString();
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();

        StudentModel model = new StudentModel(roll, name, email);
        
        boolean isUpdated = helper.updateInfo(model);
        if (isUpdated) {
            Toast.makeText(this, "Student info updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Student info not updated", Toast.LENGTH_SHORT).show();
        }
        finish();

    }

    public void deleteStudent(View view) {
        String roll = editTextRoll.getText().toString();
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();

        StudentModel model = new StudentModel(roll, name, email);

        boolean isDeleted = helper.deleteStudent(model);
        if (isDeleted) {
            Toast.makeText(this, "Student info deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Student info not deleted", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

}
