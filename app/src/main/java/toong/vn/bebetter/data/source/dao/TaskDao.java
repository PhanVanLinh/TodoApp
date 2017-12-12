package toong.vn.bebetter.data.source.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.List;
import toong.vn.bebetter.data.model.Task;
import toong.vn.bebetter.data.model.TaskDisplay;
import toong.vn.bebetter.data.model.User;

/**
 * Created by PhanVanLinh on 08/11/2017.
 * phanvanlinh.94vn@gmail.com
 */

@Dao
public interface TaskDao {

    @Query("SELECT task.*, taskEntry.progress as progress FROM task LEFT JOIN taskEntry ON task.id=taskEntry.taskId and taskEntry.date=:today")
    Maybe<List<TaskDisplay>> getAll(String today);

    @Query("SELECT * FROM task")
    Single<List<Task>> getTask();

    @Query("SELECT * FROM task WHERE id IN (:userIds)")
    List<Task> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(Task... users);

    @Insert
    long insertTask(Task task);

    @Delete
    void delete(User user);
}
