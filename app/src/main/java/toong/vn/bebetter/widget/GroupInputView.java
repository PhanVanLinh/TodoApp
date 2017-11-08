package toong.vn.bebetter.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import toong.vn.bebetter.R;

public class GroupInputView extends LinearLayout {
    @StringDef({ MyInputType.NUMBER })
    @interface MyInputType {
        String NUMBER = "number";
        String INTEGER = "integer";
    }

    @StringDef({ Rule.NONE_EMPTY })
    @interface Rule {
        String NONE_EMPTY = "none_empty";
    }

    @StringDef({ ImeOptions.NEXT, ImeOptions.DONE })
    @interface ImeOptions {
        String DONE = "done";
        String NEXT = "next";
    }

    private TextView tvTitle;
    private EditText edtInput;
    private TextView tvError;
    private String[] rules;
    private String errorMessage;

    public GroupInputView(Context context) {
        this(context, null);
    }

    public GroupInputView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GroupInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.GroupInputView);
        try {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_group_input_view, this, true);
            tvTitle = (TextView) findViewById(R.id.text_title);
            edtInput = (EditText) findViewById(R.id.edit_input);
            tvError = (TextView) findViewById(R.id.text_error);

            String title = ta.getString(R.styleable.GroupInputView_giv_title);
            String inputHint = ta.getString(R.styleable.GroupInputView_giv_inputHint);
            String inputText = ta.getString(R.styleable.GroupInputView_giv_inputText);
            String inputType = ta.getString(R.styleable.GroupInputView_giv_inputType);
            String imeOptions = ta.getString(R.styleable.GroupInputView_giv_imeOptions);
            int maxLength = ta.getInt(R.styleable.GroupInputView_giv_input_maxLength, -1);
            boolean inputEnable = ta.getBoolean(R.styleable.GroupInputView_giv_input_enable, true);
            int inputLines = ta.getInt(R.styleable.GroupInputView_giv_input_lines, 0);
            int inputSize = ta.getDimensionPixelSize(R.styleable.GroupInputView_giv_input_size,
                    (int) (edtInput.getTextSize()));

            configTextLine(inputLines);
            edtInput.setFocusable(inputEnable);

            String ruleString = ta.getString(R.styleable.GroupInputView_giv_rule);
            if (ruleString != null) {
                rules = ruleString.split(",");
            }

            tvTitle.setText(title);
            edtInput.setText(inputText);
            edtInput.setHint(inputHint);
            edtInput.setTextSize(TypedValue.COMPLEX_UNIT_PX, inputSize);

            configInputType(inputType);
            configMaxLength(maxLength);
            configImeOptions(imeOptions);
        } finally {
            ta.recycle();
        }

        edtInput.addTextChangedListener(new ValidateTextWatcher());
    }

    private void configTextLine(int inputLines) {
        if (inputLines > 0) {
            edtInput.setGravity(Gravity.TOP);
            edtInput.setLines(inputLines);
        }
    }

    private void configInputType(String inputType) {
        if (inputType == null || inputType.isEmpty()) {
            return;
        }
        if (inputType.equals(MyInputType.NUMBER)) {
            edtInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            return;
        }
        if (inputType.equals(MyInputType.INTEGER)) {
            edtInput.setInputType(InputType.TYPE_CLASS_NUMBER);
            return;
        }
    }

    private void configImeOptions(String imeOptions) {
        if (imeOptions == null || imeOptions.isEmpty()) {
            return;
        }
        if (imeOptions.equals(ImeOptions.DONE)) {
            edtInput.setImeOptions(EditorInfo.IME_ACTION_DONE);
        }
        if (imeOptions.equals(ImeOptions.NEXT)) {
            // remember to set lines =1 when use action next
            edtInput.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        }
    }

    private void configMaxLength(int maxLength) {
        if (maxLength != -1) {
            edtInput.setFilters(new InputFilter[] { new InputFilter.LengthFilter(maxLength) });
        }
    }

    public String getText() {
        return edtInput.getText().toString();
    }

    public void setText(String text) {
        edtInput.setText(text);
    }

    public String getErrorMessage() {
        return tvError.getText().toString();
    }

    public void addInputClick(View.OnClickListener onClickListener) {
        edtInput.setOnClickListener(onClickListener);
    }

    private class ValidateTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            verify();
        }
    }

    public boolean verify() {
        if (rules == null || rules.length == 0) {
            return true;
        }
        for (String rule : rules) {
            if (rule.equals(Rule.NONE_EMPTY)) {
                if (validateEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validateEmpty() {
        if (edtInput.getText().toString().isEmpty()) {
            errorMessage = getContext().getString(R.string.msg_error_none_empty);
            tvError.setText(errorMessage);
            tvError.setVisibility(VISIBLE);
            edtInput.requestFocus();
            return true;
        }
        tvError.setVisibility(GONE);
        return false;
    }
}