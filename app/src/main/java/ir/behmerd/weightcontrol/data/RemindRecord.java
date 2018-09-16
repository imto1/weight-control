package ir.behmerd.weightcontrol.data;

/**
 * Structure class to hold a record from REMIND table of Database.
 */
public class RemindRecord {

    private int id, dietId, breakfastTime, snackTime, lunchTime, eveningTime, dinnerTime, bedTime;
    private String dietTitle;

    // Constructors
    public RemindRecord(){}
    public RemindRecord(int id, int dietId, int breakfastTime, int snackTime,
                        int lunchTime, int eveningTime, int dinnerTime, int bedTime){
        this.id = id;
        this.dietId = dietId;
        this.breakfastTime = breakfastTime;
        this.snackTime = snackTime;
        this.lunchTime = lunchTime;
        this.eveningTime = eveningTime;
        this.dinnerTime = dinnerTime;
        this.bedTime = bedTime;
    }
    public RemindRecord(int dietId, int breakfastTime, int snackTime,
                        int lunchTime, int eveningTime, int dinnerTime, int bedTime){
        this.dietId = dietId;
        this.breakfastTime = breakfastTime;
        this.snackTime = snackTime;
        this.lunchTime = lunchTime;
        this.eveningTime = eveningTime;
        this.dinnerTime = dinnerTime;
        this.bedTime = bedTime;
    }

    // Commands
    public void setId(int id){
        this.id = id;
    }
    public void setDietId(int dietId){
        this.dietId = dietId;
    }
    public void setBreakfastTime(int breakfastTime){
        this.breakfastTime = breakfastTime;
    }
    public void setSnackTime(int snackTime){
        this.snackTime = snackTime;
    }
    public void setLunchTime(int lunchTime){
        this.lunchTime = lunchTime;
    }
    public void setEveningTime(int eveningTime){
        this.eveningTime = eveningTime;
    }
    public void setDinnerTime(int dinnerTime){
        this.dinnerTime = dinnerTime;
    }
    public void setBedTime(int bedTime){
        this.bedTime = bedTime;
    }
    public void setDietTitle(String dietTitle){
        this.dietTitle = dietTitle;
    }

    // Queries
    public int getId(){
        return this.id;
    }
    public int getDietId(){
        return this.dietId;
    }
    public int getBreakfastTime(){
        return this.breakfastTime;
    }
    public int getSnackTime(){
        return this.snackTime;
    }
    public int getLunchTime(){
        return this.lunchTime;
    }
    public int getEveningTime(){
        return this.eveningTime;
    }
    public int getDinnerTime(){
        return this.dinnerTime;
    }
    public int getBedTime(){
        return this.bedTime;
    }
    public String getDietTitle(){
        return this.dietTitle;
    }
}
