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
        HoaDon hoaDon=iHoaDonRepo.findByUsers(users);
        List<HoaDonChiTiet> list=iHoaDonChiTietRepo.findByIdHoaDon(hoaDon);
        model.addAttribute("listHoaDon", list);
        return "page/web/HoaDon";
    }
    @GetMapping("/add/{id}")
    public String addHoaDon(Model model,
                            HttpSession session,
                            @PathVariable("id") String id){
        Users users = (Users) session.getAttribute("userLogged");
//        SanPham sanPham = sanPhamRepository.findByUsers(users);
        HoaDon hoaDon=iHoaDonRepo.findByUsers(users);
        HoaDonChiTiet hoaDonChiTiet=new HoaDonChiTiet();
        GioHang gioHang= (GioHang) session.getAttribute("idGioHang");
        GioHangChiTiet gh = gioHangChiTietRepository.findAllByIdGioHang(gioHang);
        HoaDonChiTietId hoaDonChiTietId=new HoaDonChiTietId(hoaDon,gh.getId().getSanPham());
        List<GioHangChiTiet> gioHangChiTiet=gioHangChiTietRepository.findAll();
        for(int i=0;i<gioHangChiTiet.size();i++){
            hoaDonChiTiet.setId(hoaDonChiTietId);
            hoaDonChiTiet.setQuantity(gh.getSoLuong());
            hoaDonChiTiet.setPrice(gh.getSoLuong()*(gh.getId().getSanPham().getGiaBan()));
            iHoaDonChiTietRepo.save(hoaDonChiTiet);

        }
        gioHangChiTietRepository.delete(gh);
        hoaDon.setTrangThai(true);
        hoaDon.setNgayThanhToan(new Date());
        return "redirect:/hoa-don/hien-thi";
    }

}
