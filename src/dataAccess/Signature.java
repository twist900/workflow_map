package dataAccess;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 09.06.13
 * Time: 0:16
 * To change this template use File | Settings | File Templates.
 */
public class Signature {

    private int id;
    private String surName;
    private String name;
    private String middleName;
    private String position;

    public Signature(){

    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setSurName(String surName){
        this.surName = surName;
    }

    public String getSurName(){
        return this.surName;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setMiddleName(String middleName){
        this.middleName = middleName;
    }

    public String getMiddleName(){
        return this.middleName;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public String getPosition(){
        return this.position;
    }
}
