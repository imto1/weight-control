package ir.behmerd.weightcontrol;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ir.behmerd.weightcontrol.common.BMI;
import ir.behmerd.weightcontrol.common.Common;
import ir.behmerd.weightcontrol.data.StatusRecord;
import ir.behmerd.weightcontrol.data.StatusTableAdapter;


public class StatusActivity extends ActionBarActivity {
    private StatusRecord record;
    private MenuItem save;
    private MenuItem delete;
    private MenuItem edit;
    private MenuItem cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        record = new StatusRecord();
        if(getIntent().getExtras() != null)
            record = (StatusRecord) getIntent().getSerializableExtra("status");

        set_fonts();
        represent(record);
    }


    private void set_fonts(){
        Typeface nazanin_regular = Typeface.createFromAsset(getAssets(), "font/BNazanin.ttf");

        /*EditText height_text = (EditText) findViewById(R.id.et_main_height);
        EditText weight_text = (EditText) findViewById(R.id.et_main_weight);*/
        TextView height = (TextView) findViewById(R.id.sa_height);
        TextView height_title = (TextView) findViewById(R.id.sa_height_title);
        TextView weight = (TextView) findViewById(R.id.sa_weight);
        TextView weight_title = (TextView) findViewById(R.id.sa_weight_title);
        TextView bmi = (TextView) findViewById(R.id.sa_bmi);
        TextView bmi_title = (TextView) findViewById(R.id.sa_bmi_title);
        TextView body_status = (TextView) findViewById(R.id.sa_status);
        TextView body_status_title = (TextView) findViewById(R.id.sa_status_title);
        TextView ideal = (TextView) findViewById(R.id.sa_ideal);
        TextView ideal_title = (TextView) findViewById(R.id.sa_ideal_title);
        TextView to_ideal = (TextView) findViewById(R.id.sa_to_ideal);
        TextView to_ideal_title = (TextView) findViewById(R.id.sa_to_ideal_title);
        TextView normal_range = (TextView) findViewById(R.id.sa_normal_range);
        TextView normal_range_title = (TextView) findViewById(R.id.sa_normal_range_title);
        TextView difference = (TextView) findViewById(R.id.sa_difference);
        TextView difference_title = (TextView) findViewById(R.id.sa_difference_title);

        /*height_text.setTypeface(nazanin_regular);
        weight_text.setTypeface(nazanin_regular);*/
        height.setTypeface(nazanin_regular);
        weight.setTypeface(nazanin_regular);
        height_title.setTypeface(nazanin_regular);
        weight_title.setTypeface(nazanin_regular);
        bmi.setTypeface(nazanin_regular);
        bmi_title.setTypeface(nazanin_regular);
        body_status.setTypeface(nazanin_regular);
        body_status_title.setTypeface(nazanin_regular);
        ideal.setTypeface(nazanin_regular);
        ideal_title.setTypeface(nazanin_regular);
        to_ideal.setTypeface(nazanin_regular);
        to_ideal_title.setTypeface(nazanin_regular);
        normal_range.setTypeface(nazanin_regular);
        normal_range_title.setTypeface(nazanin_regular);
        difference.setTypeface(nazanin_regular);
        difference_title.setTypeface(nazanin_regular);
    }

    private void represent(StatusRecord status){

        TextView height_text = (TextView) findViewById(R.id.sa_height);
        TextView weight_text = (TextView) findViewById(R.id.sa_weight);
        TextView bmi_text = (TextView) findViewById(R.id.sa_bmi);
        TextView body_status_text = (TextView) findViewById(R.id.sa_status);
        TextView ideal_text = (TextView) findViewById(R.id.sa_ideal);
        TextView to_ideal_text = (TextView) findViewById(R.id.sa_to_ideal);
        TextView normal_range_text = (TextView) findViewById(R.id.sa_normal_range);
        TextView difference_text = (TextView) findViewById(R.id.sa_difference);
        ImageView body_shape = (ImageView) findViewById(R.id.sa_body_status_image);
        ImageView face = (ImageView) findViewById(R.id.sa_fece_icon);

        float ideal_weight,to_ideal_weight;
        String  minimum_weight, maximum_weight;

        BMI bmi = new BMI();
        Common common = new Common();

        ideal_weight = bmi.getIdealWeight(status.getHeight());
        to_ideal_weight = bmi.getToIdealWeight(status.getWeight(), ideal_weight);
        minimum_weight = String.valueOf(common.float_to_int(bmi.getMinimum(status.getHeight(), ideal_weight)));
        maximum_weight = String.valueOf(common.float_to_int(bmi.getMaximum(status.getHeight(), ideal_weight)));

        height_text.setText(String.valueOf(status.getHeight()));
        weight_text.setText(String.valueOf(status.getWeight()));
        bmi_text.setText(String.valueOf(status.getBmi()));
        ideal_text.setText(String.valueOf(ideal_weight));
        if (to_ideal_weight>0)
            to_ideal_text.setText("+" + String.valueOf(to_ideal_weight));
        else
            to_ideal_text.setText(String.valueOf(to_ideal_weight));
        normal_range_text.setText(minimum_weight + " - " + maximum_weight);
        switch (status.getBody_status()){
            case 0:
                body_status_text.setText(getString(R.string.weight_status_underweight));
                body_shape.setImageResource(R.drawable.underweight);
                body_shape.setBackgroundResource(R.drawable.main_analyze_image_underweight);
                break;
            case 1:
                body_status_text.setText(getString(R.string.weight_status_normal));
                body_shape.setImageResource(R.drawable.median);
                body_shape.setBackgroundResource(R.drawable.main_analyze_image_normal);
                break;
            case 2:
                body_status_text.setText(getString(R.string.weight_status_standard));
                body_shape.setImageResource(R.drawable.standard);
                body_shape.setBackgroundResource(R.drawable.main_analyze_image_normal);
                break;
            case 3:
                body_status_text.setText(getString(R.string.weight_status_overweight));
                body_shape.setImageResource(R.drawable.overweight);
                body_shape.setBackgroundResource(R.drawable.main_analyze_image_overweight);
                break;
            case 4:
                body_status_text.setText(getString(R.string.weight_status_obesity1));
                body_shape.setImageResource(R.drawable.obese);
                body_shape.setBackgroundResource(R.drawable.main_analyze_image_obesity);
                break;
            case 5:
                body_status_text.setText(getString(R.string.weight_status_obesity2));
                body_shape.setImageResource(R.drawable.obese);
                body_shape.setBackgroundResource(R.drawable.main_analyze_image_obesity);
                break;
            case 6:
                body_status_text.setText(getString(R.string.weight_status_obesity3));
                body_shape.setImageResource(R.drawable.obese);
                body_shape.setBackgroundResource(R.drawable.main_analyze_image_obesity);
                break;
        }

        difference_text.setText(String.valueOf(status.getDifference()));
        face.setImageResource(common.get_face(status.getDifference()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_status, menu);

        save = menu.findItem(R.id.sa_action_save);
        delete = menu.findItem(R.id.sa_action_delete);
        edit = menu.findItem(R.id.sa_action_edit);
        cancel = menu.findItem(R.id.sa_action_cancel);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.sa_action_save:
                break;
            case R.id.sa_action_cancel:
                break;
            case R.id.sa_action_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(StatusActivity.this);
                builder.setMessage(getString(R.string.delete_warning));
                builder.setCancelable(true);
                builder.setPositiveButton(getString(R.string.yes),
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StatusTableAdapter database = new StatusTableAdapter(StatusActivity.this);
                        if (database.delete(record.getId())) {
                            Toast.makeText(StatusActivity.this,
                                    getString(R.string.delete_confirmed), Toast.LENGTH_LONG).show();
                            finish();
                        } else
                            Toast.makeText(StatusActivity.this,
                                    getString(R.string.delete_failed), Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                break;
            case R.id.sa_action_edit:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
