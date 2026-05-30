import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class QRCodeGUI extends JFrame {

    private JTextField inputField;
    private JLabel QRCodeDisplay;
    private JButton generateButton;

    public QRCodeGUI() {
        setTitle("QR Code Generator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputField = new JTextField("https://www.linkedin.com");
        generateButton = new JButton("Generate QR");

        topPanel.add(new JLabel("Enter Text or URL:"), BorderLayout.NORTH);
        topPanel.add(inputField, BorderLayout.CENTER);
        topPanel.add(generateButton, BorderLayout.SOUTH);

        QRCodeDisplay = new JLabel("", SwingConstants.CENTER);

        QRCodeDisplay.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        add(topPanel, BorderLayout.NORTH);
        add(QRCodeDisplay, BorderLayout.CENTER);

        generateButton.addActionListener(e -> handleGeneration());
    }

    private void handleGeneration() {

        String text = inputField.getText().trim();

        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter some text first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            BufferedImage qrImage = generateQRCodeInMemory(text, 300, 300);

            QRCodeDisplay.setIcon(new ImageIcon(qrImage));
        } catch (WriterException ex) {
            JOptionPane.showMessageDialog(this, "Error generating Qr code: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private BufferedImage generateQRCodeInMemory(String text, int width, int height) throws WriterException {
        MultiFormatWriter qrCodeWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater( () -> {QRCodeGUI gui = new QRCodeGUI(); gui.setVisible(true);});
    }
}
