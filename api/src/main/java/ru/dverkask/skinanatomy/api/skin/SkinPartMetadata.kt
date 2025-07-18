package ru.dverkask.skinanatomy.api.skin

import ru.dverkask.skinanatomy.api.enums.SkinPartType

@Target(AnnotationTarget.CLASS)
annotation class SkinPartMetadata(
    val type: SkinPartType,
    val description: String = ""
)
