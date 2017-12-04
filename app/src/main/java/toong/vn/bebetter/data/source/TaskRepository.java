package toong.vn.bebetter.data.source;

import android.support.annotation.NonNull;
import io.reactivex.Single;
import toong.vn.bebetter.data.model.Task;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by PhanVanLinh on 08/11/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class TaskRepository implements TaskDataSource {
    private static TaskRepository INSTANCE = null;
    private final TaskDataSource mTaskLocalDataSource;

    private TaskRepository(@NonNull TaskDataSource taskLocalDataSource) {
        mTaskLocalDataSource = checkNotNull(taskLocalDataSource);
    }

    public static TaskRepository getInstance(TaskDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TaskRepository(tasksLocalDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void saveTask(@NonNull Task task) {
        mTaskLocalDataSource.saveTask(task);
    }

    @Override
    public void getTask(@NonNull Integer taskId, @NonNull GetTaskCallback callback) {

    }

    @Override
    public Single<Double> getBestProgressOf(int taskId) {
        return mTaskLocalDataSource.getBestProgressOf(taskId);
    }

    @Override
    public Single<Double> getYesterdayProgressOf(int taskId) {
        return mTaskLocalDataSource.getYesterdayProgressOf(taskId);
    }
}