<?php
// {"personalNumber":"196605057253","endUserIp":"94.140.57.181"}
// / {"orderRef":"42921963-e912-4bfc-9ba8-2a876ba46cf1","autoStartToken":"bf5c055c-945d-45c2-84d8-be028e33dad8"}
// / {"errorCode":"alreadyInProgress","details":"Order already in progress for pno"}
$data = array (
		"personalNumber" => "196605057253",
		// "personalNumber" => "198901166291",
		"endUserIp" => "94.140.57.181"
);
$json_string = json_encode ( $data );

$handle = curl_init ();
$options = array (
		CURLOPT_CUSTOMREQUEST => 'POST',
		CURLOPT_POSTFIELDS => $json_string,
		CURLOPT_HEADER => false,
		CURLOPT_HTTPHEADER => array (
				'Content-Type:application/json',
				'Content-Length: ' . strlen ( $json_string )
		),
		CURLOPT_RETURNTRANSFER => true,
		CURLOPT_SSL_VERIFYHOST => '0',
		CURLOPT_SSL_VERIFYPEER => '0',
		CURLOPT_SSLCERT => '/Users/bihla/git/DBTest/src/se/payer/bankid/PayerClientCertificate.pem',
		CURLOPT_SSLCERTPASSWD => 'qwerty123',
		CURLOPT_USERAGENT => 'Mozilla/4.0 (compatible; MSIE 5.01; Windows NT 5.0)',
		CURLOPT_VERBOSE => false,
		CURLOPT_URL => 'https://appapi2.bankid.com/rp/v5/auth'
);

curl_setopt_array ( $handle, $options );
$result = curl_exec ( $handle );
if (curl_errno ( $handle )) {
	echo 'Error: ' . curl_error ( $handle );
}
// curl_close($handle);
$json = json_decode ( $result );
$url = "https://www.svt.se";
$urle = urlencode ( $url );
echo "\n>>$result<<\n\n";
echo "\n https://app.bankid.com/?autostarttoken={$json->autoStartToken}&redirect=$urle\n";
echo "\n bankid:///?autostarttoken={$json->autoStartToken}&redirect=$urle\n";
mail ( "bjorn@ihlar.com", "BankID", "bankid:///?autostarttoken={$json->autoStartToken}&redirect=$urle" );
echo "\n errorCode=" . $json->errorCode;
echo "\n details=" . $json->details;
echo "\n autoStartToken=" . $json->autoStartToken;
echo "\n orderRef=" . $json->orderRef;
echo "\n ";

$data = array (
		"orderRef" => "$json->orderRef"
);
$json_string = json_encode ( $data );
$options = array (
		CURLOPT_CUSTOMREQUEST => 'POST',
		CURLOPT_POSTFIELDS => $json_string,
		CURLOPT_HEADER => false,
		CURLOPT_HTTPHEADER => array (
				'Content-Type:application/json',
				'Content-Length: ' . strlen ( $json_string )
		),
		CURLOPT_RETURNTRANSFER => true,
		CURLOPT_SSL_VERIFYHOST => '0',
		CURLOPT_SSL_VERIFYPEER => '0',
		CURLOPT_SSLCERT => '/Users/bihla/git/DBTest/src/se/payer/bankid/PayerClientCertificate.pem',
		CURLOPT_SSLCERTPASSWD => 'qwerty123',
		CURLOPT_USERAGENT => 'Mozilla/4.0 (compatible; MSIE 5.01; Windows NT 5.0)',
		CURLOPT_VERBOSE => false,
		CURLOPT_URL => 'https://appapi2.bankid.com/rp/v5/collect'
);
for($i = 0; $i < 10; $i ++) {
	curl_setopt_array ( $handle, $options );
	$result = curl_exec ( $handle );
	if (curl_errno ( $handle )) {
		echo 'Error: ' . curl_error ( $handle );
	}
	var_dump ( $result );
	$json = json_decode ( $result );
	$completionData = $json->completionData;
	$cert = $completionData->cert;
	sleep ( 3 );
	if ($json->status == "complete") {
		echo "cert:notBefore:" . date ( "Y-m-d H:i:s", $cert->notBefore ) . "\n";
		echo "cert:notAfter::" . date ( "Y-m-d H:i:s", $cert->notAfter ) . "\n";
		echo "signature:" . base64_decode ( $completionData->signature ) . "\n";
		// echo "ocspResponse:".base64_decode($completionData->ocspResponse);
		break;
	}
}
/*
 *
 * string(106) "{"orderRef":"66157411-01ae-447f-9337-e341f9b3d034","status":"pending","hintCode":"outstandingTransaction"}"
 * string(92) "{"orderRef":"66157411-01ae-447f-9337-e341f9b3d034","status":"pending","hintCode":"userSign"}"
 * string(14175) "{"orderRef":"66157411-01ae-447f-9337-e341f9b3d034","status":"complete","completionData":
 * {"user":{"personalNumber":"196605057253","name":"Björn Gunnar Ihlar","givenName":"Björn Gunnar","surname":"Ihlar"},
 * "device":{"ipAddress":"213.112.56.123"},
 * "cert":{"notBefore":"1522360800000","notAfter":"1617055199000"},
 * "signature":"PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9Im5vIj8+PFNpZ25hdHVyZSB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnIyI+PFNpZ25lZEluZm8geG1sbnM9Imh0dHA6Ly93d3cudzMub3"...
 * /Users/bihla/git/DBTest/src/se/payer/bankid/bankid.php:77:
 * string(59) "{"errorCode":"invalidParameters","details":"No such order"}"
 */

curl_close ( $handle );