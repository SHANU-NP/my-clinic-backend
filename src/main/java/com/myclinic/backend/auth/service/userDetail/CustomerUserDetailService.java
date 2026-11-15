package com.myclinic.backend.auth.service.userDetail;

import com.myclinic.backend.auth.constants.AuthConstants;
import com.myclinic.backend.auth.entity.UserAuth;
import com.myclinic.backend.auth.service.repoHandler.UserAuthRepoHandler;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    private final UserAuthRepoHandler userAuthRepoHandler;

    public CustomerUserDetailService(UserAuthRepoHandler userAuthRepoHandler) {
        this.userAuthRepoHandler = userAuthRepoHandler;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth user = userAuthRepoHandler.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(AuthConstants.USER_NOT_FOUND));
        return new CustomUserDetails(user);
    }
}
