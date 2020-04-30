import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit {

  userForm: FormGroup;
  login = new FormControl('', [Validators.required]);
  mdp = new FormControl('', [Validators.required]);

  progressBarShowed: boolean;

  httpError: HttpErrorResponse;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.initForm();
    this.progressBarShowed = false;
  }

  public isLogged(){
    return this.authService.isLogged;
  }

  formValid(): boolean{
    return !this.login.hasError('required') && !this.mdp.hasError('required');
  }

  initForm() {
    this.userForm = this.formBuilder.group({
      login: '',
      mdp: '',
    });
  }


  getErrorMessage(field: string) {
    switch(field) { 
      case 'login': { 
        if (this.login.hasError('required')) {
          return 'Vous devez entrer une valeur';
        }
         break; 
      } 
      case 'mdp': { 
        if (this.mdp.hasError('required')) {
          return 'Vous devez entrer une valeur';
        }
         break; 
      } 
      default: { 
         break; 
      } 
   } 
  }

  public onSubmitForm(): void {
    if(this.authService.isLogged == false){
        this.connecter();
    }
    else{
        this.deconnecter();
    }
  }

  public connecter(): void {
    /*const formValue = this.userForm.value;
    var login = formValue['login'];
    var mdp = formValue['mdp'];*/
    this.authService.authentification(this.login.value, this.mdp.value).subscribe(
      response => {
          this.progressBarShowed = true;
          setTimeout(
            () => {
              console.log('connexion');
              this.progressBarShowed = false;

              this.authService.setToken = response.headers.get('Authorization');
              this.authService.setLogin = this.login.value;
              this.authService.isLogged = true;
              //localStorage.setItem('token', this.token);
              this.router.navigate(['/profile']);
            }, 1000
          );
      },
      error => {
        //this.errorService.raisedLoginError('AuthentificationComponent', err);
        this.httpError = error;
        console.log('Erreur ! : ' + this.httpError.message);
      }
    );

  }

  public deconnecter(): void {
    this.authService.deconnexion().subscribe(
      response => {
        this.progressBarShowed = true;
        setTimeout(
          () => {
            console.log('deconnexion');
            
            this.authService.setLogin = "";
            this.authService.setToken = "";
            this.authService.isLogged = false;
                //localStorage.removeItem('login');
                //localStorage.removeItem('token');
            this.router.navigate(['/home']);
          }, 1000
        );
      },
      error => {
        this.httpError = error;
        console.log('Erreur ! : ' + this.httpError.message);
      }
    );
  }

}
