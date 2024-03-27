import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }

  /** Method to keep only blue pixels */
  public void keepOnlyBlue(){
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels){
      for (Pixel pixelObj : rowArray){
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }

  /** Method to negate all pixels */
  public void negate(){
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels){
      for (Pixel pixelObj : rowArray){
        pixelObj.setRed(255-pixelObj.getRed());
        pixelObj.setGreen(255-pixelObj.getGreen());
        pixelObj.setBlue(255-pixelObj.getBlue());
      }
    }
  }


  /** Method to turn the picture into shades of gray */
  public void grayscale(){
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels){
      for (Pixel pixelObj : rowArray){
        int average = (pixelObj.getRed()+pixelObj.getBlue()+pixelObj.getGreen())/3;
        pixelObj.setRed(average);
        pixelObj.setGreen(average);
        pixelObj.setBlue(average);
      }
    }
  }

  /** Method to make the fish easier to see */
  public void fixUnderwater(){
    Pixel[][] pixels = this.getPixels2D();
    int minBlue = 255;
    int minGreen = 255;
    for (Pixel[] rowArray : pixels){
      for (Pixel pixelObj : rowArray){
        minBlue = Math.min(minBlue,pixelObj.getBlue());
        minGreen = Math.min(minGreen,pixelObj.getGreen());
      }
    }
    for (Pixel[] rowArray : pixels){
      for (Pixel pixelObj : rowArray){
        pixelObj.setBlue((pixelObj.getBlue()-minBlue));
        pixelObj.setGreen((pixelObj.getGreen()-minGreen));
        pixelObj.setRed(pixelObj.getRed());
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }


  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from right to left */
  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  }

  /** Method that mirrors the picture around a 
    * horizontal mirror in the center of the picture
    * from top to bottom */
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for (int row = 0; row < height/2; row++)
    {
      for (int col = 0; col <pixels[0].length; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[height-row-1][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  }

  /** Method that mirrors the picture around a 
    * horizontal mirror in the center of the picture
    * from bottom to top */
  public void mirrorHorizontalBotToTop() {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for (int row = 0; row < height/2; row++)
    {
      for (int col = 0; col <pixels[0].length; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[height-row-1][col];
        topPixel.setColor(bottomPixel.getColor());
      }
    } 
  }

  /** Method that mirrors the picture around a 
    * diagonal mirror in the top left of the picture
    * from left to right */
  public void mirrorDiagonal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int height = pixels.length;
    int width = pixels[0].length;
    int min = Math.min(height,width);
    int count = 0;
    for (int row = 0; row < min; row++)
    {
      for (int col = count; col < min; col++)
      {
        rightPixel = pixels[row][col];
        leftPixel = pixels[col][row];
        rightPixel.setColor(leftPixel.getColor());
      }
      count++;
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
        count++;
      }
    }
    System.out.print(count);
  }

  /** Mirror just the arms of a picture of a snowman */
  public void mirrorArms()
  {
    int mirrorPoint = 0;
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    for (int row = 159; row<=190; row++){
      for (int col = 105; col<=169; col++){
        topPixel = pixels[row][col];
        bottomPixel = pixels[380-row][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    }
    for (int row = 172; row<=190; row++){
      for (int col = 239; col<=293; col++){
        topPixel = pixels[row][col];
        bottomPixel = pixels[380-row][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    }
  }

  /** Mirrors a seagull to create 2 */
  public void mirrorGull()
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    for (int row = 235; row <= 320; row++)
    {
      for (int col = 238; col <= 345; col++)
      {
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row][700-col];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  public void copy(Picture fromPic, int startRow, int startCol, int fromRowStart, int fromRowEnd, int fromColStart, int fromColEnd)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = fromRowStart, toRow = startRow; fromRow < fromRowEnd &&toRow < toPixels.length; fromRow++, 
    toRow++)
    {
      for (int fromCol = fromColStart, toCol = startCol; 
           fromCol < fromColEnd &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }

  /** Method to create a collage of several pictures */
  public void myCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture seagull = new Picture("seagull.jpg");
    Picture snowman = new Picture("snowman.jpg");
    flower1.mirrorHorizontal();
    seagull.negate();
    snowman.mirrorArms();
    snowman = snowman.scale(0.25,0.25);
    this.copy(flower1,0,0);
    this.copy(seagull,0,200,237,332, 234, 348);
    this.copy(snowman,200,0);
    flower1.mirrorVertical();
    seagull.zeroBlue();
    snowman.fixUnderwater();
    this.copy(flower1,0,400);
    this.copy(seagull,400,0,237,332, 234, 348);
    this.copy(snowman,200,200);
    flower1.keepOnlyBlue();
    seagull.grayscale();
    snowman.mirrorDiagonal();
    this.copy(flower1,400,200);
    this.copy(seagull,400,375,237,332, 234, 348);
    this.copy(snowman,200,400);
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel mainPixel = null;
    Pixel rightPixel = null;
    Pixel bottomPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    Color bottomColor = null;
    for (int row = 0; row < pixels.length-1; row++)
    {
      for (int col = 0; col < pixels[0].length-1; col++)
      {
        mainPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        bottomPixel = pixels[row+1][col];
        rightColor = rightPixel.getColor();
        bottomColor = bottomPixel.getColor();
        if (mainPixel.colorDistance(rightColor) > edgeDist){
          mainPixel.setColor(Color.BLACK);
        } else if (mainPixel.colorDistance(bottomColor) > edgeDist){
        mainPixel.setColor(Color.BLACK);
        }else {
          mainPixel.setColor(Color.WHITE);
        }
        
      }
    }
  }

  /**Method to show large changes in color 
    * @param edgeDist the distance for finding edges  */
  public void edgeDetection2(){
    grayscale();
    Pixel[][] pixels = this.getPixels2D();
    int[][] kernelx = {{-1,0,1},{-2,0,2}, {-1,0,1}};
    int[][] kernely = {{-1,-2,-1},{0,0,0}, {1,2,1}};
    double[][] magX = new double[pixels.length][pixels[0].length];
    double[][] magY = new double[pixels.length][pixels[0].length];
    int[][] val = new int[pixels.length][pixels[0].length];;
    double[][] angle = new double[pixels.length][pixels[0].length];;
    for (int x = 1; x < pixels.length-1; x++){
      for (int y = 1; y < pixels[0].length-1; y++){
        for(int a = 0; a < 3; a++)
        {
          for(int b = 0; b < 3; b++)
          {            
            int xn = x + a - 1;
            int yn = y + b - 1;
            magX[x][y] += pixels[xn][yn].getRed() * kernelx[a][b];
            magY[x][y] += pixels[xn][yn].getRed() * kernely[a][b];
          }
        }
        val[x][y] = (int)(Math.sqrt(Math.pow(magX[x][y],2) + Math.pow(magY[x][y],2)));
      }
    }
    for (int row = 0; row<pixels.length; row++){
      for (int col = 0; col<pixels[0].length; col++){
        pixels[row][col].setRed(val[row][col]);
        pixels[row][col].setGreen(val[row][col]);
        pixels[row][col].setBlue(val[row][col]);
      }
    }
  }
  
}
