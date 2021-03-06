package com.talkies.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.MediaController;

import com.talkies.R;
import com.talkies.activities.VideoPlayerActivity;

public class FullScreenMediaController extends MediaController {

     ImageButton fullScreen;
    private String isFullScreen;
    Activity mActivity;

    public FullScreenMediaController(Context context,Activity mActivity) {
        super(context);
        this.mActivity=mActivity;

    }

    @Override
    public void setAnchorView(View view) {

        super.setAnchorView(view);

        //image button for full screen to be added to media controller
        fullScreen = new ImageButton (super.getContext());
        fullScreen.setMaxHeight(10);
        fullScreen.setMinimumHeight(10);
        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.RIGHT;
        params.rightMargin = 80;
        addView(fullScreen, params);

        //fullscreen indicator from intent
        isFullScreen =  ((Activity)getContext()).getIntent().
                getStringExtra("fullScreenInd");

        if("y".equals(isFullScreen)){
            fullScreen.setImageResource(R.drawable.exo_controls_fullscreen_exit);
        }else{
            fullScreen.setImageResource(R.drawable.exo_controls_fullscreen_enter);
        }

        //add listener to image button to handle full screen and exit full screen events
        fullScreen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        });

    }


}