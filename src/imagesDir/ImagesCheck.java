package imagesDir;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import com.sun.media.imageio.plugins.tiff.*;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
public class ImagesCheck {
	

	public void generateReport(String path,String book,int minPage,int maxPage) {
		// TODO Auto-generated method stub
		File[] images;
        File f = new File(path+'/');
        // Populates the array with names of files and directories
        images = f.listFiles();
		FileWriter report=null;
		try {
			report = new FileWriter(path+"/report.txt");
			allImages(f.list(),minPage,maxPage,book,report);
			averagePageWidthAndHeight(images,report);
			report.close();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "ALERT", "TITLE", JOptionPane.WARNING_MESSAGE);
		}
	}
	

	public static void allImages(String[] imagesNames,int min,int max,String book,FileWriter report){
		boolean nothingMissing=true;
		 try {
			 for(int i=min;i<max;i++) {
				boolean exist=Arrays.asList(imagesNames).contains(book+"-"+
						String.format("%03d",i)+".tif");
				if(!exist) {
					nothingMissing=false;
						report.write(String.format("%03d",i)+" missing\n");
				} 
			}
			 if(nothingMissing) {
				 report.write("All files are located!\n\n\n");
			 }else {
				 report.write("\n\n\n");
			 }
        }catch (IOException e) {
        	JOptionPane.showMessageDialog(null, "ALERT", "TITLE", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void averagePageWidthAndHeight(File[] images,FileWriter report) {
		float sumWidth=0, sumHeight=0;
		boolean noCroppedPage=true;
		BufferedImage bimg= null;
		try {
			for (File image : images) {
				if(!image.getName().equals("cache")&&!image.getName().equals("report.txt")) {
					bimg = ImageIO.read(image);
					sumWidth+= bimg.getWidth();
					sumHeight+= bimg.getHeight();						
				}
	        }
			float aveWidth=sumWidth/images.length;
			float aveHeight=sumHeight/images.length;
			for (File image : images) {
				if(!image.getName().equals("cache")&&!image.getName().equals("report.txt")) {
					bimg = ImageIO.read(image);
					if(aveWidth-bimg.getWidth()>100) {
						noCroppedPage=false;
						report.write(image.getName()+" maybe cropped page\n");
					}else if(aveHeight-bimg.getHeight()>100) {
						noCroppedPage=false;
						report.write(image.getName()+" maybe cropped page\n");
					}
				}
	        }
			if(noCroppedPage) {
				report.write("There is no suspicion of cropped images!");
			}
		}catch (IOException e) {
			JOptionPane.showMessageDialog(null, 
                    "ALERT", "TITLE", JOptionPane.WARNING_MESSAGE);
		}
	}

}
