package ChongErUtils.PictureToOCR;

import java.awt.AWTException;
/**
 * 这个类用来实现图片文字识别功能
 * 截取屏幕图片，识别图片上的文字
 */
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class PicToOCR {

	private String ocr;// chi_sim ：简体中文， eng 根据需求选择语言库
	private String lagnguagePath; // 训练库的位置

	/**
	 * 
	 * @param ocr           chi_sim ：简体中文， eng 根据需求选择语言库
	 * @param lagnguagePath 训练库的位置
	 */
	public PicToOCR(String ocr, String lagnguagePath) {
		this.ocr = ocr;
		this.lagnguagePath = lagnguagePath;
	}

	/**
	 * 截取屏幕图片，识别图片上的文字，并返回识别结果
	 * 
	 * @param X 截取图片左上角X坐标
	 * @param Y 截取图片左上角Y坐标
	 * @param W 截取图片宽
	 * @param H 截取图片高
	 * @return strText 返回识别结果
	 * @throws TesseractException
	 * @throws AWTException
	 */
	public String picToOCR(int X, int Y, int W, int H) throws TesseractException, AWTException {

		ITesseract instance = new Tesseract();
		instance.setDatapath(lagnguagePath);// 设置训练库的位置
		instance.setLanguage(ocr);// chi_sim ：简体中文， eng 根据需求选择语言库

		//获取截图
		BufferedImage image = 
				new Robot().createScreenCapture(
						new Rectangle(X, Y, W, H));

		return instance.doOCR(image);
	}
}
