package com.hb.dialog.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

import com.hb.dialog.R;

import java.util.List;

/**
 * Created by hanbin on 2017/7/26.
 */

public class PasswordKeyboardView extends KeyboardView implements
        KeyboardView.OnKeyboardActionListener {

    // 用于区分左下角空白的按键
    private static final int KEYCODE_EMPTY = -10;

    private int      mDeleteBackgroundColor;
    private Rect mDeleteDrawRect;
    private Drawable mDeleteDrawable;

    private IOnKeyboardListener mOnKeyboardListener;

    public PasswordKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public PasswordKeyboardView(Context context, AttributeSet attrs,
                                int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs,
                      int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.PasswordKeyboardView, defStyleAttr, 0);
        mDeleteDrawable = a.getDrawable(
                R.styleable.PasswordKeyboardView_pkvDeleteDrawable);
        mDeleteBackgroundColor = a.getColor(
                R.styleable.PasswordKeyboardView_pkvDeleteBackgroundColor,
                Color.TRANSPARENT);
        a.recycle();

        // 设置软键盘按键的布局
        Keyboard keyboard = new Keyboard(context,
                R.xml.keyboard_number_password);
        setKeyboard(keyboard);

        setEnabled(true);
        setPreviewEnabled(false);
        setOnKeyboardActionListener(this);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 遍历所有的按键
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for (Keyboard.Key key : keys) {
            // 如果是左下角空白的按键，重画按键的背景
            if (key.codes[0] == KEYCODE_EMPTY) {
                drawKeyBackground(key, canvas, mDeleteBackgroundColor);
            }
            // 如果是右下角的删除按键，重画背景，并且绘制删除的图标
            else if (key.codes[0] == Keyboard.KEYCODE_DELETE) {
                drawKeyBackground(key, canvas, mDeleteBackgroundColor);
                drawDeleteButton(key, canvas);
            }
        }
    }

    // 绘制按键的背景
    private void drawKeyBackground(Keyboard.Key key, Canvas canvas,
                                   int color) {
        ColorDrawable drawable = new ColorDrawable(color);
        drawable.setBounds(key.x, key.y,
                key.x + key.width, key.y + key.height);
        drawable.draw(canvas);
    }

    // 绘制删除按键
    private void drawDeleteButton(Keyboard.Key key, Canvas canvas) {
        if (mDeleteDrawable == null)
            return;

        // 计算删除图标绘制的坐标
        if (mDeleteDrawRect == null || mDeleteDrawRect.isEmpty()) {
            int intrinsicWidth = mDeleteDrawable.getIntrinsicWidth();
            int intrinsicHeight = mDeleteDrawable.getIntrinsicHeight();
            int drawWidth = intrinsicWidth;
            int drawHeight = intrinsicHeight;

            // 限制图标的大小，防止图标超出按键
            if (drawWidth > key.width) {
                drawWidth = key.width;
                drawHeight = drawWidth * intrinsicHeight / intrinsicWidth;
            }
            if (drawHeight > key.height) {
                drawHeight = key.height;
                drawWidth = drawHeight * intrinsicWidth / intrinsicHeight;
            }

            // 获取删除图标绘制的坐标
            int left = key.x + (key.width - drawWidth) / 2;
            int top = key.y + (key.height - drawHeight) / 2;
            mDeleteDrawRect = new Rect(left, top,
                    left + drawWidth, top + drawHeight);
        }

        // 绘制删除的图标
        if (mDeleteDrawRect != null && !mDeleteDrawRect.isEmpty()) {
            mDeleteDrawable.setBounds(mDeleteDrawRect.left,
                    mDeleteDrawRect.top, mDeleteDrawRect.right,
                    mDeleteDrawRect.bottom);
            mDeleteDrawable.draw(canvas);
        }
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        // 处理按键的点击事件
        // 点击删除按键
        if (primaryCode == Keyboard.KEYCODE_DELETE) {
            if (mOnKeyboardListener != null) {
                mOnKeyboardListener.onDeleteKeyEvent();
            }
        }
        // 点击了非左下角按键的其他按键
        else if (primaryCode != KEYCODE_EMPTY) {
            if (mOnKeyboardListener != null) {
                mOnKeyboardListener.onInsertKeyEvent(
                        Character.toString((char) primaryCode));
            }
        }
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    /**
     * 设置键盘的监听事件。
     *
     * @param listener
     *         监听事件
     */
    public void setIOnKeyboardListener(IOnKeyboardListener listener) {
        this.mOnKeyboardListener = listener;
    }

    public interface IOnKeyboardListener {

        void onInsertKeyEvent(String text);

        void onDeleteKeyEvent();
    }

}
