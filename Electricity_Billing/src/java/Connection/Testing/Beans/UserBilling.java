/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection.Testing.Beans;

public class UserBilling {

    private String userId;
    private String type;
    private int total_unit;
    private int cost;
    private String pdate;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTotalUnit(int total_unit) {
        this.total_unit = total_unit;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public int getTotalUnit() {
        return total_unit;
    }

    public String getPdate() {
        return pdate;
    }

}
