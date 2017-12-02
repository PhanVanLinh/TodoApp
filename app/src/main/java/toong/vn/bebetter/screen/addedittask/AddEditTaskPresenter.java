package toong.vn.bebetter.screen.addedittask;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import toong.vn.bebetter.data.model.Task;
import toong.vn.bebetter.data.source.TaskDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by PhanVanLinh on 08/11/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class AddEditTaskPresenter implements AddEditTaskContract.Presenter,
        TaskDataSource.GetTaskCallback {

    @NonNull
    private final TaskDataSource mTasksRepository;

    @NonNull
    private final AddEditTaskContract.View mAddTaskView;

    @Nullable
    private Integer mTaskId;

    private boolean mIsDataMissing;

    /**
     * Creates a presenter for the add/edit view.
     *
     * @param taskId ID of the task to edit or null for a new task
     * @param tasksRepository a repository of data for tasks
     * @param addTaskView the add/edit view
     * @param shouldLoadDataFromRepo whether data needs to be loaded or not (for config changes)
     */
    public AddEditTaskPresenter(@Nullable Integer taskId, @NonNull TaskDataSource tasksRepository,
            @NonNull AddEditTaskContract.View addTaskView, boolean shouldLoadDataFromRepo) {
        mTaskId = taskId;
        mTasksRepository = checkNotNull(tasksRepository);
        mAddTaskView = checkNotNull(addTaskView);
        mIsDataMissing = shouldLoadDataFromRepo;

        mAddTaskView.setPresenter(this);
    }

    @Override
    public void start() {
        if (!isNewTask() && mIsDataMissing) {
            populateTask();
        }
    }

    @Override
    public void saveTask(String title, String description) {
        if (isNewTask()) {
            createTask(title, description);
        } else {
            updateTask(title, description);
        }
    }

    @Override
    public void populateTask() {
        if (isNewTask()) {
            throw new RuntimeException("populateTask() was called but task is new.");
        }
        mTasksRepository.getTask(mTaskId, this);
    }

    @Override
    public void onTaskLoaded(Task task) {
        // The view may not be able to handle UI updates anymore
        if (mAddTaskView.isActive()) {
            mAddTaskView.setTitle(task.getTitle());
            mAddTaskView.setDescription(task.getDescription());
        }
        mIsDataMissing = false;
    }

    @Override
    public void onDataNotAvailable() {
        // The view may not be able to handle UI updates anymore
        if (mAddTaskView.isActive()) {
            mAddTaskView.showEmptyTaskError();
        }
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    private boolean isNewTask() {
        return mTaskId == null;
    }

    private void createTask(String title, String description) {
//        Task newTask = new Task(title);
//        if (newTask.isEmpty()) {
//            mAddTaskView.showEmptyTaskError();
//        } else {
//            mTasksRepository.saveTask(newTask);
//            mAddTaskView.showTasksList();
//        }
    }

    private void updateTask(String title, String description) {
//        if (isNewTask()) {
//            throw new RuntimeException("updateTask() was called but task is new.");
//        }
//        mTasksRepository.saveTask(new Task(title, description, mTaskId));
//        mAddTaskView.showTasksList(); // After an edit, go back to the list.
    }
}
