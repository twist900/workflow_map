package dataAccess;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 09.06.13
 * Time: 0:16
 * To change this template use File | Settings | File Templates.
 */
public class Map {
    private String number;
    private String name;
    private String startDate;

    public Map(){

    }

    public void setNumber(String number){
        this.number = number;
    }

    public String getNumber(){
        return this.number;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setStartDate(String startDate){
        this.startDate = startDate;
    }

    public String getStartDate(){
        return this.startDate;
    }



}
