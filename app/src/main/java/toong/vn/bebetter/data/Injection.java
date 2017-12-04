package toong.vn.bebetter.data;

import android.content.Context;
import android.support.annotation.NonNull;
import toong.vn.bebetter.data.source.TaskRepository;
import toong.vn.bebetter.data.source.database.AppDatabase;
import toong.vn.bebetter.data.source.local.TaskLocalDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {

    public static TaskRepository provideTasksRepository(@NonNull Context context) {
        checkNotNull(context);
        AppDatabase database = AppDatabase.getInstance(context);
        return TaskRepository.getInstance(
                TaskLocalDataSource.getInstance(database.taskDao(), database.taskEntryDao()));
    }
}