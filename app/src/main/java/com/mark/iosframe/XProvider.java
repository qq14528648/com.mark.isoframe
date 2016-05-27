package com.mark.iosframe;

import android.content.Context;

import com.mark.iosframe.Provider.ApplicationInfoProvider;
import com.mark.iosframe.Provider.IProvider;

import java.util.Collection;


/**
 * 作者：john on 2016/5/16 11:33
 * 邮箱：mark14528648@yahoo.co.jp
 *
 */

public class XProvider {
    /**
     *
     * @param context
     * @param filterSystemApp
     * @return IProvider
     *IProvider接口：
     *方法：query(String parameter)中参数为package
     *方法：返回的Bean类型为XApplicationInfo
     *
     */
    public static IProvider getApplicationInfoProvider(Context context, boolean filterSystemApp){
        ApplicationInfoProvider appProvider= ApplicationInfoProvider.get(context);
        appProvider.setFilterSystemApp(filterSystemApp);
        return appProvider;
    }
    public static IProvider getApplicationInfoProvider(Context context ){

        return  getApplicationInfoProvider(context,true);
    }
}
