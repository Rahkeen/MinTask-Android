package me.rikinmarfatia.mintask;

import android.app.Fragment;

public class NewTaskActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new NewTaskFragment();
    }
}
