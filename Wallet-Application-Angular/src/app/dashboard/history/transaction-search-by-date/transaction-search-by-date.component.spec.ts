import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionSearchByDateComponent } from './transaction-search-by-date.component';

describe('TransactionSearchByDateComponent', () => {
  let component: TransactionSearchByDateComponent;
  let fixture: ComponentFixture<TransactionSearchByDateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TransactionSearchByDateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TransactionSearchByDateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
