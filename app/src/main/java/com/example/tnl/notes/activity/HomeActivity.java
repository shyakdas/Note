package com.example.tnl.notes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tnl.notes.R;
import com.example.tnl.notes.adapter.DataAdapter;
import com.example.tnl.notes.model.DataModel;
import com.example.tnl.notes.database.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.floatingActionButton)
    android.support.design.widget.FloatingActionButton mFloatingActionButton;
    private DataAdapter dataAdapter;
    private RecyclerView mRecylerView;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mRecylerView = findViewById(R.id.noteList);
        layoutManager = new LinearLayoutManager(HomeActivity.this);
        layoutManager.setReverseLayout(true);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
        mRecylerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        dataAdapter = new DataAdapter(new ArrayList<DataModel>(), this);
        mRecylerView.setAdapter(dataAdapter);
        mRecylerView.setHasFixedSize(true);
        populateDataToRecyclerView();
    }

    @OnClick(R.id.floatingActionButton)
    public void floatingAction() {
        Intent newNoteCreation = new Intent(HomeActivity.this, NoteActivity.class);
        startActivityForResult(newNoteCreation, 100);
    }

    private void populateDataToRecyclerView() {
        List<DataModel> results = RealmHelper.getInstance().fetchData();
        if (results == null || results.size() == 0) {
        } else {
            List<DataModel> models = new ArrayList<>();
            for (DataModel model : results) {
                DataModel dataModel = new DataModel(model.getTitle(), model.getNotes(), model.getDateTime(), model.getId());
                models.add(dataModel);
            }
            dataAdapter.add(models);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        populateDataToRecyclerView();
    }
}