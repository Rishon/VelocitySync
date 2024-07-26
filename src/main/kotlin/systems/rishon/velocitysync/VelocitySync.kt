package systems.rishon.velocitysync;

import com.google.inject.Inject
import com.velocitypowered.api.event.PostOrder
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.ProxyServer
import org.slf4j.Logger
import systems.rishon.velocitysync.handler.FileHandler
import systems.rishon.velocitysync.handler.IHandler
import systems.rishon.velocitysync.handler.MainHandler
import java.nio.file.Path

class VelocitySync @Inject constructor(
    val logger: Logger, val proxy: ProxyServer, @param:DataDirectory val directory: Path
) {

    // Handlers
    private val handlers: MutableList<IHandler> = mutableListOf()

    @Subscribe(order = PostOrder.FIRST)
    fun onProxyInitialization(event: ProxyInitializeEvent) {
        instance = this

        // Initialize Handlers
        this.handlers.addAll(
            listOf(
                FileHandler(this), MainHandler(this)
            )
        )
        this.handlers.forEach { it.init() }

        this.logger.info("VelocitySync has been initialized.")
    }

    @Subscribe(order = PostOrder.LAST)
    fun onProxyShutdown(event: ProxyShutdownEvent) {
        // End Handlers
        this.handlers.forEach { it.end() }

        this.logger.info("VelocitySync has been shutdown.")
    }

    companion object {
        // Static Access
        lateinit var instance: VelocitySync
    }
}
