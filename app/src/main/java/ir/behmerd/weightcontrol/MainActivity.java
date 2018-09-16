package ir.behmerd.weightcontrol;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import ir.behmerd.weightcontrol.common.BMI;
import ir.behmerd.weightcontrol.common.Common;
import ir.behmerd.weightcontrol.common.PersianCalendar;
import ir.behmerd.weightcontrol.data.StatusRecord;
import ir.behmerd.weightcontrol.data.StatusTableAdapter;


public class MainActivity extends ActionBarActivity {

    private int body_status;
    private float body_mass_index, last_weight, difference_weight;
    private BMI bmi;
    private Common common;
    private String edit_height_text, edit_weight_text;
    private MenuItem save_menu;


    private void make_chart(){
        try {
            StatusTableAdapter database = new StatusTableAdapter(MainActivity.this);
            List<StatusRecord> data_table = database.getLast5();

            PersianCalendar calendar = new PersianCalendar();
            ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
            ArrayList<String> labels = new ArrayList<String>();
            int row = 0;
            for (int i = data_table.size() - 1;i >= 0;i--) {
                entries.add(new BarEntry(data_table.get(i).getWeight(),row++));
                String check_date = String.valueOf(calendar.Utilities.extractYear(data_table.get(i).getCheckDate())) +
                        "/" + String.valueOf(calendar.Utilities.extractMonth(data_table.get(i).getCheckDate()));
                labels.add(check_date.substring(2));
            }

            last_weight = data_table.get(0).getWeight();

            BarDataSet dataset = new BarDataSet(entries, getString(R.string.main_form_weight));

            BarChart chart = (BarChart) findViewById(R.id.chart);
            BarData data = new BarData(labels, dataset);
            chart.setData(data);
            chart.setDescription(getString(R.string.main_chart_title));
            chart.animateY(2000);
            dataset.setColors(ColorTemplate.PASTEL_COLORS);
            chart.invalidate();
        } catch (Exception exception){
            Log.e("MainActivity", "Error on making chart!", exception);
        }
    }

    private void set_fonts(){
        Typeface nazanin_bold = Typeface.createFromAsset(getAssets(), "font/BNaznnBd.ttf");
        Typeface nazanin_regular = Typeface.createFromAsset(getAssets(), "font/BNazanin.ttf");

        EditText height_text = (EditText) findViewById(R.id.et_main_height);
        EditText weight_text = (EditText) findViewById(R.id.et_main_weight);
        Button calculate_button = (Button) findViewById(R.id.btn_main_calculate);
        TextView height_label = (TextView) findViewById(R.id.tv_main_height);
        TextView weight_label = (TextView) findViewById(R.id.tv_main_weight);
        TextView bmi_label = (TextView) findViewById(R.id.tv_main_bmi);
        TextView body_status_label = (TextView) findViewById(R.id.tv_main_body_status);
        TextView ideal_weight_label = (TextView) findViewById(R.id.tv_main_ideal_weight);
        TextView to_ideal_weight_label = (TextView) findViewById(R.id.tv_main_to_ideal_weight);
        TextView normal_weight_range_label = (TextView) findViewById(R.id.tv_main_normal_range);
        TextView height_caption_label = (TextView) findViewById(R.id.tv_main_height_caption);
        TextView weight_caption_label = (TextView) findViewById(R.id.tv_main_weight_caption);
        TextView bmi_caption_label = (TextView) findViewById(R.id.tv_main_bmi_caption);
        TextView body_status_caption_label = (TextView) findViewById(R.id.tv_main_body_status_caption);
        TextView ideal_weight_caption_label = (TextView) findViewById(R.id.tv_main_ideal_weight_caption);
        TextView to_ideal_weight_caption_label = (TextView) findViewById(R.id.tv_main_to_ideal_weight_caption);
        TextView normal_weight_range_caption_label = (TextView) findViewById(R.id.tv_main_normal_range_caption);
        TextView difference_caption_label = (TextView) findViewById(R.id.main_difference_caption);
        TextView difference_label = (TextView) findViewById(R.id.main_difference);

        height_text.setTypeface(nazanin_regular);
        weight_text.setTypeface(nazanin_regular);
        height_label.setTypeface(nazanin_regular);
        weight_label.setTypeface(nazanin_regular);
        normal_weight_range_label.setTypeface(nazanin_regular);
        bmi_label.setTypeface(nazanin_regular);
        body_status_label.setTypeface(nazanin_regular);
        ideal_weight_label.setTypeface(nazanin_regular);
        to_ideal_weight_label.setTypeface(nazanin_regular);
        height_caption_label.setTypeface(nazanin_regular);
        weight_caption_label.setTypeface(nazanin_regular);
        normal_weight_range_caption_label.setTypeface(nazanin_regular);
        bmi_caption_label.setTypeface(nazanin_regular);
        body_status_caption_label.setTypeface(nazanin_regular);
        ideal_weight_caption_label.setTypeface(nazanin_regular);
        to_ideal_weight_caption_label.setTypeface(nazanin_regular);
        difference_label.setTypeface(nazanin_regular);
        difference_caption_label.setTypeface(nazanin_regular);
        calculate_button.setTypeface(nazanin_bold);
    }

    private void represent_result(int body_status){
        TextView height_label = (TextView) findViewById(R.id.tv_main_height);
        TextView weight_label = (TextView) findViewById(R.id.tv_main_weight);
        TextView bmi_label = (TextView) findViewById(R.id.tv_main_bmi);
        TextView body_status_label = (TextView) findViewById(R.id.tv_main_body_status);
        TextView ideal_weight_label = (TextView) findViewById(R.id.tv_main_ideal_weight);
        TextView to_ideal_weight_label = (TextView) findViewById(R.id.tv_main_to_ideal_weight);
        TextView normal_weight_range_label = (TextView) findViewById(R.id.tv_main_normal_range);
        ImageView body_shape = (ImageView) findViewById(R.id.iv_main_analyze);
        ImageView face = (ImageView) findViewById(R.id.main_face_icon);
        TextView difference = (TextView) findViewById(R.id.main_difference);

        int height;
        float weight,ideal_weight,to_ideal_weight;
        String  minimum_weight, maximum_weight;

        height = Integer.valueOf(edit_height_text);
        weight = Float.valueOf(edit_weight_text);
        ideal_weight = bmi.getIdealWeight(height);
        to_ideal_weight = bmi.getToIdealWeight(weight, ideal_weight);
        minimum_weight = String.valueOf(common.float_to_int(bmi.getMinimum(height, ideal_weight)));
        maximum_weight = String.valueOf(common.float_to_int(bmi.getMaximum(height, ideal_weight)));

        height_label.setText(edit_height_text);
        weight_label.setText(edit_weight_text);
        bmi_label.setText(String.valueOf(body_mass_index));
        ideal_weight_label.setText(String.valueOf(ideal_weight));
        if (to_ideal_weight>0)
            to_ideal_weight_label.setText("+" + String.valueOf(to_ideal_weight));
        else
            to_ideal_weight_label.setText(String.valueOf(to_ideal_weight));
        normal_weight_range_label.setText(minimum_weight + " - " + maximum_weight);
        switch (body_status){
            case 0:
                body_status_label.setText(getString(R.string.weight_status_underweight));
                body_shape.setImageResource(R.drawable.underweight);
                body_shape.setBackgroundResource(R.drawable.main_analyze_image_underweight);
                break;
            case 1:
                body_status_label.setText(getString(R.string.weight_status_normal));
                body_shape.setImageResource(R.drawable.median);
                body_shape.setBackgroundResource(R.drawable.main_analyze_image_normal);
                break;
            case 2:
                body_status_label.setText(getString(R.string.weight_status_standard));
                body_shape.setImageResource(R.drawable.standard);
                body_shape.setBackgroundResource(R.drawable.main_analyze_image_normal);
                break;
            case 3:
                body_status_label.setText(getString(R.string.weight_status_overweight));
                body_shape.setImageResource(R.drawable.overweight);
                body_shape.setBackgroundResource(R.drawable.main_analyze_image_overweight);
                break;
            case 4:
                body_status_label.setText(getString(R.string.weight_status_obesity1));
                body_shape.setImageResource(R.drawable.obese);
                body_shape.setBackgroundResource(R.drawable.main_analyze_image_obesity);
                break;
            case 5:
                body_status_label.setText(getString(R.string.weight_status_obesity2));
                body_shape.setImageResource(R.drawable.obese);
                body_shape.setBackgroundResource(R.drawable.main_analyze_image_obesity);
                break;
            case 6:
                body_status_label.setText(getString(R.string.weight_status_obesity3));
                body_shape.setImageResource(R.drawable.obese);
                body_shape.setBackgroundResource(R.drawable.main_analyze_image_obesity);
                break;
        }
        if(last_weight != 0)
            difference_weight = common.fix(weight - last_weight, 1);
        else
            difference_weight = 0;

        difference.setText(String.valueOf(difference_weight));
        face.setImageResource(common.get_face(difference_weight));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bmi = new BMI();
        common = new Common();
        set_fonts();
        make_chart();

        final EditText etHeight = (EditText) findViewById(R.id.et_main_height);
        final EditText etWeight = (EditText) findViewById(R.id.et_main_weight);
        Button btnCalculate = (Button) findViewById(R.id.btn_main_calculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_height_text = etHeight.getText().toString();
                edit_weight_text = etWeight.getText().toString();
                if (!edit_height_text.equals("") && edit_height_text != "") {
                    if (!edit_weight_text.equals("") && edit_weight_text != "") {
                        // hiding form layout & display analyze layout
                        LinearLayout form_layout = (LinearLayout) findViewById(R.id.main_form_area);
                        LinearLayout chart_layout = (LinearLayout) findViewById(R.id.main_chart_area);
                        form_layout.setVisibility(View.INVISIBLE);
                        chart_layout.setVisibility(View.INVISIBLE);
                        form_layout.getLayoutParams().height = 0;
                        chart_layout.getLayoutParams().height = 0;
                        ScrollView analyze_layout = (ScrollView) findViewById(R.id.main_analyze_scroll);
                        analyze_layout.setVisibility(View.VISIBLE);
                        analyze_layout.getLayoutParams().height = ActionBar.LayoutParams.WRAP_CONTENT;


                        // result represent
                        int height = Integer.valueOf(edit_height_text);
                        float weight = Float.valueOf(edit_weight_text);
                        body_mass_index = bmi.getBMI(height, weight);
                        body_status = bmi.getStatus(body_mass_index);
                        represent_result(body_status);

                        save_menu.setEnabled(true);
                    } else {
                        Toast.makeText(MainActivity.this, getString(R.string.main_form_null_weight), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.main_form_null_height), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        make_chart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        save_menu = menu.findItem(R.id.action_save);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_settings:
                break;
            case R.id.action_new:
                // hiding analyze layout & display form layout
                LinearLayout form_layout = (LinearLayout) findViewById(R.id.main_form_area);
                LinearLayout chart_layout = (LinearLayout) findViewById(R.id.main_chart_area);
                EditText etHeight = (EditText) findViewById(R.id.et_main_height);
                EditText etWeight = (EditText) findViewById(R.id.et_main_weight);
                form_layout.setVisibility(View.VISIBLE);
                chart_layout.setVisibility(View.VISIBLE);
                form_layout.getLayoutParams().height = ActionBar.LayoutParams.WRAP_CONTENT;
                chart_layout.getLayoutParams().height =  ActionBar.LayoutParams.WRAP_CONTENT;
                etHeight.setText("");
                etWeight.setText("");
                ScrollView analyze_layout = (ScrollView) findViewById(R.id.main_analyze_scroll);
                analyze_layout.setVisibility(View.INVISIBLE);
                analyze_layout.getLayoutParams().height = 0;
                save_menu.setEnabled(false);
                break;
            case R.id.action_save:
                StatusRecord status = new StatusRecord();
                status.setHeight(Integer.valueOf(edit_height_text));
                status.setWeight(Float.valueOf(edit_weight_text));
                status.setBmi(body_mass_index);
                status.setBody_status(body_status);
                status.setDifference(difference_weight);
                Intent intent = new Intent(MainActivity.this, AddStatusActivity.class);
                intent.putExtra("status", status);
                startActivity(intent);
                break;
            case R.id.action_manage_data:
                startActivityForResult(new Intent(MainActivity.this, DatabaseManagementActivity.class), 1);
        }

        return super.onOptionsItemSelected(item);
    }
}
