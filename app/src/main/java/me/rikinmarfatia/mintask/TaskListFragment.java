package me.rikinmarfatia.mintask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import me.rikinmarfatia.mintask.models.AllTasks;
import me.rikinmarfatia.mintask.models.Task;

/**
 * @author Rikin Marfatia (rikinm10@gmail.com)
 */
public class TaskListFragment extends ListFragment {

    private static final String TAG = "TaskListFragment";
    public static final int REQUEST_NEWTASK = 1;

    private Button mBtnAddTask;
    private ArrayList<Task> mTasks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTasks = AllTasks.getInstance(getActivity()).getTasks();
        TaskAdapter adapter = new TaskAdapter(mTasks);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tasklist, container, false);

        mBtnAddTask = (Button)v.findViewById(R.id.btn_add_task);
        mBtnAddTask.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NewTaskActivity.class);
                startActivityForResult(i, REQUEST_NEWTASK);
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_NEWTASK) {
            if(resultCode == Activity.RESULT_OK) {
                ((TaskAdapter)getListAdapter()).notifyDataSetChanged();
            }
        }
    }

    private class TaskAdapter extends ArrayAdapter<Task> {

        public TaskAdapter(ArrayList<Task> tasks) {
            super(getActivity(), 0, tasks);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_task, null);
            }

            Task task = getItem(position);

            TextView taskTitle = (TextView)convertView.findViewById(R.id.tasklist_item_title);
            taskTitle.setText(task.getTitle());

            CheckBox taskCompleted = (CheckBox)convertView.findViewById(R.id.tasklist_item_checkbox);
            taskCompleted.setChecked(task.isComplete());

            convertView.setBackgroundColor(getResources().getColor(task.getColor()));
            if(task.getColor() != R.color.white) {
                taskTitle.setTextColor(getResources().getColor(R.color.white));
            }

            return convertView;
        }
    }
}
