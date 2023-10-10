package com.codegym.spring_boot_sprint_1.controller;

import com.codegym.spring_boot_sprint_1.config.jwt.JwtUtils;
import com.codegym.spring_boot_sprint_1.model.User;
import com.codegym.spring_boot_sprint_1.model.dto.JwtResponse;
import com.codegym.spring_boot_sprint_1.model.dto.LoginRequest;
import com.codegym.spring_boot_sprint_1.model.dto.MessageResponse;
import com.codegym.spring_boot_sprint_1.repositories.IUserRepository;
import com.codegym.spring_boot_sprint_1.service.IUserService;
import com.codegym.spring_boot_sprint_1.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        if (userDetails.isEnabled()) {
            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Lỗi đăng nhập."));
        }
    }


    @PostMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @PatchMapping("/user-reset-password")
    @ResponseBody
    public ResponseEntity<MessageResponse> resetUserPassword(@RequestParam("username") String username,
                                                             @RequestParam("email") String email) {
        User user = userService.findUserByEmail(email);
        if (user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email không tồn tại."));
        } else {
            if (!user.getUsername().equals(username)) {
                return ResponseEntity.badRequest().body(new MessageResponse("Tên đăng nhập không đúng."));
            }
        }
        userService.updateUserPassword(encoder.encode("Abcd1234!"), user.getId());
        return ResponseEntity.ok(new MessageResponse("Reset password thành công"));
    }
}


