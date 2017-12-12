package toong.vn.bebetter.screen.alltask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.reactivex.Completable;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.internal.operators.completable.CompletableFromAction;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import toong.vn.bebetter.BaseFragment;
import toong.vn.bebetter.R;
import toong.vn.bebetter.data.Injection;
import toong.vn.bebetter.data.model.Task;
import toong.vn.bebetter.data.model.TaskDisplay;
import toong.vn.bebetter.data.model.TaskEntry;
import toong.vn.bebetter.data.source.TaskRepository;
import toong.vn.bebetter.data.source.database.AppDatabase;
import toong.vn.bebetter.screen.alltask.adapter.TaskAdapter;
import toong.vn.bebetter.util.DateTimeUtil;

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
    private FloatingActionButton mButtonAdd;

    private TaskRepository mTaskRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppDatabase = AppDatabase.getInstance(getActivity());
        mTaskRepository = Injection.provideTasksRepository(getActivity());

        mTaskAdapter = new TaskAdapter(getActivity(), new TaskAdapter.TaskListener() {
            @Override
            public void onAddClicked(TaskDisplay taskDisplay) {
                handleTaskEntry(taskDisplay);
            }

            @Override
            public void onMinusClicked(TaskDisplay taskDisplay) {
                handleTaskEntry(taskDisplay);
            }

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

        //        Task task1 = new Task();
        //        task1.setTitle("A");
        //        task1.setDescription("");
        //
        //        final Task task2 = new Task();
        //        task2.setTitle("B");

        //        addTasks(task1,task2).subscribeOn(Schedulers.io()).subscribe(new Action() {
        //            @Override
        //            public void run() throws Exception {
        //                getAllTask();
        //            }
        //        });

        getAllTask();
    }

    private Completable addTasks(final Task... task) {
        return new CompletableFromAction(new Action() {
            @Override
            public void run() throws Exception {
                Log.i(TAG, "insert task ");
                mAppDatabase.taskDao().insertAll(task);
            }
        });
    }

    private void getAllTask() {
        mTaskRepository.getAllTask()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MaybeObserver<List<TaskDisplay>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<TaskDisplay> taskDisplays) {
                        mTaskAdapter.add(taskDisplays);
                        mTaskAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                });
    }

    private Completable insertTaskEntry(final TaskEntry entryEntry) {
        return new CompletableFromAction(new Action() {
            @Override
            public void run() throws Exception {
                Log.i(TAG, "insert entryEntry ");
                mAppDatabase.taskEntryDao().insertTaskEntry(entryEntry);
            }
        });
    }

    private Completable updateTaskEntry(final TaskEntry taskEntry) {
        return new CompletableFromAction(new Action() {
            @Override
            public void run() throws Exception {
                Log.i(TAG, "update entryEntry ");
                mAppDatabase.taskEntryDao()
                        .update(taskEntry.getProgress(), taskEntry.getDate(),
                                taskEntry.getTaskId());
            }
        });
    }

    private Completable deleteTaskEntry(final TaskEntry taskEntry) {
        return new CompletableFromAction(new Action() {
            @Override
            public void run() throws Exception {
                Log.i(TAG, "delete entryEntry ");
                mAppDatabase.taskEntryDao().delete(taskEntry.getDate(), taskEntry.getTaskId());
            }
        });
    }

    private TaskEntry getTaskEntry(TaskDisplay taskDisplay) {
        TaskEntry taskEntry = new TaskEntry();
        taskEntry.setProgress(taskDisplay.getProgress());
        taskEntry.setTaskId(taskDisplay.getId());
        taskEntry.setDate(DateTimeUtil.getTodayInString());
        return taskEntry;
    }

    private void handleTaskEntry(TaskDisplay taskDisplay) {
        TaskEntry taskEntry = getTaskEntry(taskDisplay);
        if (taskDisplay.getProgress() == 1 || taskEntry.getProgress() == -1) {
            insertTaskEntry(taskEntry).subscribeOn(Schedulers.io()).subscribe();
        } else if (taskDisplay.getProgress() == 0) {
            deleteTaskEntry(taskEntry).subscribeOn(Schedulers.io()).subscribe();
        } else {
            updateTaskEntry(taskEntry).subscribeOn(Schedulers.io()).subscribe();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add:
                mNavigator.startAddTaskActivity();
                break;
        }
    }

    @Override
    protected void initUI(View rootView) {
        mRecyclerViewTask = rootView.findViewById(R.id.recycler_task);
        mRecyclerViewTask.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewTask.addItemDecoration(
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerViewTask.setAdapter(mTaskAdapter);

        mButtonAdd = rootView.findViewById(R.id.button_add);
        mButtonAdd.setOnClickListener(this);
    }

    @Override
    protected void addListener() {

    }
}
