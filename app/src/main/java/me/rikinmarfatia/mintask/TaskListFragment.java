package me.rikinmarfatia.mintask;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import me.rikinmarfatia.mintask.models.TaskDataHelper;
import me.rikinmarfatia.mintask.models.Task;
import me.rikinmarfatia.mintask.util.ColorStrings;

/**
 * @author Rikin Marfatia (rikinm10@gmail.com)
 */
public class TaskListFragment extends Fragment {

    private static final String TAG = "TaskListFragment";
    public static final int REQUEST_NEWTASK = 1;

    private Button mBtnAddTask;
    private RecyclerView mTaskRecyclerView;
    private TaskAdapter mTaskAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tasklist, container, false);

        mTaskRecyclerView = (RecyclerView)v.findViewById(R.id.task_recycler_view);
        mTaskRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mBtnAddTask = (Button)v.findViewById(R.id.btn_add_task);
        mBtnAddTask.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NewTaskActivity.class);
                startActivityForResult(i, REQUEST_NEWTASK);
            }
        });

        updateUI();

        return v;
    }

    private void updateUI() {
        TaskDataHelper taskDataHelper = TaskDataHelper.getInstance(getActivity());
        List<Task> tasks = taskDataHelper.getTasks();

        if(mTaskAdapter == null){
            mTaskAdapter = new TaskAdapter(tasks);
            mTaskRecyclerView.setAdapter(mTaskAdapter);
        } else {
            mTaskAdapter.setTasks(tasks);
            mTaskAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_NEWTASK) {
            if(resultCode == Activity.RESULT_OK) {
               updateUI();
            }
        }
    }

    private int getColorFromString(String colorName) {

        if(colorName.equalsIgnoreCase(ColorStrings.WHITE)) {
            return ContextCompat.getColor(getActivity(), R.color.white);
        } else if (colorName.equalsIgnoreCase(ColorStrings.RED)) {
            return ContextCompat.getColor(getActivity(), R.color.red);
        } else if(colorName.equalsIgnoreCase(ColorStrings.GREEN)) {
            return ContextCompat.getColor(getActivity(), R.color.green);
        } else {
            return ContextCompat.getColor(getActivity(), R.color.blue);
        }
    }

    private class TaskHolder extends RecyclerView.ViewHolder {

        private TextView mTaskTitle;
        private CheckBox mTaskCheckBox;
        private Task mTask;

        public TaskHolder(View itemView) {
            super(itemView);

            mTaskTitle = (TextView)itemView.findViewById(R.id.tasklist_item_title);
            mTaskCheckBox = (CheckBox)itemView.findViewById(R.id.tasklist_item_checkbox);
            mTaskCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mTask.setComplete(isChecked);
                    TaskDataHelper.getInstance(getActivity()).updateTask(mTask);
                }
            });
        }

        public void bindTask(Task task) {
            mTask = task;
            mTaskTitle.setText(mTask.getTitle());
            mTaskCheckBox.setChecked(mTask.isComplete());

            itemView.setBackgroundColor(getColorFromString(mTask.getColor()));
            if(!ColorStrings.WHITE.equalsIgnoreCase(mTask.getColor())) {
                mTaskTitle.setTextColor(getColorFromString(ColorStrings.WHITE));
            }
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {

        private List<Task> mTasks;

        public TaskAdapter(List<Task> tasks) {
            mTasks = tasks;
        }

        @Override
        public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.list_item_task, parent, false);

            return new TaskHolder(v);
        }

        @Override
        public void onBindViewHolder(TaskHolder holder, int position) {
            Task task = mTasks.get(position);
            holder.bindTask(task);
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }

        public void setTasks(List<Task> tasks) {
            mTasks = tasks;
        }
    }

}
