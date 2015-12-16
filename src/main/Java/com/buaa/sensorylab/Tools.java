package com.buaa.sensorylab;

/**
 * Created by LZJing on 2015/11/29.
 */
public class Tools {

    public static int byteArrayToInt(byte[] b, int offset) {
        int value= 0;
        for (int i = 0; i < 4; i++) {
            int shift= (4 - 1 - i) * 8;
            value +=(b[i + offset] & 0x000000FF) << shift;
        }
        return value;
    }

    /**
     * ͨ��byte����ȡ��short
     *
     * @param b
     * @param index �ڼ�λ��ʼȡ
     * @return
     */
    public static short getShort(byte[] b, int index) {
        return (short) (((b[index ] << 8) | b[index + 1] & 0xff));
    }

}
