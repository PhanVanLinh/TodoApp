package toong.vn.bebetter.data.source;

import android.support.annotation.NonNull;
import io.reactivex.Single;
import java.util.List;
import toong.vn.bebetter.data.model.Task;

public interface TaskDataSource {
    interface LoadTasksCallback {

        void onTasksLoaded(List<Task> tasks);

        void onDataNotAvailable();

    }

    interface GetTaskCallback {

        void onTaskLoaded(Task task);

        void onDataNotAvailable();
    }

    void saveTask(@NonNull Task task);

    void getTask(@NonNull Integer taskId, @NonNull GetTaskCallback callback);

    Single<Double> getBestProgressOf(int taskId);

    Single<Double> getYesterdayProgressOf(int taskId);

//
//    void saveTask(@NonNull Task task);
//
//    void completeTask(@NonNull Task task);
//
//    void completeTask(@NonNull String taskId);
//
//    void activateTask(@NonNull Task task);
//
//    void activateTask(@NonNull String taskId);
//
//    void clearCompletedTasks();
//
//    void refreshTasks();
//
//    void deleteAllTasks();
//
//    void deleteTask(@NonNull String taskId);
}