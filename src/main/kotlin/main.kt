import kotlinext.js.jsObject
import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.html.InputType
import kotlinx.html.for_
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import org.w3c.dom.url.URLSearchParams
import react.RProps
import react.child
import react.dom.*
import react.functionalComponent
import react.router.dom.hashRouter
import react.router.dom.route
import styled.*
import utils.getValue
import kotlin.browser.document

fun CSSBuilder.globalCSS() {
    body {
        backgroundColor = Color("#262626")
        fontFamily = "Picon"
        fontSize = 5.mm
        put("-webkit-print-color-adjust", "exact")
    }

    a {
        textDecoration = TextDecoration.none
        color = Color.initial
    }

    universal {
        margin(0.px)
        padding(0.px)
    }
}

interface RouteProps: RProps {
    val person: String?
    val lang: String?
}

fun main() {
    render(document.getElementById("app")) {
        StyledComponents.injectGlobal { globalCSS() }

        hashRouter {
            route<RouteProps>("/:person?/:lang?") {
                val params = URLSearchParams(it.location.search)
                child(
                        functionalComponent = cvRenderer
                ) {
                    attrs {
                        person = it.match.params.person
                        lang = it.match.params.lang
                        anonymous = params.get("anon") == "1"
                        grayScale = params.get("gs") == "1"
                    }
                }
            }
        }

    }
}

interface CVProps : RProps {
    var person: String?
    var lang: String?
    var anonymous: Boolean
    var grayScale: Boolean
}

fun CVProps.copy(change: CVProps.() -> Unit): CVProps {
    return jsObject<CVProps> {
        person = this@copy.person
        lang = this@copy.lang
        anonymous = this@copy.anonymous
        grayScale = this@copy.grayScale
        change()
    }
}

private fun CVProps.genHash(): String = buildString {
    append(
            when {
                person == null -> "/"
                lang == null -> "/$person"
                else -> "/$person/$lang"
            }
    )
    if (anonymous || grayScale) {
        append("?")
        append(
                listOf(
                        if (anonymous) "anon=1" else null,
                        if (grayScale) "gs=1" else null
                )
                        .filterNotNull()
                        .joinToString(separator = "&")
        )
    }
}

val cvRenderer by functionalComponent<CVProps> { props ->

    if (props.person != null && CVs[props.person] == null) return@functionalComponent

    if (props.person != null && props.lang == null) {
        document.location!!.hash = "/${props.person}/${CVs[props.person]!!.languages.first()}"
        return@functionalComponent
    }

    styledDiv {
        css {
            display = Display.flex
            media("print") {
                display = Display.none
            }
            color = Color.kodeinLight
            flexDirection = FlexDirection.row
            justifyContent = JustifyContent.spaceBetween
            alignItems = Align.center
            width = 26.em
            fontSize = 1.em
            margin(LinearDimension.auto)
            padding(1.em)

            "select" {
                backgroundColor = Color("#262626")
                color = Color.kodeinLight
                fontSize = 1.em
            }
        }

        styledSelect {
            css {
                if (props.person == null) {
                    specific {
                        fontStyle = FontStyle.italic
                        color = Color.kodeinDarkOrange
                    }
                }
            }
            attrs {
                onChangeFunction = {
                    val new = (it.target as HTMLSelectElement).value
                    document.location!!.hash = props.copy { person = if (new.isNotEmpty()) new else null }.genHash()
                }
                value = props.person ?: ""
            }
            styledOption {
                css {
                    fontStyle = FontStyle.italic
                    color = Color.kodeinDarkOrange
                }
                attrs.value = ""
                +"Select"
            }
            CVs.forEach { entry ->
                styledOption {
                    css {
                        fontStyle = FontStyle.normal
                        color = Color.kodeinLight
                    }

                    attrs.value = entry.key
                    +entry.value.person
                }
            }
        }

        if (props.person != null) {
            val languages = CVs[props.person]!!.languages
            select {
                attrs{
                    onChangeFunction = {
                        val new = (it.target as HTMLSelectElement).value
                        document.location!!.hash = props.copy { lang = if (new.isNotEmpty()) new else null }.genHash()
                    }
                    value = props.lang ?: ""
                }
                languages.forEach {
                    option {
                        attrs.value = it
                        +it.capitalize()
                    }
                }
            }

            span {
                input(InputType.checkBox) {
                    attrs {
                        id = "anon"
                        checked = props.anonymous
                        onChangeFunction = {
                            val new = (it.target as HTMLInputElement).checked
                            document.location!!.hash = props.copy { anonymous = new } .genHash()
                        }
                    }
                }

                label {
                    attrs.htmlFor = "anon"
                    +" Anonymous"
                }
            }

            span {
                input(InputType.checkBox) {
                    attrs {
                        id = "gs"
                        checked = props.grayScale
                        onChangeFunction = {
                            val new = (it.target as HTMLInputElement).checked
                            document.location!!.hash = props.copy { grayScale = new } .genHash()
                        }
                    }
                }

                label {
                    attrs.htmlFor = "gs"
                    +" Gray scale"
                }
            }
        }
    }

    if (props.person != null && props.lang != null) {
        styledDiv {
            css {
                color = Color.white
            }

            CVs[props.person!!]?.builder(this, props.lang!!, props.anonymous, props.grayScale)
        }
    }
}
