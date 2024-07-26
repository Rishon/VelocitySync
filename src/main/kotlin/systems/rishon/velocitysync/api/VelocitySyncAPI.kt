package systems.rishon.velocitysync.api

class VelocitySyncAPI {

    init {
        instance = this
    }

    companion object {
        // Static Access
        lateinit var instance: VelocitySyncAPI
    }
}