package zork.object.levels;

import zork.object.Room;

public abstract class Level {
    public String name;
    public String objective;
    public Room startRoom;
    public abstract boolean objectiveCompleted();
    public int monsterNum;
}
