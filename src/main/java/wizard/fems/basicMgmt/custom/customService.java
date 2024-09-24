/**
*패키지명 : wizard.fems.basicMgmt.custom
*파일명: customService.java
*작성일: 2024-09-23
*개발자: daehyun
***************************************************
*변경일자            변경자             변경내용
***************************************************
 * 2024-09-19       최대현             최초작성
*/

package wizard.fems.basicMgmt.custom;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import wizard.fems.basicMgmt.custom.DTO.customDTO;
import wizard.fems.basicMgmt.machine.DTO.MCDTO;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class customService {

    @Autowired
    private customMapper mapper;

    @Transactional
    public List<customDTO> getCustomList(Map<String, Object> param) {
        List<customDTO> list = mapper.getCustomList(param);
        return list;
    }

    @Transactional
    public customDTO getCustomDetail(String plantID) {
        customDTO customdto = mapper.getCustomDetail(plantID);
        return customdto;
    }

    @Transactional
    public customDTO saveCustomDetail(customDTO customdto) {
        return mapper.saveCustomDetail(customdto);
    }

    @Transactional
    public customDTO updateCustomDetail(customDTO customdto) {
        return mapper.updateCustomDetail(customdto);
    }

    @Transactional
    public int deleteCustomID(String customID) {
        return mapper.deleteCustomID(customID);
    }

    public List<wizard.fems.basicMgmt.custom.DTO.customDTO> getAgentName(){
        return mapper.getAgentName();
    }

}
