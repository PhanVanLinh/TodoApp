package toong.vn.bebetter.data.source;

import android.support.annotation.NonNull;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import toong.vn.bebetter.data.model.Task;
import toong.vn.bebetter.data.model.TaskDisplay;

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
    public Maybe<List<TaskDisplay>> getAllTask() {
        return mTaskLocalDataSource.getAllTask()
                .flatMap(new Function<List<TaskDisplay>, MaybeSource<List<TaskDisplay>>>() {
                    @Override
                    public MaybeSource<List<TaskDisplay>> apply(List<TaskDisplay> taskDisplays)
                            throws Exception {
                        return get(taskDisplays);
                    }
                });
    }

    private Maybe<List<TaskDisplay>> get(final List<TaskDisplay> taskDisplays) {
        List<MaybeSource<Object>> maybeList = new ArrayList<>();
        for (final TaskDisplay taskDisplay : taskDisplays) {

            MaybeSource<Object> s = getPreviousProgressOf(taskDisplay.getId()).zipWith(
                    getBestProgressOf(taskDisplay.getId()),
                    new BiFunction<Double, Double, Object>() {
                        @Override
                        public Object apply(Double aDouble, Double aDouble2) throws Exception {
                            taskDisplay.setPreviousProgress(aDouble);
                            taskDisplay.setBestProgress(aDouble2);
                            return aDouble; // can return any value, cannot return null because it will throws exceptions
                        }
                    });
            maybeList.add(s);
        }

        return Maybe.zip(maybeList, new Function<Object[], List<TaskDisplay>>() {
            @Override
            public List<TaskDisplay> apply(Object[] objects) throws Exception {
                return taskDisplays;
            }
        });
    }

    @Override
    public Maybe<Double> getBestProgressOf(int taskId) {
        return mTaskLocalDataSource.getBestProgressOf(taskId);
    }

    @Override
    public Maybe<Double> getPreviousProgressOf(int taskId) {
        return mTaskLocalDataSource.getPreviousProgressOf(taskId);
    }
}