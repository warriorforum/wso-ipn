package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import ipn.NoAffiliateException;
import ipn.NoSignatureException;
import ipn.WsoIpn;

public class WsoIpnTest {

	@Test
	public void testValidIPN() {
		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("WP_BUYER_NAME","Neil calabroso");
		paramMap.put("WSO_SIGNATURE","1d9ce78c2778ecf795a93009a09f8102dd4ef38a");
		paramMap.put("WP_ITEM_NAME","Welcome to the Vault");
		paramMap.put("SHIPTONAME","Neil calabroso");
		paramMap.put("WSO_PRODUCT_ID","wp_product_3");
		paramMap.put("WP_AFFID","");
		paramMap.put("WSO_SALE_CURRENCY","USD");
		paramMap.put("EMAIL","nn+paypal@freelancer.com");
		paramMap.put("WP_ITEM_NUMBER","wp_product_3");
		paramMap.put("PAYMENTSTATUS","COMPLETED");
		paramMap.put("WP_BUYER_EMAIL","nn+paypal@freelancer.com");
		paramMap.put("WSO_PRODUCT_NAME","Welcome to the Vault");
		paramMap.put("WP_SALEID","wp_sale_3");
		paramMap.put("AMT","43");
		paramMap.put("WSO_CUSTOMER_EMAIL","nn+paypal@freelancer.com");
		paramMap.put("WSO_SALE_ACTION","SALE");
		paramMap.put("WP_SALE_AMOUNT","43");
		paramMap.put("WSO_TXN_ID","35");
		paramMap.put("saleid","wp_sale_3");
		paramMap.put("WSO_AFF_AMOUNT","0.00");
		paramMap.put("WP_ACTION","sale");
		paramMap.put("WSO_IPN_VERSION","1");
		paramMap.put("WP_TXNID","35");
		paramMap.put("WSO_SALE_ID","wp_sale_3");
		paramMap.put("payer_email","nn+paypal@freelancer.com");
		paramMap.put("WP_SALE_CURRENCY","USD");
		paramMap.put("WSO_SALE_AMOUNT","43");
		paramMap.put("WSO_AFF_EMAIL","");
		paramMap.put("TRANSACTIONID","35");
		paramMap.put("WSO_CUSTOMER_NAME","Neil calabroso");
		String secretKey = "secret";
		WsoIpn ipn = new WsoIpn(paramMap, secretKey);
		assertTrue(ipn.isValid());
	}
	
	@Test
	public void testInvalidSignature() {
		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("WSO_SIGNATURE","123");
		paramMap.put("WP_BUYER_NAME","Neil calabroso");
		paramMap.put("WP_ITEM_NAME","Welcome to the Vault");
		paramMap.put("SHIPTONAME","Neil calabroso");
		paramMap.put("WSO_PRODUCT_ID","wp_product_3");
		paramMap.put("WP_AFFID","");
		paramMap.put("WSO_SALE_CURRENCY","USD");
		paramMap.put("EMAIL","nn+paypal@freelancer.com");
		paramMap.put("WP_ITEM_NUMBER","wp_product_3");
		paramMap.put("PAYMENTSTATUS","COMPLETED");
		paramMap.put("WP_BUYER_EMAIL","nn+paypal@freelancer.com");
		paramMap.put("WSO_PRODUCT_NAME","Welcome to the Vault");
		paramMap.put("WP_SALEID","wp_sale_3");
		paramMap.put("AMT","43");
		paramMap.put("WSO_CUSTOMER_EMAIL","nn+paypal@freelancer.com");
		paramMap.put("WSO_SALE_ACTION","SALE");
		paramMap.put("WP_SALE_AMOUNT","43");
		paramMap.put("WSO_TXN_ID","35");
		paramMap.put("saleid","wp_sale_3");
		paramMap.put("WSO_AFF_AMOUNT","0.00");
		paramMap.put("WP_ACTION","sale");
		paramMap.put("WSO_IPN_VERSION","1");
		paramMap.put("WP_TXNID","35");
		paramMap.put("WSO_SALE_ID","wp_sale_3");
		paramMap.put("payer_email","nn+paypal@freelancer.com");
		paramMap.put("WP_SALE_CURRENCY","USD");
		paramMap.put("WSO_SALE_AMOUNT","43");
		paramMap.put("WSO_AFF_EMAIL","");
		paramMap.put("TRANSACTIONID","35");
		paramMap.put("WSO_CUSTOMER_NAME","Neil calabroso");
		String secretKey = "secret";
		WsoIpn ipn = new WsoIpn(paramMap, secretKey);
		assertFalse(ipn.isValid());
	}
	
	@Test
	public void testInvalidSecret() {
		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("WP_BUYER_NAME","Neil calabroso");
		paramMap.put("WSO_SIGNATURE","1d9ce78c2778ecf795a93009a09f8102dd4ef38a");
		paramMap.put("WP_ITEM_NAME","Welcome to the Vault");
		paramMap.put("SHIPTONAME","Neil calabroso");
		paramMap.put("WSO_PRODUCT_ID","wp_product_3");
		paramMap.put("WP_AFFID","");
		paramMap.put("WSO_SALE_CURRENCY","USD");
		paramMap.put("EMAIL","nn+paypal@freelancer.com");
		paramMap.put("WP_ITEM_NUMBER","wp_product_3");
		paramMap.put("PAYMENTSTATUS","COMPLETED");
		paramMap.put("WP_BUYER_EMAIL","nn+paypal@freelancer.com");
		paramMap.put("WSO_PRODUCT_NAME","Welcome to the Vault");
		paramMap.put("WP_SALEID","wp_sale_3");
		paramMap.put("AMT","43");
		paramMap.put("WSO_CUSTOMER_EMAIL","nn+paypal@freelancer.com");
		paramMap.put("WSO_SALE_ACTION","SALE");
		paramMap.put("WP_SALE_AMOUNT","43");
		paramMap.put("WSO_TXN_ID","35");
		paramMap.put("saleid","wp_sale_3");
		paramMap.put("WSO_AFF_AMOUNT","0.00");
		paramMap.put("WP_ACTION","sale");
		paramMap.put("WSO_IPN_VERSION","1");
		paramMap.put("WP_TXNID","35");
		paramMap.put("WSO_SALE_ID","wp_sale_3");
		paramMap.put("payer_email","nn+paypal@freelancer.com");
		paramMap.put("WP_SALE_CURRENCY","USD");
		paramMap.put("WSO_SALE_AMOUNT","43");
		paramMap.put("WSO_AFF_EMAIL","");
		paramMap.put("TRANSACTIONID","35");
		paramMap.put("WSO_CUSTOMER_NAME","Neil calabroso");
		String secretKey = "INVALID SECRET KEY";
		WsoIpn ipn = new WsoIpn(paramMap, secretKey);
		assertFalse(ipn.isValid());
	}
	
	@Test
	public void testSecretNotSet() {
		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("WSO_SIGNATURE","1d9ce78c2778ecf795a93009a09f8102dd4ef38a");

		WsoIpn ipn = new WsoIpn(paramMap);
		assertFalse(ipn.isValid());
	}
	
	@Test(expected=NoSignatureException.class)
	public void testNoSignature() {
		Map<String, String> paramMap = new HashMap<String,String>();
		String secretKey = "secret";
		WsoIpn ipn = new WsoIpn(paramMap, secretKey);
		assertFalse(ipn.isValid());
	}	
	
	
	@Test
	public void testNoAffiliate(){
		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("WSO_AFF_AMOUNT","0.00");
		paramMap.put("WSO_AFF_EMAIL","");
		WsoIpn ipn = new WsoIpn(paramMap);
		assertFalse(ipn.hasAffiliate());
	}
	
	
	@Test
	public void testHasAffiliate(){
		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("WSO_AFF_AMOUNT","100.00");
		paramMap.put("WSO_AFF_EMAIL","John Doe");
		WsoIpn ipn = new WsoIpn(paramMap);
		assertTrue(ipn.hasAffiliate());
	}
	
	@Test(expected=NoAffiliateException.class)
	public void testGetAffiliateEmail(){
		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("WSO_AFF_AMOUNT","0.00");
		paramMap.put("WSO_AFF_EMAIL","");
		WsoIpn ipn = new WsoIpn(paramMap);
		ipn.getAffiliateEmail();
		fail();
	}
	
	@Test(expected=NoAffiliateException.class)
	public void testGetAffiliateCommission(){
		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("WSO_AFF_AMOUNT","0.00");
		paramMap.put("WSO_AFF_EMAIL","");
		WsoIpn ipn = new WsoIpn(paramMap);
		ipn.getAffiliateCommission();
		fail();
	}
	
	

}
