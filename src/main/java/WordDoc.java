import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WordDoc {
	String filePath;
	String text;
	public WordDoc(String filePath){
		this.filePath = filePath;
		this.convertDocToString();
	}

	public boolean contains(String substring){
		return this.text != null && this.text.contains(substring);
	}
	public void open() {
		if(Desktop.isDesktopSupported()) {
			try {
				File myFile = new File(this.filePath);
				Desktop.getDesktop().open(myFile);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	private void convertDocToString() {
		try{
			FileInputStream fis = new FileInputStream(this.filePath);
			XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
			XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
			this.text = extractor.getText();
		}catch(NotOfficeXmlFileException e) {
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
