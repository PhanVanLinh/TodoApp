package toong.vn.bebetter.data.source;

import android.support.annotation.NonNull;
import io.reactivex.Maybe;
import java.util.List;
import toong.vn.bebetter.data.model.Task;
import toong.vn.bebetter.data.model.TaskDisplay;

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

    Maybe<List<TaskDisplay>> getAllTask();

    Maybe<Double> getBestProgressOf(int taskId);

    Maybe<Double> getPreviousProgressOf(int taskId);

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