import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class NewFrame extends JFrame
{
    JPanel panelPerson = new JPanel();
    JPanel panelCar = new JPanel();
    JPanel panelRenta = new JPanel();
    JPanel panelSpr = new JPanel(); // справка

    JTabbedPane tab = new JTabbedPane();

    public NewFrame()
    {
        this.setSize(400, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        tab.add(panelPerson, "Клиенти");
        tab.add(panelCar, "Коли");
        tab.add(panelRenta, "Наем");
        tab.add(panelSpr, "Справка по ...");

        this.add(tab);

        this.setVisible(true);
    }
}
