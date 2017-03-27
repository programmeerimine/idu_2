package ee.ttu.idu0080.hinnakiri.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "HinnakiriNullFault")
public class HinnakiriNullException extends Exception {
    
	private HinnakiriNullFault faultInfo;

    public HinnakiriNullException() {
    	this("Number is zero");
    }

    public HinnakiriNullException(String message) {
        super(message);
        
        // Set fault message
        faultInfo = new HinnakiriNullFault();
        faultInfo.setMessage(message);
    }
    
    public HinnakiriNullException(String message, HinnakiriNullFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public HinnakiriNullFault getFaultInfo() {
        return faultInfo;
    }
}