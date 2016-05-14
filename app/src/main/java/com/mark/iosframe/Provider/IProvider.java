package com.mark.iosframe.Provider;

import android.content.Context;

import com.mark.iosframe.Bean.XApplicationInfo;
import com.mark.iosframe.Bean.XBaseBean;

import java.util.List;

/**
 * Created by john on 2016/5/14.
 */
public interface IProvider {
      List<? extends XBaseBean> queryAll( );
        XBaseBean query(String parameter );
}
