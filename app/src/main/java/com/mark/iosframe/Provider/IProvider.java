package com.mark.iosframe.Provider;

import android.content.Context;

import com.mark.iosframe.Bean.XApplicationInfo;
import com.mark.iosframe.Bean.XBaseBean;

import java.util.List;


/**
 * 作者：john on 2016/5/16 11:33
 * 邮箱：mark14528648@yahoo.co.jp
 *
 */

public interface IProvider {
      List<? extends XBaseBean> queryAll( );
        XBaseBean query(String parameter );
}
