public class PlainText implements Text {
    private String text;
    
    public PlainText(String text) {
        this.text = text;
    }

    public String render() {
        return text;
    }
}
