package com.example.ass.controller;


import com.example.ass.entity.*;
import com.example.ass.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/gio-hang")
public class GioHangController {

    @Autowired
    IGioHangChiTietRepo gioHangChiTietRepository;

    @Autowired
    IGioHangRepo gioHangRepository;

    @Autowired
    ISanPhamRepo sanPhamRepository;
    @Autowired
    IHoaDonChiTietRepo iHoaDonChiTietRepo;

    @Autowired
    IHoaDonRepo iHoaDonRepo;


    @GetMapping("/hien-thi")
    public String pageGioHang(Model model,
                              HttpSession session) {
        Users users = (Users) session.getAttribute("userLogged");
        GioHang gioHang = gioHangRepository.findByUsers(users);
        List<GioHangChiTiet> list = gioHangChiTietRepository.findByIdGioHang(gioHang);
        Integer soLuong = 0;
        for (GioHangChiTiet ghct : list) {
            soLuong += Integer.parseInt(String.valueOf(ghct.getSoLuong()));
        }
        session.setAttribute("idGioHang", gioHang);
        session.removeAttribute("soLuong");
        session.setAttribute("soLuong", soLuong);

        model.addAttribute("list", list);
        return "page/web/GioHang";
    }

    @GetMapping("/them-gio-hang/{id}")
    public String themGioHang(Model model,
                              HttpSession session,
                              @PathVariable("id") String id
    ) {

        SanPham sanPham = sanPhamRepository.findById(id);
        Users users = (Users) session.getAttribute("userLogged");
        GioHang gioHang = gioHangRepository.findByUsers(users);
        System.out.println(gioHang.getId());
        GioHangChiTiet gh = gioHangChiTietRepository.findByIdSanPhamAndIdGioHang(sanPham, gioHang);
        if (gh != null) {
            gh.setSoLuong(gh.getSoLuong() + 1);
            gioHangChiTietRepository.save(gh);
            return "redirect:/gio-hang/hien-thi";
        }
        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
        GioHangChiTietId gioHangChiTietId = new GioHangChiTietId(gioHang, sanPham);
        gioHangChiTiet.setId(gioHangChiTietId);
        gioHangChiTiet.setSoLuong(1);
        gioHangChiTietRepository.save(gioHangChiTiet);
        return "redirect:/gio-hang/hien-thi";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "idSP") String idSP,
                         @RequestParam(name = "idGioHang") String idGH
    ) {
        SanPham sanPham = new SanPham();
        sanPham.setId(idSP);
        GioHang gioHang = new GioHang();
        gioHang.setId(idGH);
        GioHangChiTietId gioHangChiTietId = new GioHangChiTietId(gioHang, sanPham);
        gioHangChiTietRepository.deleteById(gioHangChiTietId);
        return "redirect:/gio-hang/hien-thi";
    }

    @GetMapping("/giam")
    public String giamSoLuong(
            @RequestParam(name = "idSP") String idSP,
            @RequestParam(name = "idGioHang") String idGH
    ) {
        SanPham sanPham = new SanPham();
        sanPham.setId(idSP);
        GioHang gioHang = new GioHang();
        gioHang.setId(idGH);
        GioHangChiTietId gioHangChiTietId = new GioHangChiTietId(gioHang, sanPham);
        GioHangChiTiet gioHangChiTiet = gioHangChiTietRepository.findById(gioHangChiTietId).get();
        gioHangChiTiet.setSoLuong(gioHangChiTiet.getSoLuong() - 1);
        if (gioHangChiTiet.getSoLuong() <= 0) {
            gioHangChiTietRepository.deleteById(gioHangChiTietId);
        } else {
            gioHangChiTietRepository.save(gioHangChiTiet);
        }
        System.out.println(gioHangChiTiet.getSoLuong());
        return "redirect:/gio-hang/hien-thi";
    }

    @GetMapping("/tang")
    public String tangSoLuong(
            @RequestParam(name = "idSP") String idSP,
            @RequestParam(name = "idGioHang") String idGH
    ) {
        SanPham sanPham = new SanPham();
        sanPham.setId(idSP);
        GioHang gioHang = new GioHang();
        gioHang.setId(idGH);
        GioHangChiTietId gioHangChiTietId = new GioHangChiTietId(gioHang, sanPham);
        GioHangChiTiet gioHangChiTiet = gioHangChiTietRepository.findById(gioHangChiTietId).get();
        gioHangChiTiet.setSoLuong(gioHangChiTiet.getSoLuong() + 1);
        gioHangChiTietRepository.save(gioHangChiTiet);
        System.out.println(gioHangChiTiet.getSoLuong());
        return "redirect:/gio-hang/hien-thi";
    }
}
