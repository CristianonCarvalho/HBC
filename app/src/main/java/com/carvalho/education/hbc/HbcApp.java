package com.carvalho.education.hbc;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by 06094547659 on 11/07/2016.
 */
public class HbcApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
    }

    @Override

    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void initDB() {
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }


}
