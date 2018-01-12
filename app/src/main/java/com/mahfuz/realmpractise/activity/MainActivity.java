package com.mahfuz.realmpractise.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.mahfuz.realmpractise.R;
import com.mahfuz.realmpractise.model.Student;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Realm mRealm;
    EditText mNameField,
            mMarkField;
    TextView mDisplayView;
    Button mSaveButton,
          mDeleteButton,
          mViewData;
    List<String> name = new ArrayList<>();
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRealm = Realm.getDefaultInstance();
        initView();
        showData();

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                showData();
            }
        });
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
                showData();
            }
        });

    }

    private void setUpAdapter() {

        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,name));
    }

    private void deleteData() {
       final RealmResults<Student> results = mRealm.where(Student.class).findAll();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteAllFromRealm();
            }
        });
    }

    private void showData() {

        RealmResults<Student> results = mRealm.where(Student.class).findAllAsync();
        results.load();
        name.clear();
        for (Student student : results){
            name.add(student.getName());
        }
        setUpAdapter();
    }

    private void saveData() {

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Student student = mRealm.createObject(Student.class);
                student.setName(mNameField.getText().toString());
                student.setMark(mMarkField.getText().toString());

            }
        });
    }

    private void initView() {

        mNameField = findViewById(R.id.name_field);
        mMarkField = findViewById(R.id.mark_field);
        mSaveButton = findViewById(R.id.save_button);
        mDeleteButton = findViewById(R.id.delete_button);
        mListView = findViewById(R.id.list_view);
    }
}
