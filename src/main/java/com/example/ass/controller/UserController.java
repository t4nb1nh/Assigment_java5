package com.example.ass.controller;

import com.example.ass.entity.GioHang;
import com.example.ass.entity.LoaiSP;
import com.example.ass.entity.SanPham;
import com.example.ass.entity.Users;
import com.example.ass.repository.IGioHangRepo;
import com.example.ass.repository.IUsersRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
public class UserController {
    //    @Autowired
//    HttpServletRequest request;
    @Autowired
    private IUsersRepo iUsersRepo;
    @Autowired
    private IGioHangRepo iGioHangRepo;

    @GetMapping("/login")
    public String getFromLogin(Model model) {
        model.addAttribute("user", new Users());
        return "page/web/Login";
    }

    @PostMapping("/login")
    public String checkLogin(Model model,
                             @Valid @ModelAttribute("user") Users users,
                             BindingResult result,
                             HttpSession session,
                             RedirectAttributes attributes
    ) {

        if (result.hasErrors()) {
            return "/page/web/Login";
        }
        Users userFromDb = iUsersRepo.findByUsernameAndPassword(users.getUsername(), users.getPassword());

        if (userFromDb != null) {

            if (userFromDb.isRole() == true) {
                session.setAttribute("adminLogged", userFromDb);
                return "redirect:/loai-sp/hien-thi";
            } else {
                session.setAttribute("userLogged", userFromDb);
                return "redirect:/san-pham/trang-chu";
            }
        }
        model.addAttribute("message", "sai thong tin");
        return "/page/web/Login";
    }

    @GetMapping("/signUp-form")
    public String addSanPham(Model model) {
        model.addAttribute("uesrsCreate", new Users());
        return "page/web/CreateAccount";
    }

    @PostMapping("/signUp")
    public String checkAdd(
            @Valid @ModelAttribute("uesrsCreate") Users uesrsCreate, BindingResult result) {
        if (result.hasErrors()) {
            return "page/web/CreateAccount";
        }
        uesrsCreate.setRole(true);
        GioHang cartUser = new GioHang();
        cartUser.setUsers(uesrsCreate);
        iUsersRepo.save(uesrsCreate);
        if (uesrsCreate.isRole()) {
            iGioHangRepo.save(cartUser);
        }

        return "redirect:/login";
    }

    @GetMapping("/user/hien-thi")
    private String loaiSpPage(Model model,
                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                              @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum
    ) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Users> page = iUsersRepo.findAll(pageable);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("listUser", page.getContent());
        return "page/admin/Users";
    }

    @GetMapping("/user/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Users users = iUsersRepo.findById(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("loaiUser", users);
        return "page/admin/UpdateUser";
    }

    @PostMapping("/user/update/{id}")
    public String updateLoaiSp(@PathVariable("id") String id, @Valid Users users,
                               BindingResult result, Model model) {

        users.setId(id);

        model.addAttribute("loaiUser", users);
        iUsersRepo.save(users);
        return "redirect:/user/hien-thi";
    }
}

