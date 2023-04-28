//
//import javax.swing.*;
//
//public class PdfSplitter extends JFrame {
//    private JPanel contentPane;
//    private JButton btnSplit;
//    private JTextField txtInputFilePath;
//    private JTextField txtOutputDirectoryPath;
//    private JSpinner spnStartPage;
//    private JSpinner spnEndPage;
//
//    public PdfSplitter() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setTitle("PDF Splitter");
//        setSize(400, 400); // set the size of the frame
//        setLocationRelativeTo(null); // center the frame on the screen
//
//        // Create the content pane and add it to the frame
//        contentPane = new JPanel();
//        setContentPane(contentPane);
//
//        // Add components to the content pane
//        JLabel lblInputFilePath = new JLabel("Input File Path:");
//        contentPane.add(lblInputFilePath);
//        txtInputFilePath = new JTextField(30);
//        contentPane.add(txtInputFilePath);
//
//        JLabel lblOutputDirectoryPath = new JLabel("Output Directory Path:");
//        contentPane.add(lblOutputDirectoryPath);
//        txtOutputDirectoryPath = new JTextField(30);
//        contentPane.add(txtOutputDirectoryPath);
//
//        JLabel lblStartPage = new JLabel("Start Page:");
//        contentPane.add(lblStartPage);
//        spnStartPage = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
//        contentPane.add(spnStartPage);
//
//        JLabel lblEndPage = new JLabel("End Page:");
//        contentPane.add(lblEndPage);
//        spnEndPage = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
//        contentPane.add(spnEndPage);
//
//        btnSplit = new JButton("Split");
//        contentPane.add(btnSplit);
//
//        // Display the frame
//        setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        PdfSplitter frame = new PdfSplitter();
//    }
//}
//
//
//
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.BoxLayout;


public class PdfSplitter extends JFrame {
    private JTextField txtInputFilePath;
    private JTextField txtOutputDirectoryPath;
    private JSpinner spnStartPage;
    private JSpinner spnEndPage;
    private JButton btnSplit;

    public PdfSplitter() {
        // Set up the frame
        setTitle("PDF Splitter");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a content pane with a vertical layout
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        setContentPane(contentPane);

        // Add components to the content pane
        JLabel lblInputFilePath = new JLabel("Input File Path:");
        contentPane.add(lblInputFilePath);
        txtInputFilePath = new JTextField(30);
        contentPane.add(txtInputFilePath);

        JLabel lblOutputDirectoryPath = new JLabel("Output Directory Path:");
        contentPane.add(lblOutputDirectoryPath);
        txtOutputDirectoryPath = new JTextField(30);
        contentPane.add(txtOutputDirectoryPath);

        JLabel lblStartPage = new JLabel("Start Page:");
        contentPane.add(lblStartPage);
        spnStartPage = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        contentPane.add(spnStartPage);

        JLabel lblEndPage = new JLabel("End Page:");
        contentPane.add(lblEndPage);
        spnEndPage = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        contentPane.add(spnEndPage);

        btnSplit = new JButton("Split");
        contentPane.add(btnSplit);

        // Attach an event listener to the "Split" button
        btnSplit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input values from the user
                String inputFilePath = txtInputFilePath.getText();
                String outputDirectoryPath = txtOutputDirectoryPath.getText();
                int startPage = (int) spnStartPage.getValue();
                int endPage = (int) spnEndPage.getValue();

                // Use PDFBox library to split the PDF file
                try (PDDocument document = PDDocument.load(new File(inputFilePath))) {
                    Splitter splitter = new Splitter();
                    splitter.setStartPage(startPage);
                    splitter.setEndPage(endPage);
                    splitter.setSplitAtPage(endPage);

                    int pageCount = 1;
                    for (PDDocument splitDocument : splitter.split(document)) {
                        splitDocument.save(outputDirectoryPath + "/output-" + pageCount++ + ".pdf");
                        splitDocument.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Display the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        new PdfSplitter();
    }
}

