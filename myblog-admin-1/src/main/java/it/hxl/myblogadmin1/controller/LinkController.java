package it.hxl.myblogadmin1.controller;

import it.hxl.myblogadmin1.entity.Link;
import it.hxl.myblogadmin1.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @RequestMapping("/updateStatus")
    @ResponseBody
    public String updateStatus(int id){
        linkService.updateStatusById(id);
        return "ok";
    }

    @RequestMapping("/add")
    public String addLink(Link link){
        linkService.insertLink(link);
        return "redirect:/link/1";
    }

    @RequestMapping("/updateLink")
    public String updateLink(Link link, HttpSession session){
        linkService.updateLinks(link);
        int page = (int)session.getAttribute("page");
        return "redirect:/link/" + page;
    }

    @RequestMapping("/deleteAllChecked")
    public String deleteAllChecked(int[] checkbox){
        String ids = Arrays.toString(checkbox);
        ids = ids.substring(1, ids.length()-1);
        linkService.deleteMultiLinks(ids);
        return "redirect:/link/1";
    }


    @RequestMapping("/delete")
    @ResponseBody
    public String deleteComments(int id){
        linkService.deleteLinkById(id);
        return "ok";
    }

    @RequestMapping("/see")
    @ResponseBody
    public Link seeMessage(int id){
        return linkService.findLinkById(id);
    }

    @GetMapping({"/{current}"})
    public String link(@PathVariable(value = "current") int current, HttpServletRequest request){
        request.setAttribute("handleCounts", linkService.getHandleCounts());
        request.getSession().setAttribute("page", current);
        int pageSize = 10;
        if (current == 0){
            current = 1;
        }
        List<Link> links = linkService.findLinksByPage(pageSize, current);
        request.setAttribute("links", links);
        request.setAttribute("current", current);
        request.setAttribute("pages", size2Arr((int) Math.ceil(linkService.getLinkCount() / (float)pageSize)));
        return "flink";
    }

    private int[] size2Arr(int size){
        int[] arr = new int[size];
        for (int i = 0; i < size; i++){
            arr[i] = i + 1;
        }
        return arr;
    }

}
