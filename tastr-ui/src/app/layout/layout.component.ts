import {Component} from "@angular/core";

@Component({
    selector: 'layout',
    template: `
        <layout-header></layout-header>
        <div class="container">
            <layout-body></layout-body>
            <layout-footer></layout-footer>
        </div>`
})
export class LayoutComponent {}
