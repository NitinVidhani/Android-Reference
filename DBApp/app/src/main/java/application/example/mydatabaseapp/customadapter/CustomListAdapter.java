package application.example.mydatabaseapp.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import application.example.mydatabaseapp.R;
import application.example.mydatabaseapp.model.StudentModel;

public class CustomListAdapter extends BaseAdapter {

    List<StudentModel> list;
    Context context;
    LayoutInflater inflater;

    public CustomListAdapter(Context context, List<StudentModel> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.custom_list_view_layout, parent, false);

        TextView textViewRoll = view.findViewById(R.id.text_view_roll);
        TextView textViewName = view.findViewById(R.id.text_view_name);
        TextView textViewEmail = view.findViewById(R.id.text_view_email);

        textViewRoll.setText(list.get(position).getRollno());
        textViewName.setText(list.get(position).getName());
        textViewEmail.setText(list.get(position).getEmail());

        return view;

    }
}
