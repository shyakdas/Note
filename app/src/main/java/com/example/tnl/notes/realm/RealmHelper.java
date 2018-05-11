package com.example.tnl.notes.realm;

import com.example.tnl.notes.model.DataModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by tnl on 12/3/2017;
 */

public class RealmHelper {
    private static RealmHelper instance;
    Realm realm;

    private RealmHelper() {

    }

    public static RealmHelper getInstance() {
        if (instance == null) {
            instance = new RealmHelper();
        }
        return instance;
    }

    public void add(final DataModel dataModel) {
        realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(dataModel);
                }
            });
        } finally {
            realm.close();
        }
    }

    public List<DataModel> fetchData() {
        realm = Realm.getDefaultInstance();
        RealmResults<DataModel> realmResults;
        try {
            RealmQuery<DataModel> realmQuery = realm.where(DataModel.class);
            realmResults = realmQuery.findAll();
        } finally {

        }
        return realmResults;
    }

    public int getSize() {
        realm = Realm.getDefaultInstance();
        RealmResults<DataModel> realmResults;
        try {
            RealmQuery<DataModel> realmQuery = realm.where(DataModel.class);
            realmResults = realmQuery.findAll();
        } finally {

        }
        return realmResults.size();
    }
}