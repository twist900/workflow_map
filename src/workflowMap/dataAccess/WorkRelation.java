package workflowMap.dataAccess;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 09.06.13
 * Time: 14:51
 * To change this template use File | Settings | File Templates.
 */
public class WorkRelation {
    private int id;
    private int workIdPrev;
    private int workIdNext;

    public WorkRelation(){

    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setWorkIdPrev(int workIdPrev){
        this.workIdPrev = workIdPrev;
    }

    public int getWorkIdPrev(){
        return this.workIdPrev;
    }

    public void setWorkIdNext(int workIdNext){
        this.workIdNext = workIdNext;
    }

    public int getWorkIdNext(){
        return this.workIdNext;
    }
}

