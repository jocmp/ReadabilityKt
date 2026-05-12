package com.jocmp.readability.model

data class PageTestData(
    val pageName: String,
    val sourceHtml: String,
    val expectedOutput: String,
    val expectedMetadata: ArticleMetadata,
)
