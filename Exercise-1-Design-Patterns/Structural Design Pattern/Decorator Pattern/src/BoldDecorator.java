public class BoldDecorator extends TextDecorator {
    public BoldDecorator(Text text) {
        super(text);
    }

    public String render() {
        return "<b>" + super.render() + "</b>";
    }
}
