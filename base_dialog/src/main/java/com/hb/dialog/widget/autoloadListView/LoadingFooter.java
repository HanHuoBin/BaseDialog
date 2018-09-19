package com.hb.dialog.widget.autoloadListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hb.dialog.R;


public class LoadingFooter {

    private View mLoadingFooter;
    private TextView mLoadingText;
    private State    mState = State.Idle;

    //private JumpingBeans jumpingBeans;

    public static enum State {
        Idle,
        TheEnd,
        Loading
    }

    public LoadingFooter(Context context) {
        mLoadingFooter = LayoutInflater.from(context).inflate(R.layout.auto_load_footer, null);
        mLoadingFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mLoadingText = (TextView) mLoadingFooter.findViewById(R.id.textView);

        setState(State.Idle);
    }

    public View getView() {
        return mLoadingFooter;
    }

    public State getState() {
        return mState;
    }

    public void setState(final State state, long delay) {
        mLoadingFooter.postDelayed(new Runnable() {
            @Override
            public void run() {
                setState(state);
            }
        }, delay);
    }

    public void setState(State status) {
        if (mState == status) {
            return;
        }
        mState = status;

        switch (status) {
            case Loading:
                mLoadingFooter.setVisibility(View.VISIBLE);
                mLoadingText.setText("加载中...");
                //jumpingBeans = new JumpingBeans.Builder().appendJumpingDots(mLoadingText).build();
                break;
            case TheEnd:
                //                if (jumpingBeans != null) {
                //                    jumpingBeans.stopJumping();
                //                }
                mLoadingText.setVisibility(View.GONE);
                mLoadingFooter.setVisibility(View.GONE);
                break;
            default:
                //                if (jumpingBeans != null) {
                //                    jumpingBeans.stopJumping();
                //                }
                mLoadingFooter.setVisibility(View.GONE);
                break;
        }
    }
}
