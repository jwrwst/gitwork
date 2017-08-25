package com.platform.rp.util.qrcode.impl;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码图片生成/读取服务的<code>zxing</code>实现
 * 
 *
 */
@Service("matrixImageService")
public class MatrixImageServiceImpl{

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;
	private static final int WIDTH = 350;
	private static final int HEIGHT = 350;
	private static final String CHARSET = "UTF-8";


	public MatrixImageServiceImpl() {
		super();
	}
    public void write(String text, String format, OutputStream stream,InputStream logo)
            throws IOException {
        BufferedImage image = write(text);
        if(null!=logo){
	        //image.createGraphics();
	        int width=WIDTH/5;
	        int height=HEIGHT/5;
	        int x=(image.getWidth()-width)/2;
	        int y=(image.getHeight()-height)/2;
	        BufferedImage logoImage = ImageIO.read(logo);
	        Graphics2D g= (Graphics2D) image.getGraphics();
	        //设置对线段的锯齿状边缘处理 
	        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION , RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        //g.drawImage(logoImage, x, y, width, height,null);
	        g.drawImage(logoImage.getScaledInstance(width,  
	        		height, Image.SCALE_SMOOTH), x, y, null);
	        g.dispose();
        }
        if (!ImageIO.write(image, "png", stream)) {
            throw new IOException("Could not write an image of format "
                    + format);
        }
    }
	public BufferedImage write(String text) throws IOException {
	    if(text == null){
	        text = "";
	    }
		BitMatrix bitMatrix = writeToBitMatrix(text, WIDTH, HEIGHT, CHARSET);
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}
	
	/*
     * 按指定的参数将二维码文本写入位矩阵对象
     * 
     * @param text
     *            要写入的文本
     * @param width
     *            图片宽度
     * @param height
     *            图片高度
     * @param charset
     *            文本字符集
     * @return 位矩阵对象
     * @throws IOException
     */
    private BitMatrix writeToBitMatrix(String text, int width, int height,
            String charset) throws IOException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, charset);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //hints.put(EncodeHintType.MARGIN, 1);
        
        try {
            return new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE,
                    width, height, hints);
        } catch (WriterException e) {
            throw new IOException(
                    "Contents cannot be encoded legally in a format");
        }
    }

}
