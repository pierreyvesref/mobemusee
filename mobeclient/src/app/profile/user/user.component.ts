import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { RoleDTO } from '../../dto/role.type';
import { element } from 'protractor';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  @Input() userLogin: string;
  @Input() userMdp: string;
  @Input() userRoles: RoleDTO[];
  @Input() userDateInscription: string;
  @Input() userNom: string;
  @Input() userPrenom: string;
  @Input() userMail: string;
  @Input() index: number;

  constructor(private router: Router) {}

  ngOnInit() {
  }

  getLogin() {
    return this.userLogin;
  }

  getMdp() {
      return "*".repeat(this.userMdp.length)
  }

  getRoles(){
    return this.userRoles
      .map(role => role.value)
      .reduce((a, b) => a + " " + b, "");
  }

  getDateInscription(){
    return this.userDateInscription;
  }

  getNom(){
    return this.userNom;
  }

  getPrenom(){
    return this.userPrenom;
  }

  getMail(){
    return this.userMail;
  }

  modifier(): void {
    this.router.navigate(['/profile']);
  }
}
