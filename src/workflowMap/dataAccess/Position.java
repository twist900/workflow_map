package workflowMap.dataAccess;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 07.06.13
 * Time: 21:48
 * To change this template use File | Settings | File Templates.
 */
public class Position {

    private int id;
    private String quarter;
    private String hours;
    private Executor executor;
    private int execId;
    private int workId;

    public Position(){

    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setQuarter(String quarter){
        this.quarter = quarter;
    }

    public String getQuarter(){
        return quarter;
    }

    public void setHours(String hours){
        this.hours = hours;
    }

    public String getHours(){
        return hours;
    }

    public void setExecId(int execId){
        this.execId = execId;
    }

    public int getExecId(){
        return execId;
    }

    public void setWorkId(int workId){
        this.workId = workId;
    }

    public int getWorkId(){
        return this.workId;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }
}
