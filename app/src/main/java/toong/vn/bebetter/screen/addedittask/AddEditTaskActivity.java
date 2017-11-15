package toong.vn.bebetter.screen.addedittask;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import toong.vn.bebetter.R;
import toong.vn.bebetter.data.model.Task;
import toong.vn.bebetter.screen.BaseActivity;
import toong.vn.bebetter.util.SingleToast;
import toong.vn.bebetter.widget.GroupInputView;

public class AddEditTaskActivity extends BaseActivity {

    GroupInputView ipTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);

        ipTitle = findViewById(R.id.input_title);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void initUI() {

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
        return validate;
    }

    private void addTask() {
        if (!isValidate()) {
            SingleToast.with(this).showShortToast("dad");
            return;
        }
        Task task = new Task(ipTitle.getText());
        task.setTitle(ipTitle.getText());
//        addTask(task).subscribe();
        Snackbar.make(findViewById(android.R.id.content), "Welcome To Main Activity",
                Snackbar.LENGTH_LONG).show();
    }

//    private Observable<Void> addTask(final Task task) {
//        return Observable.create(new ObservableOnSubscribe<Void>() {
//            @Override
//            public void subscribe(ObservableEmitter<Void> e) throws Exception {
//                AppDatabase.getInstance(AddEditTaskActivity.this).taskDao().insertTask(task);
//            }
//        }).subscribeOn(Schedulers.io());
//    }
}
