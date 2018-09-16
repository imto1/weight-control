package ir.behmerd.weightcontrol.data;

/**
 * Structure class to hold a record from PROGRAM table of Database.
 */
public class ProgramRecord {

    private int id, dietId;
    private String title, dietTitle, startDate, endDate;
    private boolean done;

    // Constructors
    public ProgramRecord(){}
    public ProgramRecord(int id, String title, String startDate,
                         String endDate, int dietId, boolean done){
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dietId = dietId;
        this.done = done;
    }
    public ProgramRecord(String title, String startDate,
                         String endDate, int dietId, boolean done){
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dietId = dietId;
        this.done = done;
    }

    // Commands
    public void setId(int id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }
    public void setDietId(int dietId){
        this.dietId = dietId;
    }
    public void setDone(boolean done){
        this.done = done;
    }
    public void setDietTitle(String dietTitle){
        this.dietTitle = dietTitle;
    }

    // Queries
    public int getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getStartDate(){
        return this.startDate;
    }
    public String getEndDate(){
        return this.endDate;
    }
    public int getDietId(){
        return this.dietId;
    }
    public boolean getDone(){
        return this.done;
    }
    public String getDietTitle(){
        return this.dietTitle;
    }
}
