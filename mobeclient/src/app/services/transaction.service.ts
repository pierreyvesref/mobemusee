import { Injectable} from '@angular/core';
import { HttpClient, HttpResponse, HttpParams, HttpHeaders, HttpHeaderResponse, HttpHandler } from '@angular/common/http';
import { AuthService } from './auth.service';
import { MoyenPaiementDTO } from '../dto/moyen-paiement.dto';
import { TransactionDTO } from '../dto/transaction.dto';
import { buildUrlPaiement, ServiceUrlNames } from '../../environments/urlconfig'


@Injectable()
export class TransactionService {

  constructor(private authHttp: HttpClient, private authService : AuthService ) {
  }

    public postTransaction(transaction : TransactionDTO, idMoyenPaiement : number){
      let headersPaiement = new HttpHeaders();
      headersPaiement = headersPaiement.set('Content-Type', 'application/json');
      headersPaiement = headersPaiement.append('Authorization', this.authService.getToken);
      headersPaiement = headersPaiement.append('login', this.authService.getLogin);
  
      ///user/{userId}/moyenPaiement/{moyenPaiementId}/transaction"
  
      return this.authHttp.post(buildUrlPaiement(ServiceUrlNames.PAIEMENT + '/user/'+ this.authService.getLogin + '/moyenPaiement/' + idMoyenPaiement + 
      '/transaction'), JSON.stringify(transaction), {
        headers: headersPaiement,
        responseType: 'text',
        observe: 'response'
      });
    }

    public getTransactions(){
      let headersPaiement = new HttpHeaders();
      //headersPaiement = headersPaiement.set('Content-Type', 'application/json');
      headersPaiement = headersPaiement.append('Authorization', this.authService.getToken);
      headersPaiement = headersPaiement.append('login', this.authService.getLogin);

      //console.log("fonction postMoyenPaiement");

      return this.authHttp.get<TransactionDTO[]>(buildUrlPaiement(ServiceUrlNames.PAIEMENT + '/user/'+ this.authService.getLogin + '/transaction'), {
      headers: headersPaiement,
      });
  }
  
}