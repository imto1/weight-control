package ir.behmerd.weightcontrol;

import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ir.behmerd.weightcontrol.common.PersianCalendar;
import ir.behmerd.weightcontrol.data.StatusRecord;
import ir.behmerd.weightcontrol.data.StatusTableAdapter;


public class AddStatusActivity extends ActionBarActivity {
    private StatusRecord record;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_status);

        set_fonts();

        final PersianCalendar calendar = new PersianCalendar();
        TextView check_date = (TextView) findViewById(R.id.save_check_date);
        TextView height = (TextView) findViewById(R.id.save_height);
        TextView weight = (TextView) findViewById(R.id.save_weight);
        TextView bmi = (TextView) findViewById(R.id.save_bmi);
        TextView difference = (TextView) findViewById(R.id.save_difference);

        if(getIntent().getExtras() != null) {
            record = new StatusRecord();
            record = (StatusRecord) getIntent().getSerializableExtra("status");
        }

        check_date.setText(check_date.getText().toString() + " " + calendar.Now.Today());
        height.setText(height.getText().toString() + " " + String.valueOf(record.getHeight()));
        weight.setText(weight.getText().toString() + " " + String.valueOf(record.getWeight()));
        bmi.setText(bmi.getText().toString() + " " + String.valueOf(record.getBmi()));
        difference.setText(difference.getText().toString() + " " + String.valueOf(record.getDifference()));

        Button btn_save = (Button) findViewById(R.id.save_insert_button);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                record.setCheckDate(calendar.Now.Today());
                EditText activities = (EditText) findViewById(R.id.save_activity_edit);
                record.setActivities(activities.getText().toString());

                StatusTableAdapter database = new StatusTableAdapter(AddStatusActivity.this);
                if(database.insert(record))
                    finish();
            }
        });
    }

    private void set_fonts(){
        Typeface nazanin_regular = Typeface.createFromAsset(getAssets(), "font/BNazanin.ttf");

        TextView check_date = (TextView) findViewById(R.id.save_check_date);
        TextView height = (TextView) findViewById(R.id.save_height);
        TextView weight = (TextView) findViewById(R.id.save_weight);
        TextView bmi = (TextView) findViewById(R.id.save_bmi);
        TextView difference = (TextView) findViewById(R.id.save_difference);
        TextView activities_caption = (TextView) findViewById(R.id.save_activities);
        EditText activities = (EditText) findViewById(R.id.save_activity_edit);

        check_date.setTypeface(nazanin_regular);
        height.setTypeface(nazanin_regular);
        weight.setTypeface(nazanin_regular);
        bmi.setTypeface(nazanin_regular);
        difference.setTypeface(nazanin_regular);
        activities_caption.setTypeface(nazanin_regular);
        activities.setTypeface(nazanin_regular);
    }
}
