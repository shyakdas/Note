package com.example.tnl.notes.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tnl.notes.R;
import com.example.tnl.notes.adapter.DataAdapter;
import com.example.tnl.notes.model.DataModel;
import com.example.tnl.notes.realm.RealmHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteActivity extends AppCompatActivity {

    @BindView(R.id.titleOfNote)
    EditText mTitle;
    @BindView(R.id.writeNote)
    EditText mNotes;
    @BindView(R.id.buttonSave)
    Button mSave;
    private DataAdapter dataAdapter;
    private DataModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);
        dataAdapter = new DataAdapter(new ArrayList<DataModel>(), this);
        if (getIntent().hasExtra("extra")) {
            model = (DataModel) getIntent().getSerializableExtra("extra");
            mSave.setText("Update");
            mTitle.setText(model.getTitle());
            mNotes.setText(model.getNotes());
            getSupportActionBar().setTitle("Edit Note");
        } else {
            getSupportActionBar().setTitle("New Note");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.buttonSave)
    public void saveNotes() {
        String title = mTitle.getText().toString();
        String note = mNotes.getText().toString();
        if (title == null || note == null) {
            Toast.makeText(NoteActivity.this, "Please enter Title and Note", Toast.LENGTH_SHORT).show();
        } else {
            if (model != null) {
                model.setTitle(title);
                model.setNotes(note);
                RealmHelper.getInstance().add(model);
                setResult(100);
                finish();
                return;
            }
            DataModel model = new DataModel();
            model.setTitle(title);
            model.setNotes(note);
            model.setId(RealmHelper.getInstance().getSize());
            RealmHelper.getInstance().add(model);
            setResult(100);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}