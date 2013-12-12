package dataAccess;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 01.12.12
 * Time: 15:41
 * To change this template use File | Settings | File Templates.
 */
public class Work implements Serializable {
    private String workId;
    private String workName;
    private String startTime;
    private String endTime;
    private ArrayList<Relation> workRelations;
    private ArrayList<Position> workPositions;

    public Work(){

    }


    public void setWorkId(String workId){
        this.workId = workId;
    }
    public String getWorkId(){
        return this.workId;
    }
    public void setWorkName(String workName){
        this.workName = workName;
    }
    public String getWorkName(){
        return this.workName;
    }
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }
    public String getStartTime(){
        return this.startTime;
    }
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }
    public String getEndTime(){
        return this.endTime;
    }

    public ArrayList<Relation> getWorkRelations(){
        return this.workRelations;
    }

    public void setWorkRelations(ArrayList<Relation> workRelations){
        this.workRelations = workRelations;
    }

    public void addWorkRelation(Relation workRelation){
        this.workRelations.add(workRelation);
    }




    public void setWorkPositions(ArrayList<Position> workPositions){
        this.workPositions = workPositions;
    }

    public void addWorkPosition(Position workPosition){
        this.workPositions.add(workPosition);
    }

    public ArrayList<Position> getWorkPositions(){
        return this.workPositions;
    }







}
