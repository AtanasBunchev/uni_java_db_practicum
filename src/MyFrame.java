import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class MyFrame extends JFrame
{
    Connection conn = null;
    PreparedStatement state = null;
    ResultSet result = null;

    int id = -1;

    JPanel upPanel = new JPanel();
    JPanel midPanel = new JPanel();
    JPanel downPanel = new JPanel();

    JLabel fnameL = new JLabel("Име:");
    JLabel lnameL = new JLabel("Фамилия:");
    JLabel genderL = new JLabel("Пол:");
    JLabel ageL = new JLabel("Години:");
    JLabel salaryL = new JLabel("Заплата:");

    JTextField fnameTF = new JTextField();
    JTextField lnameTF = new JTextField();
    JTextField ageTF = new JTextField();
    JTextField salaryTF = new JTextField();

    String[] genders = {
        "Мъж", "Жена"
    };
    JComboBox<String> genderCombo = new JComboBox<String>(genders);

    JButton addBtn = new JButton("Добавяне");
    JButton deleteBtn = new JButton("Изтриване");
    JButton editBtn = new JButton("Редакция");

    JTable table = new JTable();
    JScrollPane scrollPane = new JScrollPane(table);

    public MyFrame()
    {
        this.setSize(400, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 1));

        upPanel.setLayout(new GridLayout(5, 2));
        upPanel.add(fnameL);
        upPanel.add(fnameTF);
        upPanel.add(lnameL);
        upPanel.add(lnameTF);
        upPanel.add(genderL);
        upPanel.add(genderCombo);
        upPanel.add(ageL);
        upPanel.add(ageTF);
        upPanel.add(salaryL);
        upPanel.add(salaryTF);
        this.add(upPanel);

        midPanel.add(addBtn);
        midPanel.add(editBtn);
        midPanel.add(deleteBtn);
        this.add(midPanel);

        addBtn.addActionListener(new AddAction());

        scrollPane.setPreferredSize(new Dimension(350, 150));
        downPanel.add(scrollPane);
        this.add(downPanel);

        this.setVisible(true);
    }

    class AddAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            conn = DBConnection.instance();
            String sql = "INSERT INTO PERSON(fname, lname, gender, age, salary) values(?, ?, ?, ?, ?);";

            try {
                state = conn.prepareStatement(sql);
                state.setString(1, fnameTF.getText());
                state.setString(2, lnameTF.getText());
                state.setString(3, genderCombo.getSelectedItem().toString());
                state.setInt(4, Integer.parseInt(ageTF.getText()));
                state.setDouble(5, Double.parseDouble(salaryTF.getText()));
                state.execute();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
