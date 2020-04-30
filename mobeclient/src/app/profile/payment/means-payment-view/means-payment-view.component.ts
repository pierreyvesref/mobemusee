import { Component, OnInit, Inject } from '@angular/core';
import { MoyenPaiementDTO } from 'src/app/dto/moyen-paiement.dto';
import { ProfileService } from 'src/app/services/profile.service';
import { UserDTO } from 'src/app/dto/user.dto';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { MeanPaymentDialogComponent } from './mean-payment-dialog/mean-payment-dialog.component';
import { TypeCarteDTO } from 'src/app/dto/carte.type';
import { PaymentService } from 'src/app/services/payment.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-means-payment-view',
  templateUrl: './means-payment-view.component.html',
  styleUrls: ['./means-payment-view.component.scss']
})
export class MeansPaymentViewComponent implements OnInit {

  moyenPaiements : MoyenPaiementDTO[];
  httpError: HttpErrorResponse;

  constructor(private paymentService: PaymentService, public dialog: MatDialog) {  }


  ngOnInit(): void {
    this.subscribeMeansPayment();
  }

  public subscribeMeansPayment(){
    this.paymentService.getMeansPayment().subscribe(
      (response: MoyenPaiementDTO[]) => {
          this.moyenPaiements = response;
          return response;
      },
      (error) => {
        this.httpError = error;
        console.log('Erreur ! : ' + this.httpError.message);
      }
    );
  }

  public ajouterMoyenPaiement(){

    var titulaireData : string;
    var typeCarteData: TypeCarteDTO;
    var numCompteData: number;
    var cryptogrammeData: number;
    var dateExpirationData: Date;

    const dialogRef = this.dialog.open(MeanPaymentDialogComponent, {
      data: {titulaire: titulaireData, typeCarte: typeCarteData, numCompte: numCompteData, cryptogramme: cryptogrammeData, dateExpiration: dateExpirationData},
      width: '400px'
    });


    dialogRef.afterClosed().subscribe(result => {

        var moyenPaiement = new MoyenPaiementDTO(result.typeCarte, result.numCompte, result.dateExpiration, result.cryptogramme, result.titulaire);

        //this.moyenPaiements.push(moyenPaiement);
        this.paymentService.postMoyenPaiement(moyenPaiement).subscribe(
          response => {
          console.log('requete envoyée');
          this.subscribeMeansPayment();
          //return response;
        },
        (error) => {
          this.httpError = error;
        console.log('Erreur ! : ' + this.httpError.message);
        });

      //}
    });


  }

  meanPaiementEventHander(){
    this.subscribeMeansPayment();
  }


}