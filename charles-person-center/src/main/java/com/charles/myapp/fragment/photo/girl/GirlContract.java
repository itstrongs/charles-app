package com.charles.myapp.fragment.photo.girl;

import com.charles.library.base.BasePresenter;
import com.charles.library.base.BaseView;
import com.charles.myapp.data.bean.Girl;

import java.util.ArrayList;

/**
 * Presenter接口和View接口的契约接口，定义所有UI和逻辑操作接口
 * Created by itstrongs on 2017/10/23.
 */
public interface GirlContract {

    interface View extends BaseView<Presenter> {

        void showImage(Girl girlBean);

        void showGirlDetails(int position);
    }

    interface Presenter extends BasePresenter {

        void doLoadGirlData();

        void doLoadMoreData();

        ArrayList<Girl.ResultsBean> doGetGirlData();
    }
}
