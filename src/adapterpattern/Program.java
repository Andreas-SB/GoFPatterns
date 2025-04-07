package adapterpattern;

import java.time.LocalDate;
import java.util.*;

import adapterpattern.adaptees.*;
import adapterpattern.adapters.DiagnosisRetrieverAdapter;
import adapterpattern.adapters.RegionCapitalAdapter;
import adapterpattern.adapters.RegionMidAdapter;
import adapterpattern.adapters.RegionNorthAdapter;
import adapterpattern.adapters.RegionSouthAdapter;
import adapterpattern.model.Diagnosis;

public class Program {

	public static void main(String[] args) {

		String socialSecurityNumber = "121265-9999";
		List<Diagnosis> diagnoses = new ArrayList<Diagnosis>();

		///////////////////////////////////////////////////
		// OLD, UGLY METHOD ///////////////////////////////

		// In REGION NORTH - we have to ask for diagnoses in a date interval
		LocalDate fromDate = LocalDate.of(1950, 1, 1);
		LocalDate toDate = LocalDate.now();
		diagnoses.addAll(new RegionNorthAdaptee().getDiagnoses(fromDate, toDate, socialSecurityNumber));

		// In REGION MID - we have to ask for specific years, one by one
		RegionMidAdaptee regionMidDiagnosisRetriever = new RegionMidAdaptee();
		for (int year = 1950; year < LocalDate.now().getYear(); year++) {
			diagnoses.addAll(regionMidDiagnosisRetriever.getDiagnoses(year, socialSecurityNumber));
		}

		// In REGION SOUTH - they have a method with a different name
		RegionSouthAdaptee regionSouthDiagnosesRetriever = new RegionSouthAdaptee();
		diagnoses.addAll(regionSouthDiagnosesRetriever.getAllDiagnosesForPatient(socialSecurityNumber));

		// Printing diagnoses //////////////////////////
		for (Diagnosis diagnosis : diagnoses) {
			System.out.println(diagnosis);
		}

		System.out.println();

		diagnoses.clear();
		
		/////////////////////////////////////////////////////
		// NEW, PRETTY METHOD /////////////////////////////

		List<DiagnosisRetrieverAdapter> adapters = new ArrayList<DiagnosisRetrieverAdapter>();
		adapters.add(new RegionNorthAdapter());
		adapters.add(new RegionMidAdapter());
		adapters.add(new RegionSouthAdapter());
		adapters.add(new RegionCapitalAdapter());

		for (DiagnosisRetrieverAdapter adapter : adapters) {
			diagnoses.addAll(adapter.getDiagnoses(socialSecurityNumber));
		}

		// Printing diagnoses //////////////////////////
		for (Diagnosis diagnosis : diagnoses) {
			System.out.println(diagnosis);
		}
	}
}