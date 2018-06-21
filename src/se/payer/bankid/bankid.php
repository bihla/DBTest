<?php
// {"personalNumber":"196605057253","endUserIp":"94.140.57.181"}
/// {"orderRef":"42921963-e912-4bfc-9ba8-2a876ba46cf1","autoStartToken":"bf5c055c-945d-45c2-84d8-be028e33dad8"}
/// {"errorCode":"alreadyInProgress","details":"Order already in progress for pno"}
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
echo ">>$result<<";
echo "\n errorCode=".$json->errorCode;
echo "\n details=".$json->details;
echo "\n autoStartToken=".$json->autoStartToken;
echo "\n orderRef=".$json->orderRef;
echo "\n ";