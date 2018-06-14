<?php
// {"personalNumber":"196605057253","endUserIp":"94.140.57.181"}
$data = array(
    "personalNumber" => "196605057253",
    "endUserIp" => "94.140.57.181"
);
$json_string = json_encode($data);

$handle = curl_init();
$options = array(
    CURLOPT_CUSTOMREQUEST => 'POST',
    CURLOPT_POSTFIELDS => $json_string,
    CURLOPT_HEADER => false,
    CURLOPT_HTTPHEADER => array(
        'Content-Type:application/json',
        'Content-Length: ' . strlen($json_string)
    ),
    CURLOPT_RETURNTRANSFER => true,
    CURLOPT_SSL_VERIFYHOST => '0',
    CURLOPT_SSL_VERIFYPEER => '1',
    CURLOPT_CAINFO => '/Users/bihla/git/DBTest/DBTest/src/se/payer/bankid/bankid_CA_test.cer',
    CURLOPT_SSLCERT => '/Users/bihla/git/DBTest/DBTest/src/se/payer/bankid/FPTestcert2.p12',
    CURLOPT_SSLKEYPASSWD => 'qwerty123',
    CURLOPT_USERAGENT => 'Mozilla/4.0 (compatible; MSIE 5.01; Windows NT 5.0)',
    CURLOPT_VERBOSE => false,
    CURLOPT_URL => 'https://appapi2.test.bankid.com/rp/v5/auth'
);

curl_setopt_array($handle, $options);
$result = curl_exec($handle);
if (curl_errno($handle)) {
    echo 'Error: ' . curl_error($handle);
}
curl_close($handle);
$json = json_decode($result);
echo "\n errorCode=".$json->errorCode;
echo "\n details=".$json->details;
echo "\n autoStartToken=".$json->autoStartToken;
echo "\n orderRef=".$json->orderRef;
echo "\n ";