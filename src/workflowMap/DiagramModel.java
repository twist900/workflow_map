package workflowMap;

import workflowMap.DAO;

import javax.sql.rowset.CachedRowSet;
import javax.swing.*;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 30.11.12
 * Time: 21:58
 * To change this template use File | Settings | File Templates.
 */
public class DiagramModel {

    DiagramModel(DAO dataObject) {
        this.dataObject = dataObject;
        works = dataObject.getAllWorksEntities();

    }




    Vector<Work> works;
    DAO dataObject;
    JTable table;
    CachedRowSet worksRowSet;
    CachedRowSet curWorksRowSet;
}
