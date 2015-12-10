package Connection.Testing.Beans;

public class BillingControl {

    private int id;
    private String type;
    private int unit;
    private String sdate;
    private String edate;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getUnit() {
        return unit;
    }

    public String getSdate() {

        return sdate;
    }

    public String getEdate() {

        return edate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

}
