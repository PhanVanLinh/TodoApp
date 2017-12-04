package toong.vn.bebetter.data.source.local;

import android.support.annotation.NonNull;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import toong.vn.bebetter.data.model.Task;
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

    private TaskLocalDataSource(@NonNull TaskDao TaskDao) {
        mTaskDao = TaskDao;
    }

    public static TaskLocalDataSource getInstance(@NonNull TaskDao TaskDao,
            @NonNull TaskEntryDao taskEntryDao) {
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

    @Override
    public Single<Double> getBestProgressOf(final int taskId) {
        return Single.create(new SingleOnSubscribe<Double>() {
            @Override
            public void subscribe(SingleEmitter<Double> e) throws Exception {
                e.onSuccess(mTaskEntryDao.getBestProgressOf(taskId));
            }
        });
    }

    @Override
    public Single<Double> getYesterdayProgressOf(final int taskId) {
        return Single.create(new SingleOnSubscribe<Double>() {
            @Override
            public void subscribe(SingleEmitter<Double> e) throws Exception {
                e.onSuccess(mTaskEntryDao.getYesterdayProgressOf(taskId,
                        DateTimeUtil.getYesterDateInString()));
            }
        });
    }
}
