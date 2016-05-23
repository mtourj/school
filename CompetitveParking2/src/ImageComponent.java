import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

// Our ImageComponent will inherit all the properties of a JComponent,
// allowing it to be added to a JPanel or JFrame.
public class ImageComponent extends JComponent
{
    //We will use a BufferedImage object to get the image from a file.
    private BufferedImage image;
    // Constructor
    // You may choose to have a parameter, such as a String filename,
    // so this class can handle different files without changing the code.
    public ImageComponent( String filename )
    {
        try
        {
            // ImageIO.read() returns a BufferedImage object, decoding
            // the supplied file with an ImageReader, chosen
            // automatically from registered files. The File is wrapped
            // in an ImageInputStream object, so we don't need one.
            // Null is returned, If no registered ImageReader claims to
            // be able to read the resulting stream.
            image = ImageIO.read(new File( filename ));
        }
        catch (IOException e)
        {
            // Lets us know what happened if the file did not read
            // correctly
            System.out.println("Error reading dir: " + e.getMessage());
        }
    }
    // This next method will set the preferred size of the image.
    // It overrides the method in the Container class.
    // This method sets the preferred size to the actual image size.
    // You may choose to set the preferred size differently, it does not
    // have to be exactly as written here
    public Dimension getPreferredSize()
    {
        if (image == null)
        {
            return new Dimension(100,100);
        }
        else
        {
            return new Dimension(image.getWidth(null), image.getHeight(null));
        }
    }
    // We will need to override the paint method in order to Ã¢â‚¬Å“drawÃ¢â‚¬Â the image.
    public void paint( Graphics g )
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int x = (this.getWidth() - image.getWidth(null)) / 2;
        int y = (this.getHeight() - image.getHeight(null)) / 2;
        g2d.drawImage(image, x, y, null);
    }
}
