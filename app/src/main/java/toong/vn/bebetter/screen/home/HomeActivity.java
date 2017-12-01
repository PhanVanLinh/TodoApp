package toong.vn.bebetter.screen.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import io.reactivex.Completable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.completable.CompletableFromAction;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import toong.vn.bebetter.R;
import toong.vn.bebetter.data.model.Task;
import toong.vn.bebetter.data.source.database.AppDatabase;
import toong.vn.bebetter.screen.BaseActivity;
import toong.vn.bebetter.screen.home.adapter.TaskAdapter;
import toong.vn.bebetter.util.base.BaseRecyclerViewAdapter;

public class HomeActivity extends BaseActivity {
    private RecyclerView mRecyclerViewTask;
    private TaskAdapter mTaskAdapter;
    private AppDatabase mAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAppDatabase = AppDatabase.getInstance(HomeActivity.this);




        mTaskAdapter = new TaskAdapter(this, new BaseRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        getTask().subscribe(new Consumer<List<Task>>() {
//            @Override
//            public void accept(List<Task> tasks) throws Exception {
//                mTaskAdapter.add(tasks);
//                Log.i(TAG, "tasks size: " + tasks.size());
//            }
//        });
        addTask(new Task("1", "2", 3)).subscribeOn(Schedulers.io()).subscribe();
        addTask(new Task("1", "2", 3)).subscribeOn(Schedulers.io()).subscribe();
        getTask();
    }

    private Completable addTask(final Task task) {
        return new CompletableFromAction(new Action() {
            @Override
            public void run() throws Exception {
                Log.i(TAG, "insert task");
                mAppDatabase.taskDao().insertTask(task);
            }
        });
    }

    private void getTask() {
        mAppDatabase.taskDao().getTask().subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Task>>() {
            @Override
            public void accept(List<Task> taskList) throws Exception {
                Log.i(TAG, "tasks size: " + taskList.size());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.float_fab:
                mNavigator.startAddTaskActivity();
                break;
        }
    }

    @Override
    protected void initUI() {
        mRecyclerViewTask = findViewById(R.id.recycler_task);
        mRecyclerViewTask.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void addListener() {
        findViewById(R.id.float_fab).setOnClickListener(this);
    }
}
