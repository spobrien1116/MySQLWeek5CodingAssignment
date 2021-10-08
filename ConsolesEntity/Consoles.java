package ConsolesEntity;

public class Consoles {
    
    private String name;
    private int controllers;
    private int release_year;
    private double release_price;
    private int online_capable;

    public Consoles (String name, int controllers, int release_year, double release_price, int online_capable) {
        this.setName(name);
        this.setControllers(controllers);
        this.setRelease_year(release_year);
        this.setRelease_price(release_price);
        this.setOnline_capable(online_capable);
    }
    //Getters for Console variables.
    public String getName() {
        return name;
    }

    public int getControllers() {
        return controllers;
    }

    public int getRelease_year() {
        return release_year;
    }

    public double getRelease_price() {
        return release_price;
    }

    public int getOnline_capable() {
        return online_capable;
    }

    //Setters for Console variables.
    public void setName(String name) {
        this.name = name;
    }

    public void setControllers(int controllers) {
        this.controllers = controllers;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public void setRelease_price(double release_price) {
        this.release_price = release_price;
    }

    public void setOnline_capable(int online_capable) {
        this.online_capable = online_capable;
    }

}
