package ir.taher7.melodymine.api.events

import ir.taher7.melodymine.models.MelodyPlayer
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PrePlayerSetSelfMuteEvent(
    val melodyPlayer: MelodyPlayer,
    val value: Boolean,
) : Event(), Cancellable {

    private var cancelled = false
    override fun isCancelled(): Boolean {
        return cancelled
    }

    override fun setCancelled(cancel: Boolean) {
        cancelled = cancel
    }

    override fun getHandlers(): HandlerList {
        return handlerList
    }

    companion object {
        @JvmStatic
        val handlerList = HandlerList()
    }
}