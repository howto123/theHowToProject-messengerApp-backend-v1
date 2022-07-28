/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package messengerapp.messengerbackendv1.Entities;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lüku
 */

@Repository
public interface AppUserRepository
        extends JpaRepository<AppUser, Integer> {
    
    @Query(value = "SELECT u FROM AppUser u WHERE u.appUserName = ?1")
    List<AppUser> getUserByName(
        String appUserName);
    
  
}
