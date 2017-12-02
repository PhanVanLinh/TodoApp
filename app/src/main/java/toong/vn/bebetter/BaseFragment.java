package toong.vn.bebetter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import toong.vn.bebetter.screen.Navigator;

/**
 * Created by PhanVanLinh on 01/12/2017.
 * phanvanlinh.94vn@gmail.com
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected final String TAG = getClass().getSimpleName();
    protected Navigator mNavigator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNavigator = new Navigator(getActivity());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    protected abstract void initUI(View rootView);

    protected abstract void addListener();
}
