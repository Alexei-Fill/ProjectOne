package com.SpEx7.security;

import com.SpEx7.entity.PortalUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class UserPrincipal implements UserDetails {

    private PortalUser portalUser;

    public UserPrincipal(PortalUser portalUser) {
        this.portalUser = portalUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return portalUser.getPassword();
    }

    @Override
    public String getUsername() {
        return portalUser.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public PortalUser getPortalUser() {
        return portalUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return portalUser.equals(that.portalUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portalUser);
    }
}
