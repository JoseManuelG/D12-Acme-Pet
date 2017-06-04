package dp.nutch;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.apache.any23.extractor.html.TagSoupParser;
import org.apache.any23.extractor.microdata.ItemScope;
import org.apache.any23.extractor.microdata.MicrodataParser;
import org.w3c.dom.Document;



public class NutchMicrodataParser 
{
    public static void main( String[] args ) {
    	Collection<File> htmls;
    	Collection<ItemScope> itemScopes;
    	
    	htmls = getFiles("resources/dump");
    	itemScopes = new HashSet<ItemScope>();
    	
    	try {
			for (File file : htmls){
				for (ItemScope item : parseMicrodata(file)){
					if (item.getType().toString().equals("http://schema.org/Product")){
						itemScopes.add(item);
					}
				}
			}
			System.out.println(itemScopes.size());
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
    	
    }
    
    private static Collection<ItemScope> parseMicrodata(File file) throws IOException {
    	
    	Document document;
    	Collection<ItemScope> result;
    	ItemScope[] itemScopes;
    	
    	document = getFileAsDocument(file);

    	itemScopes = MicrodataParser.getMicrodata(document).getDetectedItemScopes();
    	result = new HashSet<ItemScope>(Arrays.asList(itemScopes));
    		
    	return result;
    
    }
    
    private static String parseMicrodataAsJson(File file) throws IOException {
    	
    	Document document;
    	ByteArrayOutputStream byteArrayOutput;
    	String result;
    	
    	document = getFileAsDocument(file);
    	byteArrayOutput = new ByteArrayOutputStream();
    	
    	MicrodataParser.getMicrodataAsJSON(document, new PrintStream(byteArrayOutput));
    	
    	result = byteArrayOutput.toString("UTF-8");
    	return result;
    
    }
    
    private static Document getFileAsDocument(File file) throws IOException {
    	
    	InputStream documentInputInputStream;
    	TagSoupParser tagSoupParser;
    	Document result;
    	
    	documentInputInputStream = new FileInputStream(file);
    	tagSoupParser = new TagSoupParser(documentInputInputStream, file.getAbsolutePath());
    	result = tagSoupParser.getDOM();
    	
    	return result;
    
    }

	private static Collection<File> getFiles(String dir) {
		File[] files;
    	Collection<File> result;
    	
    	result = new HashSet<File>();
		files = new File(dir).listFiles();
		
	    for (File file : files) {
	        if (file.isDirectory()) {
	            result.addAll(getFiles(file.getAbsolutePath()));
	        } else {
	            result.add(file);
	        }
	    }
	    return result;
	}
}
