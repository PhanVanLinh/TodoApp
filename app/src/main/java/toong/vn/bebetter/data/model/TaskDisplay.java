package toong.vn.bebetter.data.model;

/**
 * Created by PhanVanLinh on 12/3/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class TaskDisplay extends Task {

    private double progress;

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
}
