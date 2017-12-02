package toong.vn.bebetter.screen.alltask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.completable.CompletableFromAction;
import io.reactivex.schedulers.Schedulers;
import toong.vn.bebetter.BaseFragment;
import toong.vn.bebetter.R;
import toong.vn.bebetter.data.model.Task;
import toong.vn.bebetter.data.source.database.AppDatabase;
import toong.vn.bebetter.screen.alltask.adapter.TaskAdapter;
import toong.vn.bebetter.util.base.BaseRecyclerViewAdapter;

/**
 * Created by PhanVanLinh on 01/12/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class AllTaskFragment extends BaseFragment {
    public static AllTaskFragment newInstance() {
        Bundle args = new Bundle();
        AllTaskFragment fragment = new AllTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView mRecyclerViewTask;
    private TaskAdapter mTaskAdapter;
    private AppDatabase mAppDatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAppDatabase = AppDatabase.getInstance(getActivity());

        mTaskAdapter = new TaskAdapter(getActivity(), new BaseRecyclerViewAdapter
                .ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_task, null, false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        Task task1 = new Task();
        task1.setTitle("A");
        task1.setDescription("");

        final Task task2 = new Task();
        task2.setTitle("B");

        addTasks(task1,task2).subscribeOn(Schedulers.io()).subscribe(new Action() {
            @Override
            public void run() throws Exception {
                getTask();
            }
        });

    }

    private Completable addTasks(final Task... task) {
        return new CompletableFromAction(new Action() {
            @Override
            public void run() throws Exception {
                Log.i(TAG, "insert task");
                mAppDatabase.taskDao().insertAll(task);
            }
        });
    }


    private void getTask() {
        mAppDatabase.taskDao().getTask().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe(new Consumer<List<Task>>() {
            @Override
            public void accept(List<Task> taskList) throws Exception {
                Log.i(TAG, "tasks size: " + taskList.size());
                mTaskAdapter.set(taskList);
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
    protected void initUI(View rootView) {
        mRecyclerViewTask = rootView.findViewById(R.id.recycler_task);
        mRecyclerViewTask.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewTask.setAdapter(mTaskAdapter);
    }

    @Override
    protected void addListener() {

    }
}
