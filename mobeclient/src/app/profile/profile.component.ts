import { Component, OnInit, Input, NgModule } from '@angular/core';
import { ProfileService } from '../services/profile.service';
import { UserDTO } from '../dto/user.dto';
import { MoyenPaiementDTO } from '../dto/moyen-paiement.dto';
import { AuthService } from '../services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  user: UserDTO;
  httpError: HttpErrorResponse;

  getuser(){
      return this.user;
  }

  constructor(private profileService: ProfileService) { }

  ngOnInit(): void {
    this.subscribeCurrentUser();
  }

  public subscribeCurrentUser(){
    this.profileService.getCurrentUser().subscribe(
      (response: UserDTO) => {
          this.user = response;
          return response;
      },
      (error) => {
        this.httpError = error;
          console.log('Erreur ! : ' + this.httpError.message);
      }
    );
  }


  
}
