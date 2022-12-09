package TVWireHouse.Entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "TV")
public class TV {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @NotEmpty(message = "Company can not be null or empty")
    @Column(name = "company", nullable = false)
    private String company;

    @NotEmpty(message = "Model can not be null or empty")
    @Column(name = "model" , nullable = false)
    private String model;

    @NotEmpty(message = "Screen type can not be null or empty")
    @Column(name = "screen_type", nullable = false)
    private String screenType;

    @NotEmpty(message = "Resolution can not be null or empty")
    @Column(name = "resolution", nullable = false)
    private String resolution;

    @Column(name = "cost", nullable = false)
    @Min(value = 100, message = "Cost should be from 100$")
    private int cost;

    @Column(name = "diagonal", nullable = false)
    @Min(value = 10, message = "Diagonal should be from '10")
    private int diagonal;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TVWireHouse.Entities.TV{" +
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
