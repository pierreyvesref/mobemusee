import { Component, OnInit, Input } from '@angular/core';
import { TransactionDTO } from 'src/app/dto/transaction.dto';
import { ProfileService } from 'src/app/services/profile.service';
import { BilletMuseeDTO } from 'src/app/dto/billet-musee.dto';
import { MoyenPaiementDTO } from 'src/app/dto/moyen-paiement.dto';
import * as jsPDF from 'jspdf'
import * as qrcode from 'qrcode';
import { stringify } from 'querystring';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss']
})
export class TransactionComponent implements OnInit {

  @Input() dateTransaction: Date;
  @Input() montant: number;
  @Input() billetsMusee: BilletMuseeDTO[];
  @Input() moyenPaiement: MoyenPaiementDTO;
  

  constructor(private profileService: ProfileService) {  }

  ngOnInit(): void {
  }

  getDateTransaction() {
    return this.dateTransaction;
  }

  getMontant(){
    return this.montant;
  }

  getBilletsMusee(){
    return this.billetsMusee;
  }

  getMoyenPaiementNum(){
    return this.moyenPaiement.numCompte;
  }

  loadImage(url) {
    return new Promise((resolve) => {
      let img = new Image();
      img.onload = () => resolve(img);
      img.src = url;
    })
  }
  
  createQRCode(keyCode: string){
    //var QRCode = require('qrcode');
    /*qrcode.toFile('/home/smartdisplay/Documents/test.jpg', 'test', function (err) {
      //if (err) throw err
      console.log('done')
    });*/
    var qrCodeUrl = "";

    qrcode.toDataURL(keyCode, function (err, url) {
      qrCodeUrl = url;
    });
    return qrCodeUrl;
  }
  
  

  downloadPDF(){
    this.loadImage('../../assets/images/mobe.jpg').then((logo) => {
      const doc = new jsPDF('p', 'mm', 'a4');
      doc.addImage(logo, 'JPEG', 10, 10, 60, 25);
      var date = new Date(this.billetsMusee[0].dateVisite);
      doc.text('Réservation de billets pour le ' + date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + ' :', 10, 50);

      var compteur = 0;

      var start = 70;

      this.getBilletsMusee().forEach(billet => {
        var startBillet = start + 90*compteur;
        doc.addImage(this.createQRCode(billet.keyQrCode), 'PNG', 10, startBillet, 50, 50);
        doc.text('Tarif : ' + billet.typeTarif.value, 10, startBillet + 60);
        doc.text('Clé du QRcode : ' + billet.keyQrCode, 10, startBillet + 70);
        doc.text('____________________________________________________________', 10, startBillet + 85);
        
        compteur++;

        if(compteur%2 == 0){
          doc.addPage();
          compteur=0;
          start = 20;
        }

      });
  

      doc.save('reservation_mobe.pdf')
    });
  }

}
