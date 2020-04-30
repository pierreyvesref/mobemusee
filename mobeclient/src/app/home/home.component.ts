import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  homeImagePath = '../../assets/images/perspective-interieur.jpg';
  altHomeImagePath = "Image d'Accueil";

  constructor() { }

  ngOnInit(): void {
  }

  

}
