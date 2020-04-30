import { Component, OnInit, Input } from '@angular/core';
import { TypeTarifDTO } from 'src/app/dto/tarif.type';

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.scss']
})
export class TicketComponent implements OnInit {

  @Input() keyQrCode: string;
  @Input() dateVisite: Date;
  @Input() isValide: boolean;
  @Input() typeTarif: TypeTarifDTO;


  constructor() { }

  ngOnInit(): void {
  }
 
  getKeyQrCode() {
    return this.keyQrCode;
  }

  getDateVisite() {
    return this.dateVisite;
  }

  getIsValide() {
    return this.isValide;
  }

  getTypeTarif() {
    return this.typeTarif.value;
  }

}
