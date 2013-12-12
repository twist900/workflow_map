package model;

import dataAccess.DAO;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 10.06.13
 * Time: 18:59
 * To change this template use File | Settings | File Templates.
 */
public class AppModel {

    private DAO dataObject;

    public AppModel(DAO dataObject){
        this.dataObject = dataObject;
    }

    public DAO getDataObject(){
        return this.dataObject;
    }


}
