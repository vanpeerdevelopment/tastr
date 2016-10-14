import {NgModule} from "@angular/core";
import {LayoutComponent} from "./layout.component";
import {LayoutHeaderComponent} from "./layout.header.component";
import {LayoutBodyComponent} from "./layout.body.component";
import {LayoutFooterComponent} from "./layout.footer.component";

@NgModule({
    declarations: [
        LayoutComponent,
        LayoutHeaderComponent,
        LayoutBodyComponent,
        LayoutFooterComponent
    ],
    exports: [LayoutComponent]
})
export class LayoutModule {
}
