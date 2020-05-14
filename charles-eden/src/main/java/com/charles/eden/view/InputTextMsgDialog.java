package com.charles.eden.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.charles.eden.R;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2020-05-13 14:34
 */
public class InputTextMsgDialog extends AppCompatDialog {

    private Context mContext;
    private InputMethodManager imm;
    private ScrollViewEditText messageTextView;
    private TextView confirmBtn;
    private KeyboardLayout rlDlg;
    private int mLastDiff = 0;

    private int maxNumber = 150;

    public interface OnTextSendListener {
        void onInputTextString(String msg);
        void  onClickSend(String message);
    }

    private OnTextSendListener mOnTextSendListener;
    public InputTextMsgDialog(@NonNull Context context, int theme) {
        super(context, theme);
        this.mContext = context;
        init();
        setLayout();
    }
    /**
     * 最大输入字数  默认200
     */
    @SuppressLint("SetTextI18n")
    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    /**
     * 设置输入提示文字
     */
    public void setHint(String text) {
        messageTextView.setHint(text);
    }

    /*设置输入的文字*/
    public void setText(String text) {
        messageTextView.setText(text);
    }

    public void setTextInit(){
        rlDlg.mHasInit=false;
    }

    /**
     * 设置按钮的文字  默认为：发送
     */
    public void setBtnText(String text) {
        confirmBtn.setText(text);
    }

    private void init() {
        setContentView(R.layout.dialog_input_text_msg);
        getWindow().getDecorView().setPadding(0,0,0,0);
        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);
        messageTextView = findViewById(R.id.et_input_message);
        ConstraintLayout rldlgview =findViewById(R.id.rl_inputdlg_view);
        messageTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Editable editable = messageTextView.getText();
                int len = editable.length();

                if(len > maxNumber) {
                    Toast.makeText(mContext,"最大可输入"+maxNumber+"个字符",Toast.LENGTH_SHORT);
                    int selEndIndex = Selection.getSelectionEnd(editable);
                    String str = editable.toString();
                    //截取新字符串
                    String newStr = str.substring(0,maxNumber);
                    messageTextView.setText(newStr);
                    editable = messageTextView.getText();

                    //新字符串的长度
                    int newLen = editable.length();
                    //旧光标位置超过字符串长度
                    if(selEndIndex > newLen)
                    {
                        selEndIndex = editable.length();
                    }
                    //设置新光标所在的位置
                    Selection.setSelection(editable, selEndIndex);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
//                    confirmBtn.setTextColor(mContext.getResources().getColor(R.color.color_FFCCCCCC));
                    confirmBtn.setTextColor(mContext.getResources().getColor(R.color.color_translucent_white));
                } else {
//                    confirmBtn.setTextColor(mContext.getResources().getColor(R.color.color_FF666666));
                    confirmBtn.setTextColor(mContext.getResources().getColor(R.color.color_translucent_blue));
                }
            }
        });

        confirmBtn = (TextView) findViewById(R.id.confrim_btn);
        imm = (InputMethodManager) mContext.getSystemService(INPUT_METHOD_SERVICE);
        messageTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case KeyEvent.KEYCODE_ENDCALL:
                    case KeyEvent.KEYCODE_ENTER:
//                        if (messageTextView.getText().length() > maxNumber) {
//                            Toast.makeText(mContext, "超过最大字数限制", Toast.LENGTH_LONG).show();
//                            return true;
//                        }
//                        if (messageTextView.getText().length() > 0) {
//                            imm.hideSoftInputFromWindow(messageTextView.getWindowToken(), 0);
//                            dismiss();
//                        } else {
//                            Toast.makeText(mContext, "请输入文字", Toast.LENGTH_LONG).show();
//                        }
                        return true;
                    case KeyEvent.KEYCODE_BACK:
                        dismiss();
                        return false;
                    default:
                        return false;
                }
            }
        });

        messageTextView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.d("My test", "onKey " + keyEvent.getCharacters());
                return false;
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnTextSendListener.onClickSend(messageTextView.getText().toString().trim());
                imm.showSoftInput(messageTextView, InputMethodManager.SHOW_FORCED);
                imm.hideSoftInputFromWindow(messageTextView.getWindowToken(), 0);
                messageTextView.setText("");
                dismiss();
            }
        });

        rlDlg = findViewById(R.id.rl_outside_view);
        findViewById(R.id.rl_background).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnTextSendListener.onInputTextString(messageTextView.getText().toString().trim());
                imm.showSoftInput(messageTextView, InputMethodManager.SHOW_FORCED);
                imm.hideSoftInputFromWindow(messageTextView.getWindowToken(), 0);
                messageTextView.setText("");
                dismiss();

            }
        });
        rlDlg.setOnkbdStateListener(new KeyboardLayout.onKybdsChangeListener() {
            @Override
            public void onKeyBoardStateChange(int state) {
                switch (state) {
                    case KeyboardLayout.KEYBOARD_STATE_HIDE:
                        //    Toast.makeText(getApplicationContext(), "软键盘隐藏", Toast.LENGTH_SHORT).show();
                        //   Log.e("KeyboardLayout","KeyboardLayout----软键盘隐藏");
                        mOnTextSendListener.onInputTextString(messageTextView.getText().toString().trim());
                        messageTextView.setText("");
                        if(isShowing()){
                            dismiss();
                        }
                        break;
                    case KeyboardLayout.KEYBOARD_STATE_SHOW:
                        //   Toast.makeText(getApplicationContext(), "软键盘弹起", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        rldlgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(messageTextView.getWindowToken(), 0);
                dismiss();
            }
        });

        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0) {
                    dismiss();
                }
                return false;
            }
        });

    }

    public void showKeyboard() {
        if(messageTextView!=null){
            //设置可获得焦点
            messageTextView.setFocusable(true);
            messageTextView.setFocusableInTouchMode(true);
            //请求获得焦点
            messageTextView.requestFocus();
            //调用系统输入法
//            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(INPUT_METHOD_SERVICE);
//            imm.showSoftInput(messageTextView, 0);
//            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            InputMethodManager inputMethodManager=(InputMethodManager)mContext. getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(messageTextView, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    private void setLayout() {
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(p);
        setCancelable(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }


    public void setmOnTextSendListener(OnTextSendListener onTextSendListener) {
        this.mOnTextSendListener = onTextSendListener;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        //dismiss之前重置mLastDiff值避免下次无法打开
        mLastDiff = 0;
        rlDlg.setmHasInit(false);
        rlDlg.setmHasKeybord(false);
    }

    @Override
    public void show() {
        super.show();
    }
}
