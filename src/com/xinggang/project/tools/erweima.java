package com.xinggang.project.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import javax.imageio.ImageIO;
import com.swetake.util.Qrcode;

/**
 * 生成二维码工具类
 * 
 * @author Administrator
 * 
 */
public class erweima {
	/**
	 * @param path
	 *            this.getServlet().getServletContext().getRealPath
	 *            ("/Image_erweima/"+uu+".png")
	 * @param content
	 *            http://192.168.212.100:8080/XGProject/对应的jsp页面
	 * @throws IOException
	 *             自动生成唯一的字符串，二维码图片名称 UUID uu = UUID.randomUUID();
	 *             在对应的jsp页面中的img下的获得路径为<img
	 *             src="<%=basePath+"Image_erweima/"+request.getAttribute("
	 *             str")+".png"%>"/>str为保存在request中的uu，生成的二维码图片的名称
	 */
	public void getQRcode(String path, String content) throws IOException {
		// 创建一个QRcode对象
		Qrcode qrcode = new Qrcode();
		// 设置二维码的纠错能力
		// L:%7 M:%15 Q:%25 H:%30

		qrcode.setQrcodeErrorCorrect('M');
		// 以二进制的形式存储内容
		qrcode.setQrcodeEncodeMode('B');

		// 设置二维码版本40个小方格 1个版本==21*21 2=25*25
		qrcode.setQrcodeVersion(7);
		// 内容字符编码
		String str = new String(URLDecoder.decode(content, "UTF-8"));
		System.out.println(str);
		byte[] bt = new String(str).getBytes();
		// 创建一个图形缓冲区
		BufferedImage image = new BufferedImage(140, 160,
				BufferedImage.TYPE_INT_RGB);
		// 创建一直笔
		Graphics2D g = image.createGraphics();
		// 设置二维码的背景颜色
		g.setBackground(Color.WHITE);
		// 把白色填充到纸image上0,0为坐标，140,140为宽和高
		g.fillRect(0, 0, 140, 160);
		// 设置二维码前景色
		g.setColor(Color.BLUE);
		if (bt.length > 0) {
			boolean[][] b = qrcode.calQrcode(bt);
			for (int i = 0; i < b.length; i++) {
				for (int j = 0; j < b.length; j++) {
					if (b[j][i]) {
						// 根据布尔数组绘制二维码，绘制矩形
						g.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);
					}

				}
			}
		}
		g.drawString("app下载", 30, 155);
		File file = new File(path);
		ImageIO.write(image, "png", file);
	}
	
	public static void main(String[] args) throws IOException {
		new erweima().getQRcode("E:\\StockProject\\appandroid.png", "http://cargo.xg56.cn:85/XGProject/jsp/downFirst.jsp");
	}
}
