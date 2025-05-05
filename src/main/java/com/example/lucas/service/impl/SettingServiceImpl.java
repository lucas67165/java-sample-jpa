package com.example.lucas.service.impl;

import com.example.lucas.entity.Setting;
import com.example.lucas.repository.SettingRepository;
import com.example.lucas.service.SettingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class SettingServiceImpl implements SettingService {

    @Resource
    private SettingRepository settingRepository;

    @Override
    public Setting saveSetting(Setting setting) {
        return updateSetting(setting);
    }

    @Override
    @Transactional
    public List<Setting> bulkUpdateSettings(List<Setting> settings) {
        return settings.stream().map(this::updateSetting).toList();
    }

    private Setting updateSetting(Setting setting) {
        Optional<Setting> existingSetting = settingRepository.findBySettingKey(setting.getSettingKey());
        if (existingSetting.isPresent()) {
            // Merge new values with existing setting
            Setting updatedSetting = existingSetting.get();
            updatedSetting.setSettingValue(setting.getSettingValue());
            updatedSetting.setModule(setting.getModule());
            return settingRepository.save(updatedSetting);
        } else {
            // If not found, save as a new setting
            return settingRepository.save(setting);
        }
    }

    @Override
    public Setting getSettingByKey(String settingKey) {
        return settingRepository.findBySettingKey(settingKey).orElse(null);
    }

    @Override
    public List<Setting> getSettingsByModule(String module) {
        return settingRepository.findByModule(module);
    }

    @Override
    @Transactional
    public void deleteSettingByKey(String settingKey) {
        settingRepository.deleteBySettingKey(settingKey);
    }
}