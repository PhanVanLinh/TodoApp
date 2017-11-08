package toong.vn.bebetter.screen.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import toong.vn.bebetter.R;
import toong.vn.bebetter.screen.BaseActivity;

public class HomeActivity extends BaseActivity {
    FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        AppDatabase.getInstance(this).taskDao().insert();

        findViewById(R.id.float_fab).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.float_fab:
                mNavigator.startAddTaskActivity();
                break;
        }
    }

    @Override
    protected void addListener() {

    }
}
