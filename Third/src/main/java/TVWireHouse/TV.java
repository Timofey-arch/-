package TVWireHouse;

import org.hibernate.annotations.Table;
import javax.persistence.*;

@Entity
@Table(appliesTo = "TV")
public class TV {
    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "model" , nullable = false)
    private String model;

    @Column(name = "screen_type", nullable = false)
    private String screenType;

    @Column(name = "resolution", nullable = false)
    private String resolution;

    @Column(name = "cost", nullable = false)
    private int cost;

    @Column(name = "diagonal", nullable = false)
    private int diagonal;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    public int id;

    public TV(){}

    public TV(String company, String model, String screenType, String resolution, int cost, int diagonal){
        this.company = company;
        this.model = model;
        this.screenType = screenType;
        this.resolution = resolution;
        this.cost = cost;
        this.diagonal = diagonal;
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
                "Company='" + this.getCompany() + '\'' +
                ", Model='" + this.getModel() + '\'' +
                ", ScreenType='" + this.getScreenType() + '\'' +
                ", Resolution='" + this.getResolution() + '\'' +
                ", Cost=" + this.getCost() +
                ", Diagonal=" + this.getDiagonal() +
                ", id=" + this.id +
                '}';
    }
}
