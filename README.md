# SkinAnatomy

![logo](skinanatomy.png)

SkinAnatomy is a small, framework-agnostic **Kotlin library** for editing and composing
Minecraft player skin textures. It has no dependency on Bukkit, Paper, or any specific
Minecraft server API - it only deals with the 64x64 skin image itself, so you can use it
in a plugin, a bot, a web service, or a standalone tool.

It lets you address a skin by its anatomical parts - head, body, left/right arm, left/right
leg - by side (front, back, left, right, top, bottom) and by layer (the base one, or the
overlay: hat, jacket, sleeves, pants), edit any of those regions in
place or graft one part from another skin, and render the result back out as a normal
`BufferedImage`. Legacy 64x32 skins (pre-1.8, no overlay layer) are transparently upgraded
to the modern 64x64 layout.

What you do with the resulting texture - upload it, sign it through something like
[SkinsRestorer](https://skinsrestorer.net)'s MineSkin integration, apply it to a player -
is up to the consuming project; this library is deliberately scoped to just the image side
of the problem.

![demo](demo.gif)

## Adding it to your project

Published via [JitPack](https://jitpack.io/#DverkaSK/SkinAnatomy) - it builds directly from
this GitHub repository, no publishing step or account needed on your side.

```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    // A tag (recommended, e.g. "v2.0.0"), a branch name, or a commit hash all work.
    implementation("com.github.DverkaSK:SkinAnatomy:v2.0.0")
}
```

The first build for a given tag/commit takes JitPack a minute or two the first time
anyone requests it (it's compiling this repo on demand); after that it's cached.

### Local development

Working on SkinAnatomy and another project at the same time? Skip JitPack entirely with a
composite build - point the other project's `settings.gradle.kts` at this repo's
directory and Gradle substitutes the dependency with the local sources, no publish step:

```kotlin
includeBuild("../SkinAnatomy")
```

Or publish a snapshot to your local Maven cache instead:

```bash
./gradlew publishToMavenLocal
```

then depend on `mavenLocal()`:

```kotlin
repositories { mavenLocal() }
dependencies { implementation("ru.dverkask:SkinAnatomy:2.0.0") }
```

## Usage

```kotlin
import ru.dverkask.skinanatomy.api.skin.PlayerSkin
import ru.dverkask.skinanatomy.api.enums.SkinLayer
import ru.dverkask.skinanatomy.api.enums.SkinPartType

// Load once - every part below shares this same in-memory canvas.
val skin = PlayerSkin.fromUrl("https://textures.minecraft.net/texture/...")
// or: PlayerSkin.fromImage(existingBufferedImage)

// Edit a region in place, or return a different BufferedImage - both are supported.
skin.head.processImage { image ->
    image // mutate via image.graphics, or return a brand-new BufferedImage
}

// Or edit the overlay layer (hat, jacket, sleeves, pants) instead of the base one.
skin.body.processImage({ image -> image }, SkinLayer.OVERLAY)

// Graft an entire part (all six sides, both layers) from another skin.
val other = PlayerSkin.fromUrl("https://example.com/other-skin.png")
skin.copyPartFrom(other, SkinPartType.HEAD)

// Or graft just one layer - e.g. take someone's face but keep this skin's hat.
skin.copyPartFrom(other, SkinPartType.HEAD, setOf(SkinLayer.BASE))

// Get the composed 64x64 texture back out.
val texture: java.awt.image.BufferedImage = skin.render()
```

### API surface

- `PlayerSkin` - loads a skin (`fromUrl` / `fromImage`) and exposes `head`, `body`,
  `leftArm`, `rightArm`, `leftLeg`, `rightLeg`, plus `part(SkinPartType)`, `copyPartFrom`
  and `render()`.
- `ISkinPart` - `type`, `sides`, `overlaySides`, `sides(SkinLayer)`,
  `getImage(SkinSide[, SkinLayer])`, `processImage(ImageProcessor[, SkinLayer])`. Without a
  layer argument, `getImage` and `processImage` work on the base layer.
- `SkinPartType` / `SkinSide` / `SkinLayer` - the six anatomical parts, the six faces of
  each, and the two layers (`BASE`, `OVERLAY`).
- `ImageLoader` - loads a texture from a URL via `java.net.http.HttpClient`.

## Building from source

```bash
./gradlew build
```

Requires JDK 21+ (a matching toolchain is auto-provisioned via Gradle if none is found).
