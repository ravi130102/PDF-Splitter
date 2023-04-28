//import java.io.*;
//import javax.swing.*;
//
//public class PDFSplitter extends JFrame {
//    private JPanel contentPane;
//    private JButton btnSplit;
//    private JTextField txtInputFilePath;
//    private JTextField txtOutputDirectoryPath;
//    private JSpinner spnStartPage;
//    private JSpinner spnEndPage;
//
//    public PDFSplitterFrame() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setTitle("PDF Splitter");
//        setSize(600, 400);
//        setLocationRelativeTo(null);
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
//        btnSplit.addActionListener(e -> {
//            // Get the input and output paths and page range from the form
//            String inputFilePath = txtInputFilePath.getText();
//            String outputDirectoryPath = txtOutputDirectoryPath.getText();
//            int startPage = (int) spnStartPage.getValue();
//            int endPage = (int) spnEndPage.getValue();
//
//            // Create a PDFSplitter object and use it to split the file
//            try {
//                PDFSplitter splitter = new PDFSplitter();
//                splitter.setStartPage(startPage);
//                splitter.setEndPage(endPage);
//
//                File inputFile = new File(inputFilePath);
//                PDDocument document = PDDocument.load(inputFile);
//
//                // Split the document into individual pages
//                List<PDDocument> pages = splitter.split(document);
//
//                // Create the output directory if it doesn't exist
//                File outputDirectory = new File(outputDirectoryPath);
//                if (!outputDirectory.exists()) {
//                    outputDirectory.mkdirs();
//                }
//
//                // Save each page to a separate file
//                for (int i = 0; i < pages.size(); i++) {
//                    PDDocument page = pages.get(i);
//                    File outputFile = new File(outputDirectory, "page" + (i + 1) + ".pdf");
//                    page.save(outputFile);
//                    page.close();
//                }
//
//                document.close();
//
//                JOptionPane.showMessageDialog(this, "PDF file split successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(this, "Error splitting PDF file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            }
//
//
//
