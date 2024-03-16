import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListJourneyComponent } from './list-journey.component';

describe('ListJourneyComponent', () => {
  let component: ListJourneyComponent;
  let fixture: ComponentFixture<ListJourneyComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListJourneyComponent]
    });
    fixture = TestBed.createComponent(ListJourneyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
