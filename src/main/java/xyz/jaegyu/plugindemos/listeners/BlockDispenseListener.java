package xyz.jaegyu.plugindemos.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static org.bukkit.Bukkit.getServer;

public class BlockDispenseListener implements Listener {
    public void debugLogging (String text) {
        getServer().broadcastMessage(text);
    }
    public Location dispenseBlockLoc(Block actor, BlockFace face) {
        Location disp_loc = null;
        if (actor.getType() == Material.DISPENSER) {

            disp_loc = null;

            switch (face) {
                case NORTH:
                    disp_loc = actor.getLocation().add(0, 0, -1);
                    break;
                case EAST:
                    disp_loc = actor.getLocation().add(1, 0, 0);
                    break;
                case WEST:
                    disp_loc = actor.getLocation().add(-1, 0, 0);
                    break;
                case SOUTH:
                    disp_loc = actor.getLocation().add(0, 0, 1);
                    break;
                case UP:
                    disp_loc = actor.getLocation().add(0, 1, 0);
                    break;
                case DOWN:
                    disp_loc = actor.getLocation().add(0, -1, 0);
                    break;
            }


        }
        return disp_loc;
    }

    @EventHandler
    public void onPlayerShear(BlockDispenseEvent event) {
        Block blk = event.getBlock();
        Directional dir_blk = (Directional) blk.getBlockData();
        BlockFace face = dir_blk.getFacing();
        Material itm = event.getItem().getType();
        org.bukkit.block.Dispenser dispenser = (org.bukkit.block.Dispenser) blk.getState();

        Location disp_loc = dispenseBlockLoc(blk, face);

        if (getServer().getWorld(blk.getWorld().getUID()).getBlockAt(disp_loc).getType() == Material.AIR) {

            Objects.requireNonNull(getServer().getWorld(blk.getWorld().getUID())).getBlockAt(disp_loc).setType(itm);
            for (ItemStack it : dispenser.getInventory().getStorageContents()) {
                if (it != null) {
                    if (it.getType() == itm) {
                        it.setAmount(it.getAmount()-1);
                        break;
                    }
                }

            }
            event.setCancelled(true);
        }
    }
}
