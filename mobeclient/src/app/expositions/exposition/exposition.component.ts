import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-exposition',
  templateUrl: './exposition.component.html',
  styleUrls: ['./exposition.component.scss']
})
export class ExpositionComponent implements OnInit {

  @Input() idExpo: number;
  @Input() nomExpo: string;
  @Input() dateDebutExpo: Date;
  @Input() dateFinExpo: Date;

  constructor() { }

  ngOnInit(): void {
  }

  getIdExpo() {
    return this.idExpo;
  }


  getNomExpo() {
    return this.nomExpo;
  }

  getDateDebutExpo() {
    return this.dateDebutExpo;
  }

  getDateFinExpo() {
    return this.dateFinExpo;
  }

}
