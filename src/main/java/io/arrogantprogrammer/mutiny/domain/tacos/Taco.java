package io.arrogantprogrammer.mutiny.domain.tacos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Taco {

    @JsonProperty("base_layer")
    private Filling filling;

    private Mixin mixin;

    private Seasoning seasoning;

    private Condiment condiment;

    private Shell shell;

    public Taco(Filling filling, Mixin mixin, Seasoning seasoning, Condiment condiment, Shell shell) {
        this.filling = filling;
        this.mixin = mixin;
        this.seasoning = seasoning;
        this.condiment = condiment;
        this.shell = shell;
    }

    public Taco() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Taco{");
        sb.append("filling=").append(filling);
        sb.append(", mixin=").append(mixin);
        sb.append(", seasoning=").append(seasoning);
        sb.append(", condiment=").append(condiment);
        sb.append(", shell=").append(shell);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Taco taco = (Taco) o;

        if (filling != null ? !filling.equals(taco.filling) : taco.filling != null) return false;
        if (mixin != null ? !mixin.equals(taco.mixin) : taco.mixin != null) return false;
        if (seasoning != null ? !seasoning.equals(taco.seasoning) : taco.seasoning != null) return false;
        if (condiment != null ? !condiment.equals(taco.condiment) : taco.condiment != null) return false;
        return shell != null ? shell.equals(taco.shell) : taco.shell == null;
    }

    @Override
    public int hashCode() {
        int result = filling != null ? filling.hashCode() : 0;
        result = 31 * result + (mixin != null ? mixin.hashCode() : 0);
        result = 31 * result + (seasoning != null ? seasoning.hashCode() : 0);
        result = 31 * result + (condiment != null ? condiment.hashCode() : 0);
        result = 31 * result + (shell != null ? shell.hashCode() : 0);
        return result;
    }

    public Filling getFilling() {
        return filling;
    }

    public void setFilling(Filling filling) {
        this.filling = filling;
    }

    public Mixin getMixin() {
        return mixin;
    }

    public void setMixin(Mixin mixin) {
        this.mixin = mixin;
    }

    public Seasoning getSeasoning() {
        return seasoning;
    }

    public void setSeasoning(Seasoning seasoning) {
        this.seasoning = seasoning;
    }

    public Condiment getCondiment() {
        return condiment;
    }

    public void setCondiment(Condiment condiment) {
        this.condiment = condiment;
    }

    public Shell getShell() {
        return shell;
    }

    public void setShell(Shell shell) {
        this.shell = shell;
    }
}
