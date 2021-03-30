package com.articulate.egageapi;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.qrcode.QRCodeWriter;

@Component
public class QRCodeHelper {
	public BufferedImage generateEAN13BarcodeImage(String barcodeText) throws Exception {
		EAN13Writer barcodeWriter = new EAN13Writer();
		BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_13, 300, 150);

		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}

	public byte[] generateQRCodeImage(String barcodeText) throws Exception {
		QRCodeWriter barcodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

		return this.toByteArray(MatrixToImageWriter.toBufferedImage(bitMatrix),"jpeg");
	}

	private byte[] toByteArray(BufferedImage bi, String format) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bi, format, baos);
		byte[] bytes = baos.toByteArray();
		return bytes;

	}
}
