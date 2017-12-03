package toong.vn.bebetter.screen.alltask.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import toong.vn.bebetter.R;
import toong.vn.bebetter.data.model.Task;
import toong.vn.bebetter.util.base.BaseRecyclerViewAdapter;

/**
 * Created by PhanVanLinh on 15/11/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class TaskAdapter extends BaseRecyclerViewAdapter<Task> {
    private TaskListener mTaskListener;

    public TaskAdapter(@NonNull Context context, TaskListener mTaskListener) {
        super(context, mTaskListener);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_task, parent, false);
        return new ItemViewHolder(view, mTaskListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Task task = getItem(position);
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).tvTitle.setText(task.getTitle());
        }
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView tvTitle;
        private ImageView buttonAdd;
        private ImageView buttonMinus;

        private TaskListener mTaskListener;

        ItemViewHolder(View itemView, TaskListener taskListener) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.text_title);
            buttonAdd = itemView.findViewById(R.id.button_add);
            buttonMinus = itemView.findViewById(R.id.button_minus);
            mTaskListener = taskListener;

            buttonAdd.setOnClickListener(this);
            buttonMinus.setOnClickListener(this);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.button_add) {
                mTaskListener.onAddClicked(getAdapterPosition());
                return;
            }
            if (view.getId() == R.id.button_minus) {
                mTaskListener.onMinusClicked(getAdapterPosition());
                return;
            }
            mTaskListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface TaskListener extends ItemClickListener {
        void onAddClicked(int position);

        void onMinusClicked(int position);
    }
}
