import { Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.scss']
})
export class AboutComponent implements OnInit {

  aboutImagePath = '../../assets/images/mobeLocation.jpg';
  altAboutImagePath = "6 Rue Marcel Proust, 45000 Orléans";

  constructor() { }


  ngOnInit(): void {
  }

}
