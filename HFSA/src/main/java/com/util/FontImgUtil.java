package com.util;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;

import javax.imageio.ImageIO;

public class FontImgUtil {
	public static final int LIGHT_COLOR = 192;
	public static final int IMAGE_WIDTH = 120;
	public static final int IMAGE_HEIGHT = 120;
	public static final String DEFAULT_IMAGE_PATH = "/tmp/";
	/**
	 * 根据文字和图片名称创建图片 默认字体宋体 加粗
	 * 
	 * @param str
	 * @param imgName
	 * @throws Exception
	 */
	public static void createImage(String str, String imgName) throws Exception {
		createImage(str, new Font("Baoli SC", Font.BOLD, 50), new File(DEFAULT_IMAGE_PATH + imgName));
	}

	/**
	 * 根据str和文件名创建图片
	 * 
	 * @param str
	 * @param outFile
	 * @throws Exception
	 */
	public static void createImage(String str, File outFile) throws Exception {
		createImage(str, new Font("Baoli SC", Font.BOLD, 50), outFile);
	}

	/**
	 * 根据str,font的样式以及输出文件目录
	 * 
	 * @param str
	 * @param font
	 * @param file
	 */
	private static void createImage(String str, Font font, File file) throws Exception {
		int[] randomRGB = getRandomRGB();
		boolean lightColor = isLightColor(randomRGB);

		// 创建图片
		BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_BGR);
		Graphics2D fGraphics2d = image.createGraphics();
		fGraphics2d.setColor(new Color(randomRGB[0], randomRGB[1], randomRGB[2]));
		//填充背景色
		fGraphics2d.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
		//判断浅色还是深色
		if (lightColor) {
			fGraphics2d.setColor(Color.black);
		} else {
			fGraphics2d.setColor(Color.white);
		}
		//设置画笔字体
		fGraphics2d.setFont(font);
		fGraphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		//判断字体的绘制位置,居中显示
		FontMetrics fontMetrics = fGraphics2d.getFontMetrics(font);
		int textWidth = fontMetrics.stringWidth(str);
		int x = (IMAGE_WIDTH - textWidth)/2;
		int ascent = fontMetrics.getAscent();
		int descent = fontMetrics.getDescent();
		int y = (IMAGE_HEIGHT - ascent -descent)/2 + ascent;
		//画出字符串
		fGraphics2d.drawString(str,x,y);
		fGraphics2d.dispose();
		//输出图片
		ImageIO.write(image, "png", file);
	}

	/**
	 * 获得随机颜色
	 * 
	 * @return
	 */
	private static int[] getRandomRGB() {
		int[] colors = new int[3];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = (int) (Math.random() * 256);
		}
		return colors;
	}

	/**
	 * 判断是否为浅色
	 * 
	 * @param randomRGB
	 * @return
	 */
	private static boolean isLightColor(int[] colors) {
		int grayLevel = (int) (colors[0] * 0.299 + colors[1] * 0.587 + colors[2] * 0.114);
		return grayLevel >= LIGHT_COLOR;
	}
}
