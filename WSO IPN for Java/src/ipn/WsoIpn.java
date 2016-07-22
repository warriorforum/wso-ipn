package ipn;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class WsoIpn {
	
	private enum SaleAction{
		REFUNDED, REVERSED, SALE
	}
	
	private Map<String,String> ipnData = new HashMap<String, String>();
	private final String secretKey;
	

	//Takes the parameter map of IPN and the secret key as parameters
	public WsoIpn(Map<String, String> parameterMap, String secretKey){
		ipnData.putAll(parameterMap);
		this.secretKey = secretKey;
	}
	
	//Takes the parameter map of the IPN
	public WsoIpn(Map<String, String> parameterMap){
		ipnData.putAll(parameterMap);
		this.secretKey = null;
	}
	
	//To validate a WSO IPN:
	//1. Check if the request contains WSO_SIGNATURE parameter
	//2. Check if the secret key has been set
	//3. Check if the sale action is valid
	//4. Sort the the parameter map without the signature by key
	//5. Encode the sorted map as URL query string
	//6. Hash the encoded string appended by the secret key using SHA-1
	//7. Once hashed, convert to hexadicemal string and compare with the signature from the parameter map
	public boolean isValid(){
		if(!ipnData.containsKey("WSO_SIGNATURE")){
			return false;
		}
		if(secretKey == null){
			return false;
		}
		if(!isActionValid()){
			return false;
		}
		String expectedSignature = ipnData.get("WSO_SIGNATURE");
		Map<String,String> sortedMap = new TreeMap<String,String>(ipnData);
		sortedMap.remove("WSO_SIGNATURE");
		String encodedData = Encoder.urlEncodeUTF8(sortedMap);
		String actualignature = HashLib.sha1Hash(encodedData + secretKey);
		return expectedSignature.equals(actualignature);
	}

	private boolean isActionValid(){
		try{
			SaleAction.valueOf(ipnData.get("WSO_SALE_ACTION"));
		}catch(IllegalArgumentException e){
			return false;
		}catch(NullPointerException e){
			return false;
		}
		return true;
	}
	
	public String getAction(){
		return ipnData.get("WSO_SALE_ACTION");
	}
	
	public String getCustomerName(){
		return ipnData.get("WSO_CUSTOMER_NAME");
	}
	
	public String getProduct(){
		return ipnData.get("WSO_PRODUCT_NAME");
	}
	
	public String getCustomerEmail(){
		return ipnData.get("WSO_CUSTOMER_EMAIL");
	}
	
	public String getProductId(){
		return ipnData.get("WSO_PRODUCT_ID");
	}
	
	public String getSaleId(){
		return ipnData.get("WSO_SALE_ID");
	}
	
	public String getSaleAmount(){
		return ipnData.get("WSO_SALE_AMOUNT");
	}
	
	public String getSaleCurrency(){
		return ipnData.get("WSO_SALE_CURRENCY");
	}
	
	public String getTransactionId(){
		return ipnData.get("WSO_TXN_ID");
	}
	
	public boolean hasAffiliate(){
		String affiliateEmail = ipnData.get("WSO_AFF_EMAIL");
		return affiliateEmail != null && !affiliateEmail.equals("");
	}
	
	public String getAffiliateEmail(){
		if(!hasAffiliate()){
			throw new NoAffiliateException("Transaction has no affiliate.");
		}
		return ipnData.get("WSO_AFF_EMAIL");
	}
	
	public String getAffiliateCommission(){
		if(!hasAffiliate()){
			throw new NoAffiliateException("Transaction has no affiliate.");
		}
		return ipnData.get("WSO_AFF_AMOUNT");
	}
	
	public Map<String, String> getIpnMap(){
		Map<String, String> map = new HashMap<String,String>();
		for(Map.Entry<String, String> entry:ipnData.entrySet()){
			if(entry.getKey().startsWith("WSO_")){
				map.put(entry.getKey(), entry.getValue());
			}
		}
		return map;
	}
	
}
