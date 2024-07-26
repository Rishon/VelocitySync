package systems.rishon.velocitysync.jedis

import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

class JedisManager {

    // Pool
    private var jedisPool: JedisPool
    private var jedisPoolConfig: JedisPoolConfig

    init {
        instance = this

        // Config
        this.jedisPoolConfig = JedisPoolConfig().apply {
            testOnBorrow = true
            testOnReturn = true
            testWhileIdle = true
            numTestsPerEvictionRun = 3
            blockWhenExhausted = true
        }

        // Pool
        this.jedisPool = JedisPool(jedisPoolConfig, "localhost", 6379, 2000, "")
    }

    fun end() {
        this.jedisPool.close()
    }

    fun getJedisPool(): JedisPool {
        return this.jedisPool
    }

    fun getJedisPoolConfig(): JedisPoolConfig {
        return this.jedisPoolConfig
    }

    companion object {
        // Static Access
        lateinit var instance: JedisManager
    }
}