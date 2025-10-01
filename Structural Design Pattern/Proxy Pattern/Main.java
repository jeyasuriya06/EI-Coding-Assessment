
public class Main{
    public static void main(String[] args) {
        Proxy img1 = new Proxy("D:\\EI\\Exercise1\\Structural Design Pattern\\Proxy Pattern\\src\\sunset.jpeg");
        Proxy img2 = new Proxy("D:\\EI\\Exercise1\\Structural Design Pattern\\Proxy Pattern\\src\\mountain.jpg");

        img1.display();

        System.out.println("Image1 displayed");
        img1.display();

        img2.display();
    }
}