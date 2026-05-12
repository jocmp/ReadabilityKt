package com.jocmp.readability.extended

import com.jocmp.readability.Readability
import com.jocmp.readability.extended.processor.ArticleGrabberExtended
import com.jocmp.readability.extended.processor.PostprocessorExtended
import com.jocmp.readability.extended.util.RegExUtilExtended
import com.jocmp.readability.model.ReadabilityOptions
import com.jocmp.readability.processor.MetadataParser
import com.jocmp.readability.processor.Preprocessor
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


open class ReadabilityExtended : Readability {

    // for Java interoperability
    /**
     * Calls Readability(String, String, ReadabilityOptions) with default ReadabilityOptions
     */
    constructor(uri: String, html: String) : this(uri, html, ReadabilityOptions())

    constructor(uri: String, html: String, options: ReadabilityOptions = ReadabilityOptions(), regExUtil: RegExUtilExtended = RegExUtilExtended(),
                preprocessor: Preprocessor = Preprocessor(regExUtil), metadataParser: MetadataParser = MetadataParser(regExUtil),
                articleGrabber: ArticleGrabberExtended = ArticleGrabberExtended(options, regExUtil), postprocessor: PostprocessorExtended = PostprocessorExtended())
            : this(uri, Jsoup.parse(html, uri), options, regExUtil, preprocessor, metadataParser, articleGrabber, postprocessor)

    // for Java interoperability
    /**
     * Calls Readability(String, Document, ReadabilityOptions) with default ReadabilityOptions
     */
    constructor(uri: String, document: Document) : this(uri, document, ReadabilityOptions())

    constructor(uri: String, document: Document, options: ReadabilityOptions = ReadabilityOptions(), regExUtil: RegExUtilExtended = RegExUtilExtended(),
                preprocessor: Preprocessor = Preprocessor(regExUtil), metadataParser: MetadataParser = MetadataParser(regExUtil),
                articleGrabber: ArticleGrabberExtended = ArticleGrabberExtended(options, regExUtil), postprocessor: PostprocessorExtended = PostprocessorExtended())
            : super(uri, document, options, regExUtil, preprocessor, metadataParser, articleGrabber, postprocessor)

}