package com.example.tnl.notes.app;

import android.app.Application;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

/**
 * Created by tnl on 12/3/2017;
 */

public class MyApplication extends Application implements RealmMigration {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("Text")
                .schemaVersion(1)
                .migration(this)
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

    }
}