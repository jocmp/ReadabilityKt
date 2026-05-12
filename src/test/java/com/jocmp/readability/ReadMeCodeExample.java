package com.jocmp.readability;


/**
 * Not a real test, just to have the example used in README.md as real Java code
 */
public class ReadMeCodeExample {

    public void codeExample() {
        String url = "";
        String html = "";

        Readability readability = new Readability(url, html); // url is just needed to resolve relative urls
        Article article = readability.parse();

        String extractedContentHtml = article.getContent();
        String extractedContentPlainText = article.getTextContent();
        String title = article.getTitle();
        String byline = article.getByline();
        String excerpt = article.getExcerpt();
    }
}
