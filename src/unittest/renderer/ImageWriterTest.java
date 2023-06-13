package unittest.renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;
import renderer.ImageWriter;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void writeToImage() {
        int nWidth = 800;
        int nHeight = 500;
        ImageWriter imageWriter = new ImageWriter("testYellow", nWidth, nHeight);

        Color yellowColor = new Color(java.awt.Color.YELLOW);
        Color redColor = new Color(java.awt.Color.RED);

        //loop that paint the pixels. If we are in the frame - paint red, else - we are in the pixels, paint yellow.
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                //800/50 = 16 , 500/50 = 10
                if (i % 50 == 0 || j % 50 == 0) {
                    imageWriter.writePixel(i, j, redColor);
                } else {
                    imageWriter.writePixel(i, j, yellowColor);
                }
            }
        }
        imageWriter.writeToImage();
    }
}