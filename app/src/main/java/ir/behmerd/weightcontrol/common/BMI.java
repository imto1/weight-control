package ir.behmerd.weightcontrol.common;

/**
 * Body Mass Index class.
 */
public class BMI {
    private Common common;
    public BMI(){
        this.common = new Common();
    }

    final float NORMAL = (float) 18.5;
    final float STANDARD = (float) 23;
    final float OVERWEIGHT = (float) 25;
    final float OBESITY_LEVEL1 = (float) 30;
    final float OBESITY_LEVEL2 = (float) 35;
    final float OBESITY_LEVEL3 = (float) 40;

    public float getBMI(int height, float weight) {
        float Height = height / 100;
        Height += (height % 100) * 0.01;
        if (height > 0 && weight > 0)
            return this.common.fix((weight / (Height * Height)), 1);
        return 0;
    }

    public int getStatus(float bmi){
        if (bmi < NORMAL)
            return 0;
        else if ((bmi >= NORMAL) && (bmi < STANDARD))
            return 1;
        else if ((bmi >= STANDARD) && (bmi < OVERWEIGHT))
            return 2;
        else if ((bmi >= OVERWEIGHT) && (bmi < OBESITY_LEVEL1))
            return 3;
        else if ((bmi >= OBESITY_LEVEL1) && (bmi < OBESITY_LEVEL2))
            return 4;
        else if ((bmi >= OBESITY_LEVEL2) && (bmi < OBESITY_LEVEL3))
            return 5;
        else if (bmi >= OBESITY_LEVEL3)
            return 6;

        return -1;
    }

    public float getIdealWeight(int height)
    {
        float Height = (height * height / 10000);
        Height += ((height * height % 10000) / 1000) * 0.1;
        return this.common.fix(Height * 24, 1);
    }

    public float getToIdealWeight(float weight, float idealWeight)
    {
        String result = String.valueOf(this.common.fix(idealWeight - weight, 1));
        return Float.valueOf(result);
    }

    public float getMinimum(int height, float idealWeight) {
        float minimum = idealWeight;
        boolean endOfRange = false;
        int status;

        while (!endOfRange) {
            minimum--;
            status = getStatus(getBMI(height, minimum));
            if (status!=2)
                endOfRange = true;
        }

        return minimum+1;
    }

    public float getMaximum(int height, float idealWeight) {
        float maximum = idealWeight;
        boolean endOfRange = false;
        int status;

        while (!endOfRange) {
            maximum++;
            status = getStatus(getBMI(height, maximum));
            if (status!=2)
                endOfRange = true;
        }

        return  maximum-1;
    }
}
