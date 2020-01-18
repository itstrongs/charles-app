package com.charles.utils.helper;

import android.content.Context;

import com.charles.utils.Logger;
import com.charles.utils.StringUtils;
import com.charles.utils.ToastUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件读取helper类
 * Created by Charles on 2018/8/9
 */
public enum PropertiesHelper {

    INSTANCE;

    private Properties mProperties;

    private String mPropertiesFileName;

    public void setPropertiesFileName(String fileName) {
        this.mPropertiesFileName = fileName;
    }

    public String getString(Context context, String key) {
        if (StringUtils.isEmpty(mPropertiesFileName)) {
            ToastUtils.show(context, "Properties 文件名为空");
        }
        return getPropertiesValue(context, mPropertiesFileName, key);
    }

    public boolean getBoolean(Context context, String key) {
        if (StringUtils.isEmpty(mPropertiesFileName)) {
            ToastUtils.show(context, "Properties 文件名为空");
            return false;
        }
        return Boolean.parseBoolean(getPropertiesValue(context, mPropertiesFileName, key));
    }

    public String getString(Context context, String propertiesName, String key) {
        return getPropertiesValue(context, propertiesName, key);
    }

    public boolean getBoolean(Context context, String propertiesName, String key) {
        return Boolean.parseBoolean(getPropertiesValue(context, propertiesName, key));
    }

    private String getPropertiesValue(Context context, String propertiesName, String key) {
        if (mProperties != null) {
            return mProperties.getProperty(key);
        } else {
            InputStream is = null;
            try {
                mProperties = new Properties();
                is = context.getAssets().open(propertiesName);
                mProperties.load(is);
                return mProperties.getProperty(key);
            } catch (IOException e) {
                Logger.w("Properties 读取异常");
                return "";
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    Logger.e(e);
                }
            }
        }
    }
}
