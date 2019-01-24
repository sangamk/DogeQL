package doge.common

data class Color(var r: Byte, var g: Byte, var b: Byte, var a: Byte) {

    companion object {

        fun fromString(string: String): Color {
            if (string.length == 7) {
                val r = string.slice(1..2).toByte(16)
                val g = string.slice(3..4).toByte(16)
                val b = string.slice(5..6).toByte(16)

                return Color(r, g, b, 255.toByte())
            }

            throw IllegalArgumentException("$string is not a color")
        }

    }

    override fun toString(): String {
        return "#$r$g$b$a"
    }

}