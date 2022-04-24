package si.budimir.pressureactions.enums

enum class Permission(private val key: String) {
    USE("use"),
    CREATE("create"),
    INFO("info"),
    RELOAD("commands.reload");


    fun getPerm(): String {
        return "pressureactions.$key"
    }
}