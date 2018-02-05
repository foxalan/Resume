package com.example.alan.resume.application;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;


public class Configurator {

    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();

    public final HashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
        LATTE_CONFIGS.put(ConfigType.HANDLER.name(), HANDLER);
    }

    /**
     * 内部类的单例模式
     */
    private static class ViewHolder {
        static final Configurator INSTANCE = new Configurator();
    }

    //初始化字体图标
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }


    public static Configurator getInstance() {
        return ViewHolder.INSTANCE;
    }

    //设置完成
    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);

    }

    //添加数据
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }



    //添加字体图标
    public final Configurator withIconFont(IconFontDescriptor iconFont) {
        ICONS.add(iconFont);
        return this;
    }


    public Configurator withJavascriptInterface(@NonNull String name) {
        LATTE_CONFIGS.put(ConfigType.JAVASCRIPT_INTERFACE.name(), name);
        return this;
    }

    public Configurator withLoaderTime(long time){
        LATTE_CONFIGS.put(ConfigType.LOADER_DELAYED.name(),time);
        return this;
    }


    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready , call configure");
        }
    }

    @SuppressWarnings("unchecked")
    public final <T> T getConfiguration(String key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key);
    }

    @SuppressWarnings("unchecked")
    public final <T> T getConfiguration(ConfigType key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }



}
