package com.example.tracktable.util;

import android.content.Context;
import android.widget.Toast;

//Toast封装工具库
public class ToastUtil {

    //短时间弹出提示
    public static void toastShort(Context context,String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    //长时间弹出提示
    public static void toastLong(Context context,String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

}
