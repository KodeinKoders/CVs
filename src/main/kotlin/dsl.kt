import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.lh
import react.RBuilder
import react.dom.*
import styled.*
import utils.intermediate
import kotlin.js.Date

private fun Int.groupBackgroundColor() = when (this) {
    0 -> Color.kodeinLighterOrange
    1 -> Color.kodeinLight
    else -> Color.white
}

private fun Int.groupSeparatorColor() = when (this) {
    1 -> Color.kodeinOrange
    2 -> Color.kodeinLightPink
    else -> Color.black
}

typealias Text = Map<String, String>
fun T(vararg pairs: Pair<String, String>): Text = mapOf(*pairs)

val sideMargins: Number = 8

fun cv(
        name: String,
        title: Text,
        email: String,
        phone: Text,
        birth: Date,
        builder: CVBuilder.() -> Unit
): RBuilder.(String, Boolean, Boolean) -> Unit = { lang, anonymous, grayScale ->
    styledDiv {
        css {
            media("not print") {
                width = (210 - 1).mm
                height = (297 - 1).mm
                margin(2.em, LinearDimension.auto)
                boxShadow(Color.black, blurRadius = 1.em, spreadRadius = 0.5.em)
            }
            media("print") {
                height = 100.pct
            }
            display = Display.flex
            flexDirection = FlexDirection.column
            position = Position.relative
            backgroundColor = Color.white
            color = Color.black
        }

        styledDiv {
            css {
                backgroundColor = if (grayScale) Color.white else Color.kodeinDark
                display = Display.flex
                flexDirection = FlexDirection.row
                justifyContent = JustifyContent.center
                position = Position.relative
                paddingBottom = 3.mm
            }

            styledDiv {
                css {
                    display = Display.flex
                    flexDirection = FlexDirection.column
                    alignItems = Align.flexStart
                    flexGrow = 1.0
                    padding(sideMargins.mm)
                    zIndex = 3
                }

                styledImg(src = if (grayScale) "kodein-darkGray.svg.png" else "kodein-darkOrange.svg.png") {
                    css {
                        height = 10.mm
                    }
                }

                styledH1 {
                    css {
                        textAlign = TextAlign.center
                        if (grayScale) {
                            color = Color.black
                        }
                        else {
                            color = Color.kodeinOrange
//                            background = "linear-gradient(to right, ${Color.kodeinOrange}, ${Color.kodeinPink})"
//                            put("-webkit-background-clip", "text")
//                            put("-webkit-text-fill-color", "transparent")
                        }
                        display = Display.inlineBlock
                        fontSize = if (anonymous) 10.mm else 12.mm
                        fontWeight = FontWeight.w800
                        margin(3.mm, 0.mm, 0.mm, 0.mm)
                    }

                    val txt = if (anonymous) title.text(lang) else name
//                    +txt

                    txt.forEachIndexed { i, c ->
                        styledSpan {
                            css {
                                if (!grayScale) {
                                    color = intermediate(Color.kodeinOrange, Color.kodeinPink, i.toDouble() / (txt.lastIndex).toDouble())
                                }
                            }
                            +c.toString()
                        }
                    }

                }

                if (!anonymous) {
                    styledSpan {
                        css {
                            fontWeight = FontWeight.w600
                            fontSize = 6.mm
                            color = if (grayScale) Color.dimGray else Color.kodeinLightOrange
                            marginTop = -2.mm
                        }

                        +title.text(lang)
                    }
                }
            }

            styledDiv {
                css {
                    color = if (grayScale) Color.dimGray else Color.kodeinDarkOrange
                    fontWeight = FontWeight.w600
                    margin(sideMargins.mm)
                    marginRight = 8.mm
                    paddingTop = 3.mm
                    lineHeight = 6.mm.lh
                    zIndex = 3
                }

                if (anonymous) {
                    +"contact@kodein.net"
                } else {
                    +email
                    br {}
                    +phone.text(lang)
                }
                br {}
                styledSmall {
                    css {
                        paddingTop = 2.mm
                        fontSize = 4.4.mm
                        display = Display.inlineBlock
                    }

                    val age = run {
                        val today = Date()
                        val age = today.getFullYear() - birth.getFullYear();
                        val m = today.getMonth() - birth.getMonth();
                        if (m < 0 || (m == 0 && today.getDate() < birth.getDate())) {
                            age - 1
                        } else {
                            age
                        }
                    }

                    +when (lang) {
                        "fr" -> "$age ans"
                        else -> "$age years old"
                    }
                }
            }

            styledSpan {
                css {
                    position = Position.absolute
                    display = Display.block
                    bottom = 5.mm ; right = 0.mm ; left = 0.mm
                    height = 12.mm
                    background = "linear-gradient(to bottom right, ${if (grayScale) Color.white else Color.kodeinDark} 49%, transparent 51%)"
                    zIndex = 2
                }
            }
            styledSpan {
                css {
                    position = Position.absolute
                    display = Display.block
                    bottom = 0.mm ; right = 0.mm ; left = 0.mm
                    height = 17.mm
                    background = "linear-gradient(to bottom right, ${if (grayScale) Color.gray else Color.kodeinDarkerPink} 49%, ${if (grayScale) Color.white else Color.kodeinLighterOrange} 51%)"
                    zIndex = 1
                }
            }
        }

        CVBuilder(this, lang, anonymous, grayScale).builder()

        if (!grayScale) {
            styledDiv {
                css {
                    position = Position.absolute
                    bottom = 0.mm ; left = 0.mm ; right = 0.mm
                    height = 2.5.mm
                    background = "linear-gradient(to right, ${Color.kodeinOrange}, ${Color.kodeinPink})"
                }
            }
        }
    }
}

private fun Text.text(lang: String) = this[lang] ?: this["all"] ?: this["en"] ?: "UNKNOWN"

class CVBuilder(
        private val rBuilder: RBuilder,
        private val lang: String,
        private val anonymous: Boolean,
        private val grayScale: Boolean,
        private var group: Int = 0
) {
    fun section(
            title: Text,
            link: String? = null,
            endsWithSeparator: Boolean = false,
            builder: SectionBuilder.() -> Unit
    ) {
        rBuilder.styledDiv {
            css {
                backgroundColor = if (grayScale) Color.white else group.groupBackgroundColor()
                padding(sideMargins.mm)
                position = Position.relative
                paddingTop = 2.5.mm
                paddingBottom = if (endsWithSeparator) 11.mm else 0.mm
            }

            styledDiv {
                css {
                    display = Display.flex
                    flexDirection = FlexDirection.row
                    justifyContent = JustifyContent.spaceBetween
                    alignItems = Align.center
                }

                styledH1 {
                    css {
                        fontSize = 8.mm
                        fontWeight = FontWeight.w300
                    }
                    +title.text(lang)
                }

                if (!anonymous && link != null) {
                    styledA(href = link) {
                        css {
                            fontSize = 4.mm
                            paddingRight = 10.mm
                        }
                        +link
                    }
                }
            }

            SectionBuilder(this, lang).builder()

            if (endsWithSeparator) {
                styledSpan {
                    css {
                        position = Position.absolute
                        display = Display.block
                        bottom = 3.mm ; right = 0.mm ; left = 0.mm
                        height = 9.mm
                        background = "linear-gradient(to bottom right, ${if (grayScale) Color.white else group.groupBackgroundColor()} 49%, transparent 51%)"
                        zIndex = 2
                    }
                }
                ++group
                styledSpan {
                    css {
                        position = Position.absolute
                        display = Display.block
                        bottom = 0.mm ; right = 0.mm ; left = 0.mm
                        height = 12.mm
                        background = "linear-gradient(to bottom right, ${if (grayScale) Color.lightGray else group.groupSeparatorColor()} 49%, ${if (grayScale) Color.white else group.groupBackgroundColor()} 51%)"
                        zIndex = 1
                    }
                }
            }
        }
    }
}


class SectionBuilder(
        private val rBuilder: RBuilder,
        private val lang: String
) {
    companion object {
        private const val liPadding = 3.4

        private fun RBuilder.cvList(leftWidth: Number, builder: RBuilder.() -> Unit) {
            styledUl {
                css {
                    listStyleType = ListStyleType.none
                    universal { zIndex = 3 }

                    "li" {
                        paddingLeft = 8.mm
                        display = Display.flex
                        flexDirection = FlexDirection.row
                        paddingTop = liPadding.mm
                        firstChild { paddingTop = 0.mm }

                        "span" {
                            display = Display.inlineBlock
                            firstChild {
                                width = leftWidth.mm
                            }
                        }

                        "small" {
                            fontSize = 4.4.mm
                        }
                    }
                }

                this.builder()
            }
        }
    }

    fun yearSpanList(builder: YearSpanListBuilder.() -> Unit) {
        rBuilder.cvList(28) {
            YearSpanListBuilder(this, lang).builder()
        }
    }

    fun yearList(builder: YearListBuilder.() -> Unit) {
        rBuilder.cvList(15) {
            YearListBuilder(this, lang).builder()
        }
    }

    fun titleList(width: Number, builder: TitleListBuilder.() -> Unit) {
        rBuilder.cvList(width) {
            TitleListBuilder(this, lang).builder()
        }
    }

    fun keywordList(width: Number, vararg keywords: Text) {
        rBuilder.styledUl {
            css {
                listStyleType = ListStyleType.none
                universal { zIndex = 3 }
                display = Display.flex
                flexDirection = FlexDirection.row
                flexWrap = FlexWrap.wrap
                "li" {
                    margin(0.mm, 4.mm, (liPadding - 1).mm, 4.mm)
                    this.width = width.mm
                }
            }

            keywords.forEach {
                li { +it.text(lang) }
            }
        }
    }

}

class YearSpanListBuilder(private val rBuilder: RBuilder, private val lang: String) {
    operator fun IntRange.invoke(vararg builders: Pair<String, RBuilder.() -> Unit>) {
        rBuilder.li {
            span {
                val yearSpan = this@invoke
                val last = if (yearSpan.last != Int.MAX_VALUE) yearSpan.last.toString() else "..."
                +"${yearSpan.first} - $last"
            }
            span {
                val map = mapOf(*builders)
                (map[lang] ?: map["all"] ?: map["en"])?.invoke(this)
            }
        }
    }
}

class YearListBuilder(private val rBuilder: RBuilder, private val lang: String) {
    operator fun Int.invoke(vararg builders: Pair<String, RBuilder.() -> Unit>) {
        rBuilder.li {
            span {
                val year = this@invoke
                +year.toString()
            }
            span {
                val map = mapOf(*builders)
                (map[lang] ?: map["all"] ?: map["en"])?.invoke(this)
            }
        }
    }
}

class TitleListBuilder(private val rBuilder: RBuilder, private val lang: String) {
    operator fun Text.invoke(vararg builders: Pair<String, RBuilder.() -> Unit>) {
        rBuilder.li {
            span {
                val title = this@invoke
                b { +title.text(lang) }
            }
            span {
                val map = mapOf(*builders)
                (map[lang] ?: map["all"] ?: map["en"])?.invoke(this)
            }
        }
    }
}


fun RBuilder.title(bold: String, regular: String) {
    b { +bold }
    +" "
    +regular
    br {}
}

fun RBuilder.title(regular: String) {
    +regular
    br {}
}

fun RBuilder.description(vararg lines: String) {
    small {
        lines.forEachIndexed { index, line ->
            if (index != 0) br {}
            +line
        }
    }
}
