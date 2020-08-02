package me.imillusion.drawmything.events;

import lombok.Getter;
import me.imillusion.drawmything.game.Game;
import me.imillusion.drawmything.game.arena.Arena;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class GameCountdownStartEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private Game game;
    private Arena arena;

    public GameCountdownStartEvent(Game game) {
        this.game = game;

        this.arena = game.getArena();

        Bukkit.getPluginManager().callEvent(this);
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

}
