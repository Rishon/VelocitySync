package systems.rishon.velocitySync.api

class VelocitySyncAPI {

    init {
        instance = this
    }

    companion object {
        // Static Access
        lateinit var instance: VelocitySyncAPI
    }
}