package org.benetech.confluence;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.atlassian.renderer.wysiwyg.converter.DefaultWysiwygConverter;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * Command line Java application takes an HTML file and creates a Confluence-formatted HTML file
 * Primary source:
 * @author paul-nelson-baker (@github)
 * https://github.com/paul-nelson-baker/html-to-jira-markup/blob/master/src/test/java/HTML2AtlassianMarkupTests.java
 * @author cadenh@benetech.org (Glue and packaging)
 */
public class Converter {

    public static void main(final String[] args) {
        String inFileName = "infile.html";
        String outFileName = "outfile.cf";
        if (args.length > 0) {
            inFileName = args[0];
            outFileName = inFileName + ".cf";
        } else {
            System.out.println();
            System.out.println("Usage: java -jar [This JAR name] [HTML input file name] [Confluence output file name]");
            System.out.println("If [Confluence output file name] is omitted, it will be same as [HTML input file name] + .cf");
            System.out.println();
            System.exit(0);
        }
        if (args.length > 1) {
            outFileName = args[1];
        }
        try (
             final FileWriter writer = new FileWriter(outFileName, false);
             final BufferedWriter bufferedWriter = new BufferedWriter(writer)
             ) {
            String htmlText = readFile(inFileName, StandardCharsets.UTF_8);
            htmlText = Jsoup.clean(htmlText, Whitelist.relaxed());

            final DefaultWysiwygConverter converter = new DefaultWysiwygConverter();
            final String wikiMarkupBuffer = converter.convertXHtmlToWikiMarkup(htmlText);
            bufferedWriter.write(wikiMarkupBuffer);
            System.out.println(wikiMarkupBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFile(final String path, final Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
