<?php

include 'WsoIpn.php';

$ipn = new WsoIpn($_POST, 'secret'); // new WsoIpn($_POST, null) is secret  key is not set
print (date('m/d/Y h:i:s a', time()));
if ($ipn->is_valid()) {
    print('  VALID IPN RECEIVED');
} else {
    print('  INVALID IPN RECEIVED');
}

print_r($ipn->get_ipn_data());




