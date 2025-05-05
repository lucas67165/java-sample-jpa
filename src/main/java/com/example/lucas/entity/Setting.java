package com.example.lucas.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true, nullable = false, length = 191)
    private String settingKey;

    @Lob // Use @Lob for large text values
    private String settingValue;

    @Column(nullable = false, length = 50)
    private String module;

    /**
     * Convenience constructor for creating a new Setting.
     *
     * @param settingKey   The unique key for the setting.
     * @param settingValue The value associated with the setting.
     * @param module       The module or group to which the setting belongs.
     */
    public Setting(String settingKey, String settingValue, String module) {
        this.settingKey = settingKey;
        this.settingValue = settingValue;
        this.module = module;
    }

    /**
     * Updates the value of the setting.
     *
     * @param newValue The new value to set.
     */
    public void updateValue(String newValue) {
        this.settingValue = newValue;
    }

    /**
     * Checks if the setting has a specific key.
     *
     * @param key The key to check.
     * @return True if the setting key matches, false otherwise.
     */
    public boolean hasKey(String key) {
        return this.settingKey != null && this.settingKey.equals(key);
    }
}