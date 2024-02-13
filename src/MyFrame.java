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
    JComboBox<String> personCombo = new JComboBox<String>();

    JButton addBtn = new JButton("Добавяне");
    JButton deleteBtn = new JButton("Изтриване");
    JButton updateBtn = new JButton("Редакция");
    JButton searchBtn = new JButton("Търси");
    JButton refreshBtn = new JButton("Обнови");

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
        midPanel.add(updateBtn);
        midPanel.add(deleteBtn);
        midPanel.add(searchBtn);
        midPanel.add(refreshBtn);
        midPanel.add(personCombo);
        this.add(midPanel);

        addBtn.addActionListener(new AddAction());
        updateBtn.addActionListener(new UpdateAction());
        deleteBtn.addActionListener(new DeleteAction());
        searchBtn.addActionListener(new SearchAction());
        refreshBtn.addActionListener(new RefreshAction());


        scrollPane.setPreferredSize(new Dimension(350, 150));
        downPanel.add(scrollPane);
        this.add(downPanel);

        table.addMouseListener(new MouseAction());


        this.refreshTable();
        this.refreshPersonCombo();
        this.setVisible(true);
    }

    public void refreshTable()
    {
        conn = DBConnection.instance();
        String sql = "SELECT * FROM PERSON;";
        try {
            state = conn.prepareStatement(sql);
            result = state.executeQuery();
            table.setModel(new MyModel(result));
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshPersonCombo()
    {
        conn = DBConnection.instance();
        String sql = "SELECT ID, FNAME, LNAME FROM PERSON;";
        String item = "";

        personCombo.removeAllItems();

        try {
            state = conn.prepareStatement(sql);
            result = state.executeQuery();
            while(result.next()) {
                item = result.getObject(1).toString() + ".";
                item += result.getObject(2).toString() + " ";
                item += result.getObject(3).toString();

                personCombo.addItem(item);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearForm()
    {
        fnameTF.setText("");
        lnameTF.setText("");
        ageTF.setText("");
        salaryTF.setText("");
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
                clearForm();
                refreshTable();
                refreshPersonCombo();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    class DeleteAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            conn = DBConnection.instance();
            String sql = "DELETE FROM PERSON WHERE ID = ?;";

            try {
                state = conn.prepareStatement(sql);
                state.setInt(1, id);
                state.execute();
                refreshTable();
                refreshPersonCombo();
                clearForm();
                id = -1;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    class UpdateAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            conn = DBConnection.instance();
            String sql = "UPDATE PERSON SET FNAME = ?, LNAME = ?, GENDER = ?, AGE = ?, SALARY = ? WHERE ID = ?;";

            try {
                state = conn.prepareStatement(sql);
                state.setString(1, fnameTF.getText());
                state.setString(2, lnameTF.getText());
                state.setString(3, genderCombo.getSelectedItem().toString());
                state.setInt(4, Integer.parseInt(ageTF.getText()));
                state.setDouble(5, Double.parseDouble(salaryTF.getText()));
                state.setInt(6, id);
                state.execute();

                refreshTable();
                refreshPersonCombo();
                clearForm();
                id = -1;
            } catch(SQLException e1) {
                e1.printStackTrace();
            } catch(Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    class SearchAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            conn = DBConnection.instance();
            String sql = "SELECT * FROM PERSON WHERE AGE = ?;";

            try {
                state = conn.prepareStatement(sql);
                state.setInt(1, Integer.parseInt(ageTF.getText()));
                result = state.executeQuery();
                table.setModel(new MyModel(result));

            } catch(SQLException e1) {
                e1.printStackTrace();
            } catch(Exception e2) {
                e2.printStackTrace();
            }

        }
    }

    class RefreshAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            refreshTable();
            refreshPersonCombo();
        }
    }

    class MouseAction implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            int row = table.getSelectedRow();
            id = Integer.parseInt(table.getValueAt(row, 0).toString());
            fnameTF.setText(table.getValueAt(row, 1).toString());
            lnameTF.setText(table.getValueAt(row, 2).toString());
            ageTF.setText(table.getValueAt(row, 4).toString());
            salaryTF.setText(table.getValueAt(row, 5).toString());

            String gender = table.getValueAt(row, 3).toString();
            if(gender.equals("Мъж")) {
                genderCombo.setSelectedIndex(0);
            } else if (gender.equals("Жена")) {
                genderCombo.setSelectedIndex(1);
            }
        }

        @Override
        public void mousePressed(MouseEvent e)
        {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {

        }

        @Override
        public void mouseEntered(MouseEvent e)
        {

        }

        @Override
        public void mouseExited(MouseEvent e)
        {

        }
    }
}
