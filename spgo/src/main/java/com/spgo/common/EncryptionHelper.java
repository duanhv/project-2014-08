package com.spgo.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class EncryptionHelper {

	private static final String passphrase = "vytccvlibligigiftrxrzw6546476xcfg";

	public static void main(String[] args) {

		String parameter = "abcdefghijk";

		String encryptedParameter = "";

		String decryptedParameter = "";

		encryptedParameter = encrypt(parameter, passphrase);

		decryptedParameter = decrypt(encryptedParameter, passphrase);

		System.out.println("\nInitial parameter=" + parameter);

		System.out.println("\nEncrypted parameter=" + encryptedParameter);

		System.out.println("\nDecrypted parameter=" + decryptedParameter);

	}

	public static String encrypt(String msg) {
		return encrypt(msg, passphrase);
	}

	public static String encrypt(String msg, String PASS_PHRASE) {

		try {

			KeySpec keySpec = new DESedeKeySpec(PASS_PHRASE.getBytes());

			SecretKey key = SecretKeyFactory.getInstance("DESede")
					.generateSecret(keySpec);

			Cipher ecipher = Cipher.getInstance(key.getAlgorithm());

			ecipher.init(Cipher.ENCRYPT_MODE, key);

			// Encode the string into bytes using utf-8

			byte[] utf8 = msg.getBytes("UTF8");

			// Encrypt

			byte[] enc = ecipher.doFinal(utf8);

			// Encode bytes to base64 to get a string

			return new sun.misc.BASE64Encoder().encode(enc);

		} catch (InvalidKeyException e) {

			e.printStackTrace();

		} catch (InvalidKeySpecException e) {

			e.printStackTrace();

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		} catch (NoSuchPaddingException e) {

			e.printStackTrace();

		} catch (IllegalStateException e) {

			e.printStackTrace();

		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();

		} catch (BadPaddingException e) {

			e.printStackTrace();

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

		}

		return null;

	}

	public static String decrypt(String msg) {
		return decrypt(msg, passphrase);
	}

	public static String decrypt(String msg, String PASS_PHRASE) {

		try {

			KeySpec keySpec = new DESedeKeySpec(PASS_PHRASE.getBytes());

			SecretKey key = SecretKeyFactory.getInstance("DESede")
					.generateSecret(keySpec);

			Cipher decipher = Cipher.getInstance(key.getAlgorithm());

			decipher.init(Cipher.DECRYPT_MODE, key);

			// Decode base64 to get bytes

			byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(msg);

			// Decrypt

			byte[] utf8 = decipher.doFinal(dec);

			// Decode using utf-8

			return new String(utf8, "UTF8");

		} catch (InvalidKeyException e) {

			e.printStackTrace();

		} catch (InvalidKeySpecException e) {

			e.printStackTrace();

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		} catch (NoSuchPaddingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (IllegalStateException e) {

			e.printStackTrace();

		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();

		} catch (BadPaddingException e) {

			e.printStackTrace();

		}

		return null;

	}

	public static String encodeURL(String url) {

		try {

			return URLEncoder.encode(url, "UTF-8");

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

		}

		return null;

	}

	public static String decodeURL(String url) {

		try {

			return URLDecoder.decode(url, "UTF-8");

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

		}

		return null;

	}

}