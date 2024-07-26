package systems.rishon.velocitySync.handler

import systems.rishon.velocitySync.VelocitySync
import systems.rishon.velocitySync.jedis.JedisManager

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