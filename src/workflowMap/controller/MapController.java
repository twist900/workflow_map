package workflowMap.controller;

import workflowMap.model.MapModel;
import workflowMap.view.MapView;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 07.06.13
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */
public class MapController {

    private MapModel mapModel;
    private MapView mapView;


    public MapController(MapView mapView, MapModel mapModel){

        this.mapModel = mapModel;
        this.mapView = mapView;


        //mapsDialog.addOpenBtnListener(new OpenBtnListener());

    }


   /* private class OpenBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRowNum = mapsDialog.getDialogTable().getSelectedRow();
            if(selectedRowNum != -1){
                String mapId =  (String)mapsDialog.getDialogTable().getValueAt(selectedRowNum,0);
                mapModel.setCurrentMapId(mapId);

            }
        }
    }    */
}
