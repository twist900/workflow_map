package workflowMap;

import com.sun.org.apache.xml.internal.utils.SerializableLocatorImpl;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 01.12.12
 * Time: 15:41
 * To change this template use File | Settings | File Templates.
 */
public class Work implements Serializable {
    Work(){

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
    public void setMainConn(String mainConn){
            this.mainConn = mainConn;
    }
    public String getMainConn(){
        return this.mainConn;
    }
    public void setSecondaryConn(String secondaryConn){
       if(secondaryConn != null)
           this.secondaryConn = secondaryConn.split(",");
    }
    public String[] getSecondaryConn(){
        return this.secondaryConn;
    }

    private String workId;
    private String workName;
    private String startTime;
    private String endTime;
    private String mainConn;
    private String[] secondaryConn;
}
