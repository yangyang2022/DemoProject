package com.yangyang.zxing;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CreateQrCodeZxing {

    private static void createCode() throws WriterException, IOException {
        int width = 300,height=300;
        String format = "png";
        String content = "hello yangyang 你好 洋洋!!!";

        Map<EncodeHintType,Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN,2);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hints);
        MatrixToImageWriter.writeToPath(bitMatrix,format, Paths.get("C:\\code\\qrcode\\img.png"));

    }
    private static void ReadQRCode() throws IOException, NotFoundException {
        MultiFormatReader reader = new MultiFormatReader();
        File file = new File("C:\\code\\qrcode\\img.png");
        BufferedImage image = ImageIO.read(file);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

        Map<DecodeHintType,Object> hints = new HashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET,"UTF-8");

        Result result = reader.decode(bitmap,hints);

        System.out.println(result.toString());
        System.out.println("result: "+result.getText());
        System.out.println("result: "+result.getBarcodeFormat());
    }
    public static void main(String[] args) throws WriterException, IOException, NotFoundException {
        createCode();
        ReadQRCode();
    }
}
