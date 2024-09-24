/**
*패키지명 : wizard.fems.basicMgmt.custom
*파일명: customMapper.java
*작성일: 2024-09-23
*개발자: daehyun
***************************************************
*변경일자            변경자             변경내용
***************************************************
 * 2024-09-19       최대현             최초작성
*/

package wizard.fems.basicMgmt.custom;

import org.apache.ibatis.annotations.Mapper;
import wizard.fems.basicMgmt.custom.DTO.customDTO;


import java.util.List;
import java.util.Map;

@Mapper
public interface customMapper {
    List<customDTO> getCustomList(Map<String, Object> param);
    List<customDTO> getAgentName();
    customDTO getCustomDetail(String customID);
    customDTO saveCustomDetail(customDTO customdto);
    customDTO updateCustomDetail(customDTO customdto);
    int deleteCustomID(String customID);

}
