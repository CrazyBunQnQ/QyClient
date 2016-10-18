package com.i7676.qyclient.annotations;

import android.support.annotation.LayoutRes;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2016/9/5.
 */
@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.TYPE) public @interface Layout {
    @LayoutRes int value();
}
