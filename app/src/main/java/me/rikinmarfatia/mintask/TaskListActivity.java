package me.rikinmarfatia.mintask;

import android.app.Fragment;

public class TaskListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new TaskListFragment();
    }

}
