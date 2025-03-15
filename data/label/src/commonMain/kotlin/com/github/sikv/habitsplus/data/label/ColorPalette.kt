package com.github.sikv.habitsplus.data.label

object ColorPalette {

    // TODO: Add more colors.
    val default = ColorVariant("#000000", "#FFFFFF")
    val green = ColorVariant("#2CAF88", "#50D3AC")
    val pink = ColorVariant("#B81474", "#EB47A7")

    fun getAll(): List<ColorVariant> {
        return listOf(
            default,
            green,
            pink
        )
    }
}
