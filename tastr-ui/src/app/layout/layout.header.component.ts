import { Component } from '@angular/core';

@Component({
    selector: 'layout-header',
    templateUrl: 'app/layout/layout.header.html'
})
class LayoutHeaderComponent {
    jaar : number = new Date().getFullYear();
}
