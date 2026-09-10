# Releasing SkinAnatomy to Maven Central

Coordinates: `io.github.dverkask:skinanatomy`. First release on Central: **2.1.1**
(2026-09-10). Everything up to `v2.1.0` exists only on JitPack.

**Two things are permanent, so read before you start:**

- A version published to Central can **never** be deleted, replaced or re-published.
  A mistake costs a version number.
- A git tag must point at the exact commit that was published. That is why the first
  Central release is 2.1.1, not 2.1.0: the `v2.1.0` tag points at a commit without the
  licence and the publishing setup.

---

## One-time setup

Done once per machine (the GPG key and `gradle.properties`) or once ever (the account).
**Every value below is a secret: it goes into `~/.gradle/gradle.properties` and nowhere
else - not the repository, not an issue, not a chat.**

### 1. Central Portal account

Sign in at <https://central.sonatype.com> with **Continue with GitHub** as `DverkaSK`. The
namespace `io.github.dverkask` is then verified automatically - check it under
*Namespaces*. (`ru.dverkask` would need a domain `dverkask.ru` and a DNS TXT record.)

### 2. User token

Account menu → **Generate User Token**. It shows a username/password pair **once**. This
pair, not your GitHub login, is what Gradle uses.

### 3. GPG key

On Windows, `gpg` ships with Git - run these in **Git Bash**:

```bash
gpg --quick-gen-key "DverkaSK <dverkask@gmail.com>" rsa4096 sign 3y
gpg --list-keys --keyid-format long DverkaSK      # pub rsa4096/<KEYID> - 16 hex chars
```

The first command asks for a **passphrase** - the password that protects the key file.
Keep it in a password manager; if it is lost, make a new key and redo steps 3-5.

### 4. Publish the *public* key - to two keyservers, then wait

```bash
gpg --keyserver keyserver.ubuntu.com --send-keys <KEYID>
gpg --export --armor <KEYID> | curl -T - https://keys.openpgp.org
```

`keys.openpgp.org` answers with a verification link. **Open it and confirm the e-mail** -
until then it serves the key without a name, which some verifiers refuse.

Then **wait 15-30 minutes** before the first upload. See pitfall 1 below: a key that is
already on a keyserver can still be invisible to Central for a while.

Check that both servers really have it:

```bash
curl -s "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x<FINGERPRINT>" | head -1
curl -s -o /dev/null -w "%{http_code}\n" "https://keys.openpgp.org/vks/v1/by-fingerprint/<FINGERPRINT>"
```

(`gpg --list-keys --with-fingerprint <KEYID>` prints the fingerprint; drop the spaces.)

### 5. `~/.gradle/gradle.properties`

Write the secret key in as **one line**, from Git Bash (asks for the passphrase):

```bash
{ printf 'signingInMemoryKey='; gpg --armor --export-secret-keys <KEYID> | awk '{printf "%s\\n", $0}'; echo; } >> ~/.gradle/gradle.properties
```

Then add three lines by hand in an editor. The finished file:

```properties
signingInMemoryKey=-----BEGIN PGP PRIVATE KEY BLOCK-----\n\nlQ...one very long line...\n-----END PGP PRIVATE KEY BLOCK-----\n
mavenCentralUsername=<token username>
mavenCentralPassword=<token password>
signingInMemoryKeyPassword=<key passphrase>
```

- `name=value`, no quotes, no spaces around `=`, each on its own line.
- The literal `\n` inside the key are correct: `.properties` turns them back into newlines.
- Leave any other lines in the file alone.

---

## Every release

1. Bump `version` in `build.gradle.kts` and the version in `README.md`. Commit.
2. `./gradlew build` - tests green.
3. `./gradlew publishToMavenCentral`
   The output must show `:signMavenPublication` and end with
   `Uploaded bundle to Central Portal ... deployment id: <id>`.
   This only **stages** the release (`automaticRelease = false`).
4. Open <https://central.sonatype.com/publishing> and wait for the deployment status:
   - **VALIDATED** → open *Component Files* and check there are five files - `.jar`,
     `-sources.jar`, `-javadoc.jar`, `.pom`, `.module` - each with `.asc`, `.md5`, `.sha1`.
     Then press **Publish**. This is the irreversible step.
   - **FAILED** → read the errors, press **Drop**, fix, upload again. A failed
     deployment publishes nothing, and the same version can be uploaded again.
5. Tag the published commit and push:
   ```bash
   git tag -a vX.Y.Z -m "vX.Y.Z: <what changed>"
   git push origin master vX.Y.Z
   ```
6. Wait until the artifact is on Central **and on its Google mirror** - consumers such as
   Paper's `PluginLoader` read the mirror, which lags behind:
   ```bash
   P=io/github/dverkask/skinanatomy/X.Y.Z/skinanatomy-X.Y.Z.pom
   curl -s -o /dev/null -w "central %{http_code}\n" https://repo1.maven.org/maven2/$P
   curl -s -o /dev/null -w "mirror  %{http_code}\n" https://maven-central.storage-download.googleapis.com/maven2/$P
   ```
   Both `200` - the release is usable everywhere.

---

## Pitfalls we actually hit

1. **"Invalid signature ... Could not find a public key by the key fingerprint".**
   The first two uploads of 2.1.1 failed with this although the key was already on
   `keyserver.ubuntu.com` and the signatures verified locally (`gpg --verify` → *Good
   signature*, issuer = our key). The third upload passed after the key was also put on
   `keys.openpgp.org` with a confirmed e-mail and some time had passed. Which of the two
   fixed it was not isolated - so do both, and wait (setup step 4). To diagnose, check
   which key signed an artifact:
   ```bash
   gpg --list-packets build/publishing/mavenCentral/io/github/dverkask/skinanatomy/X.Y.Z/skinanatomy-X.Y.Z.pom.asc | grep keyid
   ```
   If it is not the key on the keyservers, `gradle.properties` holds a different key.
2. **The tag must match the published commit** - hence 2.1.1 (see the top).
3. **Signing switches on only when `signingInMemoryKey` is set.** Plain builds,
   `publishToMavenLocal` and JitPack run without a key; Central rejects an unsigned upload
   by itself, so nothing unsigned can slip through.
4. **The key expires on the date chosen at creation** (`3y`). Before that, extend and
   re-publish it, or releases start failing on the signature check:
   ```bash
   gpg --quick-set-expire <FINGERPRINT> 3y
   gpg --keyserver keyserver.ubuntu.com --send-keys <KEYID>
   gpg --export --armor <KEYID> | curl -T - https://keys.openpgp.org
   ```
   and re-export the secret key into `gradle.properties` (setup step 5).
5. **The mirror lags Central.** A consumer that resolves through the Google mirror sees a
   new version later than `repo1.maven.org` does - wait for step 6 before relying on it.
6. **JitPack caches a tag forever by its name.** Moving a tag (`git tag -f`) does not
   rebuild it there; `v2.0.0` stayed broken for good. Never move a published tag - release
   a new version instead. The same holds for Central, where there is no way around it at
   all.
