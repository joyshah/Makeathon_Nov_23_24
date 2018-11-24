import { Component, OnInit } from "@angular/core";
import 'rxjs/add/observable/interval';
import { Hotspot, HotspotNetwork } from "@ionic-native/hotspot";
import { Subscription, Observable } from "rxjs";

@Component({
  selector: "page-home",
  templateUrl: "home.html"
})
export class HomePage implements OnInit {
  constructor(private hotspot: Hotspot) { }
  vari: any;
  sub: Subscription;
  nevigating = false;
  ngOnInit() {
  }

  getStrength() {
    this.nevigating = true;
    this.sub = Observable.interval(500)
      .subscribe((val) => {
        this.hotspot.scanWifi().then((networks: Array<HotspotNetwork>) => {
          console.log(networks);
          this.vari = networks;
        });
      });
  }

  stopSubscription() {
    this.sub.unsubscribe();
  }
}
