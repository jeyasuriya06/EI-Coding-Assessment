import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RealImage implements Image{
    private String fileName;
    private ImageIcon image;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Loading Image");
        image = new ImageIcon(fileName);
    }

    public void display() {
        JFrame frame = new JFrame(fileName);
        JLabel label = new JLabel();
        System.out.println("Display");
        label.setIcon(image);
        frame.add(label);
        frame.setSize(image.getIconHeight()+50, image.getIconWidth()+50);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
