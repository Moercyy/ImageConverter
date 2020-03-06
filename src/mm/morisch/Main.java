package mm.morisch;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;

public class Main {

    private static final int IMG_WIDTH = 500;
    private static final int IMG_HEIGHT = 500;
    //private static String filepath = "type.jpg";

    public static void main(String [] args){

        try{
            //Zusammengesuchte Bilder in den Ordner Pre laden
            File file = new File("Pre");
            if (file.isDirectory()) {
                List<File> fileList = Arrays.asList(file.listFiles());
                int counter = 1;
                for (File _file : fileList) {
                    BufferedImage originalImage = ImageIO.read(new File(_file.getAbsolutePath()));
                    int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
                    BufferedImage resizeImageJpg = resizeImage(originalImage, type);
                    //Je nachdem welcher Ordner Bsp.: "ClassOne/class_one_"+counter+".jpg"
                    ImageIO.write(resizeImageJpg, "jpg", new File("ClassTwo/class_two_" + counter + ".jpg"));
                    counter++;
                }
            }else
            {
                System.out.println("Fehler beim Einlesen vom File");
            }

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
        private static BufferedImage resizeImage(BufferedImage originalImage, int type){
            BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
            g.dispose();
            return resizedImage;
        }
    }