package com.i7676.qyclient.di;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.inject.Scope;

/**
 * Created by Administrator on 2016/9/14.
 */
@Retention(RetentionPolicy.RUNTIME) @Scope @Target(ElementType.TYPE) public @interface MainScope {
}
