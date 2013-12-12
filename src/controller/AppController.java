package controller;

import model.AppModel;
import model.MapModel;
import view.MainForm;
import view.WorkDialog;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class AppController {

    private MainForm mainForm;
    private AppModel appModel;

    public AppController(MainForm mainForm, AppModel appModel){
        this.mainForm = mainForm;
        this.appModel = appModel;

        this.mainForm.addDeleteBtnListener(new DeleteBtnListener());
        this.mainForm.addAddBtnListener(new AddBtnListener());
        this.mainForm.addSaveBtnListener(new SaveBtnListener());
        this.mainForm.addSavePdfBtnListener(new SavePDFBtnListener());
        this.mainForm.addOpenMapBtnListener(new OpenMapBtnListener());
        this.mainForm.addEditBtnListener(new EditBtnListener());




    }

    private class DeleteBtnListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            System.out.println("deleteBtn pressed");

        }
    }

    private class AddBtnListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String currentMapId = appModel.getDataObject().getCurrentMapId();
            if(currentMapId != null){
                int tabIndex = mainForm.tabbedPane1.getSelectedIndex();
                if(tabIndex != -1){
                    String tabTitle = mainForm.tabbedPane1.getTitleAt(tabIndex);
                    if(tabTitle.equals("Works")){
                        mainForm.worksTableModel.setCurrentWorkId(-1);
                        createAndShowWorkDialog(-1);
                    }
                    else if(tabTitle.equals("Executers")){
                        mainForm.sigDialog.setVisible(true);
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "To perform this action choose a workflow map");
            }



        }
    }

    private class SaveBtnListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String currentMapId = appModel.getDataObject().getCurrentMapId();
            if(currentMapId != null){
                int tabIndex = mainForm.tabbedPane1.getSelectedIndex();
                if(tabIndex != -1){
                    String tabTitle = mainForm.tabbedPane1.getTitleAt(tabIndex);
                    if(tabTitle.equals("Works")){
                       // appModel.getDataObject().insertWorks(mainForm.worksTableModel.getTempWorks());
                    }
                    else if(tabTitle.equals("Executers")){

                    }
                }

            }
        }

    }

    private void createAndShowWorkDialog(int currentWorkIndex){
        WorkDialog workDialog = new WorkDialog(mainForm.worksTableModel, appModel.getDataObject());
        workDialog.setCurrentWorkIndex(currentWorkIndex);
        WorkDialogController workDialogController = new WorkDialogController(workDialog, mainForm.worksTableModel);
        workDialog.setVisible(true);
    }
    private class SavePDFBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JFileChooser fc = new JFileChooser();
            fc.setAcceptAllFileFilterUsed(false);
            fc.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    String extension = getExtension(f);
                    if (extension != null) {
                        if (extension.equals("pdf")) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                    return false;
                }

                @Override
                public String getDescription() {
                    return null;
                }
            });
            int returnValue = fc.showSaveDialog(mainForm.frame);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File pdfFile = fc.getSelectedFile();
                try {
                    MapModel.toPdf(pdfFile, mainForm.mapView);
                } catch (IOException m) {
                    m.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }


            } else {


            }
        }
    }

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

    private class OpenMapBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainForm.mapDialog.setVisible(true);
        }
    }


    private class EditBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String currentMapId = appModel.getDataObject().getCurrentMapId();
            if(currentMapId != null){
                int tabIndex = mainForm.tabbedPane1.getSelectedIndex();
                if(tabIndex != -1){
                    String tabTitle = mainForm.tabbedPane1.getTitleAt(tabIndex);
                    if(tabTitle.equals("Works")){
                        int selectedRowNum = mainForm.worksTable.getSelectedRow();
                        if(selectedRowNum != -1){
                            int workId =  Integer.parseInt((String)mainForm.worksTable.getValueAt(selectedRowNum,0));
                            mainForm.worksTableModel.setCurrentWorkId(workId);
                            createAndShowWorkDialog(selectedRowNum);
                        }
                        else if(selectedRowNum == -1){
                            JOptionPane.showMessageDialog(null, "Choose a workflow map for editing");
                        }
                    }
                    else if(tabTitle.equals("Executers")){
                        mainForm.sigDialog.setVisible(true);
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "To perform this action choose a workflow map");
            }
        }
    }
}
//test