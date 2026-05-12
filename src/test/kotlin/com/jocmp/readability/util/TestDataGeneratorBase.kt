package com.jocmp.readability.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.jocmp.readability.Article
import com.jocmp.readability.model.ArticleMetadata
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.nio.charset.Charset


abstract class TestDataGeneratorBase {

    companion object {
        val objectMapper = ObjectMapper()
    }


    protected open fun writeTestData(sourceHtml: String, article: Article, articleExtended: Article, testFolderName: String, testCaseName: String) {
        val testCaseFolder = getTestCaseFolder(testFolderName, testCaseName)

        writeFile(testCaseFolder, "source.html", sourceHtml)
        writeFile(testCaseFolder, "expected.html", article.content ?: "")
        writeFile(testCaseFolder, "expected-metadata.json", generateMetadataJson(article))

        if(articleExtended.content != article.content) {
            writeFile(testCaseFolder, "expected-extended.html", articleExtended.content ?: "")
        }
    }

    protected open fun getTestCaseFolder(testFolderName: String, testCaseName: String): File {
        val testPagesFolder = File(getPathToTestResourcesFolder(), testFolderName)
        val testCaseFolder = File(testPagesFolder, testCaseName)

        if(testCaseFolder.exists() == false) {
            testCaseFolder.mkdirs()
        }

        return testCaseFolder
    }

    protected open fun generateMetadataJson(article: Article): String {
        val metadata = ArticleMetadata(article.title, article.byline, article.excerpt, article.dir)

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(metadata)
    }

    protected open fun writeFile(testCaseFolder: File, fileName: String, fileContent: String) {
        val writer = OutputStreamWriter(FileOutputStream(File(testCaseFolder, fileName)), Charset.forName("UTF-8").newEncoder()) // TODO: set encoding

        writer.write(fileContent)

        writer.flush()
        writer.close()
    }

    protected open fun getPathToTestResourcesFolder(): File {
        val testPageResourceUrl = this.javaClass.classLoader.getResource("logback-test.xml") // get url to any test resource that's for sure there
        val testPageResourceFile = File(testPageResourceUrl.toURI())

        val readabilityFolder = testPageResourceFile.parentFile.parentFile.parentFile.parentFile

        return File(File(File(readabilityFolder, "src"), "test"), "resources")
    }

}