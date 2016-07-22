import unittest
from wso_ipn import WsoIpn


class TestWsoIpn(unittest.TestCase):

    def test_valid_ipn(self):
        data = dict(
                WP_BUYER_NAME="Neil calabroso",
                WSO_SIGNATURE="1d9ce78c2778ecf795a93009a09f8102dd4ef38a",
                WP_ITEM_NAME="Welcome to the Vault",
                SHIPTONAME="Neil calabroso",
                WSO_PRODUCT_ID="wp_product_3",
                WP_AFFID="",
                WSO_SALE_CURRENCY="USD",
                EMAIL="nn+paypal@freelancer.com",
                WP_ITEM_NUMBER="wp_product_3",
                PAYMENTSTATUS="COMPLETED",
                WP_BUYER_EMAIL="nn+paypal@freelancer.com",
                WSO_PRODUCT_NAME="Welcome to the Vault",
                WP_SALEID="wp_sale_3",
                AMT="43",
                WSO_CUSTOMER_EMAIL="nn+paypal@freelancer.com",
                WSO_SALE_ACTION="SALE",
                WP_SALE_AMOUNT="43",
                WSO_TXN_ID="35",
                saleid="wp_sale_3",
                WSO_AFF_AMOUNT="0.00",
                WP_ACTION="sale",
                WSO_IPN_VERSION="1",
                WP_TXNID="35",
                WSO_SALE_ID="wp_sale_3",
                payer_email="nn+paypal@freelancer.com",
                WP_SALE_CURRENCY="USD",
                WSO_SALE_AMOUNT="43",
                WSO_AFF_EMAIL="",
                TRANSACTIONID="35",
                WSO_CUSTOMER_NAME="Neil calabroso",
        )

        ipn = WsoIpn(data, "secret")
        self.assertTrue(ipn.is_valid())

    def test_invalid_secret_key(self):
        data = dict(
                WP_BUYER_NAME="Neil calabroso",
                WSO_SIGNATURE="1d9ce78c2778ecf795a93009a09f8102dd4ef38a",
                WP_ITEM_NAME="Welcome to the Vault",
                SHIPTONAME="Neil calabroso",
                WSO_PRODUCT_ID="wp_product_3",
                WP_AFFID="",
                WSO_SALE_CURRENCY="USD",
                EMAIL="nn+paypal@freelancer.com",
                WP_ITEM_NUMBER="wp_product_3",
                PAYMENTSTATUS="COMPLETED",
                WP_BUYER_EMAIL="nn+paypal@freelancer.com",
                WSO_PRODUCT_NAME="Welcome to the Vault",
                WP_SALEID="wp_sale_3",
                AMT="43",
                WSO_CUSTOMER_EMAIL="nn+paypal@freelancer.com",
                WSO_SALE_ACTION="SALE",
                WP_SALE_AMOUNT="43",
                WSO_TXN_ID="35",
                saleid="wp_sale_3",
                WSO_AFF_AMOUNT="0.00",
                WP_ACTION="sale",
                WSO_IPN_VERSION="1",
                WP_TXNID="35",
                WSO_SALE_ID="wp_sale_3",
                payer_email="nn+paypal@freelancer.com",
                WP_SALE_CURRENCY="USD",
                WSO_SALE_AMOUNT="43",
                WSO_AFF_EMAIL="",
                TRANSACTIONID="35",
                WSO_CUSTOMER_NAME="Neil calabroso",
        )

        ipn = WsoIpn(data, "invalid_secret")
        self.assertFalse(ipn.is_valid())

    def test_invalid_signature(self):
        data = dict(
                WP_BUYER_NAME="Neil calabroso",
                WSO_SIGNATURE="123",
                WP_ITEM_NAME="Welcome to the Vault",
                SHIPTONAME="Neil calabroso",
                WSO_PRODUCT_ID="wp_product_3",
                WP_AFFID="",
                WSO_SALE_CURRENCY="USD",
                EMAIL="nn+paypal@freelancer.com",
                WP_ITEM_NUMBER="wp_product_3",
                PAYMENTSTATUS="COMPLETED",
                WP_BUYER_EMAIL="nn+paypal@freelancer.com",
                WSO_PRODUCT_NAME="Welcome to the Vault",
                WP_SALEID="wp_sale_3",
                AMT="43",
                WSO_CUSTOMER_EMAIL="nn+paypal@freelancer.com",
                WSO_SALE_ACTION="SALE",
                WP_SALE_AMOUNT="43",
                WSO_TXN_ID="35",
                saleid="wp_sale_3",
                WSO_AFF_AMOUNT="0.00",
                WP_ACTION="sale",
                WSO_IPN_VERSION="1",
                WP_TXNID="35",
                WSO_SALE_ID="wp_sale_3",
                payer_email="nn+paypal@freelancer.com",
                WP_SALE_CURRENCY="USD",
                WSO_SALE_AMOUNT="43",
                WSO_AFF_EMAIL="",
                TRANSACTIONID="35",
                WSO_CUSTOMER_NAME="Neil calabroso",
        )
        
        ipn = WsoIpn(data, "secret")
        self.assertFalse(ipn.is_valid())


if __name__ == '__main__':
    unittest.main()
