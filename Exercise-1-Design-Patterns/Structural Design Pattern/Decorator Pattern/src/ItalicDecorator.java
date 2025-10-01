public class ItalicDecorator extends TextDecorator{
    
    public ItalicDecorator(Text text) {
        super(text);
    }

    public String render() {
        return "<i>" + super.render() + "</i>";
    }
}
