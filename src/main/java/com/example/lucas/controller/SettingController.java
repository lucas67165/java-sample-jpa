package com.example.lucas.controller;

import com.example.lucas.data.response.Result;
import com.example.lucas.entity.Setting;
import com.example.lucas.service.SettingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/settings")
@Validated
public class SettingController {

    @Resource
    private SettingService settingService;

    /**
     * Add or update a single setting.
     *
     * @param setting The setting object to save (validated).
     * @return The saved setting wrapped in a Result object.
     */
    @PostMapping
    @PreAuthorize("hasRole('developer')")
    public ResponseEntity<Result<Setting>> saveSetting(@RequestBody @Valid Setting setting) {
        Setting savedSetting = settingService.saveSetting(setting);
        return ResponseEntity.status(HttpStatus.CREATED).body(Result.ok(savedSetting));
    }

    /**
     * Bulk update for multiple settings.
     *
     * @param settings List of settings to update (validated).
     * @return List of updated settings wrapped in a Result object.
     */
    @PutMapping("/bulk-update")
    @PreAuthorize("hasRole('developer')")
    public ResponseEntity<Result<List<Setting>>> bulkUpdateSettings(@RequestBody @Valid List<Setting> settings) {
        if (settings == null || settings.isEmpty()) {
            return ResponseEntity.badRequest().body(Result.error("No settings provided for bulk update."));
        }
        List<Setting> updatedSettings = settingService.bulkUpdateSettings(settings);
        return ResponseEntity.status(HttpStatus.CREATED).body(Result.ok(updatedSettings));
    }

    /**
     * Retrieve a setting by its key.
     *
     * @param settingKey The unique key of the setting.
     * @return The setting, or 404 if not found.
     */
    @GetMapping("/{settingKey}")
    @PreAuthorize("hasRole('developer')")
    public ResponseEntity<Result<Setting>> getSettingByKey(@PathVariable String settingKey) {
        Setting setting = settingService.getSettingByKey(settingKey);
        if (setting != null) {
            return ResponseEntity.ok(Result.ok(setting));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Result.error("Setting not found: " + settingKey));
        }
    }

    /**
     * Retrieve all settings for a specific module.
     *
     * @param module The module name.
     * @return A list of settings for the specified module.
     */
    @GetMapping("/module/{module}")
    @PreAuthorize("hasRole('developer')")
    public ResponseEntity<Result<?>> getSettingsByModule(@PathVariable String module) {
        List<Setting> settings = settingService.getSettingsByModule(module);
        if (settings == null || settings.isEmpty()) {
            return ResponseEntity.ok(Result.ok(List.of().toString(), "No settings found for module: " + module));
        }
        return ResponseEntity.ok(Result.ok(settings));
    }

    /**
     * Delete a setting by its key.
     *
     * @param settingKey The unique key of the setting.
     * @return 204 No Content on success, or 404 if not found.
     */
    @DeleteMapping("/{settingKey}")
    @PreAuthorize("hasRole('developer')")
    public ResponseEntity<Void> deleteSettingByKey(@PathVariable String settingKey) {
        if (settingService.getSettingByKey(settingKey) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        settingService.deleteSettingByKey(settingKey);
        return ResponseEntity.noContent().build();
    }
}