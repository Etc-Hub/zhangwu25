package com.example.zhangwu25;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.LinkedHashSet;
import java.util.Set;

public class CacheUtil {

    public static String TEMPORARY = "temporary";
    public static String PERMANENT = "permanent";

    private static void setValue(SharedPreferences sp, String key, Object value){
        SharedPreferences.Editor editor = sp.edit();
        if (value instanceof Boolean){
            editor.putBoolean(key, (Boolean) value);
        }else if (value instanceof Float){
            editor.putFloat(key, (Float) value);
        }else if (value instanceof Integer){
            editor.putInt(key, (Integer) value);
        }else if (value instanceof Long){
            editor.putLong(key, (Long) value);
        }else if (value instanceof String){
            editor.putString(key, (String) value);
        }else if (value instanceof Set<?>){
            if ((((Set<?>)value).toArray())[0] instanceof String){
                if (Build.VERSION.SDK_INT < 11){
                    return;
                }
                editor.putStringSet(key, (Set<String>) value);
            }else{
                String putValue = "";
                Object[] objects = ((Set<?>)value).toArray();
                for (Object object : objects){
                    putValue += object.toString()+"||";
                }
                editor.putString(key, putValue);
            }
        }else{
            editor.putString(key, value.toString());
        }
        editor.commit();
    }

    private static <T>T getValue(SharedPreferences sp, String key, Class<T> clazz, T defaultValue){
        Object returnValue = null;
        if (clazz == Boolean.class){
            returnValue = sp.getBoolean(key, (Boolean) defaultValue);
        }else if (clazz == Float.class){
            returnValue = sp.getFloat(key, (Float) defaultValue);
        }else if (clazz == Integer.class){
            returnValue = sp.getInt(key, (Integer) defaultValue);
        }else if (clazz == Long.class){
            returnValue = sp.getLong(key, (Long) defaultValue);
        }else if (clazz == String.class){
            returnValue = sp.getString(key, (String) defaultValue);
        }else{
            if (ParseObject.class.isAssignableFrom(clazz)){
                String value = sp.getString(key, null);
                if (value != null){
                    try {
                        T temp = clazz.newInstance();
                        ((ParseObject)temp).stringParseObject(value);
                        return temp;
                    } catch (Exception e){
                        return defaultValue;
                    }
                }
            }
            return defaultValue;
        }
        if (returnValue == null)
            return defaultValue;
        return (T) returnValue;
    }

    private static <T>Set<T> getValueSet(SharedPreferences sp, String key, Class<T> clazz){
        if (clazz == String.class){
            if (Build.VERSION.SDK_INT < 11){
                return null;
            }
            return (Set<T>) sp.getStringSet(key, null);
        }else{
            if (ParseObject.class.isAssignableFrom(clazz)){
                String value = sp.getString(key, null);
                if (value != null) {
                    String[] values = value.split("\\|\\|");
                    LinkedHashSet<T> lists = new LinkedHashSet<>();
                    for (String string : values){
                        try {
                            T temp = null;
                            temp = clazz.newInstance();
                            ((ParseObject)temp).stringParseObject(string);
                            lists.add(temp);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    return lists;
                }
            }
            return null;
        }
    }

    public static void setTemporary(String key, Object value){
        SharedPreferences sp = AppApplication.getInstance().getSharedPreferences(TEMPORARY, Context.MODE_PRIVATE);
        setValue(sp, key, value);
    }


    public static <T>T getTemporary(String key, Class<T> clazz, T defaultValue){
        SharedPreferences sp = AppApplication.getInstance().getSharedPreferences(TEMPORARY, Context.MODE_PRIVATE);
        return getValue(sp, key, clazz, defaultValue);
    }

    public static <T>Set<T> getTemporarySet(String key, Class<T> clazz){
        SharedPreferences sp = AppApplication.getInstance().getSharedPreferences(TEMPORARY, Context.MODE_PRIVATE);
        return getValueSet(sp, key, clazz);
    }

    public static void setPermanent(String key, Object value){
        SharedPreferences sp = AppApplication.getInstance().getSharedPreferences(PERMANENT, Context.MODE_PRIVATE);
        setValue(sp, key, value);
    }

    public static <T>T getPermanent(String key, Class<T> clazz, T defaultValue){
        SharedPreferences sp = AppApplication.getInstance().getSharedPreferences(PERMANENT, Context.MODE_PRIVATE);
        return getValue(sp, key, clazz, defaultValue);
    }

    public static <T>Set<T> getPermanentSet(String key, Class<T> clazz){
        SharedPreferences sp = AppApplication.getInstance().getSharedPreferences(PERMANENT, Context.MODE_PRIVATE);
        return getValueSet(sp, key, clazz);
    }

    public static void removeTemporary(){
        SharedPreferences sp = AppApplication.getInstance().getSharedPreferences(TEMPORARY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    public static abstract class ParseObject{
        public abstract void stringParseObject(String value);
    }
}
