package toong.vn.bebetter.widget.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import toong.vn.bebetter.R;

public class ItemMenuView extends LinearLayout {

    private ImageView imgImage;
    private TextView tvTitle;
    private TextView tvStatus;

    public ItemMenuView(Context context) {
        this(context, null);
    }

    public ItemMenuView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemMenuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.item_menu, this, true);

        imgImage = findViewById(R.id.image);
        tvTitle = findViewById(R.id.text_title);
        tvStatus = findViewById(R.id.text_status);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ItemMenuViewStyle);
        try {
            String title = ta.getString(R.styleable.ItemMenuViewStyle_im_title);
            String status = ta.getString(R.styleable.ItemMenuViewStyle_im_status);
            Drawable drawable = ta.getDrawable(R.styleable.ItemMenuViewStyle_im_image);

            if (drawable != null) {
                imgImage.setBackground(drawable);
            } else {
                imgImage.setVisibility(View.GONE);
            }
            tvTitle.setText(title);
            setStatus(status);
        } finally {
            ta.recycle();
        }
    }

    public void setStatus(String status) {
        tvStatus.setText(status);
    }

    public String getStatus() {
        return tvStatus.getText().toString();
    }
}