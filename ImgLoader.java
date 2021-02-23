import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

public final class ImgLoader {
    //This class is not needed since you can just do: BufferedImage image = ImageIO.read(new File("path-to-file"));
    public static BufferedImage loadImg(String path) {
        try {
            return ImageIO.read(ImgLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
    public static BufferedImage resizeBackground(BufferedImage image, int newWidth, int newHeight){
        Image imageToResize = image.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
        BufferedImage imageContainer = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2d = imageContainer.createGraphics();
        graphics2d.drawImage(imageToResize,0,0,null);
        graphics2d.dispose();
        return imageContainer;    
    }
}