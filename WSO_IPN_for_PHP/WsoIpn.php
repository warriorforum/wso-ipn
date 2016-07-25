<?php

class WsoIpn {

    private $ipn_data;
    private $secret_key;
    const SALE_ACTION = array('REFUNDED', 'REVERSED', 'SALE');

    // Takes the array of the IPN values and the secret key
    // Set secret key as null if secret is not set.
    public function __construct($data, $secret_key) {
        $this->ipn_data = $data;
        $this->secret_key = $secret_key;
    }

    // To validate a WSO IPN:
    // 1. Check if the request contains WSO_SIGNATURE parameter
    // 2. Check if the secret key has been set
    // 3. Check if the sale action is valid
    // 4. Sort the the parameter map without the signature by key
    // 5. Encode the sorted map as URL query string
    // 6. Hash the encoded string appended by the secret key using SHA-1
    // 7. Once hashed, convert to hexadicemal string and compare with the signature from the parameter map

    public function is_valid() {
        if (!array_key_exists('WSO_SIGNATURE', $this->ipn_data)) {
            return false;
        }

        if (!$this->is_action_valid()) {
            return false;
        }

        if ($this->secret_key == null) {
            return false;
        }

        return $this->is_signature_valid();
    }

    private function get_actual_signature() {
        $ipn_data = $this->ipn_data;
        unset($ipn_data['WSO_SIGNATURE']);
        ksort($ipn_data);
        $encoded_data = http_build_query($ipn_data);
        return sha1($encoded_data.$this->secret_key);
    }

    private function is_signature_valid() {
        $expected_signature =  $this->ipn_data['WSO_SIGNATURE'];
        return strcmp($expected_signature, $this->get_actual_signature()) == 0;
    }

    private function is_action_valid() {
        $action = $this->ipn_data['WSO_SALE_ACTION'];
        return in_array($action, self::SALE_ACTION);
    }

    public function has_affiliate() {

        $affiliate = $this->ipn_data['WSO_AFF_EMAIL'];
        return $affiliate != null && strcmp($affiliate, '') != 0;
    }

    public function get_ipn_data() {
        return array(
            'SALE_ACTION' => $this->ipn_data['WSO_SALE_ACTION'],
            'PRODUCT_NAME' => $this->ipn_data['WSO_PRODUCT_NAME'],
            'PRODUCT_ID' => $this->ipn_data['WSO_PRODUCT_ID'],
            'CUSTOMER_NAME' => $this->ipn_data['WSO_CUSTOMER_NAME'],
            'CUSTOMER_EMAIL' => $this->ipn_data['WSO_CUSTOMER_EMAIL'],
            'SALE_AMOUNT' => $this->ipn_data['WSO_SALE_AMOUNT'],
            'SALE_CURRENCY' => $this->ipn_data['WSO_SALE_CURRENCY'],
            'TRANSACTION_ID' => $this->ipn_data['WSO_TXN_ID'],
            'SALE_ID' => $this->ipn_data['WSO_SALE_ID'],
            'AFFILIATE_ID' => $this->ipn_data['WSO_AFF_EMAIL'],
            'SIGNATURE' => $this->ipn_data['WSO_SIGNATURE'],
         );
    }

}


