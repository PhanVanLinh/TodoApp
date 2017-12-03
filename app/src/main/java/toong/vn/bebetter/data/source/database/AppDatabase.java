package toong.vn.bebetter.data.source.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import toong.vn.bebetter.data.model.TaskEntry;
import toong.vn.bebetter.data.source.dao.TaskDao;
import toong.vn.bebetter.data.source.dao.TaskEntryDao;
import toong.vn.bebetter.data.source.dao.UserDao;
import toong.vn.bebetter.data.model.Task;
import toong.vn.bebetter.data.model.User;

@Database(entities = { User.class, Task.class, TaskEntry.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract TaskDao taskDao();
    public abstract TaskEntryDao taskEntryDao();

    private static AppDatabase INSTANCE;

    public synchronized static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                                    "be-better-database").build();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}