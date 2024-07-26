package systems.rishon.velocitySync;

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import org.slf4j.Logger

@Plugin(
    id = "velocitysync",
    name = "VelocitySync",
    version = BuildConstants.VERSION,
    authors = ["Rishon"],
)
class VelocitySync @Inject constructor(val logger: Logger) {

    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent) {

    }
}
