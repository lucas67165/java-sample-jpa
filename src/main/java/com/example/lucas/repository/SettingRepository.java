package com.example.lucas.repository;

import com.example.lucas.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {

    // Find by settingKey
    Optional<Setting> findBySettingKey(String settingKey);

    // Find all settings by module
    List<Setting> findByModule(String module);

    // Delete by settingKey
    void deleteBySettingKey(String settingKey);
}