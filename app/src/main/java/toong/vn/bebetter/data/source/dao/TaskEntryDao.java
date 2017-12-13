package toong.vn.bebetter.data.source.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import toong.vn.bebetter.data.model.TaskEntry;

/**
 * Created by PhanVanLinh on 12/3/2017.
 * phanvanlinh.94vn@gmail.com
 */

@Dao
public interface TaskEntryDao {

    @Insert
    long insertTaskEntry(TaskEntry taskEntry);

    @Query("UPDATE taskEntry SET progress = :progress WHERE date = :date AND taskId = :taskId")
    void update(double progress, String date, int taskId);

    @Query("DELETE FROM taskEntry WhERE date = :date AND taskId = :taskId")
    void delete(String date, int taskId);

    @Query("SELECT MAX(progress) FROM taskEntry WHERE taskId=:taskId")
    double getBestProgressOf(int taskId);

    @Query("SELECT MAX(progress) FROM taskEntry WHERE taskId=:taskId and date=:yesterday")
    double getYesterdayProgressOf(int taskId, String yesterday);
}
