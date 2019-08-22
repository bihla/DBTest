package se.payer.bankid;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.Iterator;

import java.io.*;
import java.security.SecureRandom;
import java.security.Security;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.*;
import javax.security.cert.*;

/**
 * This example demonstrates how to create secure connections with a custom SSL
 * context.
 */
public class ClientCustomSSL {
	protected static final transient Logger logger = Logger.getLogger(ClientCustomSSL.class);
	boolean isTest = false;

	String certPath = "/Users/bihla/git/DBTest/src/se/payer/bankid";
	String certFile = "PayerClientCertificate.pfx";

	String clientCert = certPath + "/PayerClientCertificate.p12";
	String bankIdCA = certPath + "/bankid_CA_live.cer";

	public final static void main(String[] args) throws Exception {

		System.setProperty("javax.net.debug", "all");

		ClientCustomSSL ssl = new ClientCustomSSL(false);

		logger.debug("=================== --- ===================");
		ssl.pkcs12();
		logger.debug("=================== --- ===================");

	}

	public ClientCustomSSL(boolean isTest) {
		super();
		this.isTest = isTest;
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

	private SSLSocketFactory getFactory()
			throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException,
			IOException, UnrecoverableKeyException, KeyManagementException, InvalidAlgorithmParameterException {

		String home = System.getProperty("java.home");
		String cacert = new String(home + "/lib/security/cacerts").replace('/', File.separatorChar);

		logger.debug("export JAVA_HOME=" + home);
		logger.debug("export CACERT=" + cacert);

		KeyStore trustStore = KeyStore.getInstance("JKS");
		trustStore.load(new FileInputStream(cacert), "changeit".toCharArray());

		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		InputStream certstream = fullStream (bankIdCA);
		java.security.cert.Certificate cert =  cf.generateCertificate(certstream);

		KeyStore clientStore = KeyStore.getInstance("PKCS12");
		clientStore.load(new FileInputStream(clientCert), "qwerty123".toCharArray());
		clientStore.setCertificateEntry("ca", cert);

		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(clientStore, "qwerty123".toCharArray());
		
		KeyManager[] kms = kmf.getKeyManagers();
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(trustStore);
		
		
		clientStore.store( new FileOutputStream( "/tmp/java.txt" ), "".toCharArray() );

		TrustManager[] tms = tmf.getTrustManagers();

		SSLContext sslContext = null;
		sslContext = SSLContext.getInstance("TLS");
		sslContext.init(kms, tms, new SecureRandom());

		return sslContext.getSocketFactory();
	}
	private static InputStream fullStream ( String fname ) throws IOException {
	    FileInputStream fis = new FileInputStream(fname);
	    DataInputStream dis = new DataInputStream(fis);
	    byte[] bytes = new byte[dis.available()];
	    dis.readFully(bytes);
	    ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
	    return bais;
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

	private void out(String out) {
		System.out.println("P: " + out);
		logger.debug("L: " + out);
	}

	private void print_content(HttpsURLConnection con) {
		if (con != null) {

			try {

				out("****** Content of the URL ********");
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

				String input;

				while ((input = br.readLine()) != null) {
					out(input);
				}
				br.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public void pkcs12() {

		try {
			logger.debug("1...");
			SSLSocketFactory sf = getFactory();
			HttpsURLConnection.setDefaultSSLSocketFactory(sf);

			logger.debug("2...");
			URL url = new URL(getAuthRequestURL());

			logger.debug("3...URL:" + url.toExternalForm());
			HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();

			if (true) {
				logger.debug("4...print_https_cert before");
				urlConn.setHostnameVerifier(new HostnameVerifier() {
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				});
			}

			logger.debug("71...");

			logger.debug("72...");
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setUseCaches(false);
			urlConn.setRequestProperty("Content-Type", "application/json");
			urlConn.setRequestProperty("Accept", "application/json");
			urlConn.setRequestMethod("POST");

			logger.debug("8...");
			OutputStreamWriter wr = new OutputStreamWriter(urlConn.getOutputStream());
			wr.write(getJSONbytes().toString());
			wr.flush();

			logger.debug("9...");
			StringBuilder sb = new StringBuilder();
			int HttpResult = urlConn.getResponseCode();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				System.out.println("" + sb.toString());
				logger.warn("***************************************");
				logger.debug("response:" + sb.toString());
				logger.warn("***************************************");

				logger.debug("10...");
				System.exit(0);

			}

		} catch (Exception e) {
			logger.warn("Exception caught:" + e.getMessage());
		}
	}
}