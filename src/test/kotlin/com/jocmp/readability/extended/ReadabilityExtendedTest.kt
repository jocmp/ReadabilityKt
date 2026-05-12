package com.jocmp.readability.extended

import com.jocmp.readability.Readability
import com.jocmp.readability.ReadabilityTest
import com.jocmp.readability.model.PageTestData
import com.jocmp.readability.model.ReadabilityOptions
import java.util.*


open class ReadabilityExtendedTest : ReadabilityTest() {

    override fun createReadability(url: String, testData: PageTestData): Readability {
        // Provide one class name to preserve, which we know appears in a few
        // of the test documents.
        return ReadabilityExtended(url, testData.sourceHtml,
                ReadabilityOptions(additionalClassesToPreserve = Arrays.asList("caption")))
    }

    override fun loadTestData(testPageFolderName: String, pageName: String): PageTestData {
        var testData = super.loadTestData(testPageFolderName, pageName)

        try {
            // check if test case has a different expected output placed in expected-extended.html
            val expectedExtendedOutput = getFileContentFromResource(testPageFolderName, pageName, "expected-extended.html")

            testData = PageTestData(testData.pageName, testData.sourceHtml, expectedExtendedOutput, testData.expectedMetadata)
        } catch(ignored: Exception) { }

        return testData
    }
}