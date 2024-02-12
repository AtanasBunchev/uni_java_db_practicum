import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame extends JFrame
{
    JButton addBtn = new JButton();

    public MyFrame()
    {
        this.setSize(400, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.add(addBtn);

        this.setVisible(true);
    }

}
