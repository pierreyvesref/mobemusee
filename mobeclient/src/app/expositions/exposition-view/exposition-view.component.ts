import { Component, OnInit } from '@angular/core';
import { ExpositionDTO } from 'src/app/dto/exposition.dto';
import { ExpositionService } from 'src/app/services/exposition.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-exposition-view',
  templateUrl: './exposition-view.component.html',
  styleUrls: ['./exposition-view.component.scss']
})
export class ExpositionViewComponent implements OnInit {
  httpError: HttpErrorResponse;

  constructor(private expositionService: ExpositionService) { }

  expositions : ExpositionDTO[];

  ngOnInit(): void {
    this.subscribeExpositions();
  }

  public subscribeExpositions(){
    this.expositionService.getAllExpos().subscribe(
      (response: ExpositionDTO[]) => {
          this.expositions = response;
          return response;
      },
      (error) => {
        this.httpError = error;
        console.log('Erreur ! : ' + this.httpError.message);
      }
    );
  }

}
