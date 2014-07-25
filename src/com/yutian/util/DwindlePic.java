package com.yutian.util;  
  
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
public class DwindlePic {  
  
    String InputDir; 
    String OutputDir; 
    String InputFileName;
    String OutputFileName; 
    int OutputWidth = 80; 
    int OutputHeight = 80; 
    int rate = 0;  
    boolean proportion = true; 
  
    public DwindlePic() {  
        InputDir = "";  
        OutputDir = "";  
        InputFileName = "";  
        OutputFileName = "";  
        OutputWidth = 80;  
        OutputHeight = 80;  
        rate = 0;  
    }  
  
    public boolean s_pic() {  
        File file = new File(OutputDir + OutputFileName);  
        FileOutputStream tempout = null;  
        try {  
            tempout = new FileOutputStream(file);  
        } catch (Exception ex) {  
            System.out.println(ex.toString());  
        }  
        Image img = null;  
        try {  
            img = ImageIO.read(new File(InputDir+InputFileName));
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        if (img.getWidth(null) == -1) {  
            return false;  
        } else {  
            int new_w;  
            int new_h;  
            if (this.proportion == true) { 
                double rate1 = ((double) img.getWidth(null)) / (double) OutputWidth +  
                        0.1;  
                double rate2 = ((double) img.getHeight(null)) / (double) OutputHeight +  
                        0.1;  
                double rate = rate1 > rate2 ? rate1 : rate2;  
                new_w = (int) (((double) img.getWidth(null)) / rate);  
                new_h = (int) (((double) img.getHeight(null)) / rate);  
            } else {  
                new_w = OutputWidth;
                new_h = OutputHeight;
            }  
            BufferedImage buffImg = new BufferedImage(new_w, new_h,  
                    BufferedImage.TYPE_INT_RGB);  
  
            Graphics g = buffImg.createGraphics();  
  
            g.setColor(Color.white);  
            g.fillRect(0, 0, new_w, new_h);  
  
            g.drawImage(img, 0, 0, new_w, new_h, null);  
            g.dispose();  
  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(tempout);  
            try {  
                encoder.encode(buffImg);  
                tempout.close();  
            } catch (IOException ex) {  
                System.out.println(ex.toString());  
            }  
        }  
        return true;  
    }  
  
    public boolean s_pic(String InputDir, String OutputDir, String InputFileName,  
            String OutputFileName) {  
        this.InputDir = InputDir;  
        this.OutputDir = OutputDir;  
        this.InputFileName = InputFileName;  
        this.OutputFileName = OutputFileName;  
        return s_pic();  
    }  
  
    public boolean s_pic(String InputDir, String OutputDir, String InputFileName,  
            String OutputFileName, int width, int height, boolean gp) {  
        this.InputDir = InputDir;  
        this.OutputDir = OutputDir;  
        this.InputFileName = InputFileName;  
        this.OutputFileName = OutputFileName;  
        setW_H(width, height);  
        this.proportion = gp;  
        return s_pic();  
    }  
  
    public void setInputDir(String InputDir) {  
        this.InputDir = InputDir;  
    }  
  
    public void setOutputDir(String OutputDir) {  
        this.OutputDir = OutputDir;  
    }  
  
    public void setInputFileName(String InputFileName) {  
        this.InputFileName = InputFileName;  
    }  
  
    public void setOutputFileName(String OutputFileName) {  
        this.OutputFileName = OutputFileName;  
    }  
  
    public void setOutputWidth(int OutputWidth) {  
        this.OutputWidth = OutputWidth;  
    }  
  
    public void setOutputHeight(int OutputHeight) {  
        this.OutputHeight = OutputHeight;  
    }  
  
    public void setW_H(int width, int height) {  
        this.OutputWidth = width;  
        this.OutputHeight = height;  
    }  
  
    public static void main(String[] a) {  
        DwindlePic mypic = new DwindlePic();  
        System.out.println(  
                mypic.s_pic("d:/pic/",  
                "d:/pic/",  
                "1.jpg", "2.jpg", 200, 330, false));  
    }
}