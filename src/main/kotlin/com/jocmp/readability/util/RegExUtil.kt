package com.jocmp.readability.util

import java.util.regex.Pattern

open class RegExUtil {
    companion object {
        const val UNLIKELY_CANDIDATES_DEFAULT_PATTERN =
            "banner|breadcrumbs|combx|comment|community|cover-wrap|disqus|extra|" +
                "foot|header|legends|menu|related|remark|replies|rss|shoutbox|sidebar|skyscraper|social|sponsor|supplemental|" +
                "ad-break|agegate|pagination|pager|popup|yom-remote"

        const val OK_MAYBE_ITS_A_CANDIDATE_DEFAULT_PATTERN = "and|article|body|column|main|shadow"

        const val POSITIVE_DEFAULT_PATTERN = "article|body|content|entry|hentry|h-entry|main|page|pagination|post|text|blog|story"

        const val NEGATIVE_DEFAULT_PATTERN =
            "hidden|^hid$| hid$| hid |^hid |banner|combx|comment|com-|contact|foot|footer|footnote|" +
                "masthead|media|meta|outbrain|promo|related|scroll|share|shoutbox|sidebar|skyscraper|sponsor|shopping|tags|tool|widget"

        const val EXTRANEOUS_DEFAULT_PATTERN = "print|archive|comment|discuss|e[\\-]?mail|share|reply|all|login|sign|single|utility"

        const val BYLINE_DEFAULT_PATTERN = "byline|author|dateline|writtenby|p-author"

        const val REPLACE_FONTS_DEFAULT_PATTERN = "<(/?)font[^>]*>"

        const val NORMALIZE_DEFAULT_PATTERN = "\\s{2,}"

        const val VIDEOS_DEFAULT_PATTERN = "//(www\\.)?(dailymotion|youtube|youtube-nocookie|player\\.vimeo)\\.com"

        const val NEXT_LINK_DEFAULT_PATTERN = "(next|weiter|continue|>([^\\|]|$)|»([^\\|]|$))"

        const val PREV_LINK_DEFAULT_PATTERN = "(prev|earl|old|new|<|«)"

        const val WHITESPACE_DEFAULT_PATTERN = "^\\s*$"

        const val HAS_CONTENT_DEFAULT_PATTERN = "\\S$"
    }

    protected val unlikelyCandidates: Pattern

    protected val okMaybeItsACandidate: Pattern

    protected val positive: Pattern

    protected val negative: Pattern

    protected val extraneous: Pattern

    protected val byline: Pattern

    protected val replaceFonts: Pattern

    protected val normalize: Pattern

    protected val videos: Pattern

    protected val nextLink: Pattern

    protected val prevLink: Pattern

    protected val whitespace: Pattern

    protected val hasContent: Pattern

    constructor(
        unlikelyCandidatesPattern: String = UNLIKELY_CANDIDATES_DEFAULT_PATTERN,
        okMaybeItsACandidatePattern: String = OK_MAYBE_ITS_A_CANDIDATE_DEFAULT_PATTERN,
        positivePattern: String = POSITIVE_DEFAULT_PATTERN,
        negativePattern: String = NEGATIVE_DEFAULT_PATTERN,
        extraneousPattern: String = EXTRANEOUS_DEFAULT_PATTERN,
        bylinePattern: String = BYLINE_DEFAULT_PATTERN,
        replaceFontsPattern: String = REPLACE_FONTS_DEFAULT_PATTERN,
        normalizePattern: String = NORMALIZE_DEFAULT_PATTERN,
        videosPattern: String = VIDEOS_DEFAULT_PATTERN,
        nextLinkPattern: String = NEXT_LINK_DEFAULT_PATTERN,
        prevLinkPattern: String = PREV_LINK_DEFAULT_PATTERN,
        whitespacePattern: String = WHITESPACE_DEFAULT_PATTERN,
        hasContentPattern: String = HAS_CONTENT_DEFAULT_PATTERN,
    ) {
        this.unlikelyCandidates = Pattern.compile(unlikelyCandidatesPattern, Pattern.CASE_INSENSITIVE)
        this.okMaybeItsACandidate = Pattern.compile(okMaybeItsACandidatePattern, Pattern.CASE_INSENSITIVE)
        this.positive = Pattern.compile(positivePattern, Pattern.CASE_INSENSITIVE)
        this.negative = Pattern.compile(negativePattern, Pattern.CASE_INSENSITIVE)
        this.extraneous = Pattern.compile(extraneousPattern, Pattern.CASE_INSENSITIVE)
        this.byline = Pattern.compile(bylinePattern, Pattern.CASE_INSENSITIVE)
        this.replaceFonts = Pattern.compile(replaceFontsPattern, Pattern.CASE_INSENSITIVE)
        this.normalize = Pattern.compile(normalizePattern)
        this.videos = Pattern.compile(videosPattern, Pattern.CASE_INSENSITIVE)
        this.nextLink = Pattern.compile(nextLinkPattern, Pattern.CASE_INSENSITIVE)
        this.prevLink = Pattern.compile(prevLinkPattern, Pattern.CASE_INSENSITIVE)
        this.whitespace = Pattern.compile(whitespacePattern)
        this.hasContent = Pattern.compile(hasContentPattern)
    }

    open fun isPositive(matchString: String): Boolean {
        return positive.matcher(matchString).find()
    }

    open fun isNegative(matchString: String): Boolean {
        return negative.matcher(matchString).find()
    }

    open fun isUnlikelyCandidate(matchString: String): Boolean {
        return unlikelyCandidates.matcher(matchString).find()
    }

    open fun okMaybeItsACandidate(matchString: String): Boolean {
        return okMaybeItsACandidate.matcher(matchString).find()
    }

    open fun isByline(matchString: String): Boolean {
        return byline.matcher(matchString).find()
    }

    open fun hasContent(matchString: String): Boolean {
        return hasContent.matcher(matchString).find()
    }

    open fun isWhitespace(matchString: String): Boolean {
        return whitespace.matcher(matchString).find()
    }

    open fun normalize(text: String): String {
        return normalize.matcher(text).replaceAll(" ")
    }

    open fun isVideo(matchString: String): Boolean {
        return videos.matcher(matchString).find()
    }
}
