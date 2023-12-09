package cn.openjava.basic.format.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultVo {
    ResultType type;
    int code;
    String message;
}
