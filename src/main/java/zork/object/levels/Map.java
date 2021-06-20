package zork.object.levels;

import zork.object.Room;

public abstract class Map {
    public String name;
    public String objective;
    public Room startRoom;
    public abstract boolean objectiveCompleted();
}
