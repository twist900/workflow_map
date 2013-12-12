package controller;

import dataAccess.Executor;
import dataAccess.Position;
import dataAccess.Work;
import model.WorksTableModel;
import view.WorkDialog;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 10.06.13
 * Time: 23:44
 * To change this template use File | Settings | File Templates.
 */
public class WorkDialogController {
    WorkDialog workDialog;
    WorksTableModel workTableModel;
    int selectedWorkIndex;
    Work currentWork;

    public WorkDialogController(WorkDialog workDialog, WorksTableModel workTableModel) {
        this.workDialog = workDialog;
        this.workTableModel = workTableModel;


        this.workDialog.addOkBtnListener(new OkBtnListener());
        this.workDialog.addCancelBtnListener(new CancelBtnListener());
        this.workDialog.addWindowCloseListener(new WindowCloseListener());
        this.workDialog.addNewPosBtnListener(new NewPosBtnListener());
        this.workDialog.addSavePosBtnListener(new SavePosBtnListener());
        this.workDialog.addDelPosBtnListener(new DelPosBtnListener());
        this.workDialog.addPosSelectionModelListener(new PosSelectionModelListener());
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
        currentWork = new Work();
        currentWork.setWorkId(workDialog.workNumField.getText());
        currentWork.setWorkName(workDialog.workNameField.getText());
        currentWork.setStartTime(workDialog.workStartField.getText());
        currentWork.setEndTime(workDialog.workEndField.getText());
        currentWork.setWorkPositions(workDialog.posTableModel.getPositions());
        currentWork.setWorkRelations(workDialog.relationTableModel.getRelations());

        if(workDialog.getCurrentWorkIndex() == -1){

            workTableModel.addWork(currentWork);
        }
        else{
            workTableModel.editWorkAt(workDialog.getCurrentWorkIndex(), currentWork);
        }
        workDialog.dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        workDialog.dispose();
    }


    private class NewPosBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            workDialog.depNameField.setText("");
            workDialog.quarterField.setText("");
            workDialog.hoursNumField.setText("");

        }
    }

    private class SavePosBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Executor executor = new Executor();
            executor.setName(workDialog.depNameField.getText().trim());

            Position position = new Position();
            position.setExecutor(executor);
            position.setQuarter(workDialog.quarterField.getText().trim());
            position.setHours(workDialog.hoursNumField.getText().trim());
            workDialog.posTableModel.addPosition(position);




        }
    }

    private class DelPosBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRowNum = workDialog.positionTable.getSelectedRow();
            if(selectedRowNum != -1){
               workDialog.posTableModel.removePositionAt(selectedRowNum);
            }
        }
    }

    private class PosSelectionModelListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int selectedIndex = e.getFirstIndex();
            Position selectedPosition = workDialog.posTableModel.getPositionAt(selectedIndex);
            workDialog.depNameField.setText(selectedPosition.getExecutor().getName());
            workDialog.quarterField.setText(selectedPosition.getQuarter());
            workDialog.hoursNumField.setText(selectedPosition.getHours());

        }
    }
}
