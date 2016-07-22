from wso_ipn import WsoIpn
from flask import Flask, request
import urlparse
app = Flask(__name__)


@app.route('/python-ipn/ipn-listener', methods=['POST'])
def hello():
    request_data = request.get_data()
    data = {
        key: value for (key, value) in urlparse.parse_qsl(request_data, keep_blank_values=True)
    }
    
    ipn = WsoIpn(data, "secret") # $ipn = WsoIpn(data,None);  use when secret is not set
    data = ipn.get_ipn_data()
    for key, value in data.items():
        print '%s --- %s' % (key, value)

    if ipn.is_valid():
        return "VALID IPN!"
    else:
        return "INVALID IPN!"

if __name__ == "__main__":
    app.run(host='0.0.0.0')
