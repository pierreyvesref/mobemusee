package fr.miage.orleans.isi.projet.paiementws.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Component
public class QRCodeGenerator {

    private static final String QR_CODE_IMAGE_PATH = "../data/qrcodepng/";
    private static final int WIDTH = 350;
    private static final int HEIGHT = 350;


    //créé le qrcode dans le répertoire séléctionné
    public void generateQRCodePngIntoFile(String key)
            throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(key, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
        Path path = FileSystems.getDefault().getPath(QR_CODE_IMAGE_PATH + key + ".png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    public byte[] generateQRCodeBytes(String key) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(key, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();

        return pngData;
    }

    public String readQRCodePngIntoFile() throws IOException, NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(QR_CODE_IMAGE_PATH)))));
        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap);
        return qrCodeResult.getText();
    }

    public String readQRCodeBytes(byte[] qrCodeBytes) throws IOException, NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(ImageIO.read(new ByteArrayInputStream(qrCodeBytes)))));
        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap);

        return qrCodeResult.getText();
    }

}