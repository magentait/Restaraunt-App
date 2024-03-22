package business;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestaurantStateTemplate {
    private double totalRevenue;
    public RestaurantStateTemplate() {
        totalRevenue = RestaurantStats.getTotalRevenue();
    }

    public RestaurantStateTemplate(@JsonProperty("totalRevenue") double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
