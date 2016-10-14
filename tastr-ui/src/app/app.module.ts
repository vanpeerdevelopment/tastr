import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import { LayoutModule }  from './layout/layout.module';

@NgModule({
    imports:      [ BrowserModule, LayoutModule],
    declarations: [ AppComponent ],
    bootstrap:    [ AppComponent ]
})
export class AppModule { }
