package workflowMap;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 19.03.13
 * Time: 16:26
 * To change this template use File | Settings | File Templates.
 */
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
            applicationObj.workDialog.setVisible(true);
        }
    }
}
