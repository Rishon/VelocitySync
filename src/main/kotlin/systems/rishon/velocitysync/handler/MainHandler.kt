package systems.rishon.velocitysync.handler

import systems.rishon.velocitysync.VelocitySync
import systems.rishon.velocitysync.jedis.JedisManager

class MainHandler(private val instance: VelocitySync) : IHandler {

    // Jedis
    private var jedisManager: JedisManager = null!!

    override fun init() {
        handler = this

        // Initialize Jedis
        this.jedisManager = JedisManager()
    }

    override fun end() {
        // End Jedis
        this.jedisManager.end()
    }

    companion object {
        // Static Access
        lateinit var handler: MainHandler
    }
}