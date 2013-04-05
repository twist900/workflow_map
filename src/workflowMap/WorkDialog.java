package workflowMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkDialog extends JDialog {

    WorkDialog workDialog;

    private JTextField jtfId = new JTextField();
    private JTextField jtfName = new JTextField();
    private JTextField jtfStart = new JTextField();
    private JTextField jtfEnd = new JTextField();
    private JTextField jtfMainConn = new JTextField();
    private JTextField jtfSecConn = new JTextField();

    private JButton jbtSubmit = new JButton("Сохранить");

    public WorkDialog(JFrame parentFrame, boolean isModal){
        super(parentFrame, isModal);
        workDialog = this;
        setTitle("Работа");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        Double frameWidth = dimension.getWidth()/3;
        Double frameHeight = dimension.getHeight()/3;
        setSize(frameWidth.intValue(), frameHeight.intValue());

        JPanel p1 = new JPanel(new GridLayout(6, 2));
        p1.add(new JLabel("Номер работы"));
        p1.add(jtfId);

        p1.add(new JLabel("Название работы"));
        p1.add(jtfName);

        p1.add(new JLabel("Начало работы"));
        p1.add(jtfStart);

        p1.add(new JLabel("Конец работы"));
        p1.add(jtfEnd);

        p1.add(new JLabel("Главная связь"));
        p1.add(jtfMainConn);

        p1.add(new JLabel("Второстепенные связи"));
        p1.add(jtfSecConn);

        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p2.add(jbtSubmit);
        jbtSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workDialog.setVisible(false);
            }
        });

        add(p1, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);

    }
    public void clearFields(){
            jtfId.setText("");
            jtfName.setText("");
            jtfStart.setText("");
            jtfEnd.setText("");
            jtfMainConn.setText("");
            jtfSecConn.setText("");
    }

    public void setFields(Work work){
        clearFields();
        if (work.getWorkId() != null ){
            jtfId.setText(work.getWorkId());
        }
        if (work.getWorkName() != null ){
            jtfName.setText(work.getWorkName());
        }
        if (work.getStartTime() != null ){
            jtfStart.setText(work.getStartTime());
        }
        if (work.getEndTime() != null ){
            jtfEnd.setText(work.getEndTime());
        }
        if (work.getMainConn() != null ){
            jtfMainConn.setText(work.getMainConn());
        }
        //jtfSecConn.setText(work.getSecondaryConn());

    }


}
