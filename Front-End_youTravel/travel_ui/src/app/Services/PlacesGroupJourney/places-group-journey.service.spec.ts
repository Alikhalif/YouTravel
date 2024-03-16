import { TestBed } from '@angular/core/testing';

import { PlacesGroupJourneyService } from './places-group-journey.service';

describe('PlacesGroupJourneyService', () => {
  let service: PlacesGroupJourneyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlacesGroupJourneyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
