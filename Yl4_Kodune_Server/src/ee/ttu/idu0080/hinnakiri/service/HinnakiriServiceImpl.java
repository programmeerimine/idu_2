/**
 * Hinnakirja teenus
 */

package ee.ttu.idu0080.hinnakiri.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import ee.ttu.idu0080.hinnakiri.exceptions.*;
import ee.ttu.idu0080.hinnakiri.types.GetHinnakiriResponse;
import ee.ttu.idu0080.hinnakiri.types.Hind;
import ee.ttu.idu0080.hinnakiri.types.Hinnakiri;
import ee.ttu.idu0080.hinnakiri.types.Toode;

/**
 * This class was generated by Apache CXF 2.2.6 Thu Mar 04 20:52:32 EET 2010
 * Generated source version: 2.2.6
 * 
 */

@javax.jws.WebService(serviceName = "HinnakiriService", portName = "HinnakiriPort", targetNamespace = "http://www.ttu.ee/idu0080/hinnakiri/wsdl/1.0", endpointInterface = "ee.ttu.idu0080.hinnakiri.service.HinnakiriService")
public class HinnakiriServiceImpl implements HinnakiriService {

	private static final Logger logger = Logger
			.getLogger(HinnakiriServiceImpl.class.getName());

	public GetHinnakiriResponse getHinnakiri(java.lang.String parameters) 
	throws HinnakiriNumberFormatException, HinnakiriNullException, HinnakiriNegativeException, HinnakiriDecimalException {
		logger.info("Executing operation getHinnakiri");
		
		Double maximumPrice;
		
		try {
			maximumPrice = Double.parseDouble(parameters);
		} catch (NumberFormatException e) {
			throw new HinnakiriNumberFormatException();
		}
		
		if(maximumPrice == 0){
			throw new HinnakiriNullException();
		}
		else if(maximumPrice < 0){
			throw new HinnakiriNegativeException();
		}
		
		//http://stackoverflow.com/questions/6264576/number-of-decimal-digits-in-a-double
		String text = Double.toString(Math.abs(maximumPrice));
		int integerPlaces = text.indexOf('.');
		int decimalPlaces = text.length() - integerPlaces - 1;
		//------------------------------------------------------------------------------
		
		if(decimalPlaces > 2){
			throw new HinnakiriDecimalException();
		}
		
		
		
		try {
			GetHinnakiriResponse response = new GetHinnakiriResponse();
			Hinnakiri hinnakiri = new Hinnakiri();
			
			List<Hinnakiri.HinnakirjaRida> hinnakirjaRidaList = new ArrayList<Hinnakiri.HinnakirjaRida>();
			
			if(maximumPrice >= 100.00)
				hinnakirjaRidaList.add(createHinnakirjaRida(1, "Tuhkur", "100.00", "EEK"));
			if(maximumPrice >= 10.00)
				hinnakirjaRidaList.add(createHinnakirjaRida(2, "Nugis", "10.00", "EUR"));
			if(maximumPrice >= 923.00)
				hinnakirjaRidaList.add(createHinnakirjaRida(3, "Kobras", "923.00", "RBL"));
			if(maximumPrice >= 89.00)
				hinnakirjaRidaList.add(createHinnakirjaRida(4, "Kakaduu", "89.00", "USD"));

			hinnakiri.getHinnakirjaRida().addAll(hinnakirjaRidaList);

			response.setHinnakiri(hinnakiri);
			
			return response;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	private Hinnakiri.HinnakirjaRida createHinnakirjaRida(int tooteKood, String tooteNimetus, String tooteHind, String valuuta) {
		Hinnakiri.HinnakirjaRida hinnakirjaRida = new Hinnakiri.HinnakirjaRida();
		
		Toode toode = new Toode();
		toode.setKood(tooteKood);
		toode.setNimetus(tooteNimetus);
		hinnakirjaRida.setToode(toode);
		
		Hind hind = new Hind();
		hind.setSumma(new BigDecimal(tooteHind));
		hind.setValuuta(valuuta);
		hinnakirjaRida.setHind(hind);
		
		return hinnakirjaRida;
	}

}
