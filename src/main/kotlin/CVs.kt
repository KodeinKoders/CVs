import react.RBuilder
import utils.initials

class CV(
        val person: String,
        val languages: List<String>,
        val builder: RBuilder.(String, Boolean, Boolean) -> Unit
)

val CVs = mapOf(
        Salomon.name.initials() to CV(Salomon.name, listOf("en", "fr"), Salomon.cv),
        Romain.name.initials() to CV(Romain.name, listOf("en", "fr"), Romain.cv)
)
