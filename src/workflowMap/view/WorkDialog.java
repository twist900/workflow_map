package workflowMap.view;

import workflowMap.dataAccess.DAO;
import workflowMap.model.PosTableModel;
import workflowMap.model.WorksTableModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WorkDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    public JTextField workNumField;
    public JFormattedTextField workStartField;
    private JTabbedPane tabbedPane1;
    public JFormattedTextField workEndField;
    private JButton savePosBtn;
    private JButton delPosBtn;
    private JButton newPosBtn;
    public JTextField depNameField;
    public JTextField quarterField;
    public JTextField hoursNumField;
    public JTable positionTable;
    public JTable connTable;
    private JButton newConnBtn;
    private JButton delConnBtn;
    private JButton saveConnBtn;
    public JTextField connWorkField;
    public JTextArea workNameField;
    private WorksTableModel worksTableModel;
    public PosTableModel posTableModel;
    public ListSelectionModel posSelectionModel;

    public WorkDialog(WorksTableModel worksTableModel, DAO dataObject) {

        this.worksTableModel = worksTableModel;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pack();

        posTableModel = new PosTableModel(dataObject);
        iniPositionTable(posTableModel);
        iniButtons();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
        setSize((Toolkit.getDefaultToolkit().getScreenSize().width)/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height)/2);
        setVisible(false);

    }



    public void iniPositionTable(PosTableModel posTableModel){
        positionTable.setModel(posTableModel);

        posSelectionModel = positionTable.getSelectionModel();
        posSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       // scrollPane1.setViewportView(execTable);
    }

    public void iniButtons(){
        try {
            newConnBtn.setContentAreaFilled(false);
            newPosBtn.setContentAreaFilled(false);
            File fAdd = new File(System.getProperty("user.dir"));
            BufferedImage addIcon = ImageIO.read(new File(fAdd.getPath() + "//images//add-icon.png"));
            newConnBtn.setIcon(new ImageIcon(addIcon));
            newPosBtn.setIcon(new ImageIcon(addIcon));

            delConnBtn.setContentAreaFilled(false);
            delPosBtn.setContentAreaFilled(false);
            File fDelete = new File(System.getProperty("user.dir"));
            BufferedImage deleteIcon = ImageIO.read(new File(fDelete.getPath()+"//images//Delete-icon.png"));
            delConnBtn.setIcon(new ImageIcon(deleteIcon));
            delPosBtn.setIcon(new ImageIcon(deleteIcon));

            saveConnBtn.setContentAreaFilled(false);
            savePosBtn.setContentAreaFilled(false);
            File fSave = new File(System.getProperty("user.dir"));
            BufferedImage saveIcon = ImageIO.read(new File(fSave.getPath()+"//images//check-icon.png"));
            saveConnBtn.setIcon(new ImageIcon(saveIcon));
            savePosBtn.setIcon(new ImageIcon(saveIcon));


        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public void addOkBtnListener(ActionListener okBtnListener){
        this.buttonOK.addActionListener(okBtnListener);
    }

    public void addCancelBtnListener(ActionListener cancelBtnListener){
        this.buttonCancel.addActionListener(cancelBtnListener);
    }

    public void addWindowCloseListener(WindowAdapter windowCloseListener){
        this.addWindowListener(windowCloseListener);
    }

    public void addNewPosBtnListener(ActionListener newPosBtnListener){
        this.newPosBtn.addActionListener(newPosBtnListener);
    }

    public void addDelPosBtnListener(ActionListener delPosBtnListener){
        this.delPosBtn.addActionListener(delPosBtnListener);
    }

    public void addSavePosBtnListener(ActionListener savePosBtnListener){
        this.savePosBtn.addActionListener(savePosBtnListener);
    }

    public void addNewConnBtnListener(ActionListener newConnBtnListener){
        this.newConnBtn.addActionListener(newConnBtnListener);
    }

    public void addDelConnBtnListener(ActionListener delConnBtnListener){
        this.delConnBtn.addActionListener(delConnBtnListener);
    }

    public void addSaveConnBtnListener(ActionListener saveConnBtnListener){
        this.saveConnBtn.addActionListener(saveConnBtnListener);
    }
    public void addPosSelectionModelListener(ListSelectionListener posSelectionModelListener){
        this.posSelectionModel.addListSelectionListener(posSelectionModelListener);
    }


}
