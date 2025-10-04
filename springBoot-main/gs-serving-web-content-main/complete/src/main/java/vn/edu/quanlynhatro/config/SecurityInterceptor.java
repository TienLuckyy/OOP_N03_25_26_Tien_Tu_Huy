package vn.edu.quanlynhatro.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                           @NonNull HttpServletResponse response,
                           @NonNull Object handler) throws Exception {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        // Cho phép truy cập các URL công khai
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/login") || 
            requestURI.equals("/") || 
            requestURI.startsWith("/css/") || 
            requestURI.startsWith("/js/") ||
            requestURI.startsWith("/images/")) {
            return true;
        }
        
        // Kiểm tra đăng nhập
        if (username == null) {
            response.sendRedirect("/login");
            return false;
        }
        
        return true;
    }
}