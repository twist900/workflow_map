package view;

import model.MapTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MapDialog extends JDialog {



    private JButton openBtn = new JButton("Открыть");
    private JButton newBtn = new JButton("Создать");
    private JButton cancelBtn = new JButton("Отмена");

    private JTable dialogTable;
    MapTableModel mapTableModel;
    public NewMapDialog newMapDialog;



    public MapDialog(MapTableModel mapTableModel){

        this.mapTableModel = mapTableModel;

        newMapDialog = new NewMapDialog();

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        Double frameWidth = dimension.getWidth()/3;
        Double frameHeight = dimension.getHeight()/3;
        setSize(frameWidth.intValue(), frameHeight.intValue());


        dialogTable = new JTable();
        dialogTable.setModel(this.mapTableModel);
        JScrollPane scrollPane = new JScrollPane(dialogTable);

        JPanel p1 = new JPanel(new BorderLayout());
        p1.add(scrollPane, BorderLayout.CENTER);


        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        p2.add(openBtn);
        p2.add(newBtn);
        p2.add(cancelBtn);

        p1.add(p2, BorderLayout.SOUTH);

        this.add(p1);

        /*jbtSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appObj.dataObject.addWork(getWorkFromFields());

            }
        });
        jbtSubmit.addActionListener(appObj.appController);
        */

        setVisible(false);

    }

    public JTable getDialogTable(){
        return dialogTable;
    }

    public void addOpenBtnListener(ActionListener openBtnListener){
        openBtn.addActionListener(openBtnListener);
    }

    public void addNewBtnListener(ActionListener newBtnListener){
        newBtn.addActionListener(newBtnListener);
    }

    public void addCancelBtnListener(ActionListener cancelBtnListener){
        cancelBtn.addActionListener(cancelBtnListener);
    }




}
