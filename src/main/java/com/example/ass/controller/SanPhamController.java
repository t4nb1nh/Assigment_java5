package com.example.ass.controller;

import com.example.ass.entity.LoaiSP;
import com.example.ass.entity.SanPham;
import com.example.ass.repository.ILoaiSpRepo;
import com.example.ass.repository.ISanPhamRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/san-pham")
public class SanPhamController {
    @Autowired
    private ISanPhamRepo iSanPhamRepo;
    @Autowired
    private ILoaiSpRepo iLoaiSpRepo;

    @GetMapping("/hien-thi")
    private String spPage(Model model,
                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum
    ) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPham> page = iSanPhamRepo.findAll(pageable);

        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("listSp", page.getContent());
        return "page/admin/SanPham";
    }

    @GetMapping("/trang-chu")
    private String spTrangchu(Model model,
                              @RequestParam(name = "pageSize", defaultValue = "8") Integer pageSize,
                              @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum
    ) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPham> page = iSanPhamRepo.findAll(pageable);
        List<LoaiSP> list = iLoaiSpRepo.findAll();

        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("listLoaiSp", list);
        return "pages/index";
    }

    @GetMapping("/add")
    public String addSanPham(Model model) {
        model.addAttribute("loai", new SanPham());
        model.addAttribute("listLoaiSP", iLoaiSpRepo.findAll());
        return "page/admin/AddSanPham";
    }

    @PostMapping("/save")
    public String checkAdd(@Valid @ModelAttribute("loai") SanPham loai, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "page/admin/AddSanPham";
        }
        iSanPhamRepo.save(loai);
        return "redirect:/san-pham/hien-thi";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        SanPham sanPham = iSanPhamRepo.findById(id);
        model.addAttribute("listLoaiSP1", iLoaiSpRepo.findAll());
        model.addAttribute("sanPham", sanPham);
        return "page/admin/UpdateSanPham";
    }

    @GetMapping("/shop")
    private String spShop(Model model,
                          @RequestParam(name = "pageSize", defaultValue = "8") Integer pageSize,
                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum
    ) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPham> page = iSanPhamRepo.findAll(pageable);
        List<LoaiSP> list = iLoaiSpRepo.findAll();
        model.addAttribute("totalPage1", page.getTotalPages());
        model.addAttribute("listSanPham1", page.getContent());
        model.addAttribute("listLoaiSp1", list);
        return "pages/SanPham";
    }

    @GetMapping("/{id}/loai-sp")
    public String showListLoaiSp(Model model, @PathVariable("id") String id,
                                 @RequestParam(name = "pageSize", defaultValue = "8") Integer pageSize,
                                 @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum
    ) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        List<SanPham> page = iSanPhamRepo.findAllByIdLoai(id);

        List<LoaiSP> list = iLoaiSpRepo.findAll();
//        Page<SanPham> page1 = page.get(pageable);
//        Page<SanPham> test=iSanPhamRepo.findAllByIdLoai(id)
        model.addAttribute("listSpTheoLoaiSp", page);
        model.addAttribute("listLoaiSp1", list);
        return "pages/SanPham";
    }

    @PostMapping("/update/{id}")
    public String updateLoaiSp(@PathVariable("id") String id, @Valid SanPham sanPham,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            sanPham.setId(id);
            return "page/admin/UpdateLoaiSp";
        }

        iSanPhamRepo.save(sanPham);
        return "redirect:/san-pham/hien-thi";
    }

    @GetMapping("/delete/{id}")
    public String deleteLoaiSp(@PathVariable("id") String id, Model model) {
        SanPham sanPham = iSanPhamRepo.findById(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        iSanPhamRepo.delete(sanPham);
        return "redirect:/san-pham/hien-thi";
    }

    @GetMapping("/detail/{id}")
    public String detailPage(Model model,
                             @PathVariable(name = "id") String id
    ) {
        model.addAttribute("detail", iSanPhamRepo.findById(id));
        return "pages/Detail";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file, Model model) {
        // Kiểm tra nếu tập tin được tải lên không rỗng
        if (!file.isEmpty()) {
            try {
                // Lưu trữ tập tin vào thư mục lưu trữ
                String fileName = file.getOriginalFilename();
                // Thay đổi đường dẫn dưới đây để lưu trữ ảnh theo yêu cầu của bạn
                String uploadDir = "static/image";
                file.transferTo(new File(uploadDir + fileName));

                // Thêm thông tin về tên tập tin vào model để hiển thị trên view
                model.addAttribute("fileName", fileName);
            } catch (IOException e) {
                // Xử lý nếu có lỗi xảy ra trong quá trình tải lên
                e.printStackTrace();
            }
        }
        return "upload-result"; // Tên của view để hiển thị kết quả tải lên ảnh
    }
}
