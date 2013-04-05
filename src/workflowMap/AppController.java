package workflowMap;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppController implements ActionListener {

    Application applicationObj;

    public AppController(Application applicationObj){
        this.applicationObj = applicationObj;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == applicationObj.showTableBtn) {
            CardLayout cardLayout = (CardLayout) applicationObj.cardPanel.getLayout();
            cardLayout.show(applicationObj.cardPanel, Application.TABLEPANEL);
        }
        else if(e.getSource() == applicationObj.showDiagramBtn) {
            CardLayout cardLayout = (CardLayout) applicationObj.cardPanel.getLayout();
            cardLayout.show(applicationObj.cardPanel, Application.DIAGRAMPANEL);
        }
        else if(e.getSource() == applicationObj.addBtn){
            //applicationObj.workDialog.setFields(wor)
            applicationObj.workDialog.setVisible(true);

        }
    }
}
