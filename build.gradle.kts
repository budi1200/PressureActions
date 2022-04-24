plugins {
    kotlin("jvm") version "1.6.20"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
}

group = "si.budimir"
version = "1.0"

repositories {
    mavenCentral()
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
}

dependencies {
    implementation(kotlin("stdlib"))

    // Paper API
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")

    // Configuration library
    implementation("org.spongepowered:configurate-hocon:4.1.2")
    implementation("org.spongepowered:configurate-extra-kotlin:4.1.2")
}

tasks.shadowJar {
    relocate("org.spongepowered", "${project.group}.${project.name}.libs.org.spongepowered")
}

task("buildAndPush") {
    dependsOn("shadowJar")

    doLast {
        copy {
            from("build/libs/${project.name}-" + project.version + "-all.jar")
            into("../00-server/plugins/")
        }
    }
}

bukkit {
    name= project.name
    main = "${project.group}.${project.name.toLowerCase()}.PressureActionsMain"
    apiVersion = "1.18"
    authors = listOf("budi1200")
    depend = listOf()

    // Docs: https://github.com/Minecrell/plugin-yml#bukkit=
    commands {
        register("pactions") {}
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}