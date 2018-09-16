package ir.behmerd.weightcontrol;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import ir.behmerd.weightcontrol.data.StatusRecord;
import ir.behmerd.weightcontrol.data.StatusTableAdapter;
import ir.behmerd.weightcontrol.views.ManageStatusView;


public class StatusManageActivity extends ActionBarActivity {
    StatusTableAdapter database;
    List<StatusRecord> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_manage);

        database = new StatusTableAdapter(StatusManageActivity.this);
        data = database.getAllRecords();

        ListView list = (ListView) findViewById(R.id.man_status_list);
        list.setAdapter(new ManageStatusView(StatusManageActivity.this, data));
    }

    @Override
    protected void onResume(){
        super.onResume();
        data = database.getAllRecords();
        ListView list = (ListView) findViewById(R.id.man_status_list);
        list.setAdapter(new ManageStatusView(StatusManageActivity.this, data));
    }
}
