package com.sciencelk.common.interceptors;


import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.sciencelk.common.interceptors.exeptions.SecurityTokenException;
import com.sciencelk.dto.auth.Token;
import org.apache.commons.codec.DecoderException;
import org.apache.log4j.Logger;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.paddings.ZeroBytePadding;

import java.io.UnsupportedEncodingException;

/**
 * Authentication Token Manipulation
 * Created by lasithap on 6/27/14.
 */
public class AuthenticationTokenHandler {

    // Exception Messages
    // _________________________________________________________________________________________________
    public static final String EXTRACTION_FAILED_MALFORMED_TOKEN = "Extraction Failed : Malformed token";
    public static final String EXTRACTION_FAILED_UNSUPPORTED_ENCODING = "Extraction Failed : Unsupported encoding";
    public static final String EXTRACTION_FAILED_DECODING_ERROR = "Extraction Failed : Decoding error";
    public static final String EXTRACTION_FAILED_INVALID_TOKEN = "Extraction Failed : Invalid token";
    public static final String CHARSET_NAME = "UTF-8";
    // _________________________________________________________________________________________________


    // The key used for encryption (Keep secured! and confidential)
    private static final String STR_KEY = "0713e7a35916d0bc24c89f731ca9cc1a1b343ca3fa1b15b45764ba832a014577";
    private static final Logger LOGGER = Logger.getLogger(AuthenticationTokenHandler.class);
    private BlowFishEncryptor blowFishEncryptor = new BlowFishEncryptor();
    private Gson gson = new Gson();

    /**
     * Extracts a token object from the Authentication string
     *
     * @param securityToken The authentication token object.
     * @return Return the extracted token
     * @throws Exception Throws an exception when token extraction fails
     */
    public Token extractToken(String securityToken) throws SecurityTokenException {
        try {
            // Decrypt the Authentication Header
            blowFishEncryptor.setPadding(new ZeroBytePadding());
            blowFishEncryptor.setKey(org.apache.commons.codec.binary.Hex.decodeHex(STR_KEY.toCharArray()));

            byte[] decrypted = blowFishEncryptor.decrypt(org.apache.commons.codec.binary.Hex.decodeHex(securityToken.toCharArray()));
            String strDecrypted = new String(decrypted, CHARSET_NAME);

            // Convert the decrypted data to a token object (Trim any leading and trailing spaces)
            return gson.fromJson(strDecrypted.trim(), Token.class);

        } catch (InvalidCipherTextException icte) {
            LOGGER.error(EXTRACTION_FAILED_MALFORMED_TOKEN, icte);
            throw new SecurityTokenException(EXTRACTION_FAILED_MALFORMED_TOKEN);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(EXTRACTION_FAILED_UNSUPPORTED_ENCODING, e);
            throw new SecurityTokenException(EXTRACTION_FAILED_UNSUPPORTED_ENCODING);
        } catch (DecoderException e) {
            LOGGER.error(EXTRACTION_FAILED_DECODING_ERROR, e);
            throw new SecurityTokenException(EXTRACTION_FAILED_DECODING_ERROR);
        } catch (JsonParseException e) {
            LOGGER.error(EXTRACTION_FAILED_INVALID_TOKEN, e);
            throw new SecurityTokenException(EXTRACTION_FAILED_INVALID_TOKEN);
        }
    }

    /**
     * Method responsible for creating a authentication string from a token object
     *
     * @param token The token object created during authentication.
     * @return The encoded string for the token object.
     * @throws java.io.UnsupportedEncodingException Error thrown when encoding fails.
     */
    public String createAuthString(Token token) throws UnsupportedEncodingException, InvalidCipherTextException, DecoderException {
        String strToken = gson.toJson(token);
        byte[] ba = strToken.getBytes(CHARSET_NAME);
        blowFishEncryptor.setPadding(new ZeroBytePadding());
        blowFishEncryptor.setKey(org.apache.commons.codec.binary.Hex.decodeHex(STR_KEY.toCharArray()));

        byte[] encr = blowFishEncryptor.encrypt(ba);
        return org.apache.commons.codec.binary.Hex.encodeHexString(encr);
    }
}