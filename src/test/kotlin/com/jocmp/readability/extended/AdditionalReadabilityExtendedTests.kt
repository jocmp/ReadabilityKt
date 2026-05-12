package com.jocmp.readability.extended

import com.jocmp.readability.Readability
import com.jocmp.readability.additional.AdditionalReadabilityTests
import com.jocmp.readability.model.PageTestData
import com.jocmp.readability.model.ReadabilityOptions

open class AdditionalReadabilityExtendedTests : AdditionalReadabilityTests() {
    // TODO: http://www.kleinezeitung.at/oesterreich/5298693/Maskottchen-bestraft_Verhuellungsverbot_In-Wien-musste-ein-Hai

    // TODO: https://www.nytimes.com/interactive/2017/12/19/us/ford-chicago-sexual-harassment.html

    // TODO: https://www.republik.ch/2018/02/19/interview-eribon-teil1

    override fun createReadability(
        url: String,
        testData: PageTestData,
    ): Readability {
        // Provide one class name to preserve, which we know appears in a few
        // of the test documents.
        return ReadabilityExtended(
            url,
            testData.sourceHtml,
            ReadabilityOptions(additionalClassesToPreserve = listOf("caption")),
        )
    }

    override fun loadTestData(
        testPageFolderName: String,
        pageName: String,
    ): PageTestData {
        var testData = super.loadTestData(testPageFolderName, pageName)

        try {
            // check if test case has a different expected output placed in expected-extended.html
            val expectedExtendedOutput = getFileContentFromResource(testPageFolderName, pageName, "expected-extended.html")

            testData = PageTestData(testData.pageName, testData.sourceHtml, expectedExtendedOutput, testData.expectedMetadata)
        } catch (ignored: Exception) {
        }

        return testData
    }
}
