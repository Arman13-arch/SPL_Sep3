package Duplicity_Checker_package.Code;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class Multiple_by_selecting_folder_sorting extends Basic_Frame_Duplicity {

    File  [] fileNames1;
    File  [] fileNames2;
    File  [] listoffiles1;
    File  [] listoffiles2;
    float [] matching_line_number = new float[100];
    float [] result    = new float[100];
    String[] array1    = new String[100];
    String[] array2    = new String[100];
    String[] FILENAME1 = new String[100];
    String[] FILENAME2 = new String[100];
    String[] FILENAME3 = new String[100];
    String[] FILENAME4 = new String[100];

    JFileChooser savefile;
    String save_filename;
//    private JButton save_button;
    char per = '%' ;
    File folder1 ,folder2 , file1 , file2;
    float count = 0 , line = 0 , percentage , temp ;
    int file_number1=0, file_number2=0, file_number_check1=0,file_number_check2=0,result_counter=0, names1, names2 , ignored_word=0;
    String  folderrname1,folderrname2 , string_temp1 , string_temp2 ,fileExtension2 ,extension2;
    ButtonSound sound_button = new ButtonSound();

    Multiple_by_selecting_folder_sorting() throws IOException {

        App_Icon();
        super.frame();
        super.setContainer();
        super.setPanel();
        super.userinterface();
        super.buttons();
        Logical_part();
    }

    public void Logical_part(){
        back_button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent ea){
                sound_button.playsound();
                dispose();
                Back_Button BB = new Back_Button();
                BB.backbutton();
            }
        });

//        save_button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                sound_button.playsound();
//
//                if (result_textarea.getText().length() > 0) {
//
//                    save_filename = JOptionPane.showInputDialog("Write New File Name");
//                    savefile = new JFileChooser();
//                    savefile.setDialogTitle("Choose Directory");
//                    savefile.setSelectedFile(new File(save_filename));
//                    //      BufferedWriter writer;
//                    int sf = savefile.showSaveDialog(null);
//                    if (sf == JFileChooser.APPROVE_OPTION) {
//                        sound_button.playsound();
//
//                        try {
//                            Object[] choices = {"Docx", "Pdf", "Cancel"};
//                            Object defaultChoice = choices[0];
//                            int n = JOptionPane.showOptionDialog(null, "Select Format", "Format Choice", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, defaultChoice);
//                            if (n == JOptionPane.YES_OPTION) {
//                                sound_button.playsound();
//                                save_as_docx();
//                            } else if (n == JOptionPane.NO_OPTION) {
//                                sound_button.playsound();
//                                save_as_pdf();
//
//                            } else {
//                                sound_button.playsound();
//                            }
//                        } catch (Exception ee) {
//                            JOptionPane.showMessageDialog(null, ee);
//                        }
//                    } else {
//                        sound_button.playsound();
//                    }
//                }
//                else{
//                    Object[] options = {"Ok"};
//                    int n = JOptionPane.showOptionDialog(null, "There is no text to save", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
//                    if (n == JOptionPane.OK_OPTION) {
//                        sound_button.playsound();
//                    }
//                }
//            }
//        });

        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound_button.playsound();

                if (result_textarea.getText().length() > 0) {

                    save_filename = JOptionPane.showInputDialog("Write New File Name");
                    savefile = new JFileChooser();
                    savefile.setDialogTitle("Choose Directory");
                    savefile.setSelectedFile(new File(save_filename));
                    //      BufferedWriter writer;
                    int sf = savefile.showSaveDialog(null);
                    if (sf == JFileChooser.APPROVE_OPTION) {
                        sound_button.playsound();

                        try {
                            Object[] choices = {"Docx", "Pdf", "Cancel"};
                            Object defaultChoice = choices[0];
                            int n = JOptionPane.showOptionDialog(null, "Select Format", "Format Choice", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, defaultChoice);
                            if (n == JOptionPane.YES_OPTION) {
                                sound_button.playsound();
                                save_as_docx();
                            } else if (n == JOptionPane.NO_OPTION) {
                                sound_button.playsound();
                                save_as_pdf();

                            } else {
                                sound_button.playsound();
                            }
                        } catch (Exception ee) {
                            JOptionPane.showMessageDialog(null, ee);
                        }
                    } else {
                        sound_button.playsound();
                    }
                }
                else{
                    Object[] options = {"Ok"};
                    int n = JOptionPane.showOptionDialog(null, "There is no text to save", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
                    if (n == JOptionPane.OK_OPTION) {
                        sound_button.playsound();
                    }
                }
            }
        });

        file_read_button1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent ea){
                sound_button.playsound();

                try {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setCurrentDirectory(new java.io.File("."));
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    chooser.setAcceptAllFileFilterUsed(false);;
                    int option  = chooser.showOpenDialog(null);
                    if (option == JFileChooser.APPROVE_OPTION) {
                        if (comparable_file_textarea.getText().length() > 0) {
                            comparable_file_textarea.setText("");
                        }

                        File folder1 = chooser.getCurrentDirectory();
                        Multiple_by_selecting_folder_sorting listFiles = new Multiple_by_selecting_folder_sorting();
                        new local1().listAllFiles1(folder1);

//For reading the folder name :
                        File folder_file_names1 = chooser.getCurrentDirectory();
                        listoffiles1 = folder_file_names1.listFiles();
                        fileNames1 = folder1.listFiles();

                        for (int w = 0; w < file_number1; w++) {
                            if(file_number1!=1) {
                                comparable_file_textarea.append("File Name : " + FILENAME1[w] + "\n");
                            }
                            comparable_file_textarea.append(array1[w]+"\n\n");
                        }

                        for (names1 = 0; names1 < listoffiles1.length; names1++) {
                            if (listoffiles1[names1].isDirectory()) {
                                folderrname1 = listoffiles1[names1].getName();
                            }
                        }

                    }
                    else {
                        Object[] options = {"Ok"};
                        int n = JOptionPane.showOptionDialog(null, "No File Selected", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);

                        if (n == JOptionPane.OK_OPTION) {
                            sound_button.playsound();
                        }
                    }
                }
                catch (Exception e)
                {
                    ok_button_sound();
                }
                file_number1=0;
            }
        });

        file_read_button2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent ea){

                sound_button.playsound();
                try {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setCurrentDirectory(new java.io.File("."));
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    chooser.setAcceptAllFileFilterUsed(false);
                    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        if(comparing_file_textarea.getText().length()>0){
                            comparing_file_textarea.setText("");
                        }
                        folder2 = chooser.getCurrentDirectory();
                        Multiple_by_selecting_folder_sorting listFiles = new Multiple_by_selecting_folder_sorting();
                        new local2().listAllFiles2(folder2);

//For reading the folder name :
                        File folder_file_names2 = chooser.getCurrentDirectory();
                        listoffiles2 = folder_file_names2.listFiles();
                        fileNames2 = folder2.listFiles();

                        for(int w=0 ; w<file_number2;w++){
                            if(file_number2!=1) {
                                comparing_file_textarea.append("File Name : " + FILENAME2[w] + "\n");
                            }
                            comparing_file_textarea.append(array2[w]);
                            comparing_file_textarea.append("\n\n");
                        }

                        for(names2 = 0 ; names2 < listoffiles2.length ; names2++){
                            if (listoffiles2[names2].isDirectory()){
                                folderrname2 = listoffiles2[names2].getName();
                            }
                        }

                    }
                    else {
                        Object[] options = {"Ok"};
                        int n = JOptionPane.showOptionDialog(null, "No File Selected", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);

                        if (n == JOptionPane.OK_OPTION) {
                            sound_button.playsound();
                        }
                    }
                }
                catch (Exception e) {
                    ok_button_sound();
                }
                file_number2=0;
            }
        });

        file_check_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ea) {
                try {
                    sound_button.playsound();
                    if(result_textarea.getText().length()>0){
                        result_textarea.setText("");
                    }

                    if (comparable_file_textarea.getText().length() == 0 || comparing_file_textarea.getText().length() == 0) {
                        Object[] options = {"Ok"};
                        int n = JOptionPane.showOptionDialog(null, "Nothing to Check", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);

                        if (n == JOptionPane.OK_OPTION) {
                            sound_button.playsound();
                        }
                        result_textarea.setText("");
                    }
                    if (file_number_check1 != 0 && file_number_check2 != 0) {
                        result_textarea.append("Folder Name of first choice           : " + folderrname1 + "\n");
                        result_textarea.append("Folder Name of second choice      : " + folderrname2 + "\n");
                        result_textarea.append("Total Document in first folder       : " + file_number_check1 + "\n");
                        result_textarea.append("Total Document in second folder  : " + file_number_check2 + "\n\n");
                    }

                    if (file_number_check1 == 1 && file_number_check2 == 1) {
                        single_files();
                    }
//if folder contain more than one file
                    else if (file_number_check1 > 1 || file_number_check2 >1) {
                        multilple_files();
                    }
                }
                catch (Exception e) { }
            }
        });
    }

    public void ok_button_sound(){
        Object[] options = {"Ok"};
        int n = JOptionPane.showOptionDialog(null, "No File Selected", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
        if (n == JOptionPane.OK_OPTION) {
            sound_button.playsound();
        }
    }

    public void App_Icon() {
        ImageIcon logo = new ImageIcon(getClass().getResource("Picture//icon.jpg"));
        this.setIconImage(logo.getImage());
    }

    public void single_files(){
        if(result_textarea.getText().length()>0){
            result_textarea.setText("");
        }
        try {
            String string1 = comparable_file_textarea.getText();
            String[] str = string1.split("[\\s]*[.][\\s]*");

            String string2 = comparing_file_textarea.getText();
            String[] str1 = string2.split("[\\s]*[.][\\s]*");
            repeat_from_first:
            for (int i = 0; i < str.length; i++) {                    //I am a student. I am a student.
                String[] word1 = str[i].split("\\s+");

                for (int j = 0; j < str1.length; j++) {                //I am a doctor. I am a student.
                    String[] word2 = str1[j].split("\\s+");
                    repeat:
                    for (int x = 0; x < word1.length; x++) {

                        if (word1[x].matches("am|is|are|was|were|a|an|the")) {
                            continue repeat;
                        }
                        for (int l = 0; l < word2.length; l++) {

                            if (word1[x].equals(word2[l])) {
                                String k = word2[l];
                                count++;
                                continue repeat;
                            }
                        }
                    }
                    float a = word2.length;

                    float ans = ((count / a) * 100);
                    if (ans > 60) {
                        line++;
                        if (string1.length() == 0 || string2.length() == 0) {
                            result_textarea.setText("");
                        } else {
                            result_textarea.append("Matching lines : " + str1[j]+".");
                            result_textarea.append("\n");
                        }

                    }
                    count = 0;
                    if(ans>60){
                        continue repeat_from_first;
                    }
                }
            }
            if (string1.length() == 0 || string2.length() == 0) {
                result_textarea.setText("");
            } else {
                result_textarea.append("\n"+"Matching Line : " + line+"\n\n");
                float length = str1.length;
                float result = ((line / length) * 100);
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                result_textarea.append("Percentage : " +df.format(result) + "%");
                result_textarea.append("\n\n\n");
            }
            line = 0;
        } catch (Exception e) { }
    }

    public void multilple_files() throws IOException {

        for (int u = 0; u < file_number_check1; u++) {
            String s = array1[u];

            String[] str = s.split("[\\s]*[.][\\s]*");

            for (int r = 0; r < file_number_check2; r++) {
                String s1 = array2[r];
                String[] str1 = s1.split("[\\s]*[.][\\s]*");

                repeat_from_first:
                for (int i = 0; i < str.length; i++) {
                    String[] word1 = str[i].split("\\s+");

                    for (int j = 0; j < str1.length; j++) {
                        String[] word2 = str1[j].split("\\s+");

                        repeat:
                        for (int x = 0; x < word1.length; x++) {

                            if (word1[x].matches("am|is|are|was|were|have|has|had|a|an|the")) {
                                continue repeat;
                            }
                            for (int l = 0; l < word2.length; l++) {

                                if (word1[x].equals(word2[l])) {
                                    count++;
                                    continue repeat;
                                }
                            }
                        }
                        float a = word2.length;
                        float ans = ((count / a) * 100);

                        if (ans > 60) {

                            line++;
                        }
                        count = 0;
                        if(ans>60){
                            continue repeat_from_first;
                        }
                    }
                }
                matching_line_number[result_counter] = line;
                float length = str1.length;
                result[result_counter] = ((line / length) * 100);
                line = 0;
                FILENAME3[result_counter]=FILENAME1[u];
                FILENAME4[result_counter]=FILENAME2[r];
                result_counter++;
            }
        }
        Sorting_result SR = new Sorting_result();
        SR.sort(FILENAME3,FILENAME4,matching_line_number,result,result_counter);
        display_percentage_after_sorting();
    }

public void display_percentage_after_sorting(){
    for(int aa = 0 ; aa<result_counter;aa++) {
        if (FILENAME3[aa].equals(FILENAME4[aa])) { }
        else{
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            result_textarea.append(FILENAME3[aa] + "  matched  " + matching_line_number[aa] + " line and  " + df.format(result[aa]) + per + "  with  " + FILENAME4[aa] + "\n\n");
        }
    }
    result_counter=0;
}
    private class local1 {
        public void listAllFiles1(File folder1){

file_number_check1=0;
            fileNames1 = folder1.listFiles();
            for(File file1 : fileNames1){
                // if directory call the same method again
                if(file1.isDirectory()){
                    listAllFiles1(file1);
                }
                else{
                    try {
                        String fileExtension1 = file1.getName();
                        String extension1 = "";
                        int index_position = fileExtension1.lastIndexOf('.');
                        if (index_position >= 0) {
                            extension1 = fileExtension1.substring(index_position+1);
                        }

                        if(extension1.matches("docx")){
                            FILENAME1[file_number1] = file1.getName();
                            XWPFDocument docx = new XWPFDocument(new FileInputStream(file1));
                            XWPFWordExtractor extract = new XWPFWordExtractor(docx);
                            array1[file_number1] = extract.getText();
                            file_number1++;
                            file_number_check1++;
                        }
                       else if(extension1.matches("pdf")){
                            FILENAME1[file_number1] = file1.getName();
                            PDDocument document = PDDocument.load(file1);
                            PDFTextStripper pdfStripper = new PDFTextStripper();
                            String extract = pdfStripper.getText(document);
                            array1[file_number1] = extract;
                            file_number1++;
                            file_number_check1++;
                        }
                    else{
                        if (folder1.listFiles().length < 2) {
                            Object[] options = {"Ok"};
                            int n = JOptionPane.showOptionDialog(null, "Choose docx or pdf file only", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
                            if (n == JOptionPane.OK_OPTION) { }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private class local2 {
        public void listAllFiles2(File folder2) throws IOException {
            file_number_check2=0;
            fileNames2 = folder2.listFiles();
                for(File file2 : fileNames2){
                // if directory call the same method again
                    if(file2.isDirectory()){
                    listAllFiles2(file2);
                }
                else{
                    fileExtension2 = file2.getName();
                    extension2 = "";
                    int index_position = fileExtension2.lastIndexOf('.');
                        if (index_position >= 0) {
                            extension2 = fileExtension2.substring(index_position+1);
                        }

                        if(extension2.matches("docx")){
                            FILENAME2[file_number2] = file2.getName();
                            XWPFDocument docx = new XWPFDocument(new FileInputStream(file2));
                            XWPFWordExtractor extract = new XWPFWordExtractor(docx);
                            array2[file_number2] = extract.getText();
                            file_number2++;
                            file_number_check2++;
                        }
                        else if (extension2.matches("pdf")) {
                            FILENAME2[file_number2] = file2.getName();
                            PDDocument document = PDDocument.load(file2);
                            PDFTextStripper pdfStripper = new PDFTextStripper();
                            String extract = pdfStripper.getText(document);
                            array2[file_number2] = extract;
                            file_number2++;
                            file_number_check2++;
                        }
                    else{
                        if (folder2.listFiles().length < 2) {
                            Object[] options = {"Ok"};
                            int n = JOptionPane.showOptionDialog(null, "Choose docx or pdf file only", "Warning", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
                                if (n == JOptionPane.OK_OPTION) {
                                    sound_button.playsound();
                            }
                        }
                    }
                }
            }
        }
    }

    public void save_as_docx(){
        try {
            XWPFDocument docx = new XWPFDocument();
            FileOutputStream out = new FileOutputStream(savefile.getSelectedFile()+".docx");
            XWPFParagraph n = docx.createParagraph();
            XWPFRun run = n.createRun();
            run.setText(result_textarea.getText());
            docx.write(out);
            out.close();
            JOptionPane.showMessageDialog(null,"File saved in "+savefile.getSelectedFile()+".docx","File saved",JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void save_as_pdf(){
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(savefile.getSelectedFile() + ".pdf"));
            document.open();
            document.add(new Paragraph(result_textarea.getText()));
            document.close();
            writer.close();
            JOptionPane.showMessageDialog(null, "File saved in "+savefile.getSelectedFile() + ".pdf", "File saved", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Multiple_by_selecting_folder_sorting ms = new Multiple_by_selecting_folder_sorting();
        ms.setVisible(true);
    }
}