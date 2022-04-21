public class Helpers {
    public static int randInt(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }
    public static double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static class ReferenceInt {
        int value;

        public ReferenceInt(int value) {
            this.value = value;
        }
    }

    public static class ReferenceSourceEdge {
        Edge edge;
        Node node;

        public ReferenceSourceEdge(Edge edge, Node node) {
            this.edge = edge; this.node = node;
        }
    }
}
