import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { TypeCarteDTO } from '../../../dto/carte.type';
import { PaymentService } from 'src/app/services/payment.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from 'src/app/confirmation-dialog/confirmation-dialog.component';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-mean-payment',
  templateUrl: './mean-payment.component.html',
  styleUrls: ['./mean-payment.component.scss']
})
export class MeanPaymentComponent implements OnInit {

  @Input() id: number;
  @Input() titulaire: string;
  @Input() typeCarte: TypeCarteDTO;
  @Input() numCompte: number;
  @Input() cryptogramme: number;
  @Input() dateExpiration: Date;

  @Output() meanPaiementEvent = new EventEmitter<string>();

  httpError : HttpErrorResponse;


  constructor(private paymentService : PaymentService, private router: Router, public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  getId(){
    return this.id;
  }
 
  getTitulaire() {
    return this.titulaire;
  }

  getTypeCarte() {
    return this.typeCarte.value;
  }

  getNumCompte() {
    return this.numCompte;
  }

  getCryptogramme() {
    return this.cryptogramme;
  }

  getDateExpiration() {
    return this.dateExpiration;
  }

  modifier() :void {
    
  }

  supprimer() :void {
    this.supprimerMoyenPaiement();
  }

  public supprimerMoyenPaiement(){

    var textData: string = "Voulez vous vraiment supprimer ce moyen de paiement ?";

    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: {text: textData},
      width: '450px'
    });

    var validate = false;

    dialogRef.afterClosed().subscribe(result => {
      validate = result;
      
      if(validate){
        this.paymentService.deleteMeanPayment(this.getId()).subscribe(
          response => {
          console.log('moyen de paiement supprimé');
          this.meanPaiementEvent.emit();
          //return response;
        },
        (error) => {
          this.httpError = error;
          console.log('Erreur ! : ' + this.httpError.message);
        });
      }
    });
  }

}
