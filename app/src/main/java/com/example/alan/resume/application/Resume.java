package com.example.alan.resume.application;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by Alan on 2017/12/8.
 */

public class Resume {


    //得到实例
    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplicationContext(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration((ConfigType) key);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigType.HANDLER);
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }


}
