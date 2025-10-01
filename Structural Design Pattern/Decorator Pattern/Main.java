public class Main {
    public static void main(String[] args) {
        Text text = new PlainText("My name is Jeya Suriya S");

        System.out.println(text.render());

        text = new BoldDecorator(text);
        System.out.println("Bold Text: " + text.render());

        text = new ItalicDecorator(text);
        System.out.println("Bold + Italic Text: " + text.render());

        text = new UnderlineDecorator(text);
        System.out.println("Bold + Italic + Underlined Text: " + text.render());
    }
}
