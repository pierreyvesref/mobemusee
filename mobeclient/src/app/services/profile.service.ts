//import { Subject } from 'rxjs/Subject';
import { Injectable} from '@angular/core';
import { HttpClient, HttpResponse, HttpParams, HttpHeaders, HttpHeaderResponse, HttpHandler } from '@angular/common/http';
import {UserDTO} from '../dto/user.dto'
import { Observable, Subject } from 'rxjs';
import { AuthService } from './auth.service';
import { MoyenPaiementDTO } from '../dto/moyen-paiement.dto';
import { buildUrlAuthent, ServiceUrlNames } from '../../environments/urlconfig'


@Injectable()
export class ProfileService {

  constructor(private authHttp: HttpClient, private authService : AuthService ) {
  }


  /**
   * [getCurrentUser query from the server]
   * @return {UserDTO>} [current user]
   */
  public getCurrentUser(){
    let headersProfile = new HttpHeaders();
    headersProfile = headersProfile.append('Authorization', this.authService.getToken);
    return this.authHttp
    .get<UserDTO>(buildUrlAuthent(ServiceUrlNames.AUTHENTIFICATION + '/user/' + this.authService.getLogin), {
      headers : headersProfile
    });
  }




}
