package toong.vn.bebetter.screen.addedittask;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.internal.operators.completable.CompletableFromAction;
import io.reactivex.schedulers.Schedulers;
import toong.vn.bebetter.R;
import toong.vn.bebetter.data.model.Task;
import toong.vn.bebetter.data.source.database.AppDatabase;
import toong.vn.bebetter.screen.BaseActivity;
import toong.vn.bebetter.util.SingleToast;
import toong.vn.bebetter.widget.GroupInputView;

public class AddEditTaskActivity extends BaseActivity {
    GroupInputView ipTitle;
    GroupInputView ipUnit;
    GroupInputView ipCurrentBestValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);


    }

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void initUI() {
        ipTitle = findViewById(R.id.input_title);
        ipUnit = findViewById(R.id.input_unit);
        ipCurrentBestValue = findViewById(R.id.input_current_best_value);
    }

    @Override
    protected void addListener() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                addTask();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isValidate() {
        boolean validate = ipTitle.verify();
        validate &= ipUnit.verify();
        validate &= ipCurrentBestValue.verify();
        return validate;
    }

    private void addTask() {
        if (!isValidate()) {
            SingleToast.with(this).showShortToast("dad");
            return;
        }
        Task task = new Task();
        task.setTitle(ipTitle.getText());
        task.setUnit(ipUnit.getText());
        task.setCurrentBestValue(Double.parseDouble(ipCurrentBestValue.getText()));

        addTask(task).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action() {
            @Override
            public void run() throws Exception {
                Snackbar.make(findViewById(android.R.id.content), "Welcome To Main Activity",
                        Snackbar.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private Completable addTask(final Task task) {
        return new CompletableFromAction(new Action() {
            @Override
            public void run() throws Exception {
               long result =  AppDatabase.getInstance(AddEditTaskActivity.this).taskDao().insertTask(task);
                Log.i(TAG, "insert task" + result);
            }
        });
    }

}
