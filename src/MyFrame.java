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

public class MyFrame extends JFrame
{
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
            System.out.println(fnameTF.getText() +" " + lnameTF.getText());
            System.out.println(genderCombo.getSelectedItem().toString());
            System.out.println(ageTF.getText());
            System.out.println(salaryTF.getText());
        }
    }
}
