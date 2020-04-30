import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import{DialogData} from './dialog-data';
import { TypeCarteDTO } from 'src/app/dto/carte.type';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-mean-payment-dialog',
  templateUrl: './mean-payment-dialog.component.html',
  styleUrls: ['./mean-payment-dialog.component.scss']
})

export class MeanPaymentDialogComponent implements OnInit {

  selectionCartes : SelectCarte[];

  constructor(public dialogRef: MatDialogRef<MeanPaymentDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: DialogData, private formBuilder: FormBuilder,) {}

  ngOnInit(): void {

    this.selectionCartes = [
      {value: new TypeCarteDTO("MASTERCARD"), viewValue: 'MasterCard'},
      {value: new TypeCarteDTO("VISA"), viewValue: 'Visa'},
      {value: new TypeCarteDTO("AUTRE"), viewValue: 'Autre'},
      /*{value: 'MASTERCARD', viewValue: 'MasterCard'},
      {value: 'VISA_ELECTRON', viewValue: 'Visa Electron'},
      {value: 'MAESTRO', viewValue: 'Maestro'}*/
    ];

  }


  formValid(): boolean{
    return this.data.titulaire != undefined && this.data.typeCarte != undefined && this.data.typeCarte != undefined &&
    this.data.numCompte != undefined && this.data.numCompte.toString().length == 16 &&
    this.data.cryptogramme != undefined && this.data.cryptogramme.toString().length == 3
  }


  annuler(): void {
    this.dialogRef.close();
  }

}


interface SelectCarte {
  value: TypeCarteDTO;
  viewValue: string;
}
