package toong.vn.bebetter.screen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by PhanVanLinh on 08/11/2017.
 * phanvanlinh.94vn@gmail.com
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    protected String TAG = getClass().getSimpleName();
    protected Navigator mNavigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNavigator = new Navigator(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initUI();
        addListener();
    }

    protected abstract void initUI();

    protected abstract void addListener();
}
