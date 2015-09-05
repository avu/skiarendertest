import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class SkiaPanel extends JPanel {
    private String myFontName;
    private BufferedImage myImageBlack;
    private BufferedImage myImageWhite;

    private String myText;

    private int myFontSize;

    private int myFontStyle;
    private boolean myAntiAliasing;

    public void setMyText(String myText) {
        this.myText = myText;
        render();
    }

    private void render() {
        render(myImageBlack, Color.WHITE, Color.BLACK);
        render(myImageWhite, Color.BLACK, Color.WHITE);
    }

    private void render(BufferedImage image, Color fgColor, Color bgColor) {
        Graphics2D g2d = image.createGraphics();
        g2d.setBackground(bgColor);
        g2d.clearRect(0,0, image.getWidth(), image.getHeight());
        g2d.setColor(fgColor);
        int skStyle = SkiaRenderLibrary.FONT_TYPE_NORMAL; //
        switch (myFontStyle) {
            case Font.PLAIN:
                break;
            case Font.BOLD:
                skStyle = SkiaRenderLibrary.FONT_TYPE_BOLD;
                break;
            case Font.ITALIC:
                skStyle = SkiaRenderLibrary.FONT_TYPE_ITALIC;
                break;
        }


        g2d.setFont(new Font(myFontName, myFontStyle, myFontSize));

        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                myAntiAliasing ? RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        g2d.drawString("Java: " + myText, 0, 40 + 2*myFontSize);

        int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        SkiaRenderLibrary.INSTANCE.skrender(myFontName, "Skia: " + myText, fgColor.getRGB(), myFontSize, myAntiAliasing,
                                            skStyle, 0, 20 + myFontSize, pixels, image.getWidth(),
                                            image.getHeight());
        repaint();
    }

    public void setMyFontStyle(int myFontStyle) {
        this.myFontStyle = myFontStyle;
        render();
    }

    public void setMyAntiAliasing(boolean myAntiAliasing) {
        this.myAntiAliasing = myAntiAliasing;
        render();
    }

    public void setMyFontSize(int myFontSize) {
        this.myFontSize = myFontSize;
        render();
    }

    public void setMyFontName(String myFontName) {
        this.myFontName = myFontName;
        render();
    }

    public SkiaPanel(String fontName, boolean aa, int fontSize, int width, int height) {
        myFontName = fontName;
        myAntiAliasing = aa;
        myFontSize = fontSize;
        myImageBlack = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        myImageWhite = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        myText = "Hello";
        render();
    }

    public void paint(Graphics g) {
        g.drawImage(myImageBlack, 0, 0, null);
        g.drawImage(myImageWhite, 0, 200, null);
    }
}
