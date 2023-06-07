package com.example.ass.controller;

import com.example.ass.entity.LoaiSP;
import com.example.ass.repository.ILoaiSpRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/loai-sp")
public class LoaiSpController {

//    @Autowired
//    HttpServletRequest request;
    @Autowired
    private ILoaiSpRepo iLoaiSpRepo;

    @GetMapping("/hien-thi")
    private String loaiSpPage(Model model,
                              @RequestParam(name = "pageSize", defaultValue = "3") Integer pageSize,
                              @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum
    ) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<LoaiSP> page = iLoaiSpRepo.findAll(pageable);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("listLoaiSp", page.getContent());
        return "page/admin/LoaiSp";
    }
    @GetMapping("/add")
    public String addLoaiSp(Model model) {
        List<LoaiSP> loaiSP1=iLoaiSpRepo.findAll();
        model.addAttribute("loaiSP", new LoaiSP());
        model.addAttribute("loaiSpChon",loaiSP1);
        return "page/admin/AddLoaiSp";
    }

    @PostMapping("/save")
    public String checkAdd(Model model,@Valid
                           @ModelAttribute("loaiSP") LoaiSP loaiSP, BindingResult result) {
        if (result.hasErrors()) {
            return "page/admin/AddLoaiSp";
        }
        iLoaiSpRepo.save(loaiSP);
        return "redirect:/loai-sp/hien-thi";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        LoaiSP loaiSP = iLoaiSpRepo.findById(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("loaisp", loaiSP);
        return "page/admin/UpdateLoaiSp";
    }
    @PostMapping("/update/{id}")
    public String updateLoaiSp(@PathVariable("id") String id, @Valid LoaiSP loaiSP,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            loaiSP.setId(id);
            return "page/admin/UpdateLoaiSp";
        }
        loaiSP.setId(id);
        LoaiSP loaiSPUpdate = iLoaiSpRepo.findById(id);
        loaiSP.setNgayTao(loaiSPUpdate.getNgayTao());
        iLoaiSpRepo.save(loaiSP);
        return "redirect:/loai-sp/hien-thi";
    }

    @GetMapping("/delete/{id}")
    public String deleteLoaiSp(@PathVariable("id") String id, Model model) {
        LoaiSP loaiSP = iLoaiSpRepo.findById(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        iLoaiSpRepo.delete(loaiSP);
        return "redirect:/loai-sp/hien-thi";
    }

}
