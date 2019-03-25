package com.fanshuaiko.backage.web;

import com.fanshuaiko.backage.dao.CourseDao;
import com.fanshuaiko.backage.entity.Choice;
import com.fanshuaiko.backage.entity.QuestionQueryTerm;
import com.fanshuaiko.backage.service.ChoiceService;
import com.fanshuaiko.backage.utils.ErrorCode;
import com.fanshuaiko.backage.utils.ResultData;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @ClassName QuestionController
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-21 下午1:54
 * @Version 1.0
 **/
@RestController
@RequestMapping("question")
public class QuestionController {

    private static Logger log = LoggerFactory.getLogger(QuestionController.class);
    @Autowired
    ChoiceService choiceService;



    /**
     * 插入一道题目
     * @param choice
     * @return
     */
    @PostMapping("/choice")
    public ResultData insertChoice(Choice choice) {
        try {
            ResultData resultData = choiceService.insert(choice);
            return resultData;
        } catch (Exception e) {
            log.info("--------insertChoice:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.ADD_FAILOR, ErrorCode.ADD_FAILOR_MSG);
        }
    }

    /**
     * 根据id删除题目
     * @param id
     * @return
     */
    @DeleteMapping("/choice/{id}")
    public ResultData deleteChoice(@PathVariable("id") long id){
        try {
            ResultData resultData = choiceService.deleteByPrimaryKey(id);
            return resultData;
        }catch (Exception e){
            log.info("--------deleteChoice:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.DEL_FAILOR, ErrorCode.DEL_FAILOR_MSG);
        }
    }

    /**
     * 修改题目
     * @param choice
     * @return
     */
    @PutMapping("/choice")
    public ResultData updateChoice(Choice choice){
        try {
            ResultData resultData = choiceService.updateByPrimaryKey(choice);
            return resultData;
        }catch (Exception e){
            log.info("--------updateChoice:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.DEL_FAILOR, ErrorCode.DEL_FAILOR_MSG);
        }
    }

    /**
     * 多条件分页查询题目
     * @param queryTerm
     * @return
     */
    @GetMapping("/choice")
    public ResultData choicePageQuery(QuestionQueryTerm queryTerm){
        try {
            ResultData<PageInfo<Choice>> resultData = choiceService.choicePageQuery(queryTerm);
            return resultData;
        }catch (Exception e){
            log.info("--------choicePageQuery:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.QUERY_FAILOR, ErrorCode.QUERY_FAILOR_MSG);
        }
    }

    /**
     * 选择题导入
     * @param file
     * @return
     */
    @PostMapping("/choice/file")
    public ResultData importChoice(MultipartFile file,String type){
        try{
        ResultData resultData = choiceService.importChoice(file,type);
        return resultData;
        }catch (Exception e){
            log.info("--------importChoice:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.ADD_FAILOR_MSG, ErrorCode.ADD_FAILOR_MSG);
        }
    }


}
