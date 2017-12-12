package toong.vn.bebetter.data.model;

import android.arch.persistence.room.Ignore;

/**
 * Created by PhanVanLinh on 12/3/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class TaskDisplay extends Task {
    private double progress;

    @Ignore
    private double yesterdayProgress;

    @Ignore
    private double bestProgress;

    public void increaseProgress() {
        progress++;
    }

    public void decreaseProgress() {
        progress--;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public double getProgress() {
        return progress;
    }

    public String getDisplayProgress() {
        return "" + progress;
    }

    public double getYesterdayProgress() {
        return yesterdayProgress;
    }

    public void setYesterdayProgress(double yesterdayProgress) {
        this.yesterdayProgress = yesterdayProgress;
    }

    public double getBestProgress() {
        return bestProgress;
    }

    public void setBestProgress(double bestProgress) {
        this.bestProgress = bestProgress;
    }
}
