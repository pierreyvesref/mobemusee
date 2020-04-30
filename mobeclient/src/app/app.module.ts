import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { MeansPaymentViewComponent } from './profile/payment/means-payment-view/means-payment-view.component';

import { AuthService } from './services/auth.service';
import { PaymentService } from './services/payment.service';
import { TransactionService } from './services/transaction.service';
import { AuthGuard } from './services/auth-guard.service'

import { AuthComponent } from './auth/auth.component';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from './profile/user/user.component';
import { HttpClientModule } from '@angular/common/http';
import { ProfileComponent } from './profile/profile.component';
import { ProfileService } from './services/profile.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ExpositionService } from './services/exposition.service'

import { MeanPaymentComponent } from './profile/payment/mean-payment/mean-payment.component';
import { CreditCardMaskPipe } from './pipes/credit-card-mask.pipe';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MeanPaymentDialogComponent } from './profile/payment/means-payment-view/mean-payment-dialog/mean-payment-dialog.component';
import { TransactionComponent } from './profile/transaction-history/transaction/transaction.component';
import { TicketComponent } from './profile/transaction-history/ticket/ticket.component';
import { TransactionsViewComponent } from './profile/transaction-history/transactions-view/transactions-view.component';
import { BookingComponent } from './booking/booking.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { AccountCreationComponent } from './auth/account-creation/account-creation.component';
import { SuccessFailComponent } from './success-fail/success-fail.component';
import { SuccessFailService } from './services/success-fail.service';

import {MatButtonModule} from '@angular/material/button';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatCardModule} from '@angular/material/card';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatDialogModule} from '@angular/material/dialog';
import {MatDividerModule} from '@angular/material/divider';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatInputModule} from '@angular/material/input';
import {MatListModule} from '@angular/material/list';
import {MatMenuModule} from '@angular/material/menu';
import {MatNativeDateModule, MatRippleModule} from '@angular/material/core';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatRadioModule} from '@angular/material/radio';
import {MatSelectModule} from '@angular/material/select';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';

import {MatTabsModule} from '@angular/material/tabs';
import {MatToolbarModule} from '@angular/material/toolbar';
import { ConfirmationDialogComponent } from './confirmation-dialog/confirmation-dialog.component';
import { ExpositionComponent } from './expositions/exposition/exposition.component';
import { ExpositionViewComponent } from './expositions/exposition-view/exposition-view.component';

/*import {A11yModule} from '@angular/cdk/a11y';
import {ClipboardModule} from '@angular/cdk/clipboard';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {PortalModule} from '@angular/cdk/portal';
import {ScrollingModule} from '@angular/cdk/scrolling';
import {CdkStepperModule} from '@angular/cdk/stepper';
import {CdkTableModule} from '@angular/cdk/table';
import {CdkTreeModule} from '@angular/cdk/tree';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatBadgeModule} from '@angular/material/badge';
import {MatBottomSheetModule} from '@angular/material/bottom-sheet';
import {MatChipsModule} from '@angular/material/chips';
import {MatStepperModule} from '@angular/material/stepper';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatIconModule} from '@angular/material/icon';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatSliderModule} from '@angular/material/slider';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatSortModule} from '@angular/material/sort';
import {MatTableModule} from '@angular/material/table';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatTreeModule} from '@angular/material/tree';*/


const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'profile', canActivate: [AuthGuard], component: ProfileComponent },
  { path: 'expo',canActivate: [AuthGuard], component: ExpositionViewComponent },
  { path: 'auth', component: AuthComponent },
  { path: 'booking', canActivate: [AuthGuard], component: BookingComponent },
  { path: 'about', component: AboutComponent },
  { path: 'account', component: AccountCreationComponent },
  { path: 'successFail', component: SuccessFailComponent },


];

@NgModule({
  declarations: [AppComponent, AuthComponent, UserComponent, ProfileComponent, MeanPaymentComponent, CreditCardMaskPipe, 
    MeansPaymentViewComponent, MeanPaymentDialogComponent, TransactionComponent, TicketComponent, TransactionsViewComponent, BookingComponent, HomeComponent, HomeComponent,
    AboutComponent, AccountCreationComponent, SuccessFailComponent, ConfirmationDialogComponent, ExpositionComponent, ExpositionViewComponent, ],
  imports: [BrowserModule, FormsModule, ReactiveFormsModule, HttpClientModule, RouterModule.forRoot(appRoutes), BrowserAnimationsModule, MatToolbarModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatProgressBarModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSlideToggleModule,
    MatTabsModule,
    MatToolbarModule,

    /*A11yModule,
    ClipboardModule,
    CdkStepperModule,
    CdkTableModule,
    CdkTreeModule,
    DragDropModule,
    MatAutocompleteModule,
    MatBadgeModule,
    MatBottomSheetModule,
    MatChipsModule,
    MatStepperModule,
    MatGridListModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatPaginatorModule,
    MatTooltipModule,
    MatTreeModule,
    PortalModule,
    ScrollingModule,
    MatSliderModule,*/

  ],
  providers: [ AuthService, ProfileService, ExpositionService, PaymentService, TransactionService, SuccessFailService, AuthGuard],
  bootstrap: [AppComponent],
})



export class AppModule {}
