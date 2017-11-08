package toong.vn.bebetter.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by PhanVanLinh on 08/11/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class SingleToast {
    private Toast mToast;
    private Context mContext;
    private static SingleToast INSTANCE;

    private SingleToast(Context context){
        mContext = context;
    }

    public static synchronized SingleToast with(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SingleToast(context.getApplicationContext());
        }
        return INSTANCE;
    }

    public void showShortToast(String message) {
        if (mToast != null && mToast.getView().isShown()) {
            mToast.cancel();
        }
        mToast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
        mToast.show();
    }
}
