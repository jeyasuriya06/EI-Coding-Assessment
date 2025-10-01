public class UnderlineDecorator extends TextDecorator{
    public UnderlineDecorator(Text text) {
        super(text);
    }

    public String render() {
        return "<u>" + text.render() + "</u>";
    }
}
