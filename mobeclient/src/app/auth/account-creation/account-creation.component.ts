import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { stringify } from 'querystring';
import { SuccessFailService } from 'src/app/services/success-fail.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-account-creation',
  templateUrl: './account-creation.component.html',
  styleUrls: ['./account-creation.component.scss']
})
export class AccountCreationComponent implements OnInit {
  httpError: HttpErrorResponse;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router, private successFailService : SuccessFailService) { }

  accountForm: FormGroup;

  login = new FormControl('', [Validators.required]);
  mdp = new FormControl('', [Validators.required]);
  nom = new FormControl('', [Validators.required]);
  prenom = new FormControl('', [Validators.required]);
  mail = new FormControl('', [Validators.required, Validators.email]);

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.accountForm = this.formBuilder.group({
      login: '',
      mdp: '',
      nom: '',
      prenom: '',
      mail: '',
    });
  }

  formValid(): boolean{
    return !this.login.hasError('required') && !this.login.hasError('minlength') &&
    !this.mdp.hasError('required') &&
    !this.nom.hasError('required') && !this.prenom.hasError('required') && 
    !this.mail.hasError('required') && !this.mail.hasError('email');
  }

  getErrorMessage(field: string) {
    switch(field) { 
      case 'login': { 
        if (this.login.hasError('required')) {
          return 'Vous devez entrer une valeur';
        }else if(this.login.hasError('minlength')){
          return 'Minimum 2 caractères';
        }
         break; 
      } 
      case 'mdp': { 
        if (this.mdp.hasError('required')) {
          return 'Vous devez entrer une valeur';
        }
         break; 
      }
      case 'nom': { 
        if (this.nom.hasError('required')) {
          return 'Vous devez entrer une valeur';
        }
         break; 
      }
      case 'prenom': { 
        if (this.prenom.hasError('required')) {
          return 'Vous devez entrer une valeur';
        }
         break; 
      }
      case 'mail': { 
        if (this.mail.hasError('required')) {
          return 'Vous devez entrer une valeur';
        } else if(this.mail.hasError('email')){
          return 'email non valide';
        }
         break; 
      }
      default: { 
         break; 
      } 
   } 
  }

  onSubmitForm(){
    this.authService.creationCompte(this.login.value, this.mdp.value, this.nom.value, this.prenom.value, this.mail.value).subscribe(
      response => {
          //localStorage.setItem('token', this.token);
          this.successFailService.setMessage = "Votre compte a bien été créé !";
          this.successFailService.setSubMessage = "Vous pouvez désormais vous connecter en allant sur l'onglet Authentification.";
          this.router.navigate(['/successFail']);
      },
      error => {
        //this.errorService.raisedLoginError('AuthentificationComponent', err);
        this.httpError = error;
        console.log('Erreur ! : ' + this.httpError.message);
      }
    );
  }

  annuler(){
    this.router.navigate(['/auth'])
  }

}
