package com.charles.eden.model;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static int[] TAB_TITLES;
    private final Context mContext;
    private SectionsCallback mSectionsCallback;

    public SectionsPagerAdapter(Context context, FragmentManager fm, int[] tabTitles, SectionsCallback callback) {
        super(fm);
        mContext = context;
        TAB_TITLES = tabTitles;
        mSectionsCallback = callback;
    }

    @Override
    public Fragment getItem(int position) {
        return mSectionsCallback.getItem(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }

    public interface SectionsCallback {

        Fragment getItem(int position);
    }
}