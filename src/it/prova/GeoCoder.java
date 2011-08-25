package it.prova;

public class GeoCoder {
	
	private static final String YAHOO_API_BASE_URL = "http://maps.googleapis.com/maps/api/geocode/xml?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&sensor=false";


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// String url = String.format(YAHOO_API_BASE_URL); 
		Httpretriever httpRetriever = new Httpretriever();
		
		 String response = httpRetriever.retrievePost("http://cooperazione.sian.it/wspdd/services/DatimaPrezziOsserva");
        // String response = httpRetriever.retrieve("http://www.google.it");
        
        System.out.println(response);
        // return xmlParser.parseXmlResponse(response);


	}

}
