package toong.vn.bebetter.screen.group.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import toong.vn.bebetter.R;
import toong.vn.bebetter.data.model.Group;
import toong.vn.bebetter.util.base.BaseRecyclerViewAdapter;

/**
 * Created by PhanVanLinh on 10/11/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class GroupRecyclerViewAdapter extends BaseRecyclerViewAdapter<Group> {

    protected GroupRecyclerViewAdapter(@NonNull Context context,
            ItemClickListener itemClickListener) {
        super(context, itemClickListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_group, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            Group group = getItem(position);
            ((ItemViewHolder) holder).tvTitle.setText(group.getTitle());
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;
        ImageButton ibDelete;
        ImageButton ibEdit;

        ItemViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.text_title);
            ibDelete = itemView.findViewById(R.id.image_button_delete);
            ibEdit = itemView.findViewById(R.id.image_button_edit);

            ibDelete.setOnClickListener(this);
            ibEdit.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_button_delete:
                    break;
                case R.id.image_button_edit:
                    break;
            }
        }
    }
}
