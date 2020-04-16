package utils

import kotlinx.css.Color
import kotlinx.css.times

data class RGBA(
        val red: Int,
        val green: Int,
        val blue: Int,
        val alpha: Double = 1.0
)

private fun Color.fromRGBANotation(): RGBA {
    // Match for rgb(255, 255, 255) | rgba(255, 255, 255, 0.5) | rgb(100% 100% 100%) | etc.
    val pattern = "^rgba?\\((\\d{1,3}%?)\\s*[, ]\\s*(\\d{1,3}%?)\\s*[, ]\\s*(\\d{1,3}%?)[, ]?\\s*(\\d|(?:\\d?\\.\\d+))?\\)\$"
    val match = Regex(pattern, RegexOption.IGNORE_CASE).find(value)

    fun getRGBParameter(index: Int): Int {
        val group = match?.groups?.get(index)?.value
                ?: throw IllegalArgumentException("Expected rgb or rgba notation, got $value")

        return when {
            (group.endsWith('%')) -> (Color.normalizeFractionalPercent(group.substringBefore('%').toDouble() / 100.0) * 255.0).toInt()
            else -> Color.normalizeRGB(group.toInt())
        }
    }

    val red = getRGBParameter(1)
    val green = getRGBParameter(2)
    val blue = getRGBParameter(3)
    val alpha = Color.normalizeAlpha(match?.groups?.get(4)?.value?.toDouble() ?: 1.0)

    return RGBA(red, green, blue, alpha)
}

fun Color.toRGBA(): RGBA {
    return when {
        value.startsWith("rgb") -> fromRGBANotation()

        // Matches #rgb
        value.startsWith("#") && value.length == 4 -> RGBA(
                (value[1].toString() * 2).toInt(16),
                (value[2].toString() * 2).toInt(16),
                (value[3].toString() * 2).toInt(16)
        )

        // Matches both #rrggbb and #rrggbbaa
        value.startsWith("#") && (value.length == 7 || value.length == 9) -> RGBA(
                (value.substring(1..2)).toInt(16),
                (value.substring(3..4)).toInt(16),
                (value.substring(5..6)).toInt(16)
        )
        else -> throw IllegalArgumentException("Only hexadecimal, rgb, and rgba notations are accepted, got $value")
    }
}

fun intermediate(from: Color, to: Color, p: Double): Color {
    val fromRGBA = from.toRGBA()
    val toRGBA = to.toRGBA()

    val r = (fromRGBA.red * (1 - p) + toRGBA.red * p).toInt()
    val g = (fromRGBA.green * (1 - p) + toRGBA.green * p).toInt()
    val b = (fromRGBA.blue * (1 - p) + toRGBA.blue * p).toInt()
    val a = fromRGBA.alpha * (1 - p) + toRGBA.alpha * p

    return Color("rgba($r, $g, $b, $a)")
}
