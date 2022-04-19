public abstract class ItemPlacement<T> {

    boolean validPositionExists(T[] locations) {
        for (T location : locations) {
            if (checkCondition(location)) return true;
        }

        return false;
    }

    abstract boolean checkCondition(T location);
    abstract void place(T location);
}
