package business;

public class RestStatistics {
    private static double totalRevenue;
    public static void updateTotalRevenue(double value) {
        totalRevenue += value;

        FileHandler.saveStats("stats.json");
    }

    public static double getTotalRevenue() { return totalRevenue; }

    public static void setTotalRevenue(double totalRevenue) { RestStatistics.totalRevenue = totalRevenue; }

    public static void display() {
        System.out.println();
        System.out.println("Текущая прибыль: " + totalRevenue + " $");
        System.out.println();
    }
}
