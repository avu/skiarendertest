import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SkiaFontDemo {
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    static  SkiaPanel skiaPanel;
    static JTextPane textArea;

    public static void addComponentsToPane(Container pane) {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        String[] fontFamilyNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        final JComboBox<String> fontNameCB = new JComboBox<>(fontFamilyNames);
        fontNameCB.addActionListener(e -> {
            String fontName = (String) fontNameCB.getSelectedItem();
            assert (fontName != null);
            skiaPanel.setMyFontName(fontName);
        });
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(fontNameCB, c);

        final JComboBox<String> aaCB = new JComboBox<>(new String[]{"NonAA", "AA"});
        aaCB.addActionListener(e -> {
            switch ((String)aaCB.getSelectedItem()) {
                case "AA":
                    skiaPanel.setMyAntiAliasing(true);
                    break;
                case "NonAA":
                    skiaPanel.setMyAntiAliasing(false);
                    break;
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(aaCB, c);

        final JComboBox<String> fontStyleCB = new JComboBox<>(new String[]{"PLAIN", "BOLD", "ITALIC"});
        fontStyleCB.addActionListener(e -> {
            switch ((String)fontStyleCB.getSelectedItem()) {
                case "PLAIN":
                    skiaPanel.setMyFontStyle(Font.PLAIN);
                    break;
                case "BOLD":
                    skiaPanel.setMyFontStyle(Font.BOLD);
                    break;
                case "ITALIC":
                    skiaPanel.setMyFontStyle(Font.ITALIC);
                    break;
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        pane.add(fontStyleCB, c);

        final JTextField fontSizeTF = new JTextField("14");
        fontSizeTF.addActionListener(e -> {
            try {
                skiaPanel.setMyFontSize(Integer.parseInt(fontSizeTF.getText()));
            }
            catch (NumberFormatException ex) {
                // Ignore
            }
        });

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 0;
        pane.add(fontSizeTF, c);

        skiaPanel = new SkiaPanel(fontFamilyNames.length > 0 ? fontFamilyNames[0] : "", false, 14, WIDTH, HEIGHT);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 380;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(skiaPanel, c);

        textArea = new JTextPane();
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                skiaPanel.setMyText(textArea.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                skiaPanel.setMyText(textArea.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                skiaPanel.setMyText(textArea.getText());
            }
        });
        textArea.setText("Hello");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 0;       //aligned with button 2
        c.gridwidth = 4;   //2 columns wide
        c.gridy = 2;       //third row
        pane.add(textArea, c);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("SkiaFontDemo");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(SkiaFontDemo::createAndShowGUI);
    }
}