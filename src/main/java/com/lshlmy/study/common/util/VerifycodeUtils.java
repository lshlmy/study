package com.lshlmy.study.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码生成工具类
 * 
 * @author lshlmy
 */
public class VerifycodeUtils {

	private static final Random RANDOM = new Random();

	private VerifycodeUtils() {
	}

	/**
	 * 生成验证码字符串
	 * 
	 * @param length 验证码长度
	 * @return 验证码字符串
	 */
	public static String generateTextCode(int length) {
		return generateTextCode(length, "0123456789");
	}

	/**
	 * 生成验证码字符串
	 * 
	 * @param length 验证码长度
	 * @param useCode 用于生成验证码的字符串
	 * @return 验证码字符串
	 */
	public static String generateTextCode(int length, String useCode) {
		String result = "";
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				result += useCode.charAt(RANDOM.nextInt(useCode.length()));
			}
		}
		return result;
	}

	/**
	 * 根据验证码字符串生成验证码图片
	 * 
	 * @param textCode 文本验证码
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @param interLine 图片中干扰线的条数，若为null，则采用1-3条的随机数
	 * @param randomLocation 每个字符的高低位置是否随机
	 * @param backColor 图片颜色，若为null，则采用随机颜色
	 * @param foreColor 字体颜色，若为null，则采用随机颜色
	 * @param lineColor 干扰线颜色，若为null，则采用随机颜色
	 * @return 图片缓存对象
	 */
	public static BufferedImage generateImageCode(String textCode, int width, int height, Integer interLine, boolean randomLocation,
			Color backColor, Color foreColor, Color lineColor) {

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = bufferedImage.getGraphics();

		// 画背景图
		g.setColor(backColor == null ? getRandomColor() : backColor);
		g.fillRect(0, 0, width, height);

		// 画干扰线
		interLine = interLine == null ? RANDOM.nextInt(3) + 1 : interLine;
		if (interLine > 0) {
			int x = 0, y = 0, x1 = width, y1 = 0;
			for (int i = 0; i < interLine; i++) {
				g.setColor(lineColor == null ? getRandomColor() : lineColor);
				y = RANDOM.nextInt(height);
				y1 = RANDOM.nextInt(height);
				g.drawLine(x, y, x1, y1);
			}
		}

		// 字体大小为图片高度的80%
		int fsize = (int) (height * 0.8);
		// 分配给每个字的宽度
		float twidth = width / textCode.length();
		// 字体实际宽度
		float fwidth = fsize / 2.2F;
		// 间隙宽度
		float nwidth = (twidth - fwidth) / 2;
		int fx;
		int fy = fsize;

		g.setFont(new Font(Font.DIALOG, Font.BOLD, fsize));

		// 写验证码字符
		for (int i = 0; i < textCode.length(); i++) {
			fy = randomLocation ? (int) ((Math.random() * 0.3 + 0.6) * height) : fy;// 每个字符高低是否随机
			g.setColor(foreColor == null ? getRandomColor(100, 100, 100) : foreColor);
			fx = (int) (twidth * i + nwidth);
			g.drawString(textCode.charAt(i) + "", fx, fy);
		}

		g.dispose();

		return bufferedImage;
	}

	/**
	 * 获取随机颜色
	 * 
	 * @return 颜色对象
	 */
	private static Color getRandomColor() {
		return getRandomColor(255, 255, 255);
	}

	/**
	 * 获取随机颜色
	 * 
	 * @param maxR 红色最大值(0~255)
	 * @param maxG 绿色最大值(0~255)
	 * @param maxB 蓝色最大值(0~255)
	 * @return 颜色对象
	 */
	private static Color getRandomColor(int maxR, int maxG, int maxB) {
		return new Color(RANDOM.nextInt(maxR + 1), RANDOM.nextInt(maxG + 1), RANDOM.nextInt(maxB + 1));
	}
}