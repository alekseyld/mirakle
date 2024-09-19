open class MirakleExtension {
    var host: String? = null

    var remoteFolder: String = "~/mirakle"

    var excludeLocal = setOf(
        "**/build"
    )

    var excludeRemote = setOf(
        "**/src/"
    )

    var excludeCommon = setOf(
        ".gradle",
        ".idea",
        "**/.git/",
        "**/local.properties",
        "**/mirakle.properties",
        "**/mirakle_local.properties"
    )

    var rsyncToRemoteArgs = setOf(
        "--archive",
        "--delete"
    )

    var rsyncFromRemoteArgs = setOf(
        "--archive",
        "--delete"
    )

    var sshArgs = emptySet<String>()

    var sshClient = "ssh"

    var fallback = false

    var downloadInParallel = false
    var downloadInterval = 2000L

    var breakOnTasks = emptySet<String>()

    var remoteBashCommand: String? = null

    internal fun buildRsyncToRemoteArgs(): Set<String> =
        rsyncToRemoteArgs + excludeLocal.mapToRsyncExcludeArgs()

    internal fun buildRsyncFromRemoteArgs(): Set<String> =
        rsyncFromRemoteArgs + excludeRemote.mapToRsyncExcludeArgs()

    private fun Set<String>.mapToRsyncExcludeArgs(): Set<String> =
        this.plus(excludeCommon).map { "--exclude=$it" }.toSet()
}
