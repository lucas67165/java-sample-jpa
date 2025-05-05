package com.example.lucas.service;

import com.example.lucas.entity.Setting;

import java.util.List;

public interface SettingService {

    /**
     * Add or update a single setting.
     *
     * @param setting The setting object to save.
     * @return The saved setting.
     */
    Setting saveSetting(Setting setting);

    /**
     * Bulk update for multiple settings.
     *
     * @param settings List of settings to update.
     * @return List of updated settings.
     */
    List<Setting> bulkUpdateSettings(List<Setting> settings);

    /**
     * Retrieve a setting by its key.
     *
     * @param settingKey The unique key of the setting.
     * @return The setting, or null if not found.
     */
    Setting getSettingByKey(String settingKey);

    /**
     * Retrieve all settings for a specific module.
     *
     * @param module The module name.
     * @return A list of settings for the specified module.
     */
    List<Setting> getSettingsByModule(String module);

    /**
     * Delete a setting by its key.
     *
     * @param settingKey The unique key of the setting.
     */
    void deleteSettingByKey(String settingKey);
}