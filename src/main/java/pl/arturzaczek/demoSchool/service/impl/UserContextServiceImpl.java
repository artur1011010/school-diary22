package pl.arturzaczek.demoSchool.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.entities.BaseEntity;
import pl.arturzaczek.demoSchool.model.repositories.UserRepository;
import pl.arturzaczek.demoSchool.service.UserContextService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserContextServiceImpl implements UserContextService {

    private final UserRepository userRepository;

    @Override
    public String getLoggedAs() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return authentication.getName();
    }

    @Override
    public Long getLoggedId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return userRepository
                .findFirstByEmail(authentication.getName())
                .map(BaseEntity::getId)
                .orElse(0L);
    }

    @Override
    public boolean hasRole(final String roleName) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(s -> s.equals(roleName));
    }

    @Override
    public boolean hasAnyRole(final List<String> roleNames) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        final List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if(log.isDebugEnabled()){
            log.debug("hasAnyRole: {}, ROLES: {}, has: {}", roleNames, roles, roles.containsAll(roleNames));
        }
        return !Collections.disjoint(roles, roleNames);
    }

    @Override
    public boolean isLogged() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    @Override
    public boolean isLoggedAsStudent() {
        return hasAnyRole(List.of("ROLE_STUDENT"));
    }

    @Override
    public boolean isLoggedAsSchoolEmployee() {
        return hasAnyRole(List.of("ROLE_TEACHER","ROLE_PRINCIPAL","ROLE_ADMIN"));
    }

    @Override
    public boolean isLoggedAsAdmin() {
        return hasAnyRole(List.of("ROLE_ADMIN"));
    }
}
