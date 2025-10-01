abstract class TextDecorator implements Text{
    protected Text text;

    public TextDecorator(Text text) {
        this.text = text;
    }

    public String render() {
        return text.render();
    }
}
