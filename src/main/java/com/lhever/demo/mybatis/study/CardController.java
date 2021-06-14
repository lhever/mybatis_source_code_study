package com.lhever.demo.mybatis.study;

import com.lhever.demo.mybatis.study.dao.CardDao;
import com.lhever.demo.mybatis.study.pojo.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2019/12/21 12:32
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019/12/21 12:32
 * @modify by reason:{方法名}:{原因}
 */
@RestController
@RequestMapping("card")
public class CardController {


    @Autowired
    private CardDao cardDao;


    @ResponseBody
    @GetMapping("bySId")
    public Card getByStudentId(Integer studentId) {
        return cardDao.findCardByStudentId(studentId);
    }








}
