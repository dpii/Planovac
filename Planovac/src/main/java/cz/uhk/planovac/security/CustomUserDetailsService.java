package cz.uhk.planovac.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cz.uhk.planovac.Uzivatel;
import cz.uhk.planovac.jpa.EntityManagerPlanovac;


@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService{


	/**
	 * Returns a populated {@link UserDetails} object. 
	 * The username is first retrieved from the database and then mapped to 
	 * a {@link UserDetails} object.
	 */
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		try {
			EntityManagerPlanovac emp = new EntityManagerPlanovac();
			
			Uzivatel uzivatel = emp.nactiUzivatelePodleLoginu(login);
			
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;
			
			Collection<GrantedAuthority> role = new ArrayList<GrantedAuthority>();
			role.add(new GrantedAuthorityImpl(uzivatel.getRole()));
			
			return new User(uzivatel.getLogin(), uzivatel.getHeslo_hash().toLowerCase(),uzivatel.isPovolen(),
					accountNonExpired,
					credentialsNonExpired,
					accountNonLocked,
					role);
					//getAuthorities(uzivatel.getRole().getRole()));
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Retrieves a collection of {@link GrantedAuthority} based on a numerical role
	 * @param role the numerical role
	 * @return a collection of {@link GrantedAuthority
	 */
	public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}
	
	/**
	 * Converts a numerical role to an equivalent list of roles
	 * @param role the numerical role
	 * @return list of roles as as a list of {@link String}
	 */
	public List<String> getRoles(Integer role) {
		List<String> roles = new ArrayList<String>();
		
		if (role.intValue() == 1) {
			roles.add("ROLE_USER");
			roles.add("ROLE_ADMIN");
			
		} else if (role.intValue() == 2) {
			roles.add("ROLE_USER");
		}
		
		return roles;
	}
	
	/**
	 * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
	 * @param roles {@link String} of roles
	 * @return list of granted authorities
	 */
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new GrantedAuthorityImpl(role));//SimpleGrantedAuthority(role));
		}
		return authorities;
	}
}