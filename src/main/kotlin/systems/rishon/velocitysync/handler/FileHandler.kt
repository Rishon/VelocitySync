package systems.rishon.velocitysync.handler

import com.moandjiezana.toml.Toml
import systems.rishon.velocitysync.VelocitySync
import java.io.File
import java.io.IOException
import java.nio.file.Files

class FileHandler(private val instance: VelocitySync) : IHandler {

    // Config
    private lateinit var config: Toml

    // Redis Settings
    private lateinit var redisHost: String
    private var redisPort: Int = 0
    private lateinit var redisPassword: String
    private var redisTimeout: Int = 0

    override fun init() {
        handler = this

        // Load Config
        loadConfig()

        // Write Settings
        loadSettings()
    }

    override fun end() {}

    private fun loadConfig() {
        val dir = this.instance.directory.toFile()
        dir.mkdirs()
        val configFile = File(dir, "config.toml")
        if (!configFile.exists()) {
            try {
                val stream = javaClass.classLoader.getResourceAsStream("config.toml")
                if (stream != null) Files.copy(stream, configFile.toPath())
                else configFile.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
                this.instance.logger.warn("Error while copying default configuration file.")
            }
        }

        this.config = Toml().read(configFile)
    }

    private fun loadSettings() {
        this.redisHost = this.config.getString("redis.host")
        this.redisPort = this.config.getLong("redis.port").toInt()
        this.redisPassword = this.config.getString("redis.password")
        this.redisTimeout = this.config.getLong("redis.timeout").toInt()
    }

    companion object {
        // Static Access
        lateinit var handler: FileHandler
    }
}