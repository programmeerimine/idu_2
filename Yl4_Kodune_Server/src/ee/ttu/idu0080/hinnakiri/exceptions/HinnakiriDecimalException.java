package ee.ttu.idu0080.hinnakiri.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "HinnakiriDecimalFault")
public class HinnakiriDecimalException extends Exception {
    
	private HinnakiriDecimalFault faultInfo;

    public HinnakiriDecimalException() {
    	this("Number has too many decimal places");
    }

    public HinnakiriDecimalException(String message) {
        super(message);
        
        // Set fault message
        faultInfo = new HinnakiriDecimalFault();
        faultInfo.setMessage(message);
    }
    
    public HinnakiriDecimalException(String message, HinnakiriDecimalFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public HinnakiriDecimalFault getFaultInfo() {
        return faultInfo;
    }
}