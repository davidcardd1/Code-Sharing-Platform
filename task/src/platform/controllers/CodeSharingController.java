package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.model.Code;
import platform.model.IdObject;
import platform.service.CodeService;
import java.util.List;

@Controller
public class CodeSharingController {

    @Autowired
    CodeService codeService;

    @GetMapping("/code/new")
    public String getNewCode() {
        return "getCode";
    }

    @PostMapping("/api/code/new")
    @ResponseBody
    public IdObject newCode(@RequestBody Code codeNew) {
        System.out.println("[DEBUG] NEW" + codeNew);
        Code code = new Code(codeNew.getCode(), codeNew.getTime(), codeNew.getViews());
        return new IdObject(codeService.save(code).getId());
    }

    @GetMapping("/code/latest")
    public String getLatestHTMLCode(Model model) {
        List<Code> codeList = codeService.findLatestCodes();
        if (codeList != null) {
            model.addAttribute("codes", codeList);
            //return String.format(codeHTML, code.getCode(), code.getDate());
            return "latest";
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @GetMapping("api/code/latest")
    @ResponseBody
    public List<Code> getLatestCode() {
        List<Code> codeList = codeService.findLatestCodes();
        if (codeList != null) {
            System.out.println("[DEBUG] LATEST" + codeList);
            return codeList;
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/code/{n}")
    public String getHTMLCode(@PathVariable String n, Model model) {
        Code code = codeService.findById(n);
        if(code != null) {
            List<Code> codeList = List.of(code);
            model.addAttribute("codes", codeList);
            return "code";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("api/code/{n}")
    @ResponseBody
    public Code getCode(@PathVariable String n) {
        Code code = codeService.findById(n);
        if (code != null) {
            System.out.println("[DEBUG] CODE ID" + code);
            return code;
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}
