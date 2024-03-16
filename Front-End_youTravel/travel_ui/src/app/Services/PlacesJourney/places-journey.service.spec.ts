import { TestBed } from '@angular/core/testing';

import { PlacesJourneyService } from './places-journey.service';

describe('PlacesJourneyService', () => {
  let service: PlacesJourneyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlacesJourneyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
