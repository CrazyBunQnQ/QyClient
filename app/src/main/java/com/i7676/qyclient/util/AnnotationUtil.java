package com.i7676.qyclient.util;

import com.i7676.qyclient.annotations.Layout;
import java.lang.annotation.Annotation;

/**
 * Created by Administrator on 2016/9/5.
 */
public class AnnotationUtil {

  public static int getLayoutResId(Class clz) {
    Annotation annotation = clz.getAnnotation(Layout.class);
    if (annotation == null) {
      throw new NullPointerException(clz.getSimpleName() + " layout may not be null.");
    }
    return ((Layout) annotation).value();
  }
}
