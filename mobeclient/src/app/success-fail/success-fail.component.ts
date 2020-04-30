import { Component, OnInit } from '@angular/core';
import { SuccessFailService } from '../services/success-fail.service';

@Component({
  selector: 'app-success-fail',
  templateUrl: './success-fail.component.html',
  styleUrls: ['./success-fail.component.scss']
})
export class SuccessFailComponent implements OnInit {

  constructor(private successFailService: SuccessFailService) { }

  message: string;
  subMessage: string;

  ngOnInit(): void {
    this.message = this.successFailService.getMessage;
    this.subMessage = this.successFailService.getSubMessage;
  }

}
