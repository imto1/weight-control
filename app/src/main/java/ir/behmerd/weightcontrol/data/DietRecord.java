package ir.behmerd.weightcontrol.data;

/**
 * Structure class to hold a record from DIET table of Database.
 */
public class DietRecord {

    private int id, weekDay;
    private String title, breakfast, morningSnack, lunch, eveningMeal, dinner,beforeBedtime;

    // Constructors
    public DietRecord(){}
    public DietRecord(int id, String title, int weekDay, String breakfast, String morningSnack,
                      String lunch, String eveningMeal, String dinner, String beforeBedtime){
        this.id = id;
        this.title = title;
        this.weekDay = weekDay;
        this.breakfast = breakfast;
        this.morningSnack = morningSnack;
        this.lunch = lunch;
        this.eveningMeal = eveningMeal;
        this.dinner = dinner;
        this.beforeBedtime = beforeBedtime;
    }
    public DietRecord(String title, int weekDay, String breakfast, String morningSnack, String lunch,
                      String eveningMeal, String dinner, String beforeBedtime){
        this.title = title;
        this.weekDay = weekDay;
        this.breakfast = breakfast;
        this.morningSnack = morningSnack;
        this.lunch = lunch;
        this.eveningMeal = eveningMeal;
        this.dinner = dinner;
        this.beforeBedtime = beforeBedtime;
    }

    // Commands
    public void setId(int id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setWeekDay(int weekDay){
        this.weekDay = weekDay;
    }
    public void setBreakfast(String breakfast){
        this.breakfast = breakfast;
    }
    public void setMorningSnack(String morningSnack){
        this.morningSnack = morningSnack;
    }
    public void setLunch(String lunch){
        this.lunch = lunch;
    }
    public void setEveningMeal(String eveningMeal){
        this.eveningMeal = eveningMeal;
    }
    public void setDinner(String dinner){
        this.dinner = dinner;
    }
    public void setBeforeBedtime(String beforeBedtime){
        this.beforeBedtime = beforeBedtime;
    }

    // Queries
    public int getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public int getWeekDay(){
        return this.weekDay;
    }
    public String getBreakfast(){
        return this.breakfast;
    }
    public String getMorningSnack(){
        return this.morningSnack;
    }
    public String getLunch(){
        return this.lunch;
    }
    public String getEveningMeal(){
        return this.eveningMeal;
    }
    public String getDinner(){
        return this.dinner;
    }
    public String getBeforeBedtime(){
        return this.beforeBedtime;
    }
}
