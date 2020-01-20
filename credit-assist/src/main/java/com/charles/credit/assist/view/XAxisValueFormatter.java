package com.charles.credit.assist.view;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * 说明
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年07月30日 09:36
 */
public class XAxisValueFormatter implements IAxisValueFormatter {

    private String[] xStrs = new String[]{"春", "夏", "秋", "冬"};

    @Override

    public String getFormattedValue(float value, AxisBase axis) {
        int position = (int) value;
        if (position >= 4) {
            position = 0;
        }
        return xStrs[position];
    }
}
