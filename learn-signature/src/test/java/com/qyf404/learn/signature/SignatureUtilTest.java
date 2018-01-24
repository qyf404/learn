package com.qyf404.learn.signature;

import org.junit.Test;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.*;

public class SignatureUtilTest {
    private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEAomP46p/Ft78JaSNtkqj839S+SbHt4McsrlE44YnYHO+lwnY7\n" +
            "pTEQj2iWxUguJTgZt2pGCfKYAvVyS4vs3V333DgFkhKxFCc6dT36afshgenrkrKv\n" +
            "OCEQAF08J/+fNf6x7j5tN/nFe/zn1Q7epIrC+XN8Fz4q72jt/cDHZRvtzSbEubZ4\n" +
            "tP+QXP3COhyel05CJOrbER5ypUi9biEKBG3ujsZgWbw3nHnjqleHejT1AEyDi/JN\n" +
            "rjP+y6KwnedOOe/sgYLGJ6SQ/+IvQrgO0NA1EJptXVvMMvRY96/V2NlK5lfgofIS\n" +
            "3IEdNMgZ4KH1tPhgn57wW995Cgng59jbCi8VJwIDAQABAoIBAA1pnnSaj7UWhUt2\n" +
            "zqUPaht+sdzaYmBvcwRO1xjhzsTNEm7WXyTMwaZmqBVRWeZfFNiuYWyO6flMlo0g\n" +
            "2IaMLV22Dte6zX9xogVvAhDg2iqlzHxIX+qN6IlvKfnPxjGqgL3B0DmQWrxb7jYN\n" +
            "zVvfzizWWgh9bQRrMUUFODLktKZ+DJEvbi2ApIBpWQ+aQMBG6tNdK1v69pMLvJZA\n" +
            "IARS33UZTFUlVrIYsn+la5YYyhpNWj8rbiKGx0st14oCx+xTZ06myrrTI/al87GK\n" +
            "+BrJIinoV64iewWHDgYG/isj6aAdQy0NOiKWa0kJ7JC9ul0q6yNVc5c3UbPCDlBQ\n" +
            "TEuAPHkCgYEAzGthkGSgw3Cksn8giY0UecNdYC/BdfcSdHWBVj5uvNHjZihvvmLQ\n" +
            "Ccyuj2T4rbWibSimr8AcC1pTqzROkVhzWaKtIh6aHJ9TcwsacCFd5NbPQ72dLMv5\n" +
            "M82JhJqlzLMxbo/P4Dc9HLs4oUN1L5GNvwIEKhfTITsA/hzhOBQnq40CgYEAy12z\n" +
            "wtxSB/FqWToYTLoIEoYoeVwfPiJlyP7VOdRU/sG43Zs0XH9bCCZmv0JJ16CTRBiW\n" +
            "O1M2Fd/TyZfVp7Aw9/tA1lOU5JFGH5ragNrNr9nz8vo1pzSP/8yB9ocToNFCAQY+\n" +
            "bFo7WmfQwUXPUSOXo+vM3KbBDjdgL7jTMRZyfIMCgYEAlZ6C6oy8nk/0PDGQPx1j\n" +
            "vzufPKe3kgAlIdhfsiBALTPynPM71r28R2/o1EuzOHGZUQxP9JNH84dJdiXy269B\n" +
            "vnmy/yYm5ow3KB0YXLoPX09apNpEEGW7II9v9/65G/qMU8AvBo1nzX+7E02smASR\n" +
            "Gxz+r1CA1/G36EZ7EfCumIECgYAiWETPl93SsZbAUutfqdtLJZUYSdKM78o407C2\n" +
            "/4ELNTQ/WMLTDD8XdUTM8CkAXYIBchWNF1R/BToDRb0zgLIEb05X4+21XEN3evOD\n" +
            "52/tTuXAFzmQb3QSQzt/ovhRpIV9HszUB7kWX4oF2gBVN6mYaW5DpXXdLMT1ykUA\n" +
            "eUYEeQKBgQCpMbNV0DtzrwFYEwLaGHgitZEH+CdyWp4oJ1tLbrAhVjrfvjVsO8ie\n" +
            "bm26c0MflKHlmutlo3Q3g+9pwRrtECwMYMj4pbY2s+spcKM6wswO+MkXZhek/PAA\n" +
            "lk0sTdI9NVW1VPz2xqZZOlhGBuVNfzR+b98uz1OV0gpArpN4gkMUJg==\n" +
            "-----END RSA PRIVATE KEY-----\n";

    private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAomP46p/Ft78JaSNtkqj8\n" +
            "39S+SbHt4McsrlE44YnYHO+lwnY7pTEQj2iWxUguJTgZt2pGCfKYAvVyS4vs3V33\n" +
            "3DgFkhKxFCc6dT36afshgenrkrKvOCEQAF08J/+fNf6x7j5tN/nFe/zn1Q7epIrC\n" +
            "+XN8Fz4q72jt/cDHZRvtzSbEubZ4tP+QXP3COhyel05CJOrbER5ypUi9biEKBG3u\n" +
            "jsZgWbw3nHnjqleHejT1AEyDi/JNrjP+y6KwnedOOe/sgYLGJ6SQ/+IvQrgO0NA1\n" +
            "EJptXVvMMvRY96/V2NlK5lfgofIS3IEdNMgZ4KH1tPhgn57wW995Cgng59jbCi8V\n" +
            "JwIDAQAB\n" +
            "-----END PUBLIC KEY-----\n";

    private String anotherPublicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArxqKlGLBqmrVHin2Pxhj\n" +
            "srsOy8K6uj9tHE7udVR5fQuPSejN44+XJl/oQcVJ4kKG0+rt1QyYKh07n0VoVR7W\n" +
            "y00T+/6aohnn2Ohdru5hYl0bPBpJK4Ja+nU2AUzbiGh2stkKcQm0bB+tUQtthIHM\n" +
            "tPZ6LOj/msp0dRiPOQ/sgcXdTfLZiyDDWTq+YfGrfso8ne4AHuxk7O9o7pFFVQNq\n" +
            "KlimiRPLVw9j4iUmLQ6JvNXI5ggQb9CHEqLJiGxoanlJyNbpS/TFlxd6j6EnEJAs\n" +
            "Mu8pSvK7fWZ0UmyuJnrYJHJO1k+6r+XZjzaAh+W7btHcmRoZZmUZ8Tatk79jJdB9\n" +
            "jQIDAQAB\n" +
            "-----END PUBLIC KEY-----\n";

    @Test
    public void should_verify_signature_success() throws InvalidKeySpecException, SignatureException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        String data = "abcdefg";
        String sign = SignatureUtil.sign(data, privateKey);

        assertTrue(SignatureUtil.verifySignature(data, sign, publicKey));
    }

    @Test
    public void should_verify_signature_fail_when_public_key_is_invalid() throws InvalidKeySpecException, SignatureException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        String data = "abcdefg";
        String sign = SignatureUtil.sign(data, privateKey);

        assertFalse(SignatureUtil.verifySignature(data, sign, anotherPublicKey));
    }

    @Test
    public void should_verify_signature_fail_when_data_is_invalid() throws InvalidKeySpecException, SignatureException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        String data = "abcdefg";
        String sign = SignatureUtil.sign(data, privateKey);

        assertFalse(SignatureUtil.verifySignature("abcdef", sign, anotherPublicKey));
    }
}