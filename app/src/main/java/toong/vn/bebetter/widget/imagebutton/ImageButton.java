package toong.vn.bebetter.widget.imagebutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import toong.vn.bebetter.R;

public class ImageButton extends LinearLayout {
    private static final float DEFAULT_DRAWABLE_SIZE = 50;
    private static final int DEFAULT_TITLE_COLOR = Color.BLACK;
    private static final int DEFAULT_TITLE_SIZE = 12;
    private static final int DEFAULT_CORNER = 0;
    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final int DEFAULT_BORDER_COLOR = Color.RED;

    private final static float DEFAULT_ALPHA = 1f;
    private final static float DEFAULT_ALPHA_WHEN_PRESS = 0.6f;
    private final static float DEFAULT_ALPHA_WHEN_DISABLE = 0.5f;
    private ImageView ivLeft;
    private TextView tvTitle;

    private int backgroundColor;
    private int disableBackgroundColor;

    private int borderWidth;
    private int borderColor;
    private int disableBorderColor;

    private GradientDrawable gradientDrawable;

    public ImageButton(Context context) {
        this(context, null);
    }

    public ImageButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ImageButton);
        try {

            LayoutInflater.from(getContext()).inflate(R.layout.button_image_with_text, this, true);
            ivLeft = (ImageView) findViewById(R.id.image_left);
            tvTitle = (TextView) findViewById(R.id.text_title);

            displayTitle(ta);
            displayLeftDrawable(ta);
            displayBackgroundAndBorder(ta);
        } finally {
            ta.recycle();
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            setEnableBackgroundAndBorder();
        } else {
            setDisableBackgroundAndBorder();
        }
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if (pressed) {
            setAlpha(DEFAULT_ALPHA_WHEN_PRESS);
        } else {
            setAlpha(DEFAULT_ALPHA);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d("TouchTest", "Touch down");
            setPressed(true);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.d("TouchTest", "Touch up");
            setPressed(false);
            float x = event.getX();
            float y = event.getY();
            boolean isInside = (x > 0 && x < getWidth() && y > 0 && y < getHeight());
            if (isInside) {
                performClick();
            }
        } else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
            setPressed(false);
        }
        return true;
    }

    private void displayLeftDrawable(TypedArray ta) {
        Drawable leftDrawable = ta.getDrawable(R.styleable.ImageButton_ib_leftDrawable);
        float drawableWidth =
                ta.getDimension(R.styleable.ImageButton_ib_drawableWidth, DEFAULT_DRAWABLE_SIZE);
        float drawableHeight =
                ta.getDimension(R.styleable.ImageButton_ib_drawableHeight, DEFAULT_DRAWABLE_SIZE);

        if (leftDrawable != null) {
            ivLeft.setImageDrawable(leftDrawable);
        }
        ivLeft.getLayoutParams().width = (int) drawableWidth;
        ivLeft.getLayoutParams().height = (int) drawableHeight;
    }

    private void displayTitle(TypedArray ta) {
        String title = ta.getString(R.styleable.ImageButton_ib_title);
        int titleColor = ta.getColor(R.styleable.ImageButton_ib_titleColor, DEFAULT_TITLE_COLOR);
        int titleSize =
                ta.getDimensionPixelSize(R.styleable.ImageButton_ib_titleSize, DEFAULT_TITLE_SIZE);

        tvTitle.setText(title);
        tvTitle.setTextColor(titleColor);
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
    }

    private void displayBackgroundAndBorder(TypedArray ta) {
        backgroundColor =
                ta.getColor(R.styleable.ImageButton_ib_backgroundColor, DEFAULT_TITLE_COLOR);
        disableBackgroundColor = ta.getColor(R.styleable.ImageButton_ib_disableBackgroundColor, -1);

        borderColor = ta.getColor(R.styleable.ImageButton_ib_borderColor, DEFAULT_BORDER_COLOR);
        disableBorderColor = ta.getColor(R.styleable.ImageButton_ib_disableBorderColor, -1);

        float corner = ta.getDimension(R.styleable.ImageButton_ib_corner, DEFAULT_CORNER);
        borderWidth =
                (int) ta.getDimension(R.styleable.ImageButton_ib_borderWidth, DEFAULT_BORDER_WIDTH);

        gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(corner);
        setEnableBackgroundAndBorder();
    }

    private void setEnableBackgroundAndBorder() {
        gradientDrawable.setColor(backgroundColor);
        gradientDrawable.setStroke(borderWidth, borderColor);
        this.setBackground(gradientDrawable);
        setAlpha(DEFAULT_ALPHA);
    }

    private void setDisableBackgroundAndBorder() {
        if (disableBackgroundColor != -1) {
            gradientDrawable.setColor(disableBackgroundColor);
        }
        if (disableBorderColor != -1) {
            gradientDrawable.setStroke(borderWidth, disableBorderColor);
        }
        this.setBackground(gradientDrawable);
        setAlpha(DEFAULT_ALPHA_WHEN_DISABLE);
    }

    public TextView getTvTitle() {
        return tvTitle;
    }
}
