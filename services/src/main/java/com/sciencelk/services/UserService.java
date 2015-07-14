package com.sciencelk.services;

import com.sciencelk.dataaccess.entities.User;
import com.sciencelk.dataaccess.exceptions.ItemNotFoundException;
import com.sciencelk.dataaccess.repositories.UserRepositoryInterface;
import com.sciencelk.dto.UserDetails;
import com.sciencelk.dto.auth.exceptions.AuthenticationException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.BlowfishEngine;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.paddings.ZeroBytePadding;
import org.bouncycastle.crypto.params.KeyParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * *
 * Created by SasithaG on 6/16/2015.
 */
@Service
public class UserService implements UserServiceInterface{

    private static final String CONST_SALT = "ffb1039cf4e82d719fcd06ffef5883cc";
    private static final BlockCipher BLOWFISH_ENGINE = new BlowfishEngine();
    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    
    @Autowired
    private UserRepositoryInterface userRepository;
    
    @Transactional
    @Override
    public boolean authenticateUser(String userName, String password) throws AuthenticationException {
        try {
            String stringHash = getSaltedHashedPasswordString(password);
            boolean authResult = checkUserPasswordHash(userName, stringHash);
            if (authResult){
                User user = userRepository.findByUserName(userName);
            }
            return authResult;
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e);
        } catch (ItemNotFoundException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Transactional
    @Override
    public UserDetails findByName(String userName) throws ItemNotFoundException {
        return getUserDetails(userRepository.findByUserName(userName));
    }

    private UserDetails getUserDetails(User user){
        UserDetails userDetails = new UserDetails();
        userDetails.setUserName(user.getName());
        userDetails.setUserId(user.getId());
        return userDetails;
    }
    
    private boolean checkUserPasswordHash(String username, String passwordHash) throws AuthenticationException {
        try {
            return userRepository.authenticate(username, passwordHash);
        } catch (ItemNotFoundException e) {
            LOGGER.error(e);
            throw new AuthenticationException(e);
        }
    }
    
    private String getSaltedHashedPasswordString(String password) throws NoSuchAlgorithmException, AuthenticationException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String saltedPassword = getSaltedPassword(password);
        if (saltedPassword == null) {
            throw new AuthenticationException("Salting failed");
        }
        digest.update(saltedPassword.getBytes("UTF-8"));

        byte[] byteHash = digest.digest();

        return Hex.encodeHexString(byteHash);
    }

    private String getSaltedPassword(String password) {
        PaddedBufferedBlockCipher pbbc = new PaddedBufferedBlockCipher(BLOWFISH_ENGINE, new ZeroBytePadding());
        if (password != null) {
            StringBuilder builder = new StringBuilder(password);
            try {
                byte[] input = builder.reverse().toString().getBytes("UTF-8");
                pbbc.init(true, new KeyParameter(Hex.decodeHex(CONST_SALT.toCharArray())));
                byte[] output = new byte[pbbc.getOutputSize(input.length)];
                int bytesWrittenOut = pbbc.processBytes(input, 0, input.length, output, 0);
                pbbc.doFinal(output, bytesWrittenOut);
                return Hex.encodeHexString(output);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error(e);
            } catch (InvalidCipherTextException e) {
                LOGGER.error(e);
            } catch (DecoderException e) {
                LOGGER.error(e);
            }
        }
        return null;
    }
}
