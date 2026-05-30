import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {
    public static void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {
        // This MultiFormatWriter encodes the text into a 2D array of True/False 
        MultiFormatWriter qrCodeWriter = new MultiFormatWriter();
        
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    public static void main(String[] args) {
        String text = "www.linkedin.com/in/daniel-kovacs-9b4955404";

        String outputPath = "./MyLinkedInQR.png";

        int width = 350;
        int height = 350;

        try {
            System.out.println("Generating the QR Code...");
            generateQRCodeImage(text, width, height, outputPath);
            System.out.println("Success! QR_CODE saved to: " + outputPath);
        } catch (WriterException e) {
            System.err.println("Could not encode text to QR" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Could not save the image file: " + e.getMessage());
        }
    }
}
