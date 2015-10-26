package me.rikinmarfatia.mintask;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
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

import java.util.ArrayList;

import me.rikinmarfatia.mintask.models.AllTasks;
import me.rikinmarfatia.mintask.models.Task;
import me.rikinmarfatia.mintask.util.ColorStrings;

/**
 * Created by rikin on 10/23/15.
 */
public class NewTaskFragment extends Fragment {

    private Button mEnterTask;
    private EditText mInputTask;
    private Spinner mColorSpinner;
    private ArrayList<Task> tasks;
    private Task currTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasks = AllTasks.getInstance(getActivity()).getTasks();
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

                mColorSpinner.setEnabled(true);
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
                if(currTask == null) {
                    getActivity().setResult(Activity.RESULT_CANCELED);
                } else {
                    getActivity().setResult(Activity.RESULT_OK);
                    tasks.add(currTask);
                }
                getActivity().finish();
            }
        });

        mColorSpinner = (Spinner) v.findViewById(R.id.spinner_color);
        mColorSpinner.setEnabled(false);
        ArrayAdapter<CharSequence> colorAdapter =
                ArrayAdapter.createFromResource(getActivity(), R.array.colors_array,
                        android.R.layout.simple_spinner_item);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mColorSpinner.setAdapter(colorAdapter);
        mColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(currTask != null) {
                    if (parent.getItemAtPosition(position).toString().equals(ColorStrings.WHITE)) {
                        currTask.setColor(R.color.white);
                    } else if (parent.getItemAtPosition(position).toString().equals(ColorStrings.RED)) {
                        currTask.setColor(R.color.red);
                    } else if (parent.getItemAtPosition(position).toString().equals(ColorStrings.GREEN)) {
                        currTask.setColor(R.color.green);
                    } else if (parent.getItemAtPosition(position).toString().equals(ColorStrings.BLUE)) {
                        currTask.setColor(R.color.blue);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // nothing
            }
        });

        return v;
    }

}
