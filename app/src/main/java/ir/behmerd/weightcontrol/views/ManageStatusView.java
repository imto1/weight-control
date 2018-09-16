package ir.behmerd.weightcontrol.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.List;

import ir.behmerd.weightcontrol.AddStatusActivity;
import ir.behmerd.weightcontrol.R;
import ir.behmerd.weightcontrol.StatusActivity;
import ir.behmerd.weightcontrol.common.BMI;
import ir.behmerd.weightcontrol.common.Common;
import ir.behmerd.weightcontrol.data.StatusRecord;

/**
 * Status table management view activity.
 */
public class ManageStatusView extends BaseAdapter {

    private Context context;
    List<StatusRecord> data;
    private int count;

    public ManageStatusView(Context context, List<StatusRecord> data) {
        // initialize class
        this.context = context;
        this.data = data;
        this.count = data.size();
    }

    @Override
    public int getCount() {
        // specify total list items
        return count;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        // specify list item objects
        ImageView icon;
        TextView no, weight, date, direction, difference;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // create list items
        Holder holder = null;
        if(convertView == null) {
            holder = new Holder();
            LayoutInflater inflater = ( LayoutInflater ) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.status_list_item, parent, false);

            holder.icon = (ImageView) convertView.findViewById(R.id.sli_icon);
            holder.no = (TextView) convertView.findViewById(R.id.sli_item_number);
            holder.weight =  (TextView) convertView.findViewById(R.id.sli_weight);
            holder.date = (TextView) convertView.findViewById(R.id.sli_date);
            holder.direction = (TextView) convertView.findViewById(R.id.sli_dir);
            holder.difference = (TextView) convertView.findViewById(R.id.sli_difference);


            Typeface nazanin_regular = Typeface.createFromAsset(context.getAssets(), "font/BNazanin.ttf");
            Typeface nazanin_bold = Typeface.createFromAsset(context.getAssets(), "font/BNaznnBd.ttf");
            holder.no.setTypeface(nazanin_regular);
            holder.weight.setTypeface(nazanin_bold);
            holder.date.setTypeface(nazanin_regular);
            holder.direction.setTypeface(nazanin_regular);
            holder.difference.setTypeface(nazanin_bold);
            convertView.setTag(holder);

            convertView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, StatusActivity.class);
                    intent.putExtra("status", data.get(position));
                    context.startActivity(intent);
                }
            });
        } else {
            holder = (Holder) convertView.getTag();
        }

        Common common = new Common();
        holder.no.setText( "." + String.valueOf(position + 1));
        holder.weight.setText(String.valueOf(data.get(position).getWeight()));
        holder.difference.setText(String.valueOf(data.get(position).getDifference()));
        if(data.get(position).getDifference() == 0) {
            holder.difference.setTextColor(context.getResources().getColor((R.color.black)));
            holder.direction.setText(" ");
        }
        else if(data.get(position).getDifference() > 0){
            holder.difference.setTextColor(context.getResources().getColor((R.color.obesity_image)));
            holder.direction.setText(R.string.special_character_up);
        }
        else if(data.get(position).getDifference() < 0){
            holder.difference.setTextColor(context.getResources().getColor((R.color.normal_image)));
            holder.direction.setText(R.string.special_character_down);
        }
        BMI bmi = new BMI();
        holder.weight.setTextColor(context.getResources().getColor(
                common.get_status_color(bmi.getStatus(data.get(position).getBmi()))));
        holder.date.setText(data.get(position).getCheckDate());
        holder.icon.setImageResource(common.get_face(data.get(position).getDifference()));
        return convertView;
    }
}
