package com.infosys.weixin.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infosys.basic.entity.Words;
import com.infosys.basic.service.IWordsService;
import com.infosys.basic.util.Constants;

@RequestMapping("/picword")
@Controller
public class PicWordController {
    @Inject
    private IWordsService wordsService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "picword/add";
    }

    @RequestMapping(value = "/addByJson", method = RequestMethod.POST)
    public @ResponseBody String addByJson(String brief, String htmlcontent, HttpSession session) {
        String rtnMsg = "添加失败";// 返回信息
        if (StringUtils.isBlank(htmlcontent)) {
            return rtnMsg;
        } else {
            Words word = new Words();
            word.setBrief(brief);
            word.setHtmlContents(htmlcontent);
            word.setLastUpdateTime(new Date());
            word.setStatus(Constants.T_USER_STATUS_NORMAL);
            wordsService.add(word);
        }
        rtnMsg = "添加成功";
        return rtnMsg;
    }

    @RequestMapping("/list")
    public String list(String brief, HttpSession session, Model model) {
        model.addAttribute("wordsList", wordsService.listByCondition(brief));
        return "picword/list";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable int id, Model model) {
        Words u = wordsService.load(id);
        model.addAttribute("words", u);
        return "picword/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable int id, Words words) {
        Words tu = wordsService.load(id);
        tu.setLastUpdateTime(new Date());
        tu.setBrief(words.getBrief());
        tu.setHtmlContents(words.getHtmlContents());
        tu.setStatus(words.getStatus());
        wordsService.update(tu);
        return "redirect:/picword/list";
    }

    @RequestMapping(value = "/publicDetail/{id}", method = RequestMethod.POST)
    public @ResponseBody String publicDetail(@PathVariable int id) {
        wordsService.deleteOther(id);
        return "发布成功";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        wordsService.delete(id);
        return "redirect:/picword/list";
    }

    @RequestMapping(value = "/disable/{id}", method = RequestMethod.GET)
    public String disable(@PathVariable int id) {
        Words tu = wordsService.load(id);
        tu.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_DELETE);
        wordsService.update(tu);
        return "redirect:/picword/list";
    }

}
