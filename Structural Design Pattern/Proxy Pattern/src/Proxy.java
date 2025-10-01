public class Proxy implements Image {
    private RealImage realImage;
    private String fileName;

    public Proxy(String fileName) {
        this.fileName = fileName;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
