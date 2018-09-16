package ir.behmerd.weightcontrol.common;

import com.magnetadservices.sdk.StepEvents;

import java.util.Locale;

import ir.behmerd.weightcontrol.R;

/**
 * Common class library.
 */
public class Common {
    public Common(){}

    public float fix(float value, int point){
        String expression = "%." + point + "f";
        String fixed = String.format(Locale.US, expression, value);
        if (fixed.substring(0, 1).equals(".") || fixed.substring(0, 1) == ".")
            fixed = "0" + fixed;

        return Float.valueOf(fixed);
    }

    public int float_to_int(float value){
        String value_string = String.valueOf(fix(value, 0));
        value_string = value_string.substring(0, value_string.indexOf("."));
        return Integer.valueOf(value_string);
    }

    public int get_face(float difference){
        if(difference < -1)
            return R.drawable.loss_high;
        else if(difference >= -1 && difference < -0.5)
            return R.drawable.loss_med;
        else if(difference >= -0.5 && difference < 0)
            return R.drawable.loss_low;
        else if(difference == 0)
            return R.drawable.nochange;
        else if(difference > 0 && difference <= 0.5)
            return R.drawable.gain_low;
        else if(difference > 0.5 && difference <= 1)
            return R.drawable.gain_med;
        else if( difference > 1)
            return R.drawable.gain_high;
        return 0;
    }

    public int get_status_color(int status){
        switch (status){
            case 0:
                return R.color.underweight;
            case 1:
                return R.color.normal;
            case 2:
                return R.color.normal;
            case 3:
                return R.color.overweight;
            case 4:
                return R.color.obesity;
            case 5:
                return R.color.obesity;
            case 6:
                return R.color.obesity;
        }
        return R.color.black;
    }
}
