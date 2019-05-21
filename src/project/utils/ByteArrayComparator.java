package project.utils;

import java.util.Comparator;

public class ByteArrayComparator implements Comparator {
    
    @Override
    public int compare(Object o1, Object o2) {
        int result = 0;
        if (((byte[]) o1).length >
                ((byte[]) o2).length) {
            result = 1;
        } else if (((byte[]) o1).length <
                       ((byte[]) o2).length) {
            result = -1;
        } else {
            for (int i = 0; i < ((byte[]) o1).length; i++) {
                if (((byte[]) o1)[i] <
                        ((byte[]) o2)[i]) {
                    result = -1;
                    break;
                } else if (((byte[]) o1)[i] >
                               ((byte[]) o2)[i]) {
                    result = 1;
                    break;
                }
            }
        }
        
        return result;
    }
}
