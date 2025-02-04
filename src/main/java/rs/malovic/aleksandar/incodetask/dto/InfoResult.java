package rs.malovic.aleksandar.incodetask.dto;

public class InfoResult extends Result {
    private String info;

    public InfoResult() {
    }

    public InfoResult(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
