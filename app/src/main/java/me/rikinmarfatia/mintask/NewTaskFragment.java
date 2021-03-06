package me.rikinmarfatia.mintask;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import me.rikinmarfatia.mintask.models.TaskDataHelper;
import me.rikinmarfatia.mintask.models.Task;
import me.rikinmarfatia.mintask.util.ColorStrings;

/**
 * Fragment for creating a new Task
 *
 * @author Rikin Marfatia (rikinm10@gmail.com)
 */
public class NewTaskFragment extends Fragment {

    private Button mEnterTask;
    private EditText mInputTask;
    private TaskDataHelper sTaskDataHelper;
    private Task currTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sTaskDataHelper = TaskDataHelper.getInstance(getActivity());
        getActivity().setTitle("Add a new task");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_newtask, container, false);

        mInputTask = (EditText)v.findViewById(R.id.edit_tasktitle);
        mInputTask.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(currTask == null) {
                    currTask = new Task(s.toString());
                } else {
                    currTask.setTitle(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // nothing
            }
        });

        mEnterTask = (Button)v.findViewById(R.id.btn_enter_task);
        mEnterTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currTask == null || currTask.getTitle().length() == 0) {
                    getActivity().setResult(Activity.RESULT_CANCELED);
                } else {
                    getActivity().setResult(Activity.RESULT_OK);
                    sTaskDataHelper.addTask(currTask);
                }
                getActivity().finish();
            }
        });

        return v;
    }

}
