package dataAccess;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 13.06.13
 * Time: 19:23
 * To change this template use File | Settings | File Templates.
 */
public class Executor {

    private int id;
    private String name;

    public Executor(){

    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }


}
