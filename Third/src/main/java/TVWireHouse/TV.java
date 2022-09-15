package TVWireHouse;

public class TV {
    private String company;
    private String model;
    private String screenType;
    private String resolution;

    private int cost;
    private int diagonal;
    private int TVid;

    static int id = 0;

    public TV(){

    }

    public TV(String company, String model, String screenType, String resolution, int cost, int diagonal){
        this.company = company;
        this.model = model;
        this.screenType = screenType;
        this.resolution = resolution;
        this.cost = cost;
        this.diagonal = diagonal;
        this.TVid = id;
        id = id + 1;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(int diagonal) {
        this.diagonal = diagonal;
    }

    @Override
    public String toString() {
        return "TVWireHouse.TV{" +
                "Company='" + company + '\'' +
                ", Model='" + model + '\'' +
                ", ScreenType='" + screenType + '\'' +
                ", Resolution='" + resolution + '\'' +
                ", Cost=" + cost +
                ", Diagonal=" + diagonal +
                ", TVid=" + TVid +
                '}';
    }

    public int getTVid() {
        return TVid;
    }

    public void setTVid(int TVid) {
        this.TVid = TVid;
    }
}
