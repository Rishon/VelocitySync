package systems.rishon.velocitySync;

import com.google.inject.Inject
import com.velocitypowered.api.event.PostOrder
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent
import com.velocitypowered.api.plugin.Plugin
import org.slf4j.Logger
import systems.rishon.velocitySync.handler.FileHandler
import systems.rishon.velocitySync.handler.IHandler
import systems.rishon.velocitySync.handler.MainHandler
import java.nio.file.Path

@Plugin(
    id = "velocitysync",
    name = "VelocitySync",
    version = BuildConstants.VERSION,
    authors = ["Rishon"],
)
class VelocitySync @Inject constructor(val logger: Logger, val directory: Path) {

    // Handlers
    private val handlers: MutableList<IHandler> = mutableListOf()

    @Subscribe(order = PostOrder.FIRST)
    fun onProxyInitialization(event: ProxyInitializeEvent) {
        instance = this

        // Initialize Handlers
        this.handlers.add(FileHandler(this))
        this.handlers.add(MainHandler(this))

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
