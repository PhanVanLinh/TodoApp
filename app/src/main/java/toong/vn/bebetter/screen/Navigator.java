package toong.vn.bebetter.screen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import toong.vn.bebetter.screen.addedittask.AddEditTaskActivity;
import toong.vn.bebetter.util.Extra;

/**
 * Created by PhanVanLinh on 08/11/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class Navigator {
    private Context mContext;

    public Navigator(Context context) {
        mContext = context;
    }

    public void startActivity(@NonNull Class<? extends Activity> clazz) {
        startActivity(new Intent(mContext, clazz));
    }

    public void startActivity(@NonNull Class<? extends Activity> clazz, Bundle args) {
        Intent intent = new Intent(mContext, clazz);
        intent.putExtra(Extra.EXTRA_ARGS, args);
        startActivity(intent);
    }

    private void startActivity(Intent intent) {
        mContext.startActivity(intent);
    }

    public void startAddTaskActivity() {
        startActivity(AddEditTaskActivity.class);
    }
}
