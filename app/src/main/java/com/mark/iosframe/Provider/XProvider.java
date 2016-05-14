package com.mark.iosframe.Provider;

import android.content.Context;

import java.util.Collection;

/**
 * Created by john on 2016/5/14.
 */
public class XProvider {
    /**
     *
     * @param context
     * @param filterSystemApp
     * @return IProvider:query(String parameter?package);
     * return Bean?XApplicationInfo
     *
     */
    public static IProvider  getApplicationInfoProvider(Context context,boolean filterSystemApp){
        ApplicationInfoProvider appProvider= ApplicationInfoProvider.get(context);
        appProvider.setFilterSystemApp(filterSystemApp);
        return appProvider;
    }

}
