package me.imillusion.drawmything.game.data.drawing.colors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ColorSelectionArea {

    private DyeColor color;
    private List<Location> locations = new ArrayList<>();


    public ColorSelectionArea(Location topLeft, Location bottomRight, DyeColor color)
    {
        this.color = color;

        for (int x = topLeft.getBlockX(); x <= bottomRight.getBlockX(); x++)
            for (int y = topLeft.getBlockY(); y >= bottomRight.getBlockY(); y--)
                for (int z = topLeft.getBlockZ(); z <= bottomRight.getBlockZ(); z++) {
                    Location newLocation = new Location(topLeft.getWorld(), x, y, z);
                    Block block = newLocation.getBlock();

                    if (block.getType() != Material.WOOL)
                        continue;

                    locations.add(newLocation);
                }
    }

    public static List<ColorSelectionArea> createAreas(Location topLeft, Location bottomRight)
    {
        List<ColorSelectionArea> areas = new ArrayList<>();

        Map<DyeColor, List<Location>> locations = new HashMap<>();

        //Adding all the locations to the map
        for (int x = topLeft.getBlockX(); x <= bottomRight.getBlockX(); x++)
            for (int y = topLeft.getBlockY(); y >= bottomRight.getBlockY(); y--)
                for (int z = topLeft.getBlockZ(); z <= bottomRight.getBlockZ(); z++) {
                    Location newLocation = new Location(topLeft.getWorld(), x, y, z);
                    Block block = newLocation.getBlock();

                    if (block.getType() != Material.WOOL)
                        continue;

                    DyeColor color = DyeColor.getByWoolData(block.getData());
                    locations.putIfAbsent(color, new ArrayList<>());
                    locations.get(color).add(newLocation);
                }

        locations.forEach((color, list) -> areas.add(new ColorSelectionArea(color, list)));

        return areas;
    }

    public boolean isWithin(Location location)
    {
        return locations.contains(location);
    }
}
