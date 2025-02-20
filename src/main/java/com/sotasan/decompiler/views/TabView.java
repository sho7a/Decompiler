package com.sotasan.decompiler.views;

import com.sotasan.decompiler.controllers.TabController;
import com.sotasan.decompiler.models.FileModel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

@Getter
public class TabView extends JPanel implements MouseWheelListener {

    private final RSyntaxTextArea textArea;
    private final RTextScrollPane scrollPane;
    @Setter
    private TabController controller;
    private final FileModel fileModel;

    @SneakyThrows
    public TabView(FileModel fileModel) {
        this.fileModel = fileModel;
        setLayout(new BorderLayout());

        Theme theme = Theme.load(getClass().getClassLoader().getResourceAsStream("themes/RSyntaxTheme.xml"));

        textArea = new RSyntaxTextArea();
        theme.apply(textArea);
        textArea.addMouseWheelListener(this);
        textArea.setBracketMatchingEnabled(false);
        textArea.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        textArea.setDropTarget(null);
        textArea.setEditable(false);
        textArea.setHighlightCurrentLine(false);

        scrollPane = new RTextScrollPane(textArea);
        theme.apply(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);

        setFontSize(getFont().getSize() + 2);
    }

    // TODO: global font size
    @Override
    public void mouseWheelMoved(@NotNull MouseWheelEvent event) {
        if (event.isControlDown() || event.isMetaDown())
            setFontSize(Math.min(50, Math.max(10, textArea.getFont().getSize() - event.getWheelRotation())));
        else
            for (MouseWheelListener listener : scrollPane.getMouseWheelListeners())
                listener.mouseWheelMoved(event);
    }

    private void setFontSize(float size) {
        Font font = textArea.getFont().deriveFont(size);
        textArea.setFont(font);
        scrollPane.getGutter().setLineNumberFont(font);
    }

    public void setScrollPane(JScrollPane imageScrollPane) {
        setLayout(new BorderLayout());
        removeAll();

        JLabel imageLabel = new JLabel();
        ImageIcon originalIcon = new ImageIcon(fileModel.getBytes());
        imageLabel.setIcon(originalIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);

        imageScrollPane.setViewportView(imageLabel);
        imageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        imageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        imageScrollPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                int height = getHeight();
                int width = (int) (originalIcon.getIconWidth() * ((double) height / originalIcon.getIconHeight()));

                if(width > getWidth()) {
                    width = getWidth();
                    height = (int) (originalIcon.getIconHeight() * ((double) width / originalIcon.getIconWidth()));
                }

                // Verify if the image is bigger than the scroll pane
                if (originalIcon.getIconWidth() > width || originalIcon.getIconHeight() > height) {
                    Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(scaledImage));
                    imageLabel.setPreferredSize(new Dimension(width, height));
                } else {
                    imageLabel.setIcon(originalIcon);
                    imageLabel.setPreferredSize(new Dimension(originalIcon.getIconWidth(), originalIcon.getIconHeight()));
                }
            }
        });

        add(imageScrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}