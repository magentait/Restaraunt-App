package business;

public class RestaurantStats {
    private static double totalRevenue;
    public static double updateTotalRevenue(double value) {
        totalRevenue += value;

        FileHandler.saveStats("stats.json");
        return totalRevenue;
    }

    public static double getTotalRevenue() { return totalRevenue; }

    public static void setTotalRevenue(double totalRevenue) { RestaurantStats.totalRevenue = totalRevenue; }



    public static void display() {
        System.out.println();
        System.out.println("Текущая прибыль: " + totalRevenue + " $");
        System.out.println();
    }
}
