import { Injectable} from '@angular/core';
import { HttpClient, HttpResponse, HttpParams, HttpHeaders, HttpHeaderResponse, HttpHandler } from '@angular/common/http';
import {UserDTO} from '../dto/user.dto'
import { Observable, Subject } from 'rxjs';
import { AuthService } from './auth.service';
import { MoyenPaiementDTO } from '../dto/moyen-paiement.dto';
import { buildUrlPaiement, ServiceUrlNames } from '../../environments/urlconfig'


@Injectable()
export class PaymentService {

  constructor(private authHttp: HttpClient, private authService : AuthService ) {
  }


    public getMeansPayment(){
        let headersPaiement = new HttpHeaders();
        //headersPaiement = headersPaiement.set('Content-Type', 'application/json');
        headersPaiement = headersPaiement.append('Authorization', this.authService.getToken);
        headersPaiement = headersPaiement.append('login', this.authService.getLogin);

        //console.log("fonction postMoyenPaiement");

        return this.authHttp.get<MoyenPaiementDTO[]>(buildUrlPaiement(ServiceUrlNames.PAIEMENT + '/user/'+ this.authService.getLogin + '/moyenPaiement'), {
        headers: headersPaiement,
        });
    }

    public postMoyenPaiement(moyenPaiement : MoyenPaiementDTO){
      let headersPaiement = new HttpHeaders();
      headersPaiement = headersPaiement.set('Content-Type', 'application/json');
      headersPaiement = headersPaiement.append('Authorization', this.authService.getToken);
      headersPaiement = headersPaiement.append('login', this.authService.getLogin);
  
      //console.log("fonction postMoyenPaiement");
  
      return this.authHttp.post(buildUrlPaiement(ServiceUrlNames.PAIEMENT + '/user/'+ this.authService.getLogin + '/moyenPaiement'), JSON.stringify(moyenPaiement), {
        headers: headersPaiement,
        responseType: 'text',
        observe: 'response'
      });
    }

    deleteMeanPayment(idMoyenPaiement : number){
      let headersPaiement = new HttpHeaders();
      headersPaiement = headersPaiement.set('Content-Type', 'application/json');
      headersPaiement = headersPaiement.append('Authorization', this.authService.getToken);
      headersPaiement = headersPaiement.append('login', this.authService.getLogin);

      return this.authHttp.delete(buildUrlPaiement(ServiceUrlNames.PAIEMENT + '/user/'+ this.authService.getLogin + '/moyenPaiement/' + idMoyenPaiement), {
        headers: headersPaiement,
        responseType: 'text',
        observe: 'response'
      });

    }

    
  
}