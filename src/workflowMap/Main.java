package workflowMap;

import workflowMap.controller.AppController;
import workflowMap.dataAccess.DAO;
import workflowMap.model.AppModel;
import workflowMap.view.MainForm;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 23.11.12
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args){
        DAO dataObject = new DAO();
        AppModel appModel = new AppModel(dataObject);
        MainForm mainForm = new MainForm(appModel);
        AppController appController = new AppController(mainForm, appModel);



    }

}
