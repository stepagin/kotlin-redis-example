package ru.stepagin

import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPubSub

class Receiver(private val jedis: Jedis) {

    class MessageSubscriber : JedisPubSub() {
        override fun onMessage(channel: String?, message: String?) {
            // этот метод вызывается при каждом получнии сообщения
            println("Received message: $message")
        }
    }

    fun run() {
        // Создание подписчика
        val subscriber = MessageSubscriber()

        // Подписка на канал с названием "channel"
        // Можно передавать сразу несоклько каналов через запятую
        jedis.subscribe(subscriber, "channel")
    }

}