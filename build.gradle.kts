plugins {
    kotlin("jvm") version "1.9.23"
}

group = "ru.stepagin"
// для понятности можно указать версию
version = "sender" // "receiver"

repositories {
    mavenCentral()
}

dependencies {
    // Redis implementation
    implementation("redis.clients:jedis:5.1.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

// настройка сборки джарника
tasks.withType<Jar> {
    // строчка для корректного сбора джарника с зависимостями, без этого не заработает
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    // манифест, чтобы не надо было укаывать --class-path при запуске jar'ника
    manifest {
        attributes["Main-Class"] = "ru.stepagin.MainKt"
    }

    // строчки ниже для включения зависимостей в джарник
    from(
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    )
}