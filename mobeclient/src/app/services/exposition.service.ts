import { HttpClient, HttpResponse, HttpParams, HttpHeaders } from '@angular/common/http';
import {OeuvreDTO} from '../dto/oeuvre.dto'
import {ExpositionDTO} from '../dto/exposition.dto'
import { buildUrlMusee, ServiceUrlNames } from '../../environments/urlconfig'
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';


@Injectable()
export class ExpositionService {
  constructor(private authHttp: HttpClient, private authService: AuthService) { }

  /**
   * [getAllOeuvres query from the server all oeuvres]
   * @return {OeuvreDTO[]>} [list of all oeuvres]
   */
  
  public getAllOeuvres(){
    return this.authHttp
    .get<OeuvreDTO[]>(buildUrlMusee(ServiceUrlNames.MUSEE + '/oeuvre'));
  }


  public getAllExpos(){
    let headersPaiement = new HttpHeaders();
        //headersPaiement = headersPaiement.set('Content-Type', 'application/json');
        headersPaiement = headersPaiement.append('Authorization', this.authService.getToken);
        headersPaiement = headersPaiement.append('login', this.authService.getLogin);
      return this.authHttp
      .get<ExpositionDTO[]>(buildUrlMusee(ServiceUrlNames.MUSEE + '/exposition'), {
        headers: headersPaiement,
      });
  }


}