package io.arrogantprogrammer.mutiny.beers.domain;

public class Beer {

    private String name;

    private String tagline;

    private double abv;

    private int ibu;

    public Beer(String name, String tagline, double abv, int ibu) {
        this.name = name;
        this.tagline = tagline;
        this.abv = abv;
        this.ibu = ibu;
    }

    public Beer() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Beer{");
        sb.append("name='").append(name).append('\'');
        sb.append(", tagline='").append(tagline).append('\'');
        sb.append(", abv=").append(abv);
        sb.append(", ibu=").append(ibu);
        sb.append('}');
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public int getIbu() {
        return ibu;
    }

    public void setIbu(int ibu) {
        this.ibu = ibu;
    }
}
