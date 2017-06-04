
package dp.nutch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ExtractData {

	public static void main(final String[] args) {

		File archivo;
		FileReader fr;
		BufferedReader br;
		String html, urlBase, url, nombre, precio, imagen;
		String[] divs, divs2;

		FileWriter fichero = null;
		PrintWriter pw = null;

		fr = null;
		for (int i = 1; i < 3; i++)
			try {
				urlBase = "http://www.wanyma.com";
				archivo = new File("resources/producto" + i + ".html");
				fr = new FileReader(archivo);
				br = new BufferedReader(fr);

				// Lectura del fichero
				html = br.readLine();

				divs = html.split("http://schema.org/Product");

				//url

				divs2 = divs[0].split("href=\"http://www.wanyma.com");
				divs2 = divs2[1].split("\"");
				url = urlBase + divs2[0];

				//imagen
				divs2 = divs[1].split("itemprop=\"image\" src=\"");
				divs2 = divs2[1].split("\"");
				imagen = urlBase + divs2[0];

				//nombre
				divs2 = divs[1].split("itemprop=\"name\">");
				divs2 = divs2[1].split("</");
				nombre = divs2[0];

				//precio
				divs2 = divs[1].split("\" itemprop=\"price\">");
				divs2 = divs2[0].split("<meta content=\"");
				precio = divs2[divs2.length - 1];

				divs2 = divs[1].split("\" itemprop=\"priceCurrency\">");
				divs2 = divs2[0].split("<meta content=\"");
				precio = precio + " " + divs2[divs2.length - 1];

				fichero = new FileWriter("resources/datosProducto" + i + ".txt");
				pw = new PrintWriter(fichero);

				pw.println("URL producto: " + url);
				pw.println("Nombre: " + nombre);
				pw.println("Precio: " + precio);
				pw.println("Imagen: " + imagen);

			} catch (final Exception e) {
				e.printStackTrace();
			} finally {
				// En el finally cerramos el fichero
				try {
					if (null != fr)
						fr.close();
					if (null != fichero)
						fichero.close();
				} catch (final Exception e2) {
					e2.printStackTrace();
				}
			}
	}
}
