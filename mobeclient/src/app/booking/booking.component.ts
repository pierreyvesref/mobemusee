import { Component, OnInit } from '@angular/core';
import { TypeTarifDTO } from '../dto/tarif.type';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MoyenPaiementDTO } from '../dto/moyen-paiement.dto';
import { PaymentService } from '../services/payment.service';
import { BilletMuseeDTO } from '../dto/billet-musee.dto';
import { TransactionDTO } from '../dto/transaction.dto';
import { TransactionService } from '../services/transaction.service';
import { SuccessFailService } from '../services/success-fail.service';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';
import { element } from 'protractor';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.scss']
})
export class BookingComponent implements OnInit {

  bookingForm: FormGroup;

  dateReservation = new FormControl('', [Validators.required]);
  nbBilletsNormal = new FormControl(0, [Validators.required]);
  nbBilletsJunior = new FormControl(0, [Validators.required]);
  nbBilletsSenor = new FormControl(0, [Validators.required]);
  selectMeansPayment = new FormControl('', [Validators.required]);

  total: number;
  moyensPaiement: MoyenPaiementDTO[];

  progressBarShowed: boolean;
  httpError: HttpErrorResponse;

  constructor(private formBuilder: FormBuilder, private paymentService: PaymentService, private transactionService : TransactionService, 
    private router: Router, private successFailService : SuccessFailService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.initForm();
    this.refreshTotal();
    this.subscribeMeansPayment();
    this.progressBarShowed = false;
  }

  initForm() {
    this.bookingForm = this.formBuilder.group({
      dateReservation: null,
      nbBilletsNormal: 0,
      nbBilletsJunior: 0,
      nbBilletsSenor: 0,
      selectMeansPayment: null
    });
  }

  formValid(): boolean{
    return !this.dateReservation.hasError('required') && !this.nbBilletsNormal.hasError('required') &&
    !this.nbBilletsJunior.hasError('required') && !this.nbBilletsSenor.hasError('required') && !this.selectMeansPayment.hasError('required') && 
    this.total > 0;
  }

  getErrorMessage(field: string) {
    switch(field) { 
      case 'dateReservation': { 
        if (this.dateReservation.hasError('required')) {
          return 'Vous devez entrer une valeur';
        }
         break; 
      } 
      case 'selectMeansPayment': { 
        if (this.selectMeansPayment.hasError('required')) {
          return 'Vous devez entrer une valeur';
        }
         break; 
      }
      default: { 
         break; 
      } 
   } 
  }


  public refreshTotal(){
    var totalBilletsNormal = this.nbBilletsNormal.value * 10;
    var totalBilletsJunior = this.nbBilletsJunior.value * 5;
    var totalBilletsSenor = this.nbBilletsSenor.value * 8;
    this.total = totalBilletsNormal + totalBilletsJunior + totalBilletsSenor;
  }

  public subscribeMeansPayment(){
    this.paymentService.getMeansPayment().subscribe(
      (response: MoyenPaiementDTO[]) => {
          this.moyensPaiement = response;
          return response;
      },
      (error) => {
        this.httpError = error;
        console.log('Erreur ! : ' + this.httpError.message);
      }
    );
  }

  public reserver(){

    var textData: string = "Etes-vous sûr de vouloir réserver ?";

    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: {text: textData},
      width: '450px'
    });

    var validate = false;

    dialogRef.afterClosed().subscribe(result => {
      validate = result;

      if(validate){
        var dateReservation = this.dateReservation.value;
        var nbBilletsNormal = this.nbBilletsNormal.value;
        var nbBilletsJunior = this.nbBilletsJunior.value;
        var nbBilletsSenor = this.nbBilletsSenor.value;
        var idMeanPayment = this.selectMeansPayment.value;

        var billetsMusee : BilletMuseeDTO[] = [];

        for (let i = 0; i < nbBilletsNormal; i++) {
          billetsMusee.push(new BilletMuseeDTO(dateReservation, new TypeTarifDTO('Tarif normal')));
        }

        for (let i = 0; i < nbBilletsJunior; i++) {
          billetsMusee.push(new BilletMuseeDTO(dateReservation, new TypeTarifDTO('Tarif junior')));
        }

        for (let i = 0; i < nbBilletsSenor; i++) {
          billetsMusee.push(new BilletMuseeDTO(dateReservation, new TypeTarifDTO('Tarif senior')));
        }

        var transaction = new TransactionDTO(billetsMusee);

        this.transactionService.postTransaction(transaction, idMeanPayment).subscribe(
          response => {
            this.progressBarShowed = true;
            setTimeout(
              () => {
                console.log('transaction effectuée');
                this.progressBarShowed = false;
                this.successFailService.setMessage = "Merci pour votre achat !";
                this.successFailService.setSubMessage = "Vous trouverez vos billets dans l'onglet Transactions de votre profil.\nN'oubliez pas de les télécharger !";
                this.router.navigate(['/successFail'])
              }, 3000
            );
        },
        (error) => {
          this.httpError = error;
          console.log('Erreur ! : ' + this.httpError.message);
          this.successFailService.setMessage = "La transaction a échouée.";
          this.successFailService.setSubMessage = this.httpError.error;
          this.router.navigate(['/successFail'])
        });
      }
    });
  }

}