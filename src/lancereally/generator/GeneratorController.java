package lancereally.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import com.alibaba.fastjson.JSONArray;
import com.snpit.api.common.GlobalResponse;
import com.snpit.generator.code.CodeExecutor;
import com.snpit.generator.code.base.ContextParam;
import com.snpit.generator.code.base.MpParam;
import com.snpit.generator.db.entity.DatabaseInfo;
import com.snpit.generator.model.TableFieldInfo;
import com.snpit.generator.utils.DbSourceLink;
import com.snpit.generator.utils.DbUtil;
import com.snpit.generator.utils.ZipCompress;
import com.snpit.generator.utils.ZipDownloadUtil;
import javafx.scene.control.Tab;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.swing.table.DefaultTableCellRenderer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author 徐东方.
 * @Program: snpit_plateform.
 * @ClassName: GeneratorController.
 * @date 2020年08月03日 09:27:14.
 * @Copyright 国核信息科技有限公司.
 * @Description: .
 * @Version: V1.0
 */

@RestController
@RequestMapping("generator")
public class GeneratorController {

    /**
     * @Description: 获取数据源列表
     * @Param: []
     * @Return: com.snpit.api.common.GlobalResponse<java.util.List<com.snpit.generator.db.entity.DatabaseInfo>>
     * @Author: lancereally
     * @Date: 2020/8/5 11:21
     */
    @GetMapping("/source")
    public GlobalResponse<List<DatabaseInfo>> selectDb() {
        List<DatabaseInfo> sourceList = DbSourceLink.listDbSource(null);
        return GlobalResponse.success(sourceList);
    }

    /**
     * @Description: 获取选中数据源下的表列
     * @Param: [param]
     * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: lancereally
     * @Date: 2020/8/5 11:21
     */
    @GetMapping("tables")
    public List<Map<String, Object>> getTables(@RequestParam Long dbId) {
        //TODO 清除session
        if (dbId == 0L)
            return new ArrayList<>();

        List<DatabaseInfo> param = DbSourceLink.listDbSource(dbId);
        List<Map<String, Object>> tableList = DbUtil.selectTables(param.get(0));
        return tableList;
    }

    /**
     * @Description: 获取选中表下的字段列
     * @Param: [param, tableName]
     * @Return: java.util.List<com.snpit.generator.model.TableFieldInfo>
     * @Author: lancereally
     * @Date: 2020/8/5 11:21
     */
    @GetMapping("fields")
    public List<TableFieldInfo> getFields(@RequestParam Long dbId, @RequestParam String tableName, String page, String limit) {
        List<DatabaseInfo> param = DbSourceLink.listDbSource(dbId);
        List<TableFieldInfo> fieldList = DbUtil.getTableFields(param.get(0), tableName);
        return fieldList;
    }

    /**
     * @Description: 配置字段生成session
     * @Param: [tableName, fieldList, request]
     * @Return: com.snpit.api.common.GlobalResponse<java.lang.String>
     * @Author: lancereally
     * @Date: 2020/8/5 11:21
     */
    @RequestMapping(value = "/session", method = RequestMethod.POST)
    @ResponseBody
    public GlobalResponse<Set<String>> getSession(@RequestParam(value = "tableName") String tableName, @RequestParam String fieldList, HttpServletRequest request) {
        String key = "GENERATOR_TABLE";
        JSONArray jsonArray = JSONArray.parseArray(fieldList);
        List<TableFieldInfo> fields = jsonArray.toJavaList(TableFieldInfo.class);

        Map<String, List<TableFieldInfo>> value;
        if (request.getSession().getAttribute(key) != null){
            value = (Map)request.getSession().getAttribute(key);
        }else{
            value = new HashMap<>();
        }
        value.put(tableName, fields);
        request.getSession().setAttribute(key, value);
        String sessionId = request.getSession().getId();//Jsession
        return GlobalResponse.success(value.keySet());
    }

    /**
     * @Description: 代码生成
     * @Param: [tableList, author, proPackage, dbId]
     * @Return: org.springframework.http.ResponseEntity<org.springframework.core.io.InputStreamResource>
     * @Author: lancereally
     * @Date: 2020/8/5 11:21
     */
    @GetMapping("/code")
    public ResponseEntity<InputStreamResource> codeGenerator(String tableList, String author, String proPackage, Long dbId) throws IOException {
        ContextParam contextParam = new ContextParam();
        List<DatabaseInfo> list = DbSourceLink.listDbSource(dbId);
        DatabaseInfo databaseInfo = list.get(0);
        contextParam.setJdbcDriver(databaseInfo.getJdbcDriver());
        contextParam.setJdbcUserName(databaseInfo.getUserName());
        contextParam.setJdbcPassword(databaseInfo.getPassword());
        contextParam.setJdbcUrl(databaseInfo.getJdbcUrl());
        contextParam.setOutputPath("temp");
        contextParam.setAuthor(author);
        contextParam.setProPackage(proPackage);

        MpParam mpContextParam = new MpParam();
        mpContextParam.setGeneratorInterface(Boolean.valueOf(true));
        String[] tableNames = tableList.split(",");
        mpContextParam.setIncludeTables(tableNames);

        CodeExecutor.executor(contextParam, mpContextParam);

        //TODO
        String path = "F:/snpit_plateform/"+contextParam.getOutputPath();
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String fileName = contextParam.getAuthor()+".zip";
        String filePath = "F:/snpit_plateform/"+fileName;
//      System.getProperty("java.io.tmpdir") + File.separator + "temp" + File.separator + fileName;
        ZipCompress zip = new ZipCompress(filePath);
        zip.compress(path+"/");
        String msg = "success";
        try{
            ResponseEntity<InputStreamResource> resourceResponseEntity = zip.renderFile(fileName, filePath);
            return resourceResponseEntity;
        }catch (Exception e){
            msg = e.getMessage();
        }finally {
            File file = new File(path);
            zip.delete(file);
            System.out.println(msg);
        }
        return null;
    }
}
