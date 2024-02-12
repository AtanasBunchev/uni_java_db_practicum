import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
    JTextField genderTF = new JTextField();
    JTextField ageTF = new JTextField();
    JTextField salaryTF = new JTextField();

    JButton addBtn = new JButton();

    public MyFrame()
    {
        this.setSize(400, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 1));

        this.add(upPanel);
        this.add(midPanel);
        this.add(downPanel);

        midPanel.add(addBtn);

        this.setVisible(true);
    }

}
