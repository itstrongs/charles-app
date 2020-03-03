package com.charles.education.planet.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.charles.education.planet.R;
import com.charles.education.planet.fragment.FindStudentFragment;
import com.charles.education.planet.fragment.FindTeacherFragment;
import com.charles.education.planet.fragment.HomeFragment;
import com.charles.education.planet.fragment.MyFragment;
import com.charles.education.planet.view.NavigationView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    private String mCurrentFragment;
    private Map<String, Fragment> mFragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigationView.setOnItemSelectedListener(resId -> {
            if (resId == R.id.radio_menu_home) {
                switchFragment(HomeFragment.class);
            } else if (resId == R.id.radio_menu_find_student) {
                switchFragment(FindStudentFragment.class);
            } else if (resId == R.id.radio_menu_find_teacher) {
                switchFragment(FindTeacherFragment.class);
            } else if (resId == R.id.radio_menu_my) {
                switchFragment(MyFragment.class);
            }
        });
        initFragment();
    }

    private void initFragment() {
        mFragmentMap = new HashMap<>();
        mCurrentFragment = HomeFragment.class.getSimpleName();
        mFragmentMap.put(mCurrentFragment, new HomeFragment());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_main_fragment, mFragmentMap.get(mCurrentFragment))
                .commit();
    }

    public void switchFragment(Class clazz) {
        String fragmentTag = clazz.getSimpleName();
        if (!fragmentTag.equals(mCurrentFragment)) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(mFragmentMap.get(mCurrentFragment));
            Fragment fragment = mFragmentMap.get(fragmentTag);
            if (fragment == null) {
                try {
                    fragment = (Fragment) clazz.newInstance();
                    mFragmentMap.put(fragmentTag, fragment);
                    transaction.add(R.id.frame_main_fragment, fragment, fragmentTag).commit();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                transaction.show(fragment).commit();
            }
            mCurrentFragment = fragmentTag;
        }
    }
}
