package ru.stepagin

import redis.clients.jedis.Jedis

fun main() {
    // сюда вставьте ваш пароль или получите его из внешнего файла
    val password = "your password"

    // Подключение к Redis
    val jedis = Jedis("localhost", 6379) // также можно написать Jedis("redis://localhost:6379")
    jedis.auth(password)

    val sender = Sender(jedis)
    sender.run()

    /* раскомментировать строчки ниже, если хотите сделать получателя
    * и в build.gradle.kts переименовать версию проекта на "receiver" (опционально) */
//    val receiver = Receiver(jedis)
//    receiver.run()

    // Закрытие соединения с Redis
    jedis.close()
}