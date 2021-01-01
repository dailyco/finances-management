package panels.font;

import java.awt.*;

public class TextFont {

    private Font text;

    public TextFont() {
        text = new Font("돋움", Font.PLAIN, 20);
    }

    public Font getText() {
        return text;
    }

    public void setText(Font text) {
        this.text = text;
    }
}
