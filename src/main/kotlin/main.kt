import js.core.get
import js.core.jso
import kotlinx.browser.document
import kotlinx.css.Align
import kotlinx.css.Color
import kotlinx.css.CssBuilder
import kotlinx.css.Display
import kotlinx.css.FlexDirection
import kotlinx.css.FlexWrap
import kotlinx.css.FontStyle
import kotlinx.css.JustifyContent
import kotlinx.css.LinearDimension
import kotlinx.css.a
import kotlinx.css.alignItems
import kotlinx.css.backgroundColor
import kotlinx.css.body
import kotlinx.css.color
import kotlinx.css.display
import kotlinx.css.em
import kotlinx.css.flexDirection
import kotlinx.css.flexWrap
import kotlinx.css.fontFamily
import kotlinx.css.fontSize
import kotlinx.css.fontStyle
import kotlinx.css.h1
import kotlinx.css.h2
import kotlinx.css.justifyContent
import kotlinx.css.lineHeight
import kotlinx.css.margin
import kotlinx.css.mm
import kotlinx.css.padding
import kotlinx.css.pct
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.lh
import kotlinx.css.px
import kotlinx.css.textDecoration
import kotlinx.css.width
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import react.FC
import react.Props
import react.create
import react.createElement
import react.dom.attrs
import react.dom.client.createRoot
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.option
import react.dom.input
import react.fc
import react.router.Route
import react.router.Routes
import react.router.dom.HashRouter
import react.router.dom.useSearchParams
import react.router.useParams
import styled.css
import styled.injectGlobal
import styled.styledDiv
import styled.styledOption
import styled.styledSelect
import styled.styledSpan

fun CssBuilder.globalCSS() {
    body {
        backgroundColor = Color("#262626")
        fontFamily = "Picon"
        fontSize = 5.mm
        lineHeight = 6.25.mm.lh
        put("-webkit-print-color-adjust", "exact")
    }

    h1 {
        lineHeight = 15.mm.lh
    }

    h2 {
        lineHeight = 8.mm.lh
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


fun main() {
    val app = browser.document.getElementById("app") ?: error("Couldn't find app container!")
    createRoot(app).render(App.create())
}

val App = fc<Props> {
    injectGlobal { globalCSS() }

    val page = createElement(cvPage)

    HashRouter {
        Routes {
            Route {
                attrs {
                    index = true
                    path = "/"
                    element = page
                }
            }
            Route {
                attrs {
                    path = "/:person"
                    element = page
                }
            }
            Route {
                attrs {
                    path = "/:person/:lang"
                    element = page
                }
            }
        }
    }
}

external interface CVProps : Props {
    var person: String?
    var lang: String?
    var anonymous: Boolean
    var grayScale: Boolean
}

fun CVProps.copy(change: CVProps.() -> Unit): CVProps = jso {
    person = this@copy.person
    lang = this@copy.lang
    anonymous = this@copy.anonymous
    grayScale = this@copy.grayScale
    change()
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
            listOfNotNull(
                if (anonymous) "anon=1" else null,
                if (grayScale) "gs=1" else null
            ).joinToString(separator = "&")
        )
    }
}

val cvPage = fc<Props> {
    val params = useParams()
    val (searchParams, _) = useSearchParams()

    cvRenderer {
        attrs {
            person = params["person"]
            lang = params["lang"]
            anonymous = searchParams.get("anon") == "1"
            grayScale = searchParams.get("gs") == "1"
        }
    }
}

val cvRenderer: FC<CVProps> = fc { props ->
    if (props.person != null && CVs[props.person] == null) {
        return@fc
    }

    if (props.person != null && props.lang == null) {
        document.location!!.hash = "/${props.person}/${CVs[props.person]!!.languages.first()}"
        return@fc
    }

    styledDiv {
        css {
            display = Display.flex
            media("print") {
                display = Display.none
            }
            color = Color.kodeinLight
            flexDirection = FlexDirection.row
            justifyContent = JustifyContent.center
            flexWrap = FlexWrap.wrap
            alignItems = Align.center
            width = 100.pct - 2.em
            fontSize = 1.em
            margin(LinearDimension.auto)
            padding(1.em)

            "select" {
                backgroundColor = Color("#262626")
                color = Color.kodeinLight
                fontSize = 1.em
            }
        }

        val itemMargin = 0.5.em

        styledSelect {
            css {
                margin(itemMargin)
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
                    val hash = props.copy { person = new.ifEmpty { null } }.genHash()
                    document.location!!.hash = hash
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
            styledSelect {
                css {
                    margin(itemMargin)
                }
                attrs {
                    onChangeFunction = {
                        val new = (it.target as HTMLSelectElement).value
                        document.location!!.hash = props.copy { lang = new.ifEmpty { null } }.genHash()
                    }
                    value = props.lang ?: ""
                }
                languages.forEach {
                    option {
                        attrs.value = it
                        +it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                    }
                }
            }

            styledSpan {
                css {
                    margin(itemMargin)
                }
                input(InputType.checkBox) {
                    attrs {
                        id = "anon"
                        checked = props.anonymous
                        onChangeFunction = {
                            val new = (it.target as HTMLInputElement).checked
                            document.location!!.hash = props.copy { anonymous = new }.genHash()
                        }
                    }
                }

                label {
                    attrs.htmlFor = "anon"
                    +" Anonymous"
                }
            }

            styledSpan {
                css {
                    margin(itemMargin)
                }
                input(InputType.checkBox) {
                    attrs {
                        id = "gs"
                        checked = props.grayScale
                        onChangeFunction = {
                            val new = (it.target as HTMLInputElement).checked
                            document.location!!.hash = props.copy { grayScale = new }.genHash()
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

            CVs[props.person!!]?.builder?.invoke(this, props.lang!!, props.anonymous, props.grayScale)
        }
    }
}

