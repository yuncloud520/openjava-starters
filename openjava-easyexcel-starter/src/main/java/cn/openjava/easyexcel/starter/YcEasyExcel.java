package cn.openjava.easyexcel.starter;

import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
public class YcEasyExcel extends EasyExcel {

    public static EasyExcel init(HttpServletResponse response, String fileName) {
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=utf-8''" + URLEncoder.encode(fileName, "utf-8").replaceAll("\\+", "%20") + ".xlsx");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        return new EasyExcel();
    }
}
