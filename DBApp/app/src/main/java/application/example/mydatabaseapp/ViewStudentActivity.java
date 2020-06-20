package application.example.mydatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import application.example.mydatabaseapp.customadapter.CustomListAdapter;
import application.example.mydatabaseapp.database.DatabaseHelper;
import application.example.mydatabaseapp.model.StudentModel;

public class ViewStudentActivity extends AppCompatActivity {

    public static final String TAG = "student";
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        listView = findViewById(R.id.list_view);
        DatabaseHelper helper = new DatabaseHelper(this);
        final ArrayList<StudentModel> list = (ArrayList<StudentModel>) helper.getData();

        CustomListAdapter adapter = new CustomListAdapter(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentModel parcelableModel = new StudentModel(list.get(position).getRollno(),
                                                                list.get(position).getName(),
                                                                list.get(position).getEmail());
                Intent intent = new Intent(ViewStudentActivity.this, StudentInfoActivity.class);
                intent.putExtra(TAG, parcelableModel);
                startActivity(intent);
            }
        });
    }
}
