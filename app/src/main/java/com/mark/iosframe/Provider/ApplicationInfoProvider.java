package com.mark.iosframe.Provider;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.mark.iosframe.Bean.XApplicationInfo;
import com.mark.iosframe.Bean.XBaseBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者：john on 2016/5/16 11:33
 * 邮箱：mark14528648@yahoo.co.jp
 *
 */

public class ApplicationInfoProvider implements  IProvider{
    private PackageManager packageManager;
    private static ApplicationInfoProvider sApplicationInfoProvider;
    private  boolean filterSystemApp;

    public void setFilterSystemApp(boolean filterSystemApp) {
        this.filterSystemApp = filterSystemApp;
    }

    private ApplicationInfoProvider(Context context) {
        packageManager = context.getPackageManager();
    }
    public static  ApplicationInfoProvider  get(Context context){
        if (sApplicationInfoProvider==null)
            sApplicationInfoProvider=new ApplicationInfoProvider(context);
        return  sApplicationInfoProvider;
    }


    /**
     * /**
     * 判断某一个应用程序是不是系统的应用程序，
     * 如果是返回true，否则返回false。
     */
    private static  boolean filterApp(ApplicationInfo info) {
        //有些系统应用是可以更新的，如果用户自己下载了一个系统的应用来更新了原来的，它还是系统应用，这个就是判断这种情况的
        if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
            return true;
        } else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {//判断是不是系统应用
            return true;
        }
        return false;
    }

    @Override
    public List<XApplicationInfo> queryAll( ) {

        List<XApplicationInfo> list = new ArrayList<XApplicationInfo>();

        //获取到所有安装了的应用程序的信息，包括那些卸载了的，但没有清除数据的应用程序
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        for (PackageInfo info : packageInfos) {
            XApplicationInfo mAppInfo = new XApplicationInfo();
            //拿到包名
            String packageName = info.packageName;
            //拿到应用程序的信息
            ApplicationInfo appInfo = info.applicationInfo;
            //拿到应用程序的图标
            Drawable icon = appInfo.loadIcon(packageManager);
            String appName = appInfo.loadLabel(packageManager).toString();
            mAppInfo.setPackageName(packageName);
            mAppInfo.setAppName(appName);
            mAppInfo.setIcon(icon);
            mAppInfo.setVersion(info.versionName);
             if (filterSystemApp){
                 if (filterApp(appInfo)) {
                     //非系统应用添加 进来
                     list.add(mAppInfo);
                 }
             }else {
                 list.add(mAppInfo);
             }
        }
            return list;
    }

    @Override
    public XApplicationInfo query(String pack ) {
        //获取到所有安装了的应用程序的信息，包括那些卸载了的，但没有清除数据的应用程序
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        for (PackageInfo packageInfo : packageInfos) {
            //拿到包名
            String packageName = packageInfo.packageName;
            if (pack.equals(packageName)) {
                XApplicationInfo xApplicationInfo = new XApplicationInfo();
                //拿到应用程序的信息
                ApplicationInfo appInfo = packageInfo.applicationInfo;
                //拿到应用程序的图标
                Drawable icon = appInfo.loadIcon(packageManager);

                String appName = appInfo.loadLabel(packageManager).toString();
                xApplicationInfo.setPackageName(packageName);
                xApplicationInfo.setAppName(appName);
                xApplicationInfo.setIcon(icon);
                return xApplicationInfo;
            }
        }
        return null;
    }
}
