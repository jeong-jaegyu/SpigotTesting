package xyz.jaegyu.plugindemos;


import org.bukkit.plugin.java.JavaPlugin;
import xyz.jaegyu.plugindemos.listeners.BlockDispenseListener;

public final class PluginDemos extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Lmao hi");
        registerEvents();
    }


    // listener registers
    private void registerEvents() {

        //getServer().getPluginManager().registerEvents(new XPBottleBreakListener(), this);
        // getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new BlockDispenseListener(), this);
        //getServer().getPluginManager().registerEvents(new EndDiamond(), this);
    }

}
