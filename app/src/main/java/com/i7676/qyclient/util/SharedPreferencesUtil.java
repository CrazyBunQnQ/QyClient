package com.i7676.qyclient.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public class SharedPreferencesUtil {
    private static SharedPreferencesUtil spUtil = new SharedPreferencesUtil();
    private static Context mContext;
    private static SharedPreferences.Editor saveEditor;
    private static SharedPreferences saveInfo;
    private static final String APP_PROJECT_NAME = "ynet_";

    public static SharedPreferencesUtil getInstance(Context context) {
        mContext = context;
        if (saveInfo == null && mContext != null) {
            saveInfo = mContext.getSharedPreferences(APP_PROJECT_NAME, Context.MODE_PRIVATE);
            saveEditor = saveInfo.edit();
        }
        return spUtil;
    }

    private SharedPreferencesUtil() {
        if (mContext != null) {
            saveInfo = mContext.getSharedPreferences(APP_PROJECT_NAME, Context.MODE_PRIVATE);
            saveEditor = saveInfo.edit();
        }
    }

    /**
     * 保存用户
     */
    public void saveSerializable(String key, Object value) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(value);
            oos.flush();
            oos.close();
            String strData = new String(Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT));
            saveEditor.putString(key, strData);
            saveEditor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param key
     * @return
     */
    public Object restoreSerializable(String key) {
        Object obj = null;
        String objectInfo = saveInfo.getString(key, null);
        if (objectInfo == null) {
            return null;
        }
        ByteArrayInputStream bais =
            new ByteArrayInputStream(Base64.decode(objectInfo.getBytes(), Base64.DEFAULT));
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
            ois.close();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                bais.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    /**
     * 保存用户
     */
    public void savePra(String key, Object value) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(value);
            oos.flush();
            if (oos != null) {
                oos.close();
            }
            String strData = new String(Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT));
            saveEditor.putString(key, strData);
            saveEditor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存boolean数据
     */
    public boolean putBoolean(Context context, String key, boolean value) {
        saveEditor.putBoolean(key, value);
        return saveEditor.commit();
    }

    /**
     * 获取boolean数据
     */
    public boolean getBoolean(Context context, String key, boolean defaultValue) {
        return saveInfo.getBoolean(key, defaultValue);
    }
}