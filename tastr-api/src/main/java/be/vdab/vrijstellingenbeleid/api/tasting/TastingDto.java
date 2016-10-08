package be.vdab.vrijstellingenbeleid.api.tasting;

import be.vdab.vrijstellingenbeleid.api.common.Dto;

public class TastingDto extends Dto {

    public String id;
    public int versie;
    public String naam;

    private TastingDto() {
    }

    public static TastingDto tastingDto() {
        return new TastingDto();
    }

    public TastingDto withId(String id) {
        this.id = id;
        return this;
    }

    public TastingDto withVersie(int versie) {
        this.versie = versie;
        return this;
    }

    public TastingDto withNaam(String naam) {
        this.naam = naam;
        return this;
    }
}
