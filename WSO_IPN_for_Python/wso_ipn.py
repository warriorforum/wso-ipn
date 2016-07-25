import urllib
import hashlib


class WsoIpn(object):

    '''
        Class constructor takes a dictionary of parameter names and values and
        the secret key as parameters. If secret is not set, set as None.
    '''
    def __init__(self, data, secret_key):
        self.data = data
        self.secret_key = secret_key
        self.valid_actions = ['REFUNDED', 'REVERSED', 'SALE']

    '''
        To validate a WSO IPN:
        1. Check if the request contains WSO_SIGNATURE parameter
        2. Check if the secret key has been set
        3. Check if the sale action is valid
        4. Sort the the parameter map without the signature by key
        5. Encode the sorted map as URL query string
        6. Hash the encoded string appended by the secret key using SHA-1
        7. Once hashed, convert to hexadicemal string and compare with the
            signature from the parameter map
    '''
    def is_valid(self):
        if self.data is None or 'WSO_SIGNATURE' not in self.data:
            return False

        if self.secret_key is None:
            return False

        if not self.is_action_valid():
            return True

        return self.is_signature_valid()

    def get_actual_signature(self):
        data = self.data
        data.pop('WSO_SIGNATURE')
        sorted_data = sorted(data.items(), key=lambda x: x[0])
        encoded_data = urllib.urlencode(sorted_data)
        return hashlib.sha1(encoded_data + self.secret_key).hexdigest()

    def is_signature_valid(self):
        expected_signature = self.data['WSO_SIGNATURE']
        return expected_signature == self.get_actual_signature()

    def is_action_valid(self):
        action = self.data['WSO_SALE_ACTION']
        return action in self.valid_actions

    def get_ipn_data(self):
        return dict(
            SALE_ACTION=self.data['WSO_SALE_ACTION'],
            PRODUCT_NAME=self.data['WSO_PRODUCT_NAME'],
            PRODUCT_ID=self.data['WSO_PRODUCT_ID'],
            CUSTOMER_NAME=self.data['WSO_CUSTOMER_NAME'],
            CUSTOMER_EMAIL=self.data['WSO_CUSTOMER_EMAIL'],
            SALE_AMOUNT=self.data['WSO_SALE_AMOUNT'],
            SALE_CURRENCY=self.data['WSO_SALE_CURRENCY'],
            TRANSACTION_ID=self.data['WSO_TXN_ID'],
            SALE_ID=self.data['WSO_SALE_ID'],
            AFFILIATE_ID=self.data['WSO_AFF_EMAIL'],
            SIGNATURE=self.data['WSO_SIGNATURE']
        )
