package controller;

import model.WorksTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 05.06.13
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */
public class WorksTableController {

    private JTable worksTable;
    private WorksTableModel worksTableModel;
    private WorksTableModel newWorksTableModel;

    public WorksTableController(JTable worksTable, WorksTableModel worksTableModel){
        this.worksTable = worksTable;
        this.worksTableModel = worksTableModel;
        worksTable.setModel(worksTableModel);

    }

    public class AddBtnListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

          /*  try {

                worksTableModel.insertRow("50", "51", "52");


            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } */

        }
    }

    public class DeleteBtnListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

        }
    }


   public class SaveBtnListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
           /* try {
                worksTableModel.worksRowSet.acceptChanges(DAO.getConnection());



            } catch (SQLException sqle) {
                sqle.printStackTrace();
                // Now revert back changes
                try {
                    createNewTableModel();
                } catch (SQLException sqle2) {
                    sqle2.printStackTrace();
                }

            }   */

        }
    }
}
