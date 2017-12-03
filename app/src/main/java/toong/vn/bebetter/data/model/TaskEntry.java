package toong.vn.bebetter.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by PhanVanLinh on 12/3/2017.
 * phanvanlinh.94vn@gmail.com
 */

@Entity(foreignKeys = @ForeignKey(entity = Task.class,
        parentColumns = "id",
        childColumns = "taskId"))
public class TaskEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "taskId")
    private int taskId;
    @ColumnInfo(name = "progress")
    private double progress;
    @ColumnInfo(name = "date")
    private String date;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
