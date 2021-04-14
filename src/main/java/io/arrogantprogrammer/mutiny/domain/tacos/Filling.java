package io.arrogantprogrammer.mutiny.domain.tacos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

public class Filling {

    private String name;

    private URL url;

    private String recipe;

    private String slug;

    public Filling(String name, URL url, String recipe, String slug) {
        this.name = name;
        this.url = url;
        this.recipe = recipe;
        this.slug = slug;
    }

    public Filling(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Filling{");
        sb.append("name='").append(name).append('\'');
        sb.append(", url=").append(url);
        sb.append(", recipe='").append(recipe).append('\'');
        sb.append(", slug='").append(slug).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Filling filling = (Filling) o;

        if (name != null ? !name.equals(filling.name) : filling.name != null) return false;
        if (url != null ? !url.equals(filling.url) : filling.url != null) return false;
        if (recipe != null ? !recipe.equals(filling.recipe) : filling.recipe != null) return false;
        return slug != null ? slug.equals(filling.slug) : filling.slug == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (recipe != null ? recipe.hashCode() : 0);
        result = 31 * result + (slug != null ? slug.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
