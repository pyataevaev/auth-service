package com.osu.util;

import com.osu.domain.CurrentUser;
import com.osu.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Ekaterina Pyataeva
 */
public class SecurityHelper {
    public static User getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null){
            return null;
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            CurrentUser currentUser = (CurrentUser) principal;
            return currentUser.getUser();
        }
        return null;
    }
}
