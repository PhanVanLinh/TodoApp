package toong.vn.bebetter.screen.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import toong.vn.bebetter.R;
import toong.vn.bebetter.data.model.Task;
import toong.vn.bebetter.util.base.BaseRecyclerViewAdapter;

/**
 * Created by PhanVanLinh on 15/11/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class TaskAdapter extends BaseRecyclerViewAdapter<Task> {

    public TaskAdapter(@NonNull Context context, ItemClickListener itemClickListener) {
        super(context, itemClickListener);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_task, parent, false);
        return new ItemViewHolder(view, mItemClickListener);
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
        private ItemClickListener mItemClickListener;

        ItemViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.text_title);
            mItemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
}
