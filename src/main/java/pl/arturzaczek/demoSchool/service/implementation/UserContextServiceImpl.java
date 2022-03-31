package pl.arturzaczek.demoSchool.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.entities.BaseEntity;
import pl.arturzaczek.demoSchool.model.repositories.UserRepository;
import pl.arturzaczek.demoSchool.service.UserContextService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserContextServiceImpl implements UserContextService {

    private final UserRepository userRepository;

    public String getLoggedAs() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return authentication.getName();
    }

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

    public boolean hasAnyRole(final List<String> roleNames) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }

        final List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return roles.containsAll(roleNames);
    }

    public boolean isLogged() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    public boolean isLoggedAsSchoolEmployee() {
        return hasAnyRole(List.of("TEACHER","PRINCIPAL","ADMIN"));
    }
}
