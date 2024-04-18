package ru.stepagin

import redis.clients.jedis.Jedis

class Sender(private val jedis: Jedis) {
    fun run() {
        // Отправка сообщений в Redis
        for (i in 1..10) {
            val message = "Message $i from Service A" // текст сообщения
            jedis.publish("channel", message) // передаём сообщения в канал "channel"
            println("Sent: $message") // выводим на экран, что оно было отправлено
            Thread.sleep(1000) // Подождем немного между сообщениями
        }
    }


}