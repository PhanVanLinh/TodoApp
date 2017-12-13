package toong.vn.bebetter.data.model;

import android.arch.persistence.room.Ignore;

/**
 * Created by PhanVanLinh on 12/3/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class TaskDisplay extends Task {
    private double progress;

    @Ignore
    private double previousProgress;

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

    public double getPreviousProgress() {
        return previousProgress;
    }

    public void setPreviousProgress(double previousProgress) {
        this.previousProgress = previousProgress;
    }

    public double getBestProgress() {
        return bestProgress;
    }

    public void setBestProgress(double bestProgress) {
        this.bestProgress = bestProgress;
    }
}
