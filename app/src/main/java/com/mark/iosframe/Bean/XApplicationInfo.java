package com.mark.iosframe.Bean;

import android.graphics.drawable.Drawable;


/**
 * 作者：john on 2016/5/16 11:33
 * 邮箱：mark14528648@yahoo.co.jp
 */
public class XApplicationInfo extends XBaseBean{
    private Drawable icon;
    private String appName;
    private String packageName;
    private String version;
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

}
