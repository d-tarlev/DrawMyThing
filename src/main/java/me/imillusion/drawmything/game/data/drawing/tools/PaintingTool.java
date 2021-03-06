package me.imillusion.drawmything.game.data.drawing.tools;

import me.imillusion.drawmything.game.canvas.Canvas;
import me.imillusion.drawmything.game.canvas.Point;
import org.bukkit.DyeColor;
import org.bukkit.inventory.ItemStack;

public interface PaintingTool {

    /**
     * Applies the tool to the point
     *
     * @param canvas - The canvas where to apply
     * @param point  - The point where to apply
     * @param color  - The new color of the point
     */
    void apply(Canvas canvas, Point point, DyeColor color);

    /**
     * Some tools can work while moving (Only applies if the user if "holding" right click
     *
     * @param canvas - The canvas where to apply
     * @param point  - The point where to apply
     * @param color  - The new color of the point
     */
    default void applyMove(Canvas canvas, Point point, DyeColor color)
    {
        //This will be used under specific cases
    }

    int getSlot();

    /**
     * Gets the tool item
     *
     * @return - The item
     */
    ItemStack getItem();
}
