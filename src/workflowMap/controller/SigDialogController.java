package workflowMap.controller;

import workflowMap.model.SigTableModel;
import workflowMap.view.SigDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 10.06.13
 * Time: 20:37
 * To change this template use File | Settings | File Templates.
 */
public class SigDialogController {

    SigDialog sigDialog;
    SigTableModel sigTableModel;

    public SigDialogController(SigDialog sigDialog, SigTableModel sigTableModel) {
        this.sigDialog = sigDialog;
        this.sigTableModel = sigTableModel;

        this.sigDialog.addOkBtnListener(new OkBtnListener());
        this.sigDialog.addCancelBtnListener(new CancelBtnListener());
        this.sigDialog.addWindowCloseListener(new WindowCloseListener());
    }

    private class OkBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            onOK();
        }
    }

    private class CancelBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           onCancel();
        }
    }

    private class WindowCloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            onCancel();
        }
    }


    private void onOK() {
        // add your code here
        sigDialog.dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        sigDialog.dispose();
    }


}
