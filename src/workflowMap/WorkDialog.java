package workflowMap;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 19.03.13
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */
public class WorkDialog extends JDialog {
    public WorkDialog(JFrame parentFrame, boolean isModal){

        super(parentFrame, isModal);
        setTitle("Работа");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        Double frameWidth = dimension.getWidth()/3;
        Double frameHeight = dimension.getHeight()/3;
        setSize(frameWidth.intValue(), frameHeight.intValue());

        JPanel mainPanel = new JPanel(new MigLayout("fill"));
        JPanel workLabelsPanel = new JPanel(new MigLayout());
        workLabelsPanel.add(new JLabel("Номер работы"), "cell 0 0");
        workLabelsPanel.add(new JLabel("Название работы"), "cell 0 1 ");
        workLabelsPanel.add(new JLabel("Начало работы"), "cell 0 2");
        workLabelsPanel.add(new JLabel("Конец работы"), "cell 0 3");
        workLabelsPanel.add(new JLabel("Главная связь"), "cell 0 4");
        workLabelsPanel.add(new JLabel("Второстепенные связи"), "cell 0 5");

        JPanel workInfoPanel = new JPanel(new MigLayout());
        JTextField idField = new JTextField("Номер работы");
        JTextField nameField = new JTextField("Номер работы");
        JTextField startField = new JTextField("Номер работы");
        JTextField endField = new JTextField("Номер работы");
        JTextField mainConnField = new JTextField("Номер работы");
        JTextField secConnField = new JTextField("Номер работы");

        workInfoPanel.add(idField, "cell 0 0");
        workInfoPanel.add(nameField, "cell 0 1 ");
        workInfoPanel.add(startField, "cell 0 2");
        workInfoPanel.add(endField, "cell 0 3");
        workInfoPanel.add(mainConnField, "cell 0 4");
        workInfoPanel.add(secConnField, "cell 0 5");

        mainPanel.add(workLabelsPanel, "cell 0 0, west");
        mainPanel.add(workInfoPanel, "cell 1 0, west");

        JButton submitButton = new JButton("Применить");
        mainPanel.add(submitButton, "cell 1 1, west");




        this.add(mainPanel);
    }


}
