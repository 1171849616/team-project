package cn.com.cxsw.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import javax.imageio.ImageIO;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import cn.com.cxsw.core.Activator;

public class ImageUtils {
	private static int lWidth;
	private static int lHeight;
	//改变图片大小并且上传图片的方法
	public static void uploadImgChangeSize(String sourcePath,String targetPath,int width,int height,String format){
		BufferedImage srcImg=null;
		//定义BufferedImage对象  用来存储改变大小后的图片
		BufferedImage tarImg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		//获取Graphics对象  用来绘制位图
		Graphics g=tarImg.getGraphics();
		
		try {
			//读取原来的位图
			srcImg=ImageIO.read(new File(sourcePath));
			//绘制新的位图
			g.drawImage(srcImg, 0, 0,width,height, null);
			//输出新绘制的位图
			ImageIO.write(tarImg, format, new File(targetPath));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//释放Graphics对象占用的所有资源
		g.dispose();
		
	}
	//获取目标文件夹路径的方法
	public static String getTargetPath(String targetName){
		URL url=Activator.getDefault().getBundle().getEntry(targetName);
		String targetPath="";
		try {
			targetPath=FileLocator.toFileURL(url).toExternalForm().substring(6);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return targetPath;
	}
	//上传图片
	public static String uploadImg(String sourcePath,String targetName){
		//1、获取目标文件夹的路径
		String targetPath=getTargetPath(targetName);
		//2、生成新文件名称：
		String fileName=new Date().getTime()+""+Math.round(Math.random()*10000);
		File sourceFile=new File(sourcePath);
		String oldExtends=sourceFile.getName().substring(sourceFile.getName().lastIndexOf(".")+1);
		fileName=fileName+"."+oldExtends;
		//3、生成目标文件的路径
		targetPath+=fileName;
		//4、调用文件改变大小上传的方法
		//uploadImgChangeSize(sourcePath, targetPath, lWidth, lHeight, oldExtends);
		copyImg(sourcePath,targetPath);
		return fileName;
	}
	
	
	
	//按照label的大小动态预览图片的方法
	public static void showImg(Label label,String path){
		ImageLoader loader = new ImageLoader();
		lWidth=label.getBounds().width;
		lHeight=label.getBounds().height;
		ImageData[] imageData = loader.load(path);
		label.setImage(new Image(label.getDisplay(),
				imageData[0].scaledTo(lWidth, lHeight)));
	}
	
	public static void copyImg(String sourcePath,String targetPath){
		File sourceFile=new File(sourcePath);
		File targetFile =new File(targetPath);
		FileInputStream in=null;
		FileOutputStream out=null;
		try {
			in=new FileInputStream(sourceFile);
			out=new FileOutputStream(targetFile);
			int len;
			byte[] buff=new byte[1024];
			while((len=in.read(buff)) != -1){
				out.write(buff, 0, len);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}	
}
