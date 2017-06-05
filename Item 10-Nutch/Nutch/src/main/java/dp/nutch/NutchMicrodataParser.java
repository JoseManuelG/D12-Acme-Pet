
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

public class NutchMicrodataParser {

	public static void main(final String[] args) {
		Collection<File> htmls;
		Collection<ItemScope> itemScopes, all;

		htmls = NutchMicrodataParser.getFiles("resources/dump");
		itemScopes = new HashSet<ItemScope>();
		try {
			for (final File file : htmls) {
				all = NutchMicrodataParser.parseMicrodata(file);
				for (final ItemScope item : all)
					if (item.getType().toString().equals("http://schema.org/Product"))
						itemScopes.add(item);
			}
			System.out.println(itemScopes.size());
		} catch (final IOException e) {
			System.out.println(e.getStackTrace());
		}

	}
	private static Collection<ItemScope> parseMicrodata(final File file) throws IOException {

		Document document;
		Collection<ItemScope> result;
		ItemScope[] itemScopes;

		document = NutchMicrodataParser.getFileAsDocument(file);

		itemScopes = MicrodataParser.getMicrodata(document).getDetectedItemScopes();
		result = new HashSet<ItemScope>(Arrays.asList(itemScopes));

		return result;

	}

	private static String parseMicrodataAsJson(final File file) throws IOException {

		Document document;
		ByteArrayOutputStream byteArrayOutput;
		String result;

		document = NutchMicrodataParser.getFileAsDocument(file);
		byteArrayOutput = new ByteArrayOutputStream();

		MicrodataParser.getMicrodataAsJSON(document, new PrintStream(byteArrayOutput));

		result = byteArrayOutput.toString("UTF-8");
		return result;

	}

	private static Document getFileAsDocument(final File file) throws IOException {

		InputStream documentInputInputStream;
		TagSoupParser tagSoupParser;
		Document result;

		documentInputInputStream = new FileInputStream(file);
		tagSoupParser = new TagSoupParser(documentInputInputStream, file.getAbsolutePath());
		result = tagSoupParser.getDOM();
		return result;

	}

	private static Collection<File> getFiles(final String dir) {
		File[] files;
		Collection<File> result;

		result = new HashSet<File>();
		files = new File(dir).listFiles();

		for (final File file : files)
			if (file.isDirectory())
				result.addAll(NutchMicrodataParser.getFiles(file.getAbsolutePath()));
			else
				result.add(file);
		return result;
	}
}
