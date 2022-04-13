public abstract class ItemPlacement<T> {
    abstract boolean checkCondition(T location);
    abstract void place(T location);
}
