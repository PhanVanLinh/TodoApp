package toong.vn.bebetter.screen.alltask.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import toong.vn.bebetter.R;
import toong.vn.bebetter.data.model.TaskDisplay;
import toong.vn.bebetter.util.base.BaseRecyclerViewAdapter;

/**
 * Created by PhanVanLinh on 15/11/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class TaskAdapter extends BaseRecyclerViewAdapter<TaskDisplay> {
    private TaskListener mTaskListener;

    public TaskAdapter(@NonNull Context context, TaskListener taskListener) {
        super(context, taskListener);
        mTaskListener = taskListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_task, parent, false);
        return new ItemViewHolder(view, mTaskListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TaskDisplay task = getItem(position);
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).bind(task);
        }
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView tvTitle;
        private TextView tvPreviousProgress;
        private TextView tvProgress;
        private TextView tvBestProgress;
        private ImageView buttonAdd;
        private ImageView buttonMinus;

        private TaskDisplay mTaskDisplay;
        private TaskListener mTaskListener;

        ItemViewHolder(View itemView, TaskListener taskListener) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.text_title);
            tvProgress = itemView.findViewById(R.id.text_progress);
            tvPreviousProgress = itemView.findViewById(R.id.text_previous_progress);
            tvBestProgress = itemView.findViewById(R.id.text_best_progress);
            buttonAdd = itemView.findViewById(R.id.button_add);
            buttonMinus = itemView.findViewById(R.id.button_minus);
            mTaskListener = taskListener;

            buttonAdd.setOnClickListener(this);
            buttonMinus.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        void bind(TaskDisplay taskDisplay) {
            mTaskDisplay = taskDisplay;
            tvTitle.setText(taskDisplay.getTitle());
            tvPreviousProgress.setText(String.valueOf(mTaskDisplay.getPreviousProgress()));
            tvBestProgress.setText(String.valueOf(mTaskDisplay.getBestProgress()));
            bindProgress();
        }

        private void bindProgress() {
            tvProgress.setText(mTaskDisplay.getDisplayProgress());
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.button_add) {
                mTaskDisplay.increaseProgress();
                mTaskListener.onAddClicked(mTaskDisplay);
                bindProgress();
                return;
            }
            if (view.getId() == R.id.button_minus) {
                mTaskDisplay.decreaseProgress();
                mTaskListener.onMinusClicked(mTaskDisplay);
                bindProgress();
                return;
            }
            mTaskListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface TaskListener extends ItemClickListener {
        void onAddClicked(TaskDisplay taskDisplay);

        void onMinusClicked(TaskDisplay taskDisplay);
    }
}
