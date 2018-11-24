import { Component, OnInit, style } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { myService } from "../service/myservice.service";
import { Subscription, Observable } from "rxjs";

@Component({
  selector: 'page-list',
  templateUrl: 'list.html'
})
export class ListPage implements OnInit {
  selectedItem: any;
  icons_left: string[];
  icons_midUp;
  icons_midDown;
  icons_right: String[];
  items: Array<{ title: string, note: string, icon: string }>;
  pathCord: any = [];
  sub: Subscription;

  constructor(public navCtrl: NavController, public navParams: NavParams, public service: myService) {
    // If we navigated to this page, we will have an item available as a nav param
    this.selectedItem = navParams.get('item');

    // Let's populate this page with some filler content for funzies
    this.icons_left = ['basket', 'basket', 'beer', 'football', 'basketball', 'paper-plane'];
    this.icons_midUp = ['hammer', 'document', 'hammer', 'document'];
    this.icons_midDown = ['hammer', 'document', 'hammer', 'document'];

  }
  ngOnInit() {

    this.service.getList().subscribe(response => {
      this.pathCord = response;

      if (!this.samePostion()) {
        // this.sub = Observable.interval(500).subscribe((val) => {
        for (let i = 0; i < this.pathCord.length; i++) {
          let dot = document.getElementById('dot');
          var ele = dot.getBoundingClientRect();


          dot.style.top = (ele.top + ((this.pathCord[i].x - 2) * 10)) + 'px';
          dot.style.left = (ele.left + ((this.pathCord[i].y + 2) * 10)) + 'px';
          console.log(dot)
        }


        // });
      }


    });


  }

  repeatTimes(n) {
    return Array(n);
  }


  samePostion() {
    return false;
  }
}
