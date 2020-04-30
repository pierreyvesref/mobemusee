import { Component, OnInit } from '@angular/core';
import { TransactionDTO } from 'src/app/dto/transaction.dto';
import { ProfileService } from 'src/app/services/profile.service';
import { UserDTO } from 'src/app/dto/user.dto';
import { TransactionService } from 'src/app/services/transaction.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-transactions-view',
  templateUrl: './transactions-view.component.html',
  styleUrls: ['./transactions-view.component.scss']
})
export class TransactionsViewComponent implements OnInit {

  transactions : TransactionDTO[];
  httpError: HttpErrorResponse;

  constructor(private transactionService: TransactionService) {  }

  ngOnInit(): void {
    this.subscribeCurrentUserTransactions();
  }

  public subscribeCurrentUserTransactions(){
    this.transactionService.getTransactions().subscribe(
      (response: TransactionDTO[]) => {
          this.transactions = response;
          return response;
      },
      (error) => {
        this.httpError = error;
        console.log('Erreur ! : ' + this.httpError.message);
      }
    );
  }

}
