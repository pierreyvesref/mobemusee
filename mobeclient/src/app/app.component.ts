import { Component, OnInit } from '@angular/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit{
  
  altMobeLogoPath = 'MOBE';
  mobeLogoPath = '../../assets/images/mobe.jpg';

  altOrleansLogoPath = 'Orleans Métropole';
  orleansLogoPath = '../../assets/images/OrléansMetropole.jpg';

  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  public isLogged(){
    return this.authService.isLogged;
  }


}
