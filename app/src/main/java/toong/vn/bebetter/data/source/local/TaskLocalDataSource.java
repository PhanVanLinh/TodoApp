package toong.vn.bebetter.data.source.local;

import android.support.annotation.NonNull;
import io.reactivex.Maybe;
import java.util.List;
import toong.vn.bebetter.data.model.Task;
import toong.vn.bebetter.data.model.TaskDisplay;
import toong.vn.bebetter.data.source.TaskDataSource;
import toong.vn.bebetter.data.source.dao.TaskDao;
import toong.vn.bebetter.data.source.dao.TaskEntryDao;
import toong.vn.bebetter.util.DateTimeUtil;

/**
 * Created by PhanVanLinh on 08/11/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class TaskLocalDataSource implements TaskDataSource {
    private static volatile TaskLocalDataSource INSTANCE;
    private TaskDao mTaskDao;
    private TaskEntryDao mTaskEntryDao;

    private TaskLocalDataSource(@NonNull TaskDao taskDao, @NonNull TaskEntryDao taskEntryDao) {
        mTaskDao = taskDao;
        mTaskEntryDao = taskEntryDao;
    }

    public static TaskLocalDataSource getInstance(@NonNull TaskDao taskDao,
            @NonNull TaskEntryDao taskEntryDao) {
        if (INSTANCE == null) {
            synchronized (TaskLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TaskLocalDataSource(taskDao, taskEntryDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void saveTask(@NonNull Task task) {
        mTaskDao.insertAll(task);
    }

    @Override
    public void getTask(@NonNull Integer taskId, @NonNull GetTaskCallback callback) {

    }

    @Override
    public Maybe<List<TaskDisplay>> getAllTask() {
        return mTaskDao.getAll(DateTimeUtil.getTodayInString());
    }

    @Override
    public Maybe<Double> getBestProgressOf(final int taskId) {
        return mTaskEntryDao.getBestProgressOf(taskId);
    }

    @Override
    public Maybe<Double> getYesterdayProgressOf(final int taskId) {
        return mTaskEntryDao.getYesterdayProgressOf(taskId, DateTimeUtil.getYesterdayInString());
    }
}
