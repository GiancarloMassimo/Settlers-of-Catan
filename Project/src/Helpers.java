public class Helpers {
    public static int RandInt(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }
}
