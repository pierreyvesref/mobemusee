import { Inject, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { UserDTO } from '../dto/user.dto';
import { RoleDTO } from '../dto/role.type';
import { buildUrlAuthent, ServiceUrlNames } from '../../environments/urlconfig'

@Injectable()
export class AuthService {
  private isLoggedIn = false;

  private login;
  private token;

  get getLogin(): string {
    return this.login;
  }

  set setLogin(login: string) {
    this.login = login;
  }

  get getToken(): string {
    return this.token;
  }

  set setToken(token: string) {
    this.token = token;
  }

  get isLogged(): boolean {
    return this.isLoggedIn;
  }
  set isLogged(state: boolean) {
    this.isLoggedIn = state;
  }

  // store the URL so we can redirect after logging in
  private redirectUrl: string;


  get redirect(): string {
    return this.redirectUrl;
  }
  set redirect(url: string) {
    this.redirectUrl = url;
  }

  constructor(private http: HttpClient, private router: Router) {}

  public authentification(login: string, mdp: string){

    var loginMdp = {"login" : login, "mdp" : mdp};

    let headersAuthent = new HttpHeaders();
    headersAuthent = headersAuthent.set('Content-Type', 'application/json');

    return this.http.post(buildUrlAuthent(ServiceUrlNames.AUTHENTIFICATION + '/user'), JSON.stringify(loginMdp), {
      headers: headersAuthent,
      responseType: 'text',
      observe: 'response'
    });

  }

  public deconnexion(){

    let headersPaiement = new HttpHeaders();
      headersPaiement = headersPaiement.set('Content-Type', 'application/json');
      headersPaiement = headersPaiement.append('Authorization', this.getToken);

      return this.http.delete(buildUrlAuthent(ServiceUrlNames.AUTHENTIFICATION + '/user/') + this.getLogin ,{
        headers: headersPaiement,
        responseType: 'text',
        observe: 'response'
      });

  }

  public creationCompte(login: string, mdp: string, nom: string, prenom: string, mail: string){

    let headersAuthent = new HttpHeaders();
    headersAuthent = headersAuthent.set('Content-Type', 'application/json');
    var roles : RoleDTO[] = [{value: 'User'}];
    var newUser = new UserDTO(login, mdp, roles, nom, prenom, mail);

    return this.http.post(buildUrlAuthent(ServiceUrlNames.AUTHENTIFICATION + '/userCreation'), newUser ,{
      headers: headersAuthent,
      responseType: 'text',
      observe: 'response'
    });

  }

  
  }