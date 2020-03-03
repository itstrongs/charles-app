package com.charles.myapp.fragment.music;

import android.widget.SeekBar;
import android.widget.TextView;

import com.charles.library.base.BaseFragment;
import com.charles.library.base.BasePresenter;
import com.charles.library.utils.Logger;
import com.charles.myapp.R;
import com.charles.myapp.activity.MainActivity;
import com.charles.myapp.data.PlayerHelper;
import com.charles.myapp.data.bean.Music;

import butterknife.BindView;

/**
 * Created by itstrongs on 2017/12/1.
 */
public class PlayHomeFragment extends BaseFragment<MainActivity> {

    @BindView(R.id.seek_bar)
    SeekBar seekBar;
    @BindView(R.id.text_time_start)
    TextView textTimeStart;
    @BindView(R.id.text_time_end)
    TextView textTimeEnd;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_play_home;
    }

    @Override
    protected void initView() {
        PlayerHelper.getInstance().setOnProgressListener(new PlayerHelper.OnProgressListener() {
            @Override
            public void onProgress(final int progress) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        seekBar.setProgress(progress);
                        String time = "0" + progress / 60 + ":" + (progress % 60 < 10 ? "0" + progress % 60 : progress % 60);
                        Logger.d("time=" + time);
                        textTimeStart.setText(time);
                    }
                });
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) { }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                PlayerHelper.getInstance().setProgress(seekBar.getProgress());
            }
        });
        Music currentMusic = PlayerHelper.getInstance().getCurrentMusic(mContext);
        Logger.d("当前播放的音乐:" + currentMusic);
        if (currentMusic != null) {
            seekBar.setMax(Integer.parseInt(currentMusic.getTime()) / 1000);
            textTimeEnd.setText(currentMusic.getDuration());
        }
    }

}
