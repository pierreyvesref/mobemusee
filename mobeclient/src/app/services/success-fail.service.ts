import { Injectable} from '@angular/core';
import { HttpClient, HttpResponse, HttpParams, HttpHeaders, HttpHeaderResponse, HttpHandler } from '@angular/common/http';
import { AuthService } from './auth.service';
import { MoyenPaiementDTO } from '../dto/moyen-paiement.dto';
import { TransactionDTO } from '../dto/transaction.dto';

@Injectable()
export class SuccessFailService {

    message: string;
    submessage: string;

    constructor(private authHttp: HttpClient) {

    }

    set setMessage(message: string){
        this.message = message;
    }

    get getMessage(){
        return this.message;
    }

    set setSubMessage(submessage: string){
        this.submessage = submessage;
    }

    get getSubMessage(){
        return this.submessage;
    }


}