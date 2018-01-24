package com.qyf404.learn.signature;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.util.DerInputStream;
import sun.security.util.DerValue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class SignatureUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(SignatureUtil.class);
    private static String CHARSET = "UTF-8";

    public static boolean verifySignature(String dataString, String signatureString, String pubKeyString) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException, InvalidKeySpecException {
        return verifySignature(dataString, signatureString, getPubKey(pubKeyString), CHARSET);
    }

    private static boolean verifySignature(String dataString, String signatureString, PublicKey publicKey, String charset) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        byte[] signatureBytes = Base64.decodeBase64(signatureString);
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(dataString.getBytes(charset));
        return signature.verify(signatureBytes);
    }

    private static PublicKey getPubKey(String pubKeyString) throws NoSuchAlgorithmException, InvalidKeySpecException {
        pubKeyString = pubKeyString.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");
        byte[] keyBytes = Base64.decodeBase64(pubKeyString);

        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(spec);
        return publicKey;
    }

    public static String sign(String data, String PEMEncodedPrivateKey) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        return sign(data, getPrivateKeyFromPEM(PEMEncodedPrivateKey), CHARSET);
    }

    private static String sign(String data, PrivateKey privateKey, String charset) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, SignatureException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes(charset));
        byte[] signBytes = signature.sign();

        return Base64.encodeBase64String(signBytes).replaceAll("\n|\r", "");
    }

    private static PrivateKey getPrivateKeyFromPEM(String pemEncodedPrivateKey) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        pemEncodedPrivateKey = pemEncodedPrivateKey.replaceAll("(-+BEGIN (RSA )?PRIVATE KEY-+\\r?\\n|-+END (RSA )?PRIVATE KEY-+\\r?\\n?)", "");

        byte[] privateKeyBytes = Base64.decodeBase64(pemEncodedPrivateKey);
        try {
            return generatePrivateKeyWithPKCS8(privateKeyBytes);
        } catch (InvalidKeySpecException e) {
            LOGGER.info("pemEncodedPrivateKey is not PKCS8", e);
        }
        return generatePrivateKeyWithPKCS1(privateKeyBytes);
    }

    private static PrivateKey generatePrivateKeyWithPKCS8(byte[] privateKeyBytes)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }

    private static PrivateKey generatePrivateKeyWithPKCS1(byte[] privateKeyBytes) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        DerInputStream derReader = new DerInputStream(privateKeyBytes);
        DerValue[] seq = derReader.getSequence(0);
        if (seq.length < 9) {
            throw new InvalidKeySpecException("Could not parse a PKCS1 private key.");
        }
        BigInteger modulus = seq[1].getBigInteger();
        BigInteger publicExp = seq[2].getBigInteger();
        BigInteger privateExp = seq[3].getBigInteger();
        BigInteger prime1 = seq[4].getBigInteger();
        BigInteger prime2 = seq[5].getBigInteger();
        BigInteger exp1 = seq[6].getBigInteger();
        BigInteger exp2 = seq[7].getBigInteger();
        BigInteger crtCoef = seq[8].getBigInteger();
        RSAPrivateCrtKeySpec spec = new RSAPrivateCrtKeySpec(modulus, publicExp, privateExp, prime1, prime2, exp1, exp2, crtCoef);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePrivate(spec);
    }
}
