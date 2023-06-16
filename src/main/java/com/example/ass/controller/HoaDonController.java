package com.example.ass.controller;

import com.example.ass.entity.*;
import com.example.ass.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/hoa-don")
public class HoaDonController {
    @Autowired
    IGioHangChiTietRepo gioHangChiTietRepository;
//
//    @Autowired
//    IGioHangRepo gioHangRepository;

    @Autowired
    ISanPhamRepo sanPhamRepository;

    @Autowired
    IHoaDonChiTietRepo iHoaDonChiTietRepo;

    @Autowired
    IHoaDonRepo iHoaDonRepo;
    @GetMapping("hien-thi")
    public String pageHoaDon(Model model,
                             HttpSession session){
        Users users = (Users) session.getAttribute("userLogged");
        List<HoaDon> hoaDon=iHoaDonRepo.findByUsers(users);
        for(HoaDon hdct:hoaDon){
            List<HoaDonChiTiet> list=iHoaDonChiTietRepo.findByIdHoaDon(hdct);
            model.addAttribute("listHoaDon", list);
        }
        List<HoaDonChiTiet> list1=iHoaDonChiTietRepo.findAll();
        model.addAttribute("listHoaDon", list1);
        return "page/web/HoaDon";
    }
    @GetMapping("/add/{id}")
    public String addHoaDon(Model model,
                            HttpSession session,
                            @PathVariable("id") String id){
        Users users = (Users) session.getAttribute("userLogged");
        HoaDon hoaDon1 = new HoaDon();
        hoaDon1.setUsers(users);
        hoaDon1.setNgayTao(new Date());
        hoaDon1.setTrangThai(false);
        iHoaDonRepo.save(hoaDon1);
        List<HoaDon> hoaDon=iHoaDonRepo.findByUsers(users);
        HoaDonChiTiet hoaDonChiTiet=new HoaDonChiTiet();
        GioHang gioHang= (GioHang) session.getAttribute("idGioHang");
        List<GioHangChiTiet> ghs = gioHangChiTietRepository.findAllByIdGioHang(gioHang);
        List<GioHangChiTiet> gioHangChiTiet=gioHangChiTietRepository.findAll();
        for(GioHangChiTiet gh : ghs){
            for(HoaDon hoaDon2:hoaDon) {
                HoaDonChiTietId hoaDonChiTietId = new HoaDonChiTietId(hoaDon2, gh.getId().getSanPham());
                hoaDonChiTiet.setId(hoaDonChiTietId);
                hoaDonChiTiet.setQuantity(gh.getSoLuong());
                hoaDonChiTiet.setPrice(gh.getSoLuong() * (gh.getId().getSanPham().getGiaBan()));
                iHoaDonChiTietRepo.save(hoaDonChiTiet);
            }
        }

        return "redirect:/hoa-don/hien-thi";
    }

}
