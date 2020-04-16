import react.RBuilder

class CV(
        val person: String,
        val languages: List<String>,
        val builder: RBuilder.(String, Boolean, Boolean) -> Unit
)

val CVs = mapOf(
        "SB" to CV("Salomon BRYS", listOf("en", "fr"), Salomon.cv)
)
