# ReadabilityKt
[![CI](https://github.com/jocmp/ReadabilityKt/actions/workflows/ci.yml/badge.svg)](https://github.com/jocmp/ReadabilityKt/actions/workflows/ci.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.jocmp/readabilitykt/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.jocmp/readabilitykt)

ReadabilityKt is a Kotlin port of Mozilla's Readability.js, which is used for Firefox's reader view: https://github.com/mozilla/readability.

It tries to detect the relevant content of a website and removes all clutter from it such as advertisements, navigation bars, social media buttons, etc.

The extracted text then can be used for indexing web pages, to provide the user a pleasant reading experience and similar.

As it‘s compatible with Mozilla‘s Readability.js it produces exact the same output as you would see in Firefox‘s Reader View (just some white spaces differ due to Jsoup‘s different formatting, but you can‘t see them anyway).

## Setup

Gradle:
```kotlin
dependencies {
    implementation("com.jocmp:readabilitykt:1.0.8")
}
```

Maven:
```xml
<dependency>
   <groupId>com.jocmp</groupId>
   <artifactId>readabilitykt</artifactId>
   <version>1.0.8</version>
</dependency>
```


## Usage

```kotlin
val url: String = ...
val html: String = ...

val readability4J = Readability4J(url, html) // url is just needed to resolve relative urls
val article = readability4J.parse()

// returns extracted content in a <div> element
val extractedContentHtml = article.content
// to get content wrapped in <html> tags and encoding set to UTF-8, see chapter 'Output encoding'
val extractedContentHtmlWithUtf8Encoding = article.contentWithUtf8Encoding
val extractedContentPlainText = article.textContent
val title = article.title
val byline = article.byline
val excerpt = article.excerpt
```

## Readability4J and Readability4JExtended

The `Readability4J` class sticks close to Mozilla's Readability to keep compatibility.

`Readability4JExtended` adds some handy features not supported by upstream Readability, e.g. copying the url from a
`data-src` attribute to `<img src="" />` to display lazy-loading images, using `<head><base>`'s href value for
resolving relative urls, and better detection of which images to keep in the output.

If you want to use it, simply instantiate with (the rest of the code stays the same):

```kotlin
val readability4J: Readability4J = Readability4JExtended(url, html)
val article = readability4J.parse()
```

## Output encoding

By default no encoding is applied to ReadabilityKt's output, which can result in incorrect display of non-ASCII characters.

Like Readability.js, ReadabilityKt returns its output in a `<div>` element, and the only way to set the encoding in
HTML is via a `<head><meta charset="" />` tag.

So these convenience methods are exposed on `Article`:

```kotlin
val contentHtmlWithUtf8Encoding = article.contentWithUtf8Encoding
// or (tries to apply site's charset, falling back to UTF-8)
val contentWithDocumentsCharsetOrUtf8 = article.contentWithDocumentsCharsetOrUtf8
// or
val contentHtmlWithCustomEncoding = article.getContentWithEncoding("ISO-8859-1")
```

which wrap the content in

```
<html>
 <head>
  <meta charset="utf-8" /> 
 </head>
 <body>
 <!-- content -->
 </body>
</html>
```

## Compatibility with Mozilla‘s Readability.js

As mentioned before, this is almost an exact copy of Mozilla's Readability.js. But since I didn't find the original code very readable itself, I extracted some parts from the 2000 lines of code into a new classes:

<table>
    <tr>
        <th>Readability.js function</td>
        <th>Readability4J location</td>
    </tr>
    <tr>
        <td>_removeScripts() and _prepDocument()</td>
        <td>Preprocessor.prepareDocument()</td>
    </tr>
    <tr>
        <td>_grabArticle()</td>
        <td>ArticleGrabber.grabArticle()</td>
    </tr>
    <tr>
        <td>_postProcessContent()</td>
        <td>Postprocessor.postProcessContent()</td>
    </tr>
    <tr>
        <td>_getArticleMetadata()</td>
        <td>MetadataParser.getArticleMetadata()</td>
    </tr>
</table>


Overview of which Mozilla‘s Readability.js commit a Readability4J version matches:

<table>
    <tr>
        <th>Version</td>
        <th>Commit</td>
        <th>Date</td>
    </tr>
    <tr>
        <td>1.0</td>
        <td>8da91b9</td>
        <td>12/5/17</td>
    </tr>
    <tr>
        <td>1.0.1</td>
        <td>834672e</td>
        <td>02/27/18</td>
    </tr>
</table>

## Extensibility

The library is designed to be extensible — all of the classes above can be overridden and passed to `Readability4J`'s constructor.

## Logging

ReadabilityKt uses slf4j as its logging facade, so you can use any slf4j-compatible logger (Logback, log4j, etc.) to configure and capture its log output.

# License

    Copyright 2017 dankito

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
