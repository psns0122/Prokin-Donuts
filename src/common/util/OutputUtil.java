package common.util;

import dto.memberDTO.MemberDTO;

import java.util.List;
import java.util.Optional;

public class OutputUtil {
    public static <T> void getOutput(T result){
        if (result instanceof List<?>) ((List<?>) result).forEach(System.out::println);
        else System.out.println(result);
    }
}
