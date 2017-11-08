package toong.vn.bebetter.data.source.local;

import android.support.annotation.NonNull;
import toong.vn.bebetter.data.model.Task;
import toong.vn.bebetter.data.source.TaskDataSource;
import toong.vn.bebetter.data.source.dao.TaskDao;

/**
 * Created by PhanVanLinh on 08/11/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class TaskLocalDataSource implements TaskDataSource {
    private static volatile TaskLocalDataSource INSTANCE;
    private TaskDao mTaskDao;

    private TaskLocalDataSource(@NonNull TaskDao TaskDao) {
        mTaskDao = TaskDao;
    }

    public static TaskLocalDataSource getInstance(@NonNull TaskDao TaskDao) {
        if (INSTANCE == null) {
            synchronized (TaskLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TaskLocalDataSource(TaskDao);
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
}
