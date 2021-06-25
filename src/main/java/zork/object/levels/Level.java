package zork.object.levels;

import zork.object.Room;

import java.util.Map;

public abstract class Level {
    public String name;
    public String objective;
    public Room startRoom;
    public abstract boolean objectiveCompleted();
    public int monsterNum;
    public String object;
    public Map<String,Room> roomLevel;
}
