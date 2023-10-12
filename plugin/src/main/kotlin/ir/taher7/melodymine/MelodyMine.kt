package ir.taher7.melodymine

import ir.taher7.melodymine.commands.CommandManager
import ir.taher7.melodymine.commands.TabCompletionManager
import ir.taher7.melodymine.database.Database
import ir.taher7.melodymine.listeners.MelodyMineListener
import ir.taher7.melodymine.listeners.QRCodeListeners
import ir.taher7.melodymine.services.Websocket
import ir.taher7.melodymine.utils.Placeholder
import ir.taher7.melodymine.utils.Utils
import org.bstats.bukkit.Metrics
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin


class MelodyMine : JavaPlugin() {

    override fun onEnable() {
        instance = this
        Utils.sendMelodyFiglet()

        saveDefaultConfig()
        Database.resetDate()
        Websocket.connect()
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) Placeholder().register()

        server.pluginManager.registerEvents(MelodyMineListener(), this)
        server.pluginManager.registerEvents(QRCodeListeners(), this)

        getCommand("melodymine")?.setExecutor(CommandManager())
        getCommand("melodymine")?.tabCompleter = TabCompletionManager()


        Metrics(this, 20020)
    }

    override fun onDisable() {
        Database.resetDate()
        Database.hikari.close()

    }


    companion object {
        lateinit var instance: MelodyMine
    }
}
