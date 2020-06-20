package application.example.mydatabaseapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import application.example.mydatabaseapp.R;
import application.example.mydatabaseapp.model.StudentModel;

public class AddStudentDialog extends AppCompatDialogFragment {

    private EditText editTextRoll, editTextName, editTextEmail;
    private AddStudentListener listener;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View  view = inflater.inflate(R.layout.add_student_dialog, null);

        editTextRoll = view.findViewById(R.id.edit_text_roll);
        editTextName = view.findViewById(R.id.edit_text_name);
        editTextEmail = view.findViewById(R.id.edit_text_email);

        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(view)
                .setTitle("Login")
                .setMessage("Enter student details")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String roll = editTextRoll.getText().toString();
                        String name = editTextName.getText().toString();
                        String email = editTextEmail.getText().toString();

                        StudentModel model = new StudentModel(roll, name, email);

                        listener.addStudent(model);

                    }
                })
                .setCancelable(false).create();
        return dialog;

    }

    public interface AddStudentListener {
        void addStudent(StudentModel student);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            listener = (AddStudentListener) context;
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
