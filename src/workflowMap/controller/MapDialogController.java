package workflowMap.controller;

import workflowMap.model.MapTableModel;
import workflowMap.view.MapDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 10.06.13
 * Time: 19:48
 * To change this template use File | Settings | File Templates.
 */
public class MapDialogController {

    private MapDialog mapDialog;
    private MapTableModel mapTableModel;

    public MapDialogController(MapDialog mapDialog, MapTableModel mapTableModel){

        this.mapDialog = mapDialog;
        this.mapTableModel = mapTableModel;

        this.mapDialog.addOpenBtnListener(new OpenBtnListener());
        this.mapDialog.addNewBtnListener(new newBtnListener());
    }

    private class OpenBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRowNum = mapDialog.getDialogTable().getSelectedRow();
            if(selectedRowNum != -1){
                String mapId =  (String)mapDialog.getDialogTable().getValueAt(selectedRowNum,0);
                mapTableModel.setCurrentMapId(mapId);
                mapDialog.dispose();

            }
        }
    }

    private class newBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mapDialog.newMapDialog.setVisible(true);
        }
    }
}
