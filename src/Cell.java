public class Cell {
    char content = 'X';
    XYCoordinate position;

    public Cell(XYCoordinate position) {
        this.position = position;
    }

    public XYCoordinate dig() {
        this.content = ' ';
        return position;
    }

    public char getContent() {
        return content;
    }

    public void setContent(char content) {
        this.content = content;
    }

    public XYCoordinate getPosition() {
        return position;
    }

    public void setPosition(XYCoordinate position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.valueOf(content);
    }
}
