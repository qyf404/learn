package com.qyf404.learn.jfreechart;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtil {
    private ImageUtil() {
    }

    public static void exportImage(JPanel panel, String pathname) throws IOException {
        //create a new image
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        //paint the editor onto the image
        SwingUtilities.paintComponent(image.createGraphics(),
                panel,
                new JPanel(),
                0, 0, image.getWidth(), image.getHeight());

        //save the image to file
        ImageIO.write((RenderedImage) image, "png", new File(pathname));
    }
}
