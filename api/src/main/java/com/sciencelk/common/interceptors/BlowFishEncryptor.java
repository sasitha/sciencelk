package com.sciencelk.common.interceptors;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.BlowfishEngine;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;

/**
 * Encryption Manipulation Class
 * Created by lasithap on 6/27/14.
 */
public class BlowFishEncryptor {

    private final BlockCipher blowfishEngine = new BlowfishEngine();

    private PaddedBufferedBlockCipher pbbc;
    private KeyParameter key;

    /**
     * @param bcp
     */
    public void setPadding(BlockCipherPadding bcp) {
        this.pbbc = new PaddedBufferedBlockCipher(blowfishEngine, bcp);
    }

    /**
     * @param key
     */
    public void setKey(byte[] key) {
        this.key = new KeyParameter(key);
    }

    /**
     * @param input
     * @return
     * @throws org.bouncycastle.crypto.DataLengthException
     * @throws org.bouncycastle.crypto.InvalidCipherTextException
     */
    public byte[] encrypt(byte[] input)
            throws DataLengthException, InvalidCipherTextException {
        return processing(input, true);
    }

    /**
     * @param input
     * @return
     * @throws org.bouncycastle.crypto.DataLengthException
     * @throws org.bouncycastle.crypto.InvalidCipherTextException
     */
    public byte[] decrypt(byte[] input)
            throws DataLengthException, InvalidCipherTextException {
        return processing(input, false);
    }

    /**
     * @param input
     * @param encrypt
     * @return
     * @throws org.bouncycastle.crypto.DataLengthException
     * @throws org.bouncycastle.crypto.InvalidCipherTextException
     */
    private byte[] processing(byte[] input, boolean encrypt)
            throws DataLengthException, InvalidCipherTextException {

        pbbc.init(encrypt, key);

        byte[] output = new byte[pbbc.getOutputSize(input.length)];
        int bytesWrittenOut = pbbc.processBytes(
                input, 0, input.length, output, 0);

        pbbc.doFinal(output, bytesWrittenOut);

        return output;

    }

}
