package eu.tastr.e2e.pagina;

import com.codeborne.selenide.SelenideElement;
import eu.tastr.e2e.common.RepeatableSelenideAction;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.$;

public abstract class TastrPagina<S extends TastrPagina<S>> {

    protected TastrPagina() {
        pageIdentifier().shouldBe(visible);
    }

    protected abstract SelenideElement pageIdentifier();

    protected void click(SelenideElement el) {
        el.shouldBe(exist, visible, enabled).click();
    }

    protected void clickUntilGone(SelenideElement el) {
        el.should(exist, visible, enabled);
        RepeatableSelenideAction.repeatAction(() -> el.click())
            .until(() -> el.is(not(visible)));
    }
}
