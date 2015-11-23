package me.rikinmarfatia.mintask;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
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

    private ImageButton mBtnAddTask;
    private RecyclerView mTaskRecyclerView;
    private TaskAdapter mTaskAdapter;
    private TaskDataHelper mTaskDataHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Tasks");
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tasklist, container, false);

        mTaskRecyclerView = (RecyclerView)v.findViewById(R.id.task_recycler_view);
        mTaskRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTaskRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()
                .getApplicationContext()));

        mBtnAddTask = (ImageButton)v.findViewById(R.id.btn_add_task);
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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.delete_tasks:
                Log.d(TAG, "delete_tasks clicked");
                mTaskDataHelper.deleteCompletedTasks();
                updateUI();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    private void updateUI() {
        mTaskDataHelper = TaskDataHelper.getInstance(getActivity());
        List<Task> tasks = mTaskDataHelper.getTasks();

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

    private class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public SimpleDividerItemDecoration(Context context) {
            mDivider = context.getResources().getDrawable(R.drawable.line_divider);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
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
