package eu.tastr.e2e.pagina.tasting;

import eu.tastr.e2e.pagina.TastrPagina;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TastingOverzichtPagina extends TastrPagina<TastingOverzichtPagina> {

    public TastingOverzichtPagina() {}

    @Override
    protected SelenideElement pageIdentifier() {
        return tastingOverzichtPageIdentifier();
    }

    public TastingOverzichtPagina assertAantalTastingsIs(int aantalTastings) {
        tastings().shouldHaveSize(aantalTastings);
        return this;
    }

    public int aantalTastings() {
        return tastings().size();
    }

    private static SelenideElement tastingOverzichtPageIdentifier() {
        return $("#tastr-titel");
    }

    private ElementsCollection tastings() {
        return $$("li");
    }

}
