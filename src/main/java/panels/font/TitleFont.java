package panels.font;

import java.awt.*;

public class TitleFont {

    private Font title;

    public TitleFont() {
        title = new Font("돋움", Font.PLAIN, 50);
    }

    public Font getTitle() {
        return title;
    }

    public void setTitle(Font title) {
        this.title = title;
    }
}
