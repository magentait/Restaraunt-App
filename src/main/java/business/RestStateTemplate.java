package business;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestStateTemplate {
    private double totalRevenue;
    public RestStateTemplate() {
        totalRevenue = RestStatistics.getTotalRevenue();
    }

    public RestStateTemplate(@JsonProperty("totalRevenue") double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
