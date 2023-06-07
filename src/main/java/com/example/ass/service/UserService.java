package com.example.ass.service;

import com.example.ass.entity.UserActivation;
import com.example.ass.entity.Users;
import com.example.ass.repository.IUserActivationRepository;
import com.example.ass.repository.IUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

//@Service
public class UserService {
//    @Autowired
//    private IUserActivationRepository userActivationRepository;
//    @Autowired
//    private IUsersRepo userRepository;
//    @Autowired
//    private EmailService emailService;
//
//    public void registerUser(Users user) {
//        // Lưu thông tin người dùng vào cơ sở dữ liệu
//        userRepository.save(user);
//
//        // Tạo và lưu thông tin kích hoạt tài khoản
//        UserActivation activation = new UserActivation();
//        activation.setUser(user);
//        activation.setActivationCode(generateRandomCode());
//        userActivationRepository.save(activation);
//
//        // Gửi email kích hoạt tài khoản
//        String activationLink = "http://your_domain/activate?user_id=" + user.getId() + "&activation_code=" + activation.getActivationCode();
//        emailService.sendActivationEmail(user.getEmail(), activationLink);
//    }
//
//    private String generateRandomCode() {
//        // Tạo mã kích hoạt ngẫu nhiên, ví dụ: sử dụng UUID.randomUUID()
//        return UUID.randomUUID().toString();
//    }
//    public void activateAccount(String userId, String activationCode) {
//        Users user = userRepository.findById(userId);
////                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại"));
//        UserActivation activation = userActivationRepository.findByActivationCode(activationCode);
//
//        if (activation == null) {
//            throw new IllegalArgumentException("Mã kích hoạt không hợp lệ");
//        }
//
//        // Kích hoạt tài khoản
//        user.setEnabled(true);
//        userRepository.save(user);
//
//        // Xóa thông tin kích hoạt
//        userActivationRepository.delete(activation);
//    }
}
