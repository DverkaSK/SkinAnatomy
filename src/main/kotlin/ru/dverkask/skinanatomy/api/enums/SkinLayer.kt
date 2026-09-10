package ru.dverkask.skinanatomy.api.enums

/**
 * The two texture layers of a modern 64x64 skin.
 *
 * [OVERLAY] is the outer layer the client renders slightly above the base one - hat, jacket,
 * sleeves, pants. It is mostly transparent, and a legacy 64x32 skin has it only for the head.
 */
enum class SkinLayer {
    BASE,
    OVERLAY
}
