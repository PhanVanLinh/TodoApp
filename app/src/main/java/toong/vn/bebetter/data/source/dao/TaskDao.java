package toong.vn.bebetter.data.source.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import io.reactivex.Flowable;
import java.util.List;
import toong.vn.bebetter.data.model.Task;
import toong.vn.bebetter.data.model.User;

/**
 * Created by PhanVanLinh on 08/11/2017.
 * phanvanlinh.94vn@gmail.com
 */

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM task LIMIT 2")
    Flowable<List<Task>> getTask();

    @Query("SELECT * FROM task WHERE id IN (:userIds)")
    List<Task> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(Task... users);

    /**
     * Insert a task in the database. If the task already exists, replace it.
     *
     * @param task the task to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Task task);

    @Delete
    void delete(User user);
}
