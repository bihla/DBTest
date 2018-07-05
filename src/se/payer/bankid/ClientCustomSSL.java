package se.payer.bankid;

import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.MalformedURLException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

/*
 * ====================================================================
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
import java.io.*;

import javax.net.ssl.SSLPeerUnverifiedException;

/**
 * This example demonstrates how to create secure connections with a custom SSL
 * context.
 */
public class ClientCustomSSL {
	protected static final transient Logger logger = Logger.getLogger(ClientCustomSSL.class);
	boolean isTest = false;

	String certPath = "/Users/bihla/git/DBTest/DBTest/src/se/payer/bankid";
	String certFile2 = "FPTestcert2_20150818_102329.pfx";
	String certFile3 = "FPTestcert2.p12";
	String certFile = "NEW.p12";
	String caFile = "bankid_CA_test.cer";

	String clientCertTest = certPath + "/" + certFile;
	String caCert = certPath + "/" + caFile;

	public final static void main(String[] args) throws Exception {

		System.setProperty("javax.net.debug", "ssl");

		ClientCustomSSL ssl = new ClientCustomSSL(true);

		logger.debug("=================== --- ===================");
		ssl.pkcs12();
		// ssl.testIt();
		// ssl.useP12asKeystore();
		// ssl.useBankID();
		// ssl.useStraight("PR_EXAMPLES");
		logger.debug("=================== --- ===================");

	}

	public ClientCustomSSL(boolean isTest) {
		super();
		this.isTest = isTest;
	}

	public SSLSocketFactory createSocketFactory(String websiteId) {
		logger.debug("createSocketFactory(websiteId=" + websiteId + ", isTest=" + isTest + ")...");

		try {
			// #KeyStore
			KeyStore keyStore = null;
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());

			if (isTest) {
				keyStore = KeyStore.getInstance("PKCS12");
				keyStore.load(new FileInputStream("/tmp/Swish Merchant Test Certificate 1231181189.p12"),
						"swish".toCharArray());
				kmf.init(keyStore, "swish".toCharArray());
			} else {
				keyStore = KeyStore.getInstance("JKS");
				keyStore.load(new FileInputStream("/tmp/key." + websiteId + ".jks"), "changeit".toCharArray());
				kmf.init(keyStore, "changeit".toCharArray());
			}

			KeyManager[] kms = kmf.getKeyManagers();

			// #TrustStore
			KeyStore trustStore = KeyStore.getInstance("JKS");
			trustStore.load(new FileInputStream("/tmp/trust." + websiteId + ".jks"), "changeit".toCharArray());

			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(trustStore);
			TrustManager[] tms = tmf.getTrustManagers();

			// #SSLContext
			SSLContext sslContext = null;
			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(kms, tms, new SecureRandom());
			// HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
			return sslContext.getSocketFactory();
		} catch (Exception e) {
			logger.error("Exception:" + e.getMessage(), e);
			logger.error(e);
			e.printStackTrace();
		}
		return null;
	}

	public String getBaseURL() {
		String base = isTest ? "appapi2.test.bankid.com" : "appapi2.bankid.com";
		return "https://" + base + "/rp/v5";

	}

	public String getAuthRequestURL() {
		return getBaseURL() + "/auth";
	}

	public String getSignRequestURL() {
		return getBaseURL() + "/sign";
	}

	public String getCollectRequestURL() {
		return getBaseURL() + "/collect";
	}

	/*
	 * public String getPaymentRequestURL(boolean isNew) { return getBaseURL(isNew)
	 * + "/paymentrequests"; }
	 * 
	 * public String getRefundRequestURL(boolean isNew) { return getBaseURL(isNew) +
	 * "/refunds"; }
	 */
	public void useP12asKeystore() {
		logger.debug("useP12:" + isTest);

		try {
			String urlStr = getAuthRequestURL();
			URL url = new URL(urlStr);

			// #KeyStore
			KeyStore keyStore = null;
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());

			if (isTest) {
				keyStore = KeyStore.getInstance("PKCS12");
				keyStore.load(new FileInputStream(clientCertTest), "querty123".toCharArray());
				kmf.init(keyStore, "swish".toCharArray());
			} else {
				keyStore = KeyStore.getInstance("JKS");
				keyStore.load(new FileInputStream(clientCertTest), "querty123".toCharArray());
				kmf.init(keyStore, "changeit".toCharArray());
			}

			KeyManager[] kms = kmf.getKeyManagers();

			// #TrustStore
			KeyStore trustStore = KeyStore.getInstance("JKS");
			trustStore.load(new FileInputStream(caCert), "changeit".toCharArray());

			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(trustStore);
			TrustManager[] tms = tmf.getTrustManagers();

			// #SSLContext
			SSLContext sslContext = null;
			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(kms, tms, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

			/*
			 * The Swish server IP address for IP filtering: 194.242.111.220:443 Swish API
			 * URL: https://swicpc.bankgirot.se/swish-cpcapi/api/v1/paymentrequests
			 * https://swicpc.bankgirot.se/swish-cpcapi/api/v1/refunds Swish server TLS
			 * certificate is issued under the following root CA that should to be
			 * configured as trusted: cn=Swish Root CA v1 ou=Swish Member Banks CA
			 * o=Getswish AB The complete certificate chain of the Swish server TLS
			 * certificate is available through Swish Certificate Management
			 */

			HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setUseCaches(false);
			urlConn.setRequestMethod("POST");
			urlConn.setRequestProperty("Content-Type", "application/json");

			OutputStream out = urlConn.getOutputStream();
			out.write(getJSONbytes());
			out.close();

			String temp = ((HttpsURLConnection) urlConn).getResponseMessage();
			logger.warn("***************************************");
			logger.debug("response:" + temp);
			logger.warn("***************************************");
			System.exit(0);

		} catch (Exception e) {
			logger.error("#####################################");
			logger.error(e);
			e.printStackTrace();
		}

	}

	public void useTheGenerated() {
		logger.debug("useTheGenerated:" + isTest);

		String KStore = "/tmp/key.jks";
		String TStore = "/tmp/trust.jks";

		String base = isTest ? "mss.swicpc.bankgirot.se" : "swicpc.bankgirot.se";

		try {
			URL url = new URL("https://" + base + "/swish-cpcapi/api/v1/paymentrequests/");

			// #KeyStore
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStore.load(new FileInputStream(KStore), "changeit".toCharArray());

			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(keyStore, "swish".toCharArray());
			KeyManager[] kms = kmf.getKeyManagers();

			// #TrustStore
			KeyStore trustStore = KeyStore.getInstance("JKS");
			trustStore.load(new FileInputStream(TStore), "changeit".toCharArray());

			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(trustStore);
			TrustManager[] tms = tmf.getTrustManagers();

			// #SSLContext
			SSLContext sslContext = null;
			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(kms, tms, new SecureRandom());
			// HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

			/*
			 * The Swish server IP address for IP filtering: 194.242.111.220:443 Swish API
			 * URL: https://swicpc.bankgirot.se/swish-cpcapi/api/v1/paymentrequests
			 * https://swicpc.bankgirot.se/swish-cpcapi/api/v1/refunds Swish server TLS
			 * certificate is issued under the following root CA that should to be
			 * configured as trusted: cn=Swish Root CA v1 ou=Swish Member Banks CA
			 * o=Getswish AB The complete certificate chain of the Swish server TLS
			 * certificate is available through Swish Certificate Management
			 */

			HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();
			// urlConn.setSSLSocketFactory(sslContext.getSocketFactory());
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setUseCaches(false);
			urlConn.setRequestMethod("POST");
			urlConn.setRequestProperty("Content-Type", "application/json");

			OutputStream out = urlConn.getOutputStream();
			out.write(getJSONbytes());
			out.close();

			String temp = ((HttpsURLConnection) urlConn).getResponseMessage();
			logger.info("resonse(WOW):" + temp);

		} catch (Exception e) {
			logger.error("Exception: ###########################");
			logger.error(e);
			e.printStackTrace();
		}

	}

	private SSLSocketFactory getFactory(File pKeyFile, String pKeyPassword) {
		logger.debug("SSLSocketFactory");
		KeyManagerFactory keyManagerFactory;
		try {
			keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
			KeyStore keyStore = KeyStore.getInstance("PKCS12");

			InputStream keyInput = new FileInputStream(pKeyFile);
			keyStore.load(keyInput, pKeyPassword.toCharArray());
			keyInput.close();

			keyManagerFactory.init(keyStore, pKeyPassword.toCharArray());

			SSLContext context = SSLContext.getInstance("TLS");
			context.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());

			return context.getSocketFactory();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return null;
	}

	public byte[] getJSONbytes() {
		logger.debug("getJSONbytes");

		AuthRequest pr = new AuthRequest();

		pr.personalNumber = "196605057253";
		pr.endUserIp = "94.140.57.181";

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(pr);
		logger.debug("JSON:\n" + json);
		return json.toString().getBytes();
	}

	public void useP12_2() {
		logger.debug("useP12:" + isTest);

		String base = isTest ? "mss.swicpc.bankgirot.se" : "swicpc.bankgirot.se";

		try {

			byte[] jsonBytes = getJSONbytes();

			URL url = new URL("https://" + base + "/api/v1/paymentrequests/");

			// HttpsURLConnection.setDefaultSSLSocketFactory(getFactory(new
			// File("/tmp/Swish Merchant Test Certificate 1231181189.p12"),
			// "swish"));

			HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();
			// urlConn.setSSLSocketFactory();
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setUseCaches(false);
			urlConn.setRequestMethod("POST");
			urlConn.setRequestProperty("Content-Type", "application/json");

			OutputStream out = urlConn.getOutputStream();
			out.write(jsonBytes);
			out.close();

			String temp = ((HttpsURLConnection) urlConn).getResponseMessage();

			logger.debug("resonse:" + temp);

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	public void useStraight(String websiteId) {
		String base = isTest ? "mss.swicpc.bankgirot.se" : "swicpc.bankgirot.se";

		SSLSocketFactory sslFactory = createSocketFactory(websiteId);

		try {
			String urlStr = isTest ? "https://mss.swicpc.bankgirot.se/swish-cpcapi/api/v1/paymentrequests/"
					: "https://swicpc.bankgirot.se/api/v1/paymentrequests/";
			URL url = new URL(urlStr);

			HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();
			urlConn.setSSLSocketFactory(sslFactory);
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setUseCaches(false);
			urlConn.setRequestMethod("POST");
			urlConn.setRequestProperty("Content-Type", "application/json");

			OutputStream out = urlConn.getOutputStream();
			out.write(getJSONbytes());
			out.close();

			String temp = ((HttpsURLConnection) urlConn).getResponseMessage();
			logger.debug("## resonse:" + temp);

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	public void useStraight(SSLSocketFactory socketFactory) {
		String base = isTest ? "mss.swicpc.bankgirot.se" : "swicpc.bankgirot.se";

		try {
			URL url = new URL("https://" + base + "/swish-cpcapi/api/v1/paymentrequests/");

			HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();
			urlConn.setSSLSocketFactory(socketFactory);
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setUseCaches(false);
			urlConn.setRequestMethod("POST");
			urlConn.setRequestProperty("Content-Type", "application/json");

			OutputStream out = urlConn.getOutputStream();
			out.write(getJSONbytes());
			out.close();

			String temp = ((HttpsURLConnection) urlConn).getResponseMessage();
			logger.debug("resonse:" + temp);

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

	}

	private void testIt() {

		String https_url = "https://appapi2.test.bankid.com/rp/v5/";
		URL url;
		try {

			url = new URL(https_url);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

			// dumpl all cert info
			print_https_cert(con);

			// dump all the content
			print_content(con);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void print_https_cert(HttpsURLConnection con) {

		if (con != null) {

			try {

				System.out.println("Response Code : " + con.getResponseCode());
				System.out.println("Cipher Suite : " + con.getCipherSuite());
				System.out.println("\n");

				Certificate[] certs = con.getServerCertificates();
				for (Certificate cert : certs) {
					System.out.println("Cert Type : " + cert.getType());
					System.out.println("Cert Hash Code : " + cert.hashCode());
					System.out.println("Cert Public Key Algorithm : " + cert.getPublicKey().getAlgorithm());
					System.out.println("Cert Public Key Format : " + cert.getPublicKey().getFormat());
					System.out.println("\n");
				}

			} catch (SSLPeerUnverifiedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	private void print_content(HttpsURLConnection con) {
		if (con != null) {

			try {

				System.out.println("****** Content of the URL ********");
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

				String input;

				while ((input = br.readLine()) != null) {
					System.out.println(input);
				}
				br.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public void pkcs12() {
		try {
/*
			final KeyStore keyStore = KeyStore.getInstance(AndroidKeyStore);
			URL ksUrl = Resources.getResource(keyStoreFile);
			File ksFile = new File(ksUrl.toURI());
			URL tsUrl = Resources.getResource(keyStoreFile);
			File tsFile = new File(tsUrl.toURI());
*/
			String trustStoreX = System.getProperty("javax.net.ssl.trustStore");
			if (trustStoreX == null) {
				logger.debug("javax.net.ssl.trustStore is not defined");
			} else {
				logger.debug("javax.net.ssl.trustStore = " + trustStoreX);
			}

			try {
				ClientCustomSSL.class.getResource("trustStore.jks").getFile();
			} catch (Exception e) {
				e.printStackTrace();
			}

			trustStoreX = System.getProperty("javax.net.ssl.trustStore");

			if (trustStoreX == null) {
				String storeLoc;
				storeLoc = System.getProperty("java.class.path");
				logger.debug("classpath: " + storeLoc);
			}

			trustStoreX = System.getProperty("javax.net.ssl.trustStore");
			if (trustStoreX == null) {
				logger.debug("javax.net.ssl.trustStore is not defined");
			} else {
				logger.debug("javax.net.ssl.trustStore = " + trustStoreX);
			}

			logger.debug("pkcs12");
			KeyStore clientStore = KeyStore.getInstance("PKCS12");
			logger.debug("load:" + clientCertTest);
			clientStore.load(new FileInputStream(clientCertTest), "qwerty123".toCharArray());
			logger.debug("loaded #pkcs12");

			logger.debug("KeyManagerFactory");
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(clientStore, "qwerty123".toCharArray());
			KeyManager[] kms = kmf.getKeyManagers();
			logger.debug("OK");

			logger.debug("KeyStore...");
			KeyStore trustStore = KeyStore.getInstance("JKS");
			logger.debug("load:" + caCert);
			trustStore.load(new FileInputStream(caCert), null);
			logger.debug("loaded...");

			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(trustStore);
			TrustManager[] tms = tmf.getTrustManagers();

			SSLContext sslContext = null;
			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(kms, tms, new SecureRandom());

			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
			URL url = new URL(getAuthRequestURL());

			HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();
		} catch (Exception e) {
			logger.debug("Exception:" + e.getMessage(), e);
		}
	}

}