package adapterpattern.adapters;

import java.time.LocalDate;
import java.util.List;

import adapterpattern.adaptees.RegionCapitalAdaptee;
import adapterpattern.model.Diagnosis;

public class RegionCapitalAdapter implements DiagnosisRetrieverAdapter {

		@Override
		public List<Diagnosis> getDiagnoses(String socialSecurityNumber) {
			
			//Adapt to region capital way of asking for diagnoses within date interval
			LocalDate fromDate = LocalDate.of(1800, 1,1);
			LocalDate toDate = LocalDate.of(3000,1,1);
			return new RegionCapitalAdaptee().getDiagnosesInInterval(socialSecurityNumber, fromDate, toDate);
		}
	}
